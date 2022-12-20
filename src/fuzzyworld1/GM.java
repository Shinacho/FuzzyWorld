/*
 * The MIT License
 *
 * Copyright 2022 Dra.
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
import kinugasa.game.GameLogicStorage;
import kinugasa.game.GameManager;
import kinugasa.game.GameOption;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.LockUtil;
import kinugasa.game.input.InputState;
import kinugasa.game.system.GameSystem;
import kinugasa.game.ui.FPSLabel;
import kinugasa.resource.sound.SoundLoader;
import static kinugasa.game.GameOption.GUILockMode.*;

/**
 *
 * @vesion 1.0.0 - 2022/11/12_21:36:46<br>
 * @author Dra211<br>
 */
public class GM extends GameManager {

	public static void main(String[] args) {
		LockUtil.deleteAllLockFile();
		new GM().gameStart();
	}

	private GM() {
		super(GameOption.fromGUI("Fuzzy World", OFF_DISABLE, ON_DISABLE, ON_ENABLE).setBackColor(new Color(0, 32, 66)));
	}
	private FPSLabel fps;
	private GameLogicStorage gls;

	@Override
	protected void startUp() {
		Const.Input.gamepad = GameOption.getInstance().isUseGamePad();
		Const.Screen.WIDTH = GameOption.getInstance().getWindowSize().width;
		Const.Screen.HEIGHT = GameOption.getInstance().getWindowSize().height;
		SoundLoader.loadList("resource/bgm/BGM.csv");
		SoundLoader.loadList("resource/se/SE.csv");
		OperationSprite.getInstance().setX(24);
		OperationSprite.getInstance().setY(450);
		fps = new FPSLabel((int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() - 70), 12);
		//
		gls = GameLogicStorage.getInstance();
		gls.add(new TitleLogic(this));
		gls.add(new MusicRoomLogic(this));
		gls.add(new GamePadTestLogic(this));
		gls.add(new OPLogic(this));
		gls.add(new ChapterTitleLogic(this));
		gls.add(new FieldLogic(this));

		gls.changeTo(Const.LogicName.TITLE_LOGIC);
		//
	}

	@Override
	protected void dispose() {
		gls.getCurrent().dispose();
	}

	@Override
	protected void update(GameTimeManager gtm, InputState is) {
		if (GameSystem.isDebugMode()) {
			fps.setGtm(gtm);
		}
		gls.getCurrent().update(gtm, is);
	}

	@Override
	protected void draw(GraphicsContext gc) {
		OperationSprite.getInstance().draw(gc);
		gls.getCurrent().draw(gc);
		fps.draw(gc);
	}

}
