/*
 * The MIT License
 *
 * Copyright 2023 Dra.
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
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.input.InputState;

/**
 *
 * @vesion 1.0.0 - 2023/05/07_12:06:58<br>
 * @author Dra211<br>
 */
public class BattleLogic extends GameLogic {

	public BattleLogic(GameManager gm) {
		super(Const.LogicName.BATTLE, gm);
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
		g.setColor(Color.WHITE);
		g.drawString("BATTLE LOGIC", 123, 123);
	}

}
