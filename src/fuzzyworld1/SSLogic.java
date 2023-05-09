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
package fuzzyworld1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.input.GamePadButton;
import kinugasa.game.input.InputState;
import kinugasa.game.input.InputType;
import kinugasa.game.input.Keys;
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
		super(Const.LogicName.SS_LOGIC, gm);
	}

	private FrameTimeCounter waitTime1;
	private FadeEffect effect;
	private int stage;
	private BufferedImage ssImage;

	@Override
	public void load() {
		waitTime1 = new FrameTimeCounter(150);
		stage = -1;
		ssImage = ImageUtil.load("resource/data/image/ss.png");
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
				SoundStorage.getInstance().get("SE").get("Œø‰Ê‰¹QSS.wav").load().stopAndPlay();
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
				gls.changeTo(Const.LogicName.TITLE_LOGIC);
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
