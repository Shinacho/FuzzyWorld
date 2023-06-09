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
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
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
import kinugasa.game.system.Action;
import kinugasa.game.system.ItemStorage;
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
			GM gm = new GM();
			gm.gameStart();
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
	private OperationInfo operationInfoSprite = OperationInfo.getInstance();

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
		gls.changeTo(Const.LogicName.SS);
		getWindow().setTitle("Fuzzy World" + " -" + I18N.get("金の円盤と魔法使いと不死の秘術") + "-");
		getWindow().setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
		//セーブデータ初期化
		for (int i = 0; i < Const.Save.SAVE_DATA_NUM + 1; i++) {
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
						//初期データ投入
						DBConnection.getInstance().execByFile("resource/data/sql/readData.sql");
						//MODデータ投入
						for (var mf : new File("resource/data/mods/").listFiles()) {
							if (mf.getName().toLowerCase().endsWith(".sql")) {
								DBConnection.getInstance().execByFile("resource/data/sql/" + mf.getName());
							}
						}
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
		//
		operationInfoSprite.set(OperationInfo.AvalableInput.決定, OperationInfo.AvalableInput.撮影);

		//バッグに分類されるアイテムを追加
		ItemStorage.bagItems.put("A4840", 3);
		ItemStorage.bagItems.put("A4841", 4);
		ItemStorage.bagItems.put("A4842", 2);
		ItemStorage.bagItems.put("A4843", 5);
		//魔法の術者効果キーを設定
		Action.magicUserDmage.addAll(Arrays.asList("MP", "SAN", "POW"));
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
		operationInfoSprite.draw(gc);
		fps.draw(gc);
		loading.draw(gc);
	}

}
