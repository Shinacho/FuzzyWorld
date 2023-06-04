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
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.input.GamePadButton;
import kinugasa.game.input.InputState;
import kinugasa.game.input.InputType;
import kinugasa.game.ui.FontModel;
import kinugasa.game.input.GamePadStatusMonitor;
import kinugasa.game.input.Keys;
import kinugasa.resource.sound.Sound;
import kinugasa.resource.sound.SoundStorage;

/**
 *
 * @vesion 1.0.0 - 2022/11/13_12:17:23<br>
 * @author Shinacho<br>
 */
public class GamePadTestLogic extends GameLogic {

	public GamePadTestLogic(GameManager gm) {
		super(Const.LogicName.GAMEPAD_TEST, gm);
	}
	private GamePadStatusMonitor gp;
	private Sound sound;

	@Override
	public void load() {
		gp = new GamePadStatusMonitor();
		sound = SoundStorage.getInstance().get("SD1008").load();
		OperationInfo.getInstance().set(OperationInfo.AvalableInput.戻る, OperationInfo.AvalableInput.撮影);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		gp.update(is.getGamePadState());
		if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
			sound.stopAndPlay();
			gls.changeTo(Const.LogicName.TITLE);
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		Graphics2D g2 = g.create();
		g2.setColor(Color.WHITE);
		g2.setFont(new Font(FontModel.DEFAULT.getFont().getName(), Font.PLAIN, 24));
		g2.drawString("GAMEPAD TEST", 12, 32);
		g2.dispose();
		gp.draw(g);
	}

}
