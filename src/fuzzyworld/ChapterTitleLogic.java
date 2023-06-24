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
