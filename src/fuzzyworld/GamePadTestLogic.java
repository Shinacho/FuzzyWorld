/*
 * Copyright (C) 2023 Shinacho
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
