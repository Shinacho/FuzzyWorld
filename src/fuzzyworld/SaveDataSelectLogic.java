/*
 * The MIT License
 *
 * Copyright 2023 Shinacho.
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
package fuzzyworld;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameOption;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.I18N;
import kinugasa.game.input.GamePadButton;
import kinugasa.game.input.InputState;
import kinugasa.game.input.InputType;
import kinugasa.game.input.Keys;
import kinugasa.game.ui.Choice;
import kinugasa.game.ui.FontModel;
import kinugasa.game.ui.MessageWindow;
import kinugasa.game.ui.SimpleMessageWindowModel;
import kinugasa.game.ui.Text;
import kinugasa.graphics.ColorChanger;
import kinugasa.graphics.ColorTransitionModel;
import kinugasa.graphics.FadeCounter;
import kinugasa.object.FadeEffect;
import kinugasa.resource.db.DBConnection;
import kinugasa.resource.db.KResultSet;
import kinugasa.resource.db.KSQLException;
import kinugasa.resource.sound.Sound;
import kinugasa.resource.sound.SoundStorage;

/**
 *
 * @vesion 1.0.0 - May 26, 2023_8:40:10 PM<br>
 * @author Shinacho<br>
 */
public class SaveDataSelectLogic extends GameLogic {

	public SaveDataSelectLogic(GameManager gm) {
		super(Const.LogicName.SAVE_DATA_SELECT, gm);
	}
	private Sound bgm;

	@Override

	public void load() {
		//BGM処理
		SoundStorage.getInstance().stopAll();;
		bgm = SoundStorage.getInstance().get("SD0058").load();
		bgm.stopAndPlay();
		DBConnection.getInstance().close();//0クローズ

		//セーブデータ選択窓の設定
		mw = new MessageWindow(
				24,
				48,
				(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() - 48),
				(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 * 2),
				new SimpleMessageWindowModel("")
		);
		List<Text> options = new ArrayList<>();
		for (int i = 1; i < Const.Save.SAVE_DATA_NUM + 1; i++) {
			String s = I18N.get("セーブデータ") + i;
			try {
				DBConnection.getInstance().open("file:./resource/data/data" + i, "sa", "adm");

				KResultSet rs1 = DBConnection
						.getInstance()
						.execDirect("select q.TITLE, q.DESC from S_CurrentQuest c left join Quest q on c.QuestID=q.QuestID where c.Questid = 'main'");
				if (rs1.isEmpty()) {
					s += " : ";
					s += "    " + I18N.get("新規");
				} else {
					s += " : ";
					s += "    " + rs1.row(0).get(0);
				}
				DBConnection.getInstance().close();
			} catch (KSQLException ex) {
				//処理なし
			}
			options.add(new Text(s));
		}
		stage = 0;

		mw.setText(new Choice(options, "SAVE_SELECT", I18N.get("セーブデータを選択してください")));

		OperationInfo.getInstance().set(OperationInfo.AvalableInput.決定, OperationInfo.AvalableInput.戻る, OperationInfo.AvalableInput.移動上下, OperationInfo.AvalableInput.撮影, OperationInfo.AvalableInput.メニュー);
		Const.LOADING = false;
	}

	private MessageWindow mw;

	@Override
	public void dispose() {
	}
	private int stage;
	private FadeEffect effect;

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		switch (stage) {
			case 0:
				mw.update();
				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					SoundStorage.getInstance().dispose();
					gls.changeTo(Const.LogicName.TITLE);
					break;
				}
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					OperationInfo.getInstance().set(OperationInfo.AvalableInput.決定, OperationInfo.AvalableInput.撮影);
					SoundStorage.getInstance().get("SD1003").load().stopAndPlay();
					stage++;
					break;
				}
				if (is.isPressed(GamePadButton.X, Keys.M, InputType.SINGLE)) {
					//セーブデータマネジメントシステム起動
					SaveDataManageForm f = SaveDataManageForm.getInstance();
					f.setVisible(true);
					while (true) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException ex) {
						}
						if (!f.isVisible()) {
							break;
						}
					}
					for (int i = 1; i < Const.Save.SAVE_DATA_NUM + 1; i++) {
						String s = I18N.get("セーブデータ") + i;
						try {
							DBConnection.getInstance().open("file:./resource/data/data" + i, "sa", "adm");

							KResultSet rs1 = DBConnection
									.getInstance()
									.execDirect("select q.TITLE from S_CurrentQuest c left join Quest q on c.QuestID=q.QuestID where c.Questid = 'main'");
							if (rs1.isEmpty()) {
								s += " : ";
								s += "    " + I18N.get("新規");
							} else {
								s += " : ";
								s += "    " + rs1.row(0).get(0);
							}
							DBConnection.getInstance().close();
						} catch (KSQLException ex) {
							//処理なし
						}
					}
				}
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SD1008").load().stopAndPlay();
					mw.prevSelect();
				} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SD1008").load().stopAndPlay();
					mw.nextSelect();
				}
				break;
			case 1:
				effect = new FadeEffect(gm.getWindow().getWidth(), gm.getWindow().getHeight(),
						new ColorChanger(
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								new FadeCounter(0, +6)));
				stage++;
				break;
			case 2:
				if (effect.isEnded()) {
					effect = new FadeEffect(gm.getWindow().getWidth(), gm.getWindow().getHeight(),
							new ColorChanger(
									ColorTransitionModel.valueOf(0),
									ColorTransitionModel.valueOf(0),
									ColorTransitionModel.valueOf(0),
									new FadeCounter(255, 0)));
					stage++;
				}
				break;
			case 3:
				int selected = mw.getSelect() + 1;
				Const.Save.dataNo = selected;
				DBConnection.getInstance().close();
				DBConnection.getInstance().open("file:./resource/data/data" + selected, "sa", "adm");
				KResultSet rs1 = DBConnection
						.getInstance()
						.execDirect("select q.TITLE, q.DESC from S_CurrentQuest c left join Quest q on c.QuestID=q.QuestID where c.Questid = 'main'");
				if (rs1.isEmpty()) {
					//新規
					//SD1003以外をいったん破棄
					bgm.dispose();
					SoundStorage.getInstance().filter(p -> !p.getName().equals("SD1003")).forEach(p -> p.dispose());
					Const.Chapter.currentI18NKey = "序部";
					Const.Chapter.currentSubTitleI18NKey = "戯れの介入";
					Const.Chapter.nextLogic = Const.LogicName.OP;
//					SoundStorage.getInstance().stopAll();
//					SoundStorage.getInstance().dispose();
					gls.changeTo(Const.LogicName.CHAPTER_TITLE);
					return;
				} else {
					//TODO:ロード
				}
				break;
		}
	}

	@Override
	public void draw(GraphicsContext g
	) {
		Graphics2D g2 = g.create();
		g2.setColor(Color.WHITE);
		g2.setFont(new Font(FontModel.DEFAULT.getFont().getName(), Font.PLAIN, 24));
		g2.drawString("DATA SELECT", 12, 32);
		g2.dispose();
		mw.draw(g);
		if (effect != null) {
			effect.draw(g);
		}
	}

}
