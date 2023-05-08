/*
 * The MIT License
 *
 * Copyright 2022 Shinacho.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kinugasa.game.CMDargs;
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameOption;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.I18N;
import kinugasa.game.field4.D2Idx;
import kinugasa.game.field4.FieldEventSystem;
import kinugasa.game.field4.FieldMap;
import kinugasa.game.field4.FieldMapCameraMode;
import kinugasa.game.field4.FieldMapStorage;
import kinugasa.game.field4.FieldMapTile;
import kinugasa.game.field4.FourDirAnimation;
import kinugasa.game.field4.MapChipSetStorage;
import kinugasa.game.field4.PlayerCharacterSprite;
import kinugasa.game.field4.UserOperationRequire;
import kinugasa.game.field4.VehicleStorage;
import kinugasa.game.input.GamePadButton;
import kinugasa.game.input.InputState;
import kinugasa.game.input.InputType;
import kinugasa.game.input.Keys;
import kinugasa.game.system.ActionDescWindow;
import kinugasa.game.system.AttrDescWindow;
import kinugasa.game.system.BattleConfig;
import kinugasa.game.system.BookWindow;
import kinugasa.game.system.ConditionDescWindow;
import kinugasa.game.system.EqipItemWindow;
import kinugasa.game.system.FieldStatusWindows;
import kinugasa.game.system.GameSystem;
import kinugasa.game.system.GameSystemException;
import kinugasa.game.system.GameSystemXMLLoader;
import kinugasa.game.system.InfoWindow;
import kinugasa.game.system.Item;
import kinugasa.game.system.ItemStorage;
import kinugasa.game.system.ItemWindow;
import kinugasa.game.system.MagicWindow;
import kinugasa.game.system.MaterialPageWindow;
import kinugasa.game.system.Money;
import kinugasa.game.system.MoneySystem;
import kinugasa.game.system.OrderSelectWindow;
import kinugasa.game.system.PCStatusWindow;
import kinugasa.game.system.PlayerCharacter;
import kinugasa.game.system.QuestLine;
import kinugasa.game.system.QuestLineStorage;
import kinugasa.game.system.QuestStageStorage;
import kinugasa.game.system.RaceStorage;
import kinugasa.game.system.Status;
import kinugasa.game.system.StatusDescWindow;
import kinugasa.game.ui.Choice;
import kinugasa.game.ui.Dialog;
import kinugasa.game.ui.MessageWindow;
import kinugasa.game.ui.SimpleMessageWindowModel;
import kinugasa.game.ui.Text;
import kinugasa.game.ui.TextStorageStorage;
import kinugasa.graphics.Animation;
import kinugasa.graphics.ColorChanger;
import kinugasa.graphics.ColorTransitionModel;
import kinugasa.graphics.FadeCounter;
import kinugasa.graphics.SpriteSheet;
import kinugasa.object.FadeEffect;
import kinugasa.object.FourDirection;
import kinugasa.object.KVector;
import kinugasa.resource.KImage;
import kinugasa.resource.sound.SoundStorage;
import kinugasa.util.FrameTimeCounter;

/**
 *
 * @vesion 1.0.0 - 2022/12/16_20:29:12<br>
 * @author Shinacho<br>
 */
public class FieldLogic extends GameLogic {

	public FieldLogic(GameManager gm) {
		super(Const.LogicName.FIELD, gm);
	}

	private boolean loaded = false;

	@Override
	public void load() {
		statusWindowType = 0;
		stage = 0;
		waiting = false;
		battle = false;
		if (loaded) {
			fieldMap.getBgm().play();
			return;
		}
		//PC��ADD
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
				.addQuestStage("resource/data/SQuestM.xml")
				.addEnemyMaster("resource/data/SEM.xml")
				.addEnemySet("resource/data/SESSM.xml")
				.setEnemyProgressBarKey("HP")
				.load();
		float x = Const.Screen.WIDTH / 2 - 16;
		float y = Const.Screen.HEIGHT / 2 - 16;
		Status pc1Status = new Status(Const.Player.pc1Name, RaceStorage.getInstance().get("HUMAN"));
		Animation south = new Animation(new FrameTimeCounter(14), new SpriteSheet("resource/data/image/c2.png").rows(0, 32, 32).images());
		Animation west = new Animation(new FrameTimeCounter(14), new SpriteSheet("resource/data/image/c2.png").rows(96, 32, 32).images());
		Animation east = new Animation(new FrameTimeCounter(14), new SpriteSheet("resource/data/image/c2.png").rows(64, 32, 32).images());
		Animation north = new Animation(new FrameTimeCounter(14), new SpriteSheet("resource/data/image/c2.png").rows(32, 32, 32).images());

		FourDirAnimation anime = new FourDirAnimation(south, west, east, north);
		PlayerCharacterSprite pcs = new PlayerCharacterSprite(x, y, 32, 32, new D2Idx(31, 35), anime, FourDirection.NORTH);
		pcs.setShadow(false);
		PlayerCharacter pc1 = new PlayerCharacter(pc1Status, pcs);
		GameSystem.getInstance().getParty().add(pc1);
		GameSystem.getInstance().updateParty();
		//map
		MapChipSetStorage.getInstance().readFromXML("resource/data/chipSet_outer16.xml");
		TextStorageStorage.getInstance().readFromXML("resource/data/tss.xml");
		FieldMap.getCurrentInstance().dispose();
		fieldMap = FieldMapStorage.getInstance().get("BEACH").build();
		fieldMap.setCurrentIdx(new D2Idx(31, 35));//31,35�̃C�x���g�������N������
		fieldMap.getCamera().updateToCenter();
		fieldMap.setVector(new KVector(0, 0));

		Text.getReplaceMap().put("!PC1", Const.Player.pc1Name);

		QuestLineStorage.getInstance().add(new QuestLine("MAIN"));

		menu = new MessageWindow(24, 24,
				Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4,
				Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() / 2 - 16,
				new SimpleMessageWindowModel().setNextIcon(""));
		List<Text> options = new ArrayList<>();
		options.add(new Text(I18N.translate("STATUS")));
		options.add(new Text(I18N.translate("ORDER")));
		options.add(new Text(I18N.translate("ITEM")));
		options.add(new Text(I18N.translate("MAGIC")));
		options.add(new Text(I18N.translate("BOOK")));
		options.add(new Text(I18N.translate("MATERIAL")));
		options.add(new Text(I18N.translate("INFO")));
		options.add(new Text(I18N.translate("MAP")));

		BattleConfig.setVisibleStatus(List.of("HP", "MP", "SAN"));

		menu.setText(new Choice(options, "MENU", "FUZZY WORLD"));
		menu.setVisible(false);

		statusWindow = new FieldStatusWindows(
				Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 24 + 4,
				GameSystem.getInstance().getPartyStatus());
		statusWindow.setVisible(false);

		StatusDescWindow.setUnvisibleStatusList(List.of("EXP", "CUT_V", "CRT_P", "CRT_V", "M_CUT_V", "M_CRT_P", "M_CRT_V", "ATTR"));
		StatusDescWindow.setVisibleMaxStatusList(List.of("HP", "MP", "SAN"));
		AttrDescWindow.setUnvisibleAttrName(List.of("NONE"));

		if (CMDargs.getInstance().getArgs() != null) {
			if (CMDargs.getInstance().getArgs().length > 0) {
				if (GameSystem.isDebugMode()) {
					if (Arrays.stream(CMDargs.getInstance().getArgs()).anyMatch(p -> "-soba".equals(p))) {
						System.out.println("�ւ��I��ł����肻��" + pc1Status.getRace().getItemBagSize() + "�����܂��I�I");
						System.out.println("�����ӁF���̋@�\�̓e�X�g�p�ł��B");
						for (int i = 0; i < pc1Status.getItemBag().getMax(); i++) {
							pc1Status.getItemBag().add(ItemStorage.getInstance().get("��ł��o�O���肻��1��"));
						}
					}
					if (Arrays.stream(CMDargs.getInstance().getArgs()).anyMatch(p -> "-keyitem".equals(p))) {
						System.out.println("�e�X�g�p�̃L�[�A�C�e����ǉ����邺�I");
						pc1Status.getItemBag().add(ItemStorage.getInstance().get("�L�[�A�C�e��"));
					}
				}
			}
		}

		//�}�l�[�����o�^
		{
			MoneySystem ms = GameSystem.getInstance().getMoneySystem();
			ms.addMoneyType(I18N.translate("DARESU_GOLD"));
			ms.addMoneyType(I18N.translate("BERUMA_SILVER"));
		}
		loaded = true;
	}
	private FieldMap fieldMap;
	private int stage;//changeMap�p�X�e�[�W
	private FadeEffect fadeEffect;
	private boolean waiting = false;
	private MessageWindow mw;
	private boolean first = false;
	private MessageWindow menu;
	private FieldStatusWindows statusWindow;
	private PCStatusWindow statusDescWindow;
	private int statusWindowType = 0;
	private ItemWindow itemWindow;
	private BookWindow bookWindow;
	private OrderSelectWindow orderSelectWindow;
	private MaterialPageWindow materialWindow;
	private MagicWindow magicWindow;
	private InfoWindow infoWindow;
	private boolean battle = false;

	@Override
	public void dispose() {
	}

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		//�e�X�g�p
//		System.out.println(fieldMap == null ? null : fieldMap.getCamera().cameraCantMoveDesc + " / " + stage);
		fieldMap.update();
		FieldEventSystem.getInstance().update();
		switch (stage) {
			case 0:
				//update
				if (mw != null) {
					mw.update();
				}
				//event
				if (waiting) {
					fieldMap.move();
					if (!FieldEventSystem.getInstance().isExecuting()) {
						waiting = false;
						return;
					} else {
						return;
					}
				}
				//�`�[�g�R���\�[��
				if (is.isPressed(Keys.AT, InputType.SINGLE)) {
					ConsolCmd cmd = new ConsolCmd();
					cmd.setVisible(true);
					try {
						while (true) {
							Thread.sleep(300);
							if (!cmd.isVisible()) {
								break;
							}
						}
					} catch (InterruptedException ex) {
					}
				}
				//mw����
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE) && (menu == null || !menu.isVisible())) {
					//������s���Ɏg�����f�o�C�X������
					if (!first) {
						if (is.isPressed(Keys.ENTER, InputType.SINGLE)) {
							FieldMap.setEnterOperation("(ENTER)");
						} else {
							FieldMap.setEnterOperation("(A)");
						}
						first = true;
					}
					if (mw != null && mw.isVisible()) {
						if (!mw.isAllVisible()) {
							mw.allText();
						} else if (mw.isChoice()) {
							if (mw.getChoiceOption().hasNext()) {
								mw.choicesNext();
							} else {
								fieldMap.closeMessagWindow();
							}
							if (FieldEventSystem.getInstance().hasItem()) {
								//�u���ׂ�v�ɂ��A�C�e���擾����
								Item i = FieldEventSystem.getInstance().getItem();
								if (i == null) {
									throw new GameSystemException("item is null");
								}
								if (mw.getSelect() >= GameSystem.getInstance().getParty().size()) {
									//���߂�
									mw.setVisible(false);
									mw = null;
									FieldEventSystem.getInstance().clearTmpFlags();
									FieldEventSystem.getInstance().endEvent();
									FieldEventSystem.getInstance().reset();
								} else {
									//���������̔���
									if (GameSystem.getInstance().getParty().get(mw.getSelect()).getStatus().getItemBag().canAdd()) {
										GameSystem.getInstance().getParty().get(mw.getSelect()).getStatus().getItemBag().add(i);
										FieldEventSystem.getInstance().commitFlags();
										FieldEventSystem.getInstance().endEvent();
										FieldEventSystem.getInstance().reset();
										mw.setVisible(false);
										mw = null;
									} else {
										//���ĂȂ�
										mw.setText(GameSystem.getInstance().getParty().get(mw.getSelect()).getStatus().getName() + I18N.translate("IS") + I18N.translate("CANT_HAVE"));
										mw.allText();
										mw.setVisible(true);
										FieldEventSystem.getInstance().clearTmpFlags();
										FieldEventSystem.getInstance().endEvent();
										FieldEventSystem.getInstance().reset();
									}
								}
							}
						} else if (mw.hasNext()) {
							mw.next();
						} else {
							mw.setVisible(false);
							fieldMap.closeMessagWindow();
						}
					} else if (!FieldEventSystem.getInstance().hasEvent() && fieldMap.canTalk()) {
						mw = fieldMap.talk();
					}
					FieldMapTile t = fieldMap.getCurrentTile();
					if (t.hasInNode()) {
						//Node�ɂ��ChangeMap����
						fadeEffect = new FadeEffect(gm.getWindow().getInternalBounds().width, gm.getWindow().getInternalBounds().height,
								new ColorChanger(
										ColorTransitionModel.valueOf(0),
										ColorTransitionModel.valueOf(0),
										ColorTransitionModel.valueOf(0),
										new FadeCounter(0, +6)));
						stage++;
						return;
					}

					if (mw != null && mw.isVisible()) {
						return;
					}
					//���ׂ�R�}���h
					if (FieldEventSystem.getInstance().isManual() && menu != null && !menu.isVisible()) {
						while (FieldEventSystem.getInstance().hasEvent()) {
							UserOperationRequire r = FieldEventSystem.getInstance().exec();
							switch (r) {
								case CONTINUE:
									break;
								case WAIT_FOR_EVENT:
									waiting = true;
									return;
								case CHANGE_MAP:
									fieldMap = fieldMap.changeMap(FieldEventSystem.getInstance().getNode());
									//�ŏ��̃`�F���W�}�b�v�C�x���g�ŃV���h�[��ݒ肷��
									FieldMap.getPlayerCharacter().get(0).setShadow(true);
									return;
								case GAME_OVER:
									gls.changeTo("GAME_OVER");
									return;
								case SHOW_MESSAGE:
									mw = FieldEventSystem.getInstance().showMessageWindow();
									return;
								case CLOSE_MESSAGE:
									if (mw != null) {
										mw.setVisible(false);
									}
									mw = null;
									return;
								case TO_BATTLE:
									GameSystem.getInstance().battleStart(FieldEventSystem.getInstance().getEncountInfo());
									//�t�F�[�h�A�E�g�̓C�x���g�ŁI�I
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
				//FM�����C�x���g����
				if (FieldEventSystem.getInstance().hasEvent()) {
					//�C�x���g�Z�b�g�Ƀ}�j���A���������Ă���ꍇ�͑������s���Ȃ�
					if (!FieldEventSystem.getInstance().isManual()) {
						UserOperationRequire r = FieldEventSystem.getInstance().exec();
						switch (r) {
							case CONTINUE:
								break;
							case WAIT_FOR_EVENT:
								waiting = true;
								return;
							case CHANGE_MAP:
								fieldMap = fieldMap.changeMap(FieldEventSystem.getInstance().getNode());
								FieldMap.getPlayerCharacter().get(0).setShadow(true);
								return;
							case GAME_OVER:
								gls.changeTo("GAME_OVER");
								return;
							case SHOW_MESSAGE:
								mw = FieldEventSystem.getInstance().showMessageWindow();
								return;
							case CLOSE_MESSAGE:
								if (mw != null) {
									mw.setVisible(false);
								}
								mw = null;
								return;
							case TO_BATTLE:
								//�t�F�[�h�A�E�g�̓C�x���g�ŁI�I�I�I
								GameSystem.getInstance().battleStart(FieldEventSystem.getInstance().getEncountInfo());
								gls.changeTo("BATTLE");
								return;
						}
					}
				}
				//���[�U�I�y���[�V�����ۊm�F
				if (!FieldEventSystem.getInstance().isUserOperation()) {
					return;
				}
				//���j���[����
				if (is.isPressed(GamePadButton.X, Keys.M, InputType.SINGLE)) {
					//���j���[�\��
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					menu.switchVisible();
					statusDescWindow = null;
					itemWindow = null;
					magicWindow = null;
					bookWindow = null;
					materialWindow = null;
					orderSelectWindow = null;
					infoWindow = null;
					if (menu.isVisible()) {
						statusWindow = new FieldStatusWindows(
								Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 24 + 4,
								GameSystem.getInstance().getPartyStatus());
					} else {
						statusWindow.setVisible(false);
					}
					GameSystem.getInstance().getPartyStatus().forEach(p -> p.updateAction());
				}
				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					statusWindow = new FieldStatusWindows(
							Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 24 + 4,
							GameSystem.getInstance().getPartyStatus());
					GameSystem.getInstance().getPartyStatus().forEach(p -> p.updateAction());
					if (statusDescWindow != null && statusDescWindow.isVisible()) {
						SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
						statusDescWindow = null;
					} else if (itemWindow != null && itemWindow.isVisible()) {
						SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
						if (itemWindow.close()) {
							itemWindow = null;
						}
					} else if (bookWindow != null && bookWindow.isVisible()) {
						SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
						if (bookWindow.close()) {
							bookWindow = null;
						}
					} else if (magicWindow != null && magicWindow.isVisible()) {
						SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
						if (magicWindow.close()) {
							magicWindow = null;
						}
					} else if (materialWindow != null && materialWindow.isVisible()) {
						SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
						materialWindow = null;
					} else if (orderSelectWindow != null && orderSelectWindow.isVisible()) {
						SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
						orderSelectWindow = null;
					} else if (infoWindow != null && infoWindow.isVisible()) {
						SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
						infoWindow = null;
					} else {
						SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
						menu.setVisible(false);
						statusWindow.setVisible(false);
					}
				}
				if (menu.isVisible()) {
					//�X�e�[�^�X�ؑ�
					if (statusDescWindow != null && statusDescWindow.isVisible()) {
						statusDescWindow.update();
						//�X�e�[�^�X�ڍה�\���̏ꍇ�̓J�[�\���ړ��\
						if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							statusDescWindow.nextPc();
						}
						if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							statusDescWindow.prevPc();
						}
						if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							statusDescWindow.prev();
						}
						if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							statusDescWindow.next();
						}
					} else if (orderSelectWindow != null && orderSelectWindow.isVisible()) {
						orderSelectWindow.update();
						//����E�C���h�E�̏���
						if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							orderSelectWindow.prevPC();
						}
						if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							orderSelectWindow.nextPC();
						}
						if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
							orderSelectWindow.changeOrder();
						}
					} else if (itemWindow != null && itemWindow.isVisible()) {
						//�A�C�e���E�C���h�E�̏���
						itemWindow.update();
						switch (itemWindow.currentMode()) {
							case ITEM_AND_USER_SELECT:
								//�A�C�e���I�����[�h
								if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									itemWindow.nextPC();
								}
								if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									itemWindow.prevPC();
								}
								if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									itemWindow.prevSelect();
								}
								if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									itemWindow.nextSelect();
								}
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									itemWindow.select();//���̑����
								}
								break;
							case CHOICE_USE:
							case DROP_CONFIRM:
							case DISASSE_CONFIRM:
								//�p�r�I�����[�h
								if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									itemWindow.prevSelect();
								}
								if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									itemWindow.nextSelect();
								}
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									itemWindow.select();//���̑����
								}
								break;
							case TARGET_SELECT:
								if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									itemWindow.prevPC();
								}
								if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									itemWindow.nextPC();
								}
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									itemWindow.select();//���̑����
								}
								break;
							case WAIT_MSG_CLOSE_TO_CU:
							case WAIT_MSG_CLOSE_TO_IUS:
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									itemWindow.select();//���̑����
								}
								break;
						}
					} else if (magicWindow != null && magicWindow.isVisible()) {
						//���@�E�C���h�E�̏���
						magicWindow.update();
						switch (magicWindow.getCurrentMode()) {
							case MAGIC_AND_USER_SELECT:
								if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									magicWindow.nextPC();
								}
								if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									magicWindow.prevPC();
								}
								if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									magicWindow.prevSelect();
								}
								if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									magicWindow.nextSelect();
								}
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									magicWindow.select();//���̑����
								}
								break;
							case CHOICE_USE:
								if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									magicWindow.prevSelect();
								}
								if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									magicWindow.nextSelect();
								}
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									magicWindow.select();//���̑����
								}
								break;
							case TARGET_SELECT:
								if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									magicWindow.prevPC();
								}
								if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									magicWindow.nextPC();
								}
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									magicWindow.select();//���̑����
								}
								break;
							case WAIT_MSG_CLOSE_TO_CU:
							case WAIT_MSG_CLOSE_TO_MUS:
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									magicWindow.select();//���̑����
								}
								break;
						}

					} else if (bookWindow != null && bookWindow.isVisible()) {
						//�u�b�N�E�C���h�E�̏���
						bookWindow.update();
						switch (bookWindow.currentMode()) {
							case BOOK_AND_USER_SELECT:
								//�I�����[�h
								if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									bookWindow.nextPC();
								}
								if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									bookWindow.prevPC();
								}
								if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									bookWindow.prevSelect();
								}
								if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									bookWindow.nextSelect();
								}
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									bookWindow.select();//���̑����
								}
								break;
							case CHOICE_USE:
							case DROP_CONFIRM:
							case DISASSEMBLY_CONFIRM:
								//�p�r�I�����[�h
								if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									bookWindow.prevSelect();
								}
								if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									bookWindow.nextSelect();
								}
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									bookWindow.select();//���̑����
								}
								break;
							case TARGET_SELECT:
								if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									bookWindow.prevPC();
								}
								if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
									bookWindow.nextPC();
								}
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									bookWindow.select();//���̑����
								}
								break;
							case WAIT_MSG_CLOSE_TO_CU:
							case WAIT_MSG_CLOSE_TO_IUS:
								if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
									SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
									bookWindow.select();//���̑����
								}
								break;
						}
					} else if (materialWindow != null && materialWindow.isVisible()) {
						//�f�ރE�C���h�E�̏���
						materialWindow.update();
						if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							materialWindow.switchMode();
						}
						if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							materialWindow.switchMode();
						}
						if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							materialWindow.prev();
						}
						if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							materialWindow.next();
						}
					} else if (infoWindow != null && infoWindow.isVisible()) {
						infoWindow.update();
						if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							infoWindow.switchMode();
						}
						if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							infoWindow.switchMode();
						}
						if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							infoWindow.prevSelect();
						}
						if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							infoWindow.nextSelect();
						}
					} else {
						//�ǂ̃E�C���h�E����\���̏ꍇ�̓��j���[�̃J�[�\���ړ��\
						if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							menu.nextSelect();
						}
						if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load().stopAndPlay();
							menu.prevSelect();
						}
					}
					//A�{�^���E�E�E���j���[����
					if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
						if (statusDescWindow != null && statusDescWindow.isVisible()) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
							int pcIdx = statusDescWindow.getPcIdx();
							switch (statusWindowType) {
								case 0:
									statusDescWindow = new AttrDescWindow(
											(int) (24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4),
											24 + 8,
											(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32),
											GameSystem.getInstance().getPartyStatus()
									);
									statusDescWindow.setPcIdx(pcIdx);
									statusWindowType++;
									break;
								case 1:
									statusDescWindow = new EqipItemWindow(
											(int) (24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4),
											24 + 8,
											(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32),
											GameSystem.getInstance().getPartyStatus()
									);
									statusDescWindow.setPcIdx(pcIdx);
									statusWindowType++;
									break;
								case 2:
									statusDescWindow = new ActionDescWindow(
											(int) (24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4),
											24 + 8,
											(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32),
											GameSystem.getInstance().getPartyStatus()
									);
									statusDescWindow.setPcIdx(pcIdx);
									statusWindowType++;
									break;
								case 3:
									statusDescWindow = new ConditionDescWindow(
											(int) (24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4),
											24 + 8,
											(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32),
											GameSystem.getInstance().getPartyStatus()
									);
									statusDescWindow.setPcIdx(pcIdx);
									statusWindowType++;
									break;
								case 4:
									statusDescWindow = new StatusDescWindow(
											(int) (24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4),
											24 + 8,
											(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32),
											GameSystem.getInstance().getPartyStatus()
									);
									statusDescWindow.setPcIdx(pcIdx);
									statusWindowType = 0;
									break;
							}
						} else if ((itemWindow == null || !itemWindow.isVisible())
								&& (bookWindow == null || !bookWindow.isVisible())
								&& (orderSelectWindow == null || !orderSelectWindow.isVisible())
								&& (materialWindow == null || !materialWindow.isVisible())
								&& (magicWindow == null || !magicWindow.isVisible())
								&& (infoWindow == null || !infoWindow.isVisible())) {
							SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
							switch (menu.getSelect()) {
								case Const.MenuIdx.STATUS:
									statusDescWindow = new StatusDescWindow(
											(int) (24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4),
											24 + 8,
											(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32),
											GameSystem.getInstance().getPartyStatus()
									);
									statusWindowType = 0;
									break;
								case Const.MenuIdx.ORDER:
									orderSelectWindow = new OrderSelectWindow(
											24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4,
											24 + 8,
											(float) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32
									);
									break;
								case Const.MenuIdx.ITEM:
									itemWindow = new ItemWindow(
											24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4,
											24 + 8,
											(float) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32);
									break;
								case Const.MenuIdx.MAGIC:
									magicWindow = new MagicWindow(
											(int) (24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4),
											24 + 8,
											(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32));
									break;
								case Const.MenuIdx.BOOK:
									bookWindow = new BookWindow(
											24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4,
											24 + 8,
											(float) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32);
									break;
								case Const.MenuIdx.MATERIAL:
									materialWindow = new MaterialPageWindow(
											(int) (24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4),
											24 + 8,
											(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32));
									break;
								case Const.MenuIdx.INFO:
									infoWindow = new InfoWindow(
											(int) (24 + Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 4 + 8 + 4),
											24 + 8,
											(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() / 1.5),
											(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 - 32));
									break;
								case Const.MenuIdx.MAP:
									KImage mapImage = fieldMap.createMiniMap(512, 512, true);
									Dialog.image(I18N.translate(fieldMap.getName()) + I18N.translate("S") + I18N.translate("MAP"), mapImage.get());
									break;
							}
						}
					}

				}
				if (menu != null && menu.isVisible()) {
					return;
				}

				//-------------------FM_MOVE
				KVector v = new KVector(0, 0);
				if (is.getGamePadState() != null) {
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
				if (v.getSpeed() == 0f) {
					break;
				}

				fieldMap.setVector(v);

				fieldMap.move();
				//PC�̌�������
				PlayerCharacterSprite c = FieldMap.getPlayerCharacter().get(0);

				if (v.getSpeed() != 0 && fieldMap.getCamera().getMode() == FieldMapCameraMode.FOLLOW_TO_CENTER) {
					c.updateAnimation();
					c.to(v.round());
				}

				//--------------�G���J�E���g����
				//���ʉ��Q�퓬�J�n.wav
				if (fieldMap.isEncount()) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�퓬�J�n.wav").load().stopAndPlay();
					battle = true;
					fadeEffect = new FadeEffect(gm.getWindow().getInternalBounds().width, gm.getWindow().getInternalBounds().height,
							new ColorChanger(
									ColorTransitionModel.valueOf(0),
									ColorTransitionModel.valueOf(0),
									ColorTransitionModel.valueOf(0),
									new FadeCounter(0, +6)
							));
					stage++;
				}

				break;
			case 1:
				if (fadeEffect.isEnded()) {
					fadeEffect = new FadeEffect(gm.getWindow().getInternalBounds().width, gm.getWindow().getInternalBounds().height,
							new ColorChanger(
									ColorTransitionModel.valueOf(0),
									ColorTransitionModel.valueOf(0),
									ColorTransitionModel.valueOf(0),
									ColorTransitionModel.valueOf(255)
							));
					if (battle) {
						GameSystem.getInstance().battleStart(fieldMap.createEncountInfo());
						gls.changeTo(Const.LogicName.BATTLE);
					} else {
						fieldMap = fieldMap.changeMap();
					}
					stage++;
				}
				break;
			case 2:
				fadeEffect = new FadeEffect(gm.getWindow().getInternalBounds().width, gm.getWindow().getInternalBounds().height,
						new ColorChanger(
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								new FadeCounter(255, -6)
						));
				stage++;
				break;
			case 3:
				if (fadeEffect.isEnded()) {
					stage = 0;
				}
				break;
			default:
				throw new AssertionError("undefined FL stage : " + stage);
		}

	}

	@Override
	public void draw(GraphicsContext g) {
		fieldMap.draw(g);
		if (mw != null) {
			mw.draw(g);
		}
		if (menu != null) {
			menu.draw(g);
		}
		statusWindow.draw(g);
		if (statusDescWindow != null) {
			statusDescWindow.draw(g);
		}
		if (itemWindow != null) {
			itemWindow.draw(g);
		}
		if (bookWindow != null) {
			bookWindow.draw(g);
		}
		if (orderSelectWindow != null) {
			orderSelectWindow.draw(g);
		}
		if (materialWindow != null) {
			materialWindow.draw(g);
		}
		if (magicWindow != null) {
			magicWindow.draw(g);
		}
		if (infoWindow != null) {
			infoWindow.draw(g);
		}
		FieldEventSystem.getInstance().draw(g);
		if (fadeEffect != null) {
			fadeEffect.draw(g);
		}
	}

}
