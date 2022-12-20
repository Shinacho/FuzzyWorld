/*
 * The MIT License
 *
 * Copyright 2022 Dra.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fuzzyworld1;

import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.I18N;
import kinugasa.game.field4.D2Idx;
import kinugasa.game.field4.FieldEventSystem;
import kinugasa.game.field4.FieldEventType;
import kinugasa.game.field4.FieldMap;
import kinugasa.game.field4.FieldMapCameraMode;
import kinugasa.game.field4.FieldMapStorage;
import kinugasa.game.field4.FieldMapTile;
import kinugasa.game.field4.FourDirAnimation;
import kinugasa.game.field4.MapChipSetStorage;
import kinugasa.game.field4.Node;
import kinugasa.game.field4.PlayerCharacterSprite;
import kinugasa.game.field4.UserOperationRequire;
import kinugasa.game.field4.VehicleStorage;
import kinugasa.game.input.GamePadButton;
import kinugasa.game.input.InputState;
import kinugasa.game.input.InputType;
import kinugasa.game.input.Keys;
import kinugasa.game.system.GameSystem;
import kinugasa.game.system.GameSystemException;
import kinugasa.game.system.GameSystemXMLLoader;
import kinugasa.game.system.Item;
import kinugasa.game.system.PlayerCharacter;
import kinugasa.game.system.QuestLine;
import kinugasa.game.system.QuestLineStorage;
import kinugasa.game.system.RaceStorage;
import kinugasa.game.system.Status;
import kinugasa.game.ui.MessageWindow;
import kinugasa.game.ui.Text;
import kinugasa.game.ui.TextStorageStorage;
import kinugasa.graphics.Animation;
import kinugasa.graphics.SpriteSheet;
import kinugasa.object.FourDirection;
import kinugasa.object.KVector;
import kinugasa.util.FrameTimeCounter;

/**
 *
 * @vesion 1.0.0 - 2022/12/16_20:29:12<br>
 * @author Dra211<br>
 */
public class FieldLogic extends GameLogic {

	public FieldLogic(GameManager gm) {
		super(Const.LogicName.FIELD, gm);
	}

	@Override
	public void load() {
		//PCのADD
		new GameSystemXMLLoader()
				.addAttrKeyStorage("resource/data/SAttrM.xml")
				.addBattleActionStorage("resource/data/SActionM.xml")
				.addRaceStorage("resource/data/SRaceM.xml")
				.addBookList("resource/data/SBookM.xml")
				.addItemEqipmentSlotStorage("resource/data/SItemSlotM.xml")
				.addStatusKeyStorage("resource/data/SStatusM.xml")
				.addWeaponMagicTypeStorage("resource/data/SWMTM.xml")
				.addBattleField("resource/data/SBattleFieldM.xml")
				.addConditionValueStorage("resource/data/SConditionM.xml")
				.load();
		float x = Const.Screen.WIDTH / 2 - 16;
		float y = Const.Screen.HEIGHT / 2 - 16;
		Status pc1Status = new Status(Const.Player.pc1Name, RaceStorage.getInstance().get("HUMAN"));
		Animation south = new Animation(new FrameTimeCounter(30), new SpriteSheet("resource/data/image/c2.png").rows(0, 32, 32).images());
		Animation west = new Animation(new FrameTimeCounter(30), new SpriteSheet("resource/data/image/c2.png").rows(96, 32, 32).images());
		Animation east = new Animation(new FrameTimeCounter(30), new SpriteSheet("resource/data/image/c2.png").rows(64, 32, 32).images());
		Animation north = new Animation(new FrameTimeCounter(30), new SpriteSheet("resource/data/image/c2.png").rows(32, 32, 32).images());

		FourDirAnimation anime = new FourDirAnimation(south, west, east, north);
		PlayerCharacterSprite pcs = new PlayerCharacterSprite(x, y, 32, 32, new D2Idx(31, 35), anime, FourDirection.NORTH);
		pcs.setShadow(false);
		PlayerCharacter pc1 = new PlayerCharacter(pc1Status, pcs);
		pc1.getSprite().getAnimation().setStop(true);
		GameSystem.getInstance().getParty().add(pc1);
		GameSystem.getInstance().updateParty();
		//map
		MapChipSetStorage.getInstance().readFromXML("resource/data/chipSet_outer16.xml");
		TextStorageStorage.getInstance().readFromXML("resource/data/tss.xml");
		FieldMap.getCurrentInstance().dispose();
		map = FieldMapStorage.getInstance().get("BEACH").build();
		map.setCurrentIdx(new D2Idx(31, 35));//31,35のイベントが自動起動する
		map.getCamera().updateToCenter();
		map.setVector(new KVector(0, 0));

		Text.getReplaceMap().put("!PC1", Const.Player.pc1Name);

		stage = 0;
		waiting = false;

		QuestLineStorage.getInstance().add(new QuestLine("MAIN"));

		OperationSprite.getInstance().setText("(ENTER)");
	}
	private FieldMap map;
	private int stage;
	private boolean waiting = false;
	private MessageWindow mw;
	private boolean first = false;
	private boolean menu = false;

	@Override
	public void dispose() {
	}

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		//update
		map.update();
		FieldEventSystem.getInstance().update();
		if (mw != null) {
			mw.update();
		}
		//event
		if (waiting) {
			map.move();
			if (!FieldEventSystem.getInstance().isExecuting()) {
				waiting = false;
				return;
			} else {
				return;
			}
		}
		//mw処理
		if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
			//初回実行時に使ったデバイスを検査
			if (!first) {
				if (is.isPressed(Keys.ENTER, InputType.SINGLE)) {
					FieldMap.setEnterOperation("(ENTER)");
				} else {
					FieldMap.setEnterOperation("(A)");
				}
				OperationSprite.getInstance().setText(FieldMap.getEnterOperation());
				first = true;
			}
			if (mw != null && mw.isVisible()) {
				if (!mw.isAllVisible()) {
					mw.allText();
				} else if (mw.isChoice()) {
					if (mw.getChoiceOption().hasNext()) {
						mw.choicesNext();
					} else {
						map.closeMessagWindow();
					}
					if (FieldEventSystem.getInstance().hasItem()) {
						//アイテムウインドウ処理
						Item i = FieldEventSystem.getInstance().getItem();
						if (i == null) {
							throw new GameSystemException("item is null");
						}
						if (mw.getSelect() >= GameSystem.getInstance().getParty().size()) {
							//諦める
							mw.setVisible(false);
							mw = null;
							FieldEventSystem.getInstance().clearTmpFlags();
							FieldEventSystem.getInstance().endEvent();
							FieldEventSystem.getInstance().reset();
						} else {
							//持ち物数の判定
							if (GameSystem.getInstance().getParty().get(mw.getSelect()).getStatus().getItemBag().canAdd()) {
								GameSystem.getInstance().getParty().get(mw.getSelect()).getStatus().getItemBag().add(i);
								FieldEventSystem.getInstance().commitFlags();
								FieldEventSystem.getInstance().endEvent();
								FieldEventSystem.getInstance().reset();
								mw.setVisible(false);
								mw = null;
							} else {
								//持てない
								mw.setText(I18N.translate("CANT_HAVE"));
								FieldEventSystem.getInstance().clearTmpFlags();
								FieldEventSystem.getInstance().endEvent();
								FieldEventSystem.getInstance().reset();
							}
						}
					}
				} else if (mw.hasNext()) {
					mw.next();
				} else {
					map.closeMessagWindow();
				}
			} else if (map.canTalk()) {
				mw = map.talk();
			}
			FieldMapTile t = map.getCurrentTile();
			if (t.hasInNode()) {
				//NodeによるChangeMap処理
				map = map.changeMap();
				return;
			}

			if (mw != null && mw.isVisible()) {
				return;
			}
			//調べるコマンド
			if (FieldEventSystem.getInstance().isManual()) {
				while (FieldEventSystem.getInstance().hasEvent()) {
					UserOperationRequire r = FieldEventSystem.getInstance().exec();
					switch (r) {
						case CONTINUE:
							break;
						case WAIT_FOR_EVENT:
							waiting = true;
							return;
						case CHANGE_MAP:
							map = map.changeMap(FieldEventSystem.getInstance().getNode());
							FieldMap.getPlayerCharacter().get(0).setShadow(true);
							return;
						case GAME_OVER:
							gls.changeTo("GAME_OVER");
							return;
						case SHOW_MESSAGE:
							mw = FieldEventSystem.getInstance().showMessageWindow();
							return;
						case CLOSE_MESSAGE:
							mw.setVisible(false);
							mw = null;
							return;
						case TO_BATTLE:
							GameSystem.getInstance().battleStart(FieldEventSystem.getInstance().getEncountInfo());
							gls.changeTo("BATTLE");
							return;
						case GET_ITEAM:
							mw = FieldEventSystem.getInstance().showItemGetMessageWindow();
							return;
					}
				}
			}
		}
		if (mw != null && mw.isChoice()) {
			if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
				mw.nextSelect();
			}
			if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
				mw.prevSelect();
			}
		}
		if (mw != null && mw.isVisible()) {
			return;
		}
		//FMイベント処理

		if (FieldEventSystem.getInstance().hasEvent()) {
			//イベントセットにマニュアルが入っている場合は即時実行しない
			if (!FieldEventSystem.getInstance().isManual()) {
				UserOperationRequire r = FieldEventSystem.getInstance().exec();
				switch (r) {
					case CONTINUE:
						break;
					case WAIT_FOR_EVENT:
						waiting = true;
						return;
					case CHANGE_MAP:
						map = map.changeMap(FieldEventSystem.getInstance().getNode());
						FieldMap.getPlayerCharacter().get(0).setShadow(true);
						return;
					case GAME_OVER:
						gls.changeTo("GAME_OVER");
						return;
					case SHOW_MESSAGE:
						mw = FieldEventSystem.getInstance().showMessageWindow();
						return;
					case CLOSE_MESSAGE:
						mw.setVisible(false);
						mw = null;
						return;
					case TO_BATTLE:
						GameSystem.getInstance().battleStart(FieldEventSystem.getInstance().getEncountInfo());
						gls.changeTo("BATTLE");
						return;
				}
			}
		}
		//ユーザオペレーション可否確認

		if (!FieldEventSystem.getInstance().isUserOperation()) {
			return;
		}
		OperationSprite.getInstance().setText("←→↑↓:" + I18N.translate("MOVE"));

		//メニュー操作
		if (menu) {
			return;
		}
		//ユーザオペレーション処理
		//MENU

		if (is.isPressed(GamePadButton.X, Keys.M, InputType.SINGLE)) {
			//メニュー表示
			menu = !menu;
		}

		if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
			menu = false;
		}

		//FM_MOVE
		KVector v = new KVector(0, 0);
		if (Const.Input.gamepad && is.getGamePadState() != null) {
			v = new KVector(is.getGamePadState().sticks.LEFT.getLocation(VehicleStorage.getInstance().getCurrentVehicle().getSpeed()));
		}

		if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.CONTINUE)) {
			v.setAngle(FourDirection.WEST);
			v.setSpeed(VehicleStorage.getInstance().getCurrentVehicle().getSpeed());
		} else if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.CONTINUE)) {
			v.setAngle(FourDirection.EAST);
			v.setSpeed(VehicleStorage.getInstance().getCurrentVehicle().getSpeed());
		}

		if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.CONTINUE)) {
			v.setAngle(FourDirection.NORTH);
			v.setSpeed(VehicleStorage.getInstance().getCurrentVehicle().getSpeed());
		} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.CONTINUE)) {
			v.setAngle(FourDirection.SOUTH);
			v.setSpeed(VehicleStorage.getInstance().getCurrentVehicle().getSpeed());
		}

		map.setVector(v);

		map.move();
		//PCの向き調整
		PlayerCharacterSprite c = FieldMap.getPlayerCharacter().get(0);

		if (v.getSpeed() != 0 && map.getCamera().getMode() == FieldMapCameraMode.FOLLOW_TO_CENTER) {
			c.to(v.round());
		}

	}

	@Override
	public void draw(GraphicsContext g) {
		map.draw(g);
		if (mw != null) {
			mw.draw(g);
		}
		OperationSprite.getInstance().draw(g);
		FieldEventSystem.getInstance().draw(g);
	}

}
