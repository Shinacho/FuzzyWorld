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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameOption;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.input.GamePadButton;
import kinugasa.game.input.InputState;
import kinugasa.game.input.InputType;
import kinugasa.game.input.Keys;
import kinugasa.game.ui.FontModel;
import kinugasa.game.ui.MessageWindow;
import kinugasa.game.ui.MusicRoom;
import kinugasa.game.ui.SimpleMessageWindowModel;
import kinugasa.graphics.Animation;
import kinugasa.graphics.ImageEditor;
import kinugasa.graphics.ImageUtil;
import kinugasa.graphics.RenderingQuality;
import kinugasa.object.AnimationSprite;
import kinugasa.resource.sound.CachedSound;
import kinugasa.resource.sound.SoundStorage;
import kinugasa.util.FrameTimeCounter;

/**
 *
 * @vesion 1.0.0 - 2022/11/13_10:34:45<br>
 * @author Shinacho<br>
 */
public class MusicRoomLogic extends GameLogic {

	public MusicRoomLogic(GameManager gm) {
		super(Const.LogicName.MUSIC_ROOM, gm);
	}

	private MusicRoom mr;
	private MessageWindow comment;

	@Override
	public void load() {
		mr = new MusicRoom(
				24,
				48,
				(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() - 48),
				(int) (Const.Screen.HEIGHT / GameOption.getInstance().getDrawSize() - 48 * 2),
				SimpleMessageWindowModel.maxLine);
		comment = new MessageWindow(240, 280, 442, 140, new SimpleMessageWindowModel(""));

		soundNameSprite = new SoundNameSprite(380, 64);
		OperationInfo.getInstance().set(OperationInfo.AvalableInput.決定, OperationInfo.AvalableInput.移動上下, OperationInfo.AvalableInput.開く, OperationInfo.AvalableInput.撮影, OperationInfo.AvalableInput.戻る);
	}

	@Override
	public void dispose() {
		SoundStorage.getInstance().stopAll();
		SoundStorage.getInstance().dispose();
	}

	private SoundNameSprite soundNameSprite;

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
			mr.nextSelect();
		}
		if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
			mr.prevSelect();
		}

		if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
			comment.setText(mr.getSelectedCachedSound().getDesc());
			comment.allText();
			mr.play();
			soundNameSprite.setText(mr.getSelectedCachedSound().getFileName().replaceAll(".wav", ""));
		}
		if (is.isPressed(GamePadButton.X, Keys.M, InputType.SINGLE)) {
			try {
				Runtime.getRuntime().exec("cmd /C start .\\resource\\bgm");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
			mr.dispose();
			SoundStorage.getInstance().dispose();
			gls.changeTo(Const.LogicName.TITLE);
		}
		mr.update();
		soundNameSprite.update();
	}

	@Override
	public void draw(GraphicsContext g) {
		Graphics2D g2 = g.create();
		g2.setColor(Color.WHITE);
		g2.setFont(new Font(FontModel.DEFAULT.getFont().getName(), Font.PLAIN, 24));
		g2.drawString("MUSIC ROOM", 12, 32);
		g2.dispose();
		mr.draw(g);
		soundNameSprite.draw(g);
		comment.draw(g);
	}

	static class SoundNameSprite extends AnimationSprite {

		public SoundNameSprite(int x, int y) {
			super(x, y, 300, 300);
		}

		void setText(String t) {
			List<BufferedImage> images = new ArrayList<>();
			for (int i = 0; i < 360; i++) {
				BufferedImage image = ImageUtil.newImage((int) getWidth(), (int) getHeight());
				Graphics2D g = ImageUtil.createGraphics2D(image, RenderingQuality.QUALITY);
				g.setColor(Color.WHITE);
				g.setFont(new Font(FontModel.DEFAULT.getFont().getName(), Font.PLAIN, 16));
				g.fillOval(0, 0, (int) getWidth(), (int) getHeight());
				g.setColor(Color.BLACK);
				g.drawString(t, getWidth() / 2 - (t.length() / 2 * 17), getHeight() / 2);
				g.dispose();
				image = ImageEditor.rotate(image, i, null);
				images.add(image);
			}
			setAnimation(new Animation(new FrameTimeCounter(1), images));
		}

	}
}
