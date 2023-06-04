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

import kinugasa.game.GameManager;
import kinugasa.game.GameOption;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.input.InputState;
import kinugasa.util.Random;
import kinugasa.util.StringUtil;

/**
 *
 * @vesion 1.0.0 - May 26, 2023_10:03:31 AM<br>
 * @author Shinacho<br>
 */
public class Test extends GameManager {

	public static void main(String[] args) {
		for (int i = 34; i < 519; i++) {
			String bookID = "BK" + StringUtil.zeroUme(i + "", 4);
			int n = Random.d10(1) + 1;
			for (int j = 0; j < n; j++) {
				int r = Random.randomAbsInt(1561) + 1;
				String pageID = "PG" + StringUtil.zeroUme(r + "", 4);
				System.out.println(bookID + "\t" + pageID);
			}
		}
//		new Test().gameStart();

	}

	private Test() {
		super(GameOption.defaultOption());
	}

	@Override
	protected void startUp() {
	}

	@Override
	protected void dispose() {
	}

	@Override
	protected void update(GameTimeManager gtm, InputState is) {
	}

	@Override
	protected void draw(GraphicsContext gc) {
	}

}
