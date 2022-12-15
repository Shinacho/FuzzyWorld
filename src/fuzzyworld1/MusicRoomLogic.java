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
import java.io.IOException;
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
import kinugasa.game.ui.FontModel;
import kinugasa.game.ui.MusicRoom;
import kinugasa.game.ui.SimpleTextLabelModel;
import kinugasa.game.ui.TextLabelSprite;
import kinugasa.object.FourDirection;
import kinugasa.resource.sound.SoundStorage;
import kinugasa.util.FrameTimeCounter;

/**
 *
 * @vesion 1.0.0 - 2022/11/13_10:34:45<br>
 * @author Dra211<br>
 */
public class MusicRoomLogic extends GameLogic {

	public MusicRoomLogic(GameManager gm) {
		super(Const.LogicName.MUSIC_ROOM, gm);
	}

	private MusicRoom mr;

	@Override
	public void load() {
		mr = new MusicRoom("BGM", Const.Screen.WIDTH / GameOption.getInstance().getDrawSize(), Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize());

		if (Const.Input.gamepad) {
			OperationSprite.getInstance().setText("(A):" + I18N.translate("SUBMIT") + " / " + "Å™Å´Å©Å®:" + I18N.translate("MOVE") + " / " + "(B):" + I18N.translate("RETURN") + " / " + "(X):" + I18N.translate("OPEN"));
		} else {
			OperationSprite.getInstance().setText("[ENTER]:" + I18N.translate("SUBMIT") + " / " + "Å™Å´Å©Å®:" + I18N.translate("MOVE") + " / " + "[BACK SPACE]::" + I18N.translate("RETURN") + " / " + "[O]:" + I18N.translate("OPEN"));
		}
	}

	@Override
	public void dispose() {
		SoundStorage.getInstance().get("BGM").stopAll();
		SoundStorage.getInstance().get("BGM").dispose();
	}

	@Override
	public void update(GameTimeManager gtm) {
		InputState is = InputState.getInstance();
		if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
			mr.next();
		}
		if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
			mr.prev();
		}
		if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
			mr.nextColumn();
		}
		if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
			mr.prevColumn();
		}

		if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
			mr.exec();
		}
		if (is.isPressed(GamePadButton.X, Keys.O, InputType.SINGLE)) {
			try {
				Runtime.getRuntime().exec("cmd /C start .\\resource\\bgm");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
			gls.changeTo(Const.LogicName.TITLE_LOGIC);
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		Graphics2D g2 = g.create();
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("MONOSPACED", Font.PLAIN, 24));
		g2.drawString("MUSIC ROOM", 12, 32);
		g2.dispose();
		mr.draw(g);
	}

}
