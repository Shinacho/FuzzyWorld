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
import java.awt.Graphics2D;
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameOption;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.I18N;
import kinugasa.game.input.InputState;
import kinugasa.game.ui.FontModel;
import kinugasa.graphics.ColorChanger;
import kinugasa.graphics.ColorTransitionModel;
import kinugasa.graphics.FadeCounter;
import kinugasa.object.FadeEffect;
import kinugasa.util.FrameTimeCounter;

/**
 *
 * @vesion 1.0.0 - 2022/12/16_21:12:55<br>
 * @author Shinacho<br>
 */
public class ChapterTitleLogic extends GameLogic {

	public ChapterTitleLogic(GameManager gm) {
		super(Const.LogicName.CHAPTER_TITLE, gm);
	}
	private FrameTimeCounter waitTime1;
	private FadeEffect effect;
	private int stage;

	@Override
	public void load() {
		effect = new FadeEffect(gm.getWindow().getWidth(), gm.getWindow().getHeight(),
				new ColorChanger(
						ColorTransitionModel.valueOf(0),
						ColorTransitionModel.valueOf(0),
						ColorTransitionModel.valueOf(0),
						new FadeCounter(255, -6)
				));
		waitTime1 = new FrameTimeCounter(150);
		stage = 0;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		switch (stage) {
			case 0:
				if (effect.isEnded()) {
					stage++;
				}
				break;
			case 1:
				if (waitTime1.isReaching()) {
					stage++;
				}
				break;
			case 2:
				effect = new FadeEffect(gm.getWindow().getWidth(), gm.getWindow().getHeight(),
						new ColorChanger(
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								new FadeCounter(0, +6)
						));
				stage++;
				break;
			case 3:
				if (effect.isEnded()) {
					gls.changeTo(Const.Chapter.nextLogic);
					stage++;
				}
				break;
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		Graphics2D g2 = g.create();
		int x = 64;
		int y = 64;
		g2.setColor(Color.WHITE);
		g2.setFont(FontModel.DEFAULT.clone().setFontSize(32).getFont());
		g2.drawString(I18N.get(Const.Chapter.currentI18NKey), x, y);
		x += 48;
		y += 64;
		g2.drawString("- " + I18N.get(Const.Chapter.currentSubTitleI18NKey) + " -", x, y);
		effect.draw(g);
		g2.dispose();
	}
}
