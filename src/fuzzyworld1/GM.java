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
package fuzzyworld1;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import kinugasa.game.GameLog;
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
import kinugasa.game.I18N;
import kinugasa.game.PlayerConstants;
import kinugasa.game.input.InputType;
import kinugasa.game.input.Keys;
import kinugasa.game.system.GameSystemException;
import kinugasa.game.ui.FontModel;
import kinugasa.graphics.ImageUtil;
import kinugasa.resource.sound.SoundStorage;

/**
 *
 * @vesion 1.0.0 - 2022/11/12_21:36:46<br>
 * @author Shinacho<br>
 */
public class GM extends GameManager {

	public static void main(String[] args) {
		new GM().gameStart();
	}

	private GM() {
		super(GameOption.fromGUI("Fuzzy World", ON_DISABLE, ON_DISABLE, ON_ENABLE).setBackColor(new Color(0, 32, 66)));
	}
	private FPSLabel fps;
	private GameLogicStorage gls;

	@Override
	protected void startUp() {
		Const.Screen.WIDTH = GameOption.getInstance().getWindowSize().width;
		Const.Screen.HEIGHT = GameOption.getInstance().getWindowSize().height;
		SoundLoader.loadList("resource/bgm/BGM.csv");
		SoundLoader.loadList("resource/se/SE.csv");
		fps = new FPSLabel((int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() - 70), 12);
		//
		gls = GameLogicStorage.getInstance();
		gls.add(new TitleLogic(this));
		gls.add(new MusicRoomLogic(this));
		gls.add(new GamePadTestLogic(this));
		gls.add(new OPLogic(this));
		gls.add(new ChapterTitleLogic(this));
		gls.add(new FieldLogic(this));
		gls.add(new BattleLogic(this));
		gls.add(new SSLogic(this));
		//
		//フォントのロード
		//
		String fontName = "MONOSPACED";
		try {
			fontName = Files.readAllLines(new File("resource/data/font.txt").toPath(), Charset.forName("MS932")).get(0);
			FontModel.DEFAULT.setFont(new Font(fontName, Font.PLAIN, FontModel.DEFAULT.getFont().getSize()));
		} catch (Exception ex) {
			GameLog.printInfo("font.txt is not found or font name is not found, using default font[MONOSPACED]");
		}

		gls.changeTo(Const.LogicName.SS_LOGIC);
		getWindow().setTitle("Fuzzy World" + " -" + I18N.translate("SUB_TITLE") + "-");
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

		//スクリーンショット
		if (is.isPressed(Keys.F12, InputType.SINGLE)) {
			JFileChooser c = new JFileChooser();
			c.setDialogTitle(I18N.translate("SCREEN_SHOT"));
			c.setSelectedFile(new File(PlayerConstants.getInstance().DESKTOP_PATH + "/screenShot_" + System.currentTimeMillis() + ".png"));
			c.setMultiSelectionEnabled(false);
			int r = c.showSaveDialog(null);
			if (r == JFileChooser.APPROVE_OPTION) {
				File f = c.getSelectedFile();
				SoundStorage.getInstance().get("SE").get("screenShot.wav").load().stopAndPlay();
				ImageUtil.screenShot(f.getAbsolutePath(), getWindow().getBounds());
			}
		}

		gls.getCurrent().update(gtm, is);
	}

	@Override
	protected void draw(GraphicsContext gc) {
		gls.getCurrent().draw(gc);
		fps.draw(gc);
	}

}
