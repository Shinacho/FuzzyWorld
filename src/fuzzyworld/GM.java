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
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import kinugasa.game.GameLog;
import kinugasa.game.GameLogicStorage;
import kinugasa.game.GameManager;
import kinugasa.game.GameOption;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.input.InputState;
import kinugasa.game.system.GameSystem;
import kinugasa.game.ui.FPSLabel;
import static kinugasa.game.GameOption.GUILockMode.*;
import kinugasa.game.I18N;
import kinugasa.game.PlayerConstants;
import kinugasa.game.input.GamePadButton;
import kinugasa.game.input.InputType;
import kinugasa.game.input.Keys;
import kinugasa.game.system.Status;
import kinugasa.game.ui.FontModel;
import kinugasa.game.ui.SimpleMessageWindowModel;
import kinugasa.game.ui.SimpleTextLabelModel;
import kinugasa.game.ui.TextLabelSprite;
import kinugasa.graphics.ImageUtil;
import kinugasa.resource.db.DBConnection;
import kinugasa.resource.sound.CachedSound;
import kinugasa.resource.sound.SoundStorage;

/**
 *
 * @vesion 1.0.0 - 2022/11/12_21:36:46<br>
 * @author Shinacho<br>
 */
public class GM extends GameManager {

	public static void main(String[] args) {
		try {
			new GM().gameStart();
		} catch (Throwable ex) {
			kinugasa.game.GameLog.print(ex);
		}
	}

	private GM() {
		super(GameOption.fromGUI("Kinugasa Game Launcher", ON_DISABLE, ON_DISABLE, ON_ENABLE).setBackColor(new Color(0, 32, 66)));
		//
		//フォントのロード
		//
		String fontName = "MONOSPACED";
		try {
			fontName = Files.readAllLines(new File("resource/data/font.txt").toPath(), Charset.forName("UTF-8")).get(0);
			FontModel.DEFAULT.setFont(new Font(fontName, Font.PLAIN, FontModel.DEFAULT.getFont().getSize()));
			GameLog.print("font[" + fontName + "] is loaded");
		} catch (Exception ex) {
			GameLog.print("font.txt is not found or font name is not found, using default font[MONOSPACED]");
		}
	}
	private FPSLabel fps;
	private TextLabelSprite loading;
	private GameLogicStorage gls;

	@Override
	protected void startUp() {
		GameLog.print("--MEM INFO--");
		GameLog.print("TOTAL:" + Runtime.getRuntime().totalMemory() / 1024 / 1024);
		GameLog.print("MAX:" + Runtime.getRuntime().maxMemory() / 1024 / 1024);
		GameLog.print("FREE:" + Runtime.getRuntime().freeMemory() / 1024 / 1024);

		Const.Screen.WIDTH = GameOption.getInstance().getWindowSize().width;
		Const.Screen.HEIGHT = GameOption.getInstance().getWindowSize().height;
		Status.canMagicStatusName = "CAN_MAGIC";
		Status.canMagicStatusValue = "1";
		SimpleMessageWindowModel.maxLine = 22;
		fps = new FPSLabel((int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() - 60), 12);
		loading = new TextLabelSprite("", new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(12)),
				(int) (Const.Screen.WIDTH / GameOption.getInstance().getDrawSize() - 60), 20, 0, 0);
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
		gls.add(new SaveDataSelectLogic(this));
		gls.add(new GameOverLogic(this));

		//
		gls.changeTo(Const.LogicName.SS_LOGIC);
		getWindow().setTitle("Fuzzy World" + " -" + I18N.get("魔法使いと不死の秘術") + "-");
		getWindow().setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
		//

		//
		for (int i = 0; i < Const.SAVE_DATA_NUM + 1; i++) {
			String fileName = "resource/data/data" + i + ".mv.db";
			File f = new File(fileName);
			if (!f.exists()) {
				try {
					f.createNewFile();
					DBConnection.getInstance().open("file:./resource/data/data" + i, "sa", "adm");
					//Sound
					DBConnection.getInstance().execByFile("resource/data/sql/insertSound.sql");
					if (i != 0) {
						//CreateTable
						DBConnection.getInstance().execByFile("resource/data/sql/createTable.sql");
					}
					DBConnection.getInstance().close();
				} catch (IOException ex) {
					GameLog.print(ex);
				}
			}
		}
		//0(BGMのみ)を仮で開く
		DBConnection.getInstance().open("file:./resource/data/data" + 0, "sa", "adm");
		SoundVolumeForm volumeForm = SoundVolumeForm.getInstance();
		float volumeBgm = volumeForm.getMulBgm();
		float volumeSe = volumeForm.getMulSe();
		SoundStorage.volumeBgm = volumeBgm;
		SoundStorage.volumeSe = volumeSe;
		if (GameSystem.isDebugMode()) {
			kinugasa.game.GameLog.print("volume: BGM[" + volumeBgm + "] SE:[" + volumeSe + "]");
		}
		SoundStorage.getInstance().rebuild();
//		GameLog.print("SOUND------");
//		for (var s : SoundStorage.getInstance()) {
//			GameLog.print(s + " / " + ((CachedSound) s).getBuilder());
//		}
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
		//ロード中
		if (Const.LOADING) {
			loading.setText("LOADING");
		} else {
			loading.setText("");
		}

		//スクリーンショット
		if (is.isPressed(GamePadButton.BACK, Keys.F12, InputType.SINGLE)) {
			JFileChooser c = new JFileChooser();
			c.setDialogTitle(I18N.get("スクリーンショットの保存"));
			c.setSelectedFile(new File(PlayerConstants.getInstance().DESKTOP_PATH + "/screenShot_" + System.currentTimeMillis() + ".png"));
			c.setMultiSelectionEnabled(false);

			int r = c.showSaveDialog(null);
			if (r == JFileChooser.APPROVE_OPTION) {
				File f = c.getSelectedFile();
				SoundStorage.getInstance().get("SD1000").load().stopAndPlay();
				Rectangle rec = getWindow().getBounds();
				ImageUtil.screenShot(f.getAbsolutePath(), rec);
			}
		}
		//チートコンソール
		if (is.isPressed(Keys.AT, InputType.SINGLE)) {
			ConsolCmd cmd = new ConsolCmd();
			cmd.setVisible(true);
			try {
				while (true) {
					Thread.sleep(300);
					if (!cmd.isVisible()) {
						break;
					}
				}
			} catch (InterruptedException ex) {
			}
		}

		gls.getCurrent().update(gtm, is);
	}

	@Override
	protected void draw(GraphicsContext gc) {
		gls.getCurrent().draw(gc);
		fps.draw(gc);
		loading.draw(gc);
	}

}