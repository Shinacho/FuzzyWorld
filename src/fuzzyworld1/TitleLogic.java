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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
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
import kinugasa.game.ui.TextLabelSprite;
import kinugasa.resource.sound.SoundStorage;
import kinugasa.game.I18N;
import kinugasa.game.input.Keys;
import kinugasa.game.ui.Dialog;
import kinugasa.resource.sound.Sound;
import kinugasa.util.Versions;

/**
 *
 * @vesion 1.0.0 - 2022/11/12_21:36:53<br>
 * @author Dra211<br>
 */
public class TitleLogic extends GameLogic {

	public TitleLogic(GameManager gm) {
		super(Const.LogicName.TITLE_LOGIC, gm);
	}
	private ActionTextSpriteGroup atsg;
	private int selected;
	private Sound sound;

	@Override
	public void load() {

		atsg = new ActionTextSpriteGroup(540, 340,
				new ActionTextSprite(I18N.translate("NEW_GAME"), new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(14)), 0, 0, 18, 0, new Action() {
					@Override
					public void exec() {
						gls.changeTo(Const.LogicName.OP);
					}
				}),
				new ActionTextSprite(I18N.translate("LOAD_GAME"), new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(14)), 0, 0, 18, 0, new Action() {
					@Override
					public void exec() {
						gls.changeTo(Const.LogicName.LOAD_GAME);
					}
				}),
				new ActionTextSprite(I18N.translate("MUSIC_ROOM"), new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(14)), 0, 0, 18, 0, new Action() {
					@Override
					public void exec() {
						SoundStorage.getInstance().get("BGM").stopAll();
						gls.changeTo(Const.LogicName.MUSIC_ROOM);
					}
				}),
				new ActionTextSprite(I18N.translate("GAME_PAD_TEST"), new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(14)), 0, 0, 18, 0, new Action() {
					@Override
					public void exec() {
						gls.changeTo(Const.LogicName.GAMEPAD_TEST);
					}
				}),
				new ActionTextSprite(I18N.translate("GAME_EXIT"), new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(14)), 0, 0, 18, 0, new Action() {
					@Override
					public void exec() {
						SoundStorage.getInstance().get("BGM").stopAll();
						gm.gameExit();
					}
				})
		);

		sound = SoundStorage.getInstance().get("SE").get("効果音＿選択1.wav").load();

		SoundStorage.getInstance().get("BGM").get("フィールド５.wav").load().play();
		atsg.setSelectedIdx(selected);

		if (Const.Input.gamepad) {
			OperationSprite.getInstance().setText("(A):" + I18N.translate("SUBMIT") + " / " + "↑↓:" + I18N.translate("MOVE"));
		} else {
			OperationSprite.getInstance().setText("[ENTER]:" + I18N.translate("SUBMIT") + " / " + "↑↓:" + I18N.translate("MOVE"));
		}

	}

	@Override
	public void dispose() {
	}

	@Override
	public void update(GameTimeManager gtm) {
		InputState is = InputState.getInstance();
		if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
			atsg.next();
			sound.stopAndPlay();
			selected = atsg.getSelectedIdx();
		}
		if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
			atsg.prev();
			sound.stopAndPlay();
			selected = atsg.getSelectedIdx();
		}
		if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
			if (selected == 3 && !GameOption.getInstance().isUseGamePad()) {
				Toolkit.getDefaultToolkit().beep();
				Dialog.info(I18N.translate("GAMEPAD_NOTFOUND"));
				return;
			}
			selected = atsg.getSelectedIdx();
			sound.stopAndPlay();
			atsg.exec();
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		//imageに書いた方が早いかもしれないが、簡易なのでここで直接書く
		gc.setColor(Color.WHITE);
		Font f = new Font(Font.SERIF, Font.PLAIN, 40);
		atsg.draw(gc);
		Graphics2D g = gc.create();
		g.setFont(f);
		g.drawString("Fuzzy World", 24, 70);

		f = new Font(Font.SERIF, Font.PLAIN, 28);
		g.setFont(f);
		g.drawString("-" + I18N.translate("SUB_TITLE") + "-", 38, 120);
		g.drawLine(16, 85, 240, 85);

		f = new Font(Font.SERIF, Font.PLAIN, 16);
		g.setFont(f);
		g.setColor(Color.DARK_GRAY);
		g.drawString(Versions.COPY_RIGHT, 16, 16);

		g.dispose();
	}

}
