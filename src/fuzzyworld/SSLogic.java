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
import java.awt.image.BufferedImage;
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.input.InputState;
import kinugasa.graphics.ColorChanger;
import kinugasa.graphics.ColorTransitionModel;
import kinugasa.graphics.FadeCounter;
import kinugasa.graphics.ImageUtil;
import kinugasa.object.FadeEffect;
import kinugasa.resource.sound.SoundStorage;
import kinugasa.util.FrameTimeCounter;

/**
 *
 * @vesion 1.0.0 - 2023/05/08_19:07:18<br>
 * @author Shinacho<br>
 */
public class SSLogic extends GameLogic {

	public SSLogic(GameManager gm) {
		super(Const.LogicName.SS, gm);
	}

	private FrameTimeCounter waitTime1;
	private FadeEffect effect;
	private int stage;
	private BufferedImage ssImage;

	@Override
	public void load() {
		waitTime1 = new FrameTimeCounter(150);
		stage = -1;
		ssImage = ImageUtil.load("resource/image/ss.png");
	}

	@Override
	public void dispose() {
	}

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		if (is.isAnyButtonInput()) {
			stage = 4;
		}
		switch (stage) {
			case -1:
				effect = new FadeEffect(gm.getWindow().getWidth(), gm.getWindow().getHeight(),
						new ColorChanger(
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								ColorTransitionModel.valueOf(0),
								new FadeCounter(255, -8)
						));
				SoundStorage.getInstance().get("SD1002").load().stopAndPlay();
				stage++;
				break;
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
								new FadeCounter(0, +8)
						));
				stage++;
				break;
			case 3:
				if (effect.isEnded()) {
					stage++;
				}
				break;
			case 4:
				gls.changeTo(Const.LogicName.TITLE);
				break;
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		if (stage != -1) {
			g.drawImage(ssImage, 0, 0);
		}
		effect.draw(g);
		if (stage >= 4) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, gm.getWindow().getWidth(), gm.getWindow().getHeight());
		}
	}

}
