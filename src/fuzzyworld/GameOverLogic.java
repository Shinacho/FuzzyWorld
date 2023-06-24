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

import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.input.InputState;

/**
 *
 * @vesion 1.0.0 - 2023/05/17_6:46:26<br>
 * @author Shinacho<br>
 */
public class GameOverLogic extends GameLogic {

	GameOverLogic(GameManager gm) {
		super(Const.LogicName.GAME_OVER, gm);
	}

	@Override
	public void load() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void update(GameTimeManager gtm, InputState is) {
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawString("GAME_OVER", 123, 123);
	}

}
