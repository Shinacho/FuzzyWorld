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
package fuzzyworld;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameOption;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.input.GamePadButton;
import kinugasa.game.input.InputState;
import kinugasa.game.input.InputType;
import kinugasa.game.ui.Action;
import kinugasa.game.ui.ActionTextSprite;
import kinugasa.game.ui.ActionTextSpriteGroup;
import kinugasa.game.ui.FontModel;
import kinugasa.game.ui.SimpleTextLabelModel;
import kinugasa.resource.sound.SoundStorage;
import kinugasa.game.I18N;
import kinugasa.game.input.Keys;
import kinugasa.game.ui.Dialog;
import kinugasa.graphics.ColorChanger;
import kinugasa.graphics.ColorTransitionModel;
import kinugasa.graphics.FadeCounter;
import kinugasa.object.FadeEffect;
import kinugasa.util.FrameTimeCounter;
import kinugasa.util.StopWatch;
import kinugasa.util.Versions;

/**
 *
 * @vesion 1.0.0 - 2022/11/12_21:36:53<br>
 * @author Shinacho<br>
 */
public class TitleLogic extends GameLogic {

	public TitleLogic(GameManager gm) {
		super(Const.LogicName.TITLE, gm);
	}
	private ActionTextSpriteGroup atsg;
	private int selected;

	@Override
	public void load() {
		stage = -2;
		atsg = new ActionTextSpriteGroup(560, 340,
				new ActionTextSprite(I18N.get("スタート"), new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(14)), 0, 0, 18, 0, new Action() {
					@Override
					public void exec() {
						System.gc();
						gls.changeTo(Const.LogicName.SAVE_DATA_SELECT);
					}
				}),
				new ActionTextSprite(I18N.get("ミュージックルーム"), new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(14)), 0, 0, 18, 0, new Action() {
					@Override
					public void exec() {
						SoundStorage.getInstance().stopAll();
						gls.changeTo(Const.LogicName.MUSIC_ROOM);
					}
				}),
				new ActionTextSprite(I18N.get("コントローラーテスト"), new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(14)), 0, 0, 18, 0, new Action() {
					@Override
					public void exec() {
						gls.changeTo(Const.LogicName.GAMEPAD_TEST);
					}
				}),
				new ActionTextSprite(I18N.get("サウンド設定"), new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(14)), 0, 0, 18, 0, new Action() {
					@Override
					public void exec() {
						SwingUtilities.invokeLater(() -> SoundVolumeForm.getInstance().setVisible(true));
					}
				}),
				new ActionTextSprite(I18N.get("おわる"), new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(14)), 0, 0, 18, 0, new Action() {
					@Override
					public void exec() {
						SoundStorage.getInstance().stopAll();
						gm.gameExit();
					}
				})
		);
		SoundStorage.getInstance().filter(p -> !p.getName().equals("SD1002")).forEach(p -> p.dispose());
		if (kinugasa.util.Random.percent(0.5f)) {
			SoundStorage.getInstance().get("SD0026").load().play();
		} else {
			SoundStorage.getInstance().get("SD0027").load().play();
		}
		atsg.setSelectedIdx(selected);

		OperationInfo.getInstance().set(OperationInfo.AvalableInput.決定, OperationInfo.AvalableInput.移動上下, OperationInfo.AvalableInput.撮影);

	}

	@Override
	public void dispose() {
	}
	private int stage;

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		switch (stage) {
			case -2:
				effect = new FadeEffect(gm.getWindow().getWidth(), gm.getWindow().getHeight(),
						new ColorChanger(
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								new FadeCounter(255, -8)
						));
				stage++;
				break;
			case -1:
				if (effect.isEnded()) {
					effect = null;
					stage++;
				}
				break;
			case 0:
				if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
					if (is.isPressed(GamePadButton.POV_DOWN, InputType.SINGLE)) {
						OperationInfo.getInstance().set(OperationInfo.Mode.GAME_PAD);
					} else {
						OperationInfo.getInstance().set(OperationInfo.Mode.KEYBOAR);
					}
					atsg.next();
					SoundStorage.getInstance().get("SD1008").load().stopAndPlay();
					selected = atsg.getSelectedIdx();
				}
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
					if (is.isPressed(GamePadButton.POV_UP, InputType.SINGLE)) {
						OperationInfo.getInstance().set(OperationInfo.Mode.GAME_PAD);
					} else {
						OperationInfo.getInstance().set(OperationInfo.Mode.KEYBOAR);
					}
					atsg.prev();
					SoundStorage.getInstance().get("SD1008").load().stopAndPlay();
					selected = atsg.getSelectedIdx();
				}
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					if (is.isPressed(GamePadButton.A, InputType.SINGLE)) {
						OperationInfo.getInstance().set(OperationInfo.Mode.GAME_PAD);
					} else {
						OperationInfo.getInstance().set(OperationInfo.Mode.KEYBOAR);
					}
					selected = atsg.getSelectedIdx();
					if (selected == 3) {
						atsg.exec();
						is.keyReleaseEvent(gm, Keys.ENTER);
						return;
					}
					if (selected == 2 && !GameOption.getInstance().isUseGamePad()) {
						Toolkit.getDefaultToolkit().beep();
						Dialog.info(I18N.get("コントローラは有効化されていません。コントローラを使う場合はゲームを再起動してください。"));
						is.keyReleaseEvent(gm, Keys.ENTER);
						return;
					}
					if (selected == 0) {
						nextStage();
						Const.LOADING = true;
						return;
					}
					SoundStorage.getInstance().get("SD1008").load().stopAndPlay();
					atsg.exec();
				}
				break;
			case 1:
				effect = new FadeEffect(gm.getWindow().getWidth(), gm.getWindow().getHeight(),
						new ColorChanger(
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								new FadeCounter(0, +6)
						));
				nextStage();
				break;
			case 2:
				if (effect.isEnded()) {
					waitTime = new FrameTimeCounter(60);
					nextStage();
				}
				break;
			case 3:
				if (waitTime.isReaching()) {
					atsg.getSelected().exec();
				}
				break;
		}
	}

	private void nextStage() {
		stage++;
	}
	private FadeEffect effect;
	private FrameTimeCounter waitTime;

	@Override
	public void draw(GraphicsContext gc) {
		//imageに書いた方が早いかもしれないが、簡易なのでここで直接書く
		gc.setColor(Color.WHITE);
		atsg.draw(gc);
		Graphics2D g = gc.create();
		Font f = new Font(FontModel.DEFAULT.getFont().getName(), Font.PLAIN, 40);
		g.setFont(f);
		g.drawString("Fuzzy World", 24, 70);

		f = new Font(Font.SERIF, Font.PLAIN, 28);
		g.setFont(f);
		g.drawString("-" + I18N.get("金の円盤と魔法使いと不死の秘術") + "-", 38, 120);
		g.drawLine(-2, 85, 440, 85);

		f = new Font(Font.SERIF, Font.PLAIN, 16);
		g.setFont(f);
		g.setColor(Color.DARK_GRAY);
		g.drawString(Versions.COPY_RIGHT, 16, 450);

		if (effect != null) {
			if (effect.isEnded()) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, gm.getWindow().getWidth(), gm.getWindow().getHeight());
			} else {
				effect.draw(g);
			}
		}
		g.dispose();
	}

}
