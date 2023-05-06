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
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.I18N;
import kinugasa.game.field4.D2Idx;
import kinugasa.game.field4.FieldMap;
import kinugasa.game.field4.FieldMapXMLLoader;
import kinugasa.game.input.InputState;
import kinugasa.game.system.GameSystem;
import kinugasa.game.ui.Dialog;
import kinugasa.game.ui.DialogIcon;
import kinugasa.game.ui.DialogOption;
import kinugasa.graphics.ColorChanger;
import kinugasa.graphics.ColorTransitionModel;
import kinugasa.graphics.FadeCounter;
import kinugasa.object.FadeEffect;
import kinugasa.resource.sound.SoundStorage;
import kinugasa.util.FrameTimeCounter;

/**
 *
 * @vesion 1.0.0 - 2022/11/13_14:58:58<br>
 * @author Dra211<br>
 */
public class OPLogic extends GameLogic {

	public OPLogic(GameManager gm) {
		super(Const.LogicName.OP, gm);
	}
	private FieldMap map;
	private int stage = 0;
	private Map<String, String> nameMap;

	@Override
	public void load() {

		map = new FieldMapXMLLoader()
				.addFieldMapStorage("resource/data/fms.xml")
				.addMapChipAttr("resource/data/chipAttr.xml")
				.addMapChipSet("resource/data/chipSet_inner16.xml")
				.addVehicle("resource/data/vehicle.xml")
				.setInitialFieldMapName("ROOM")
				.setInitialLocation(new D2Idx(4, 4))
				.setInitialVehicleName("WALK")
				.load();
		map.getCamera().updateToCenterNoPC();
		effect = new FadeEffect(gm.getWindow().getWidth(), gm.getWindow().getHeight(),
				new ColorChanger(
						ColorTransitionModel.valueOf(0),
						ColorTransitionModel.valueOf(0),
						ColorTransitionModel.valueOf(0),
						new FadeCounter(255, -6)
				));
		waitTime1 = new FrameTimeCounter(180);
		stage = 4;
		nameMap = new HashMap<>();
		nameMap.put("あ", "あなたは・・・とても急いでいるようですね。");
		nameMap.put("ああああ", "あなたは・・・急いでいるようですね。");
		nameMap.put("a", "あなたは・・・とても急いでいるようですね。");
		nameMap.put("aaaa", "あなたは・・・急いでいるようですね。");
		nameMap.put("とんぬら", "あなたは・・・伝説の勇者のようですね。");
		nameMap.put("トンヌラ", "あなたは・・・伝説の勇者のようですね。");
		nameMap.put("ほも", "あなたは・・・入力速度を考慮したのですか？。はい、よーいスタート。");
		nameMap.put("ほよ", "あなたは・・・入力速度を考慮したのですか？。はい、よーいスタート。");
		nameMap.put("homo", "あなたは・・・入力速度を考慮したのですか？。はい、よーいスタート。");
		nameMap.put("hoyo", "あなたは・・・入力速度を考慮したのですか？。はい、よーいスタート。");
		nameMap.put("のんけ", "あなたは・・・のんけではありませんね？はい、よーいスタート。");
		nameMap.put("サマル", "あなたは・・・途中で呪われそうな名前ですね。");
		nameMap.put("すけさん", "あなたは・・・途中で呪われそうな名前ですね。");
		nameMap.put("もょもと", "あなたは・・・レベル48ですか？残念ながらそのような機能はありません。");
		nameMap.put("リンク", "あなたは・・・時の勇者ですか？");
		nameMap.put("hoge", "あなたは・・・テストユーザですか？");
		nameMap.put("piyo", "あなたは・・・テストユーザですか？");
		nameMap.put("fuga", "あなたは・・・テストユーザですか？");
		nameMap.put("ほげ", "あなたは・・・テストユーザですか？");
		nameMap.put("ぴよ", "あなたは・・・テストユーザですか？");
		nameMap.put("ふが", "あなたは・・・テストユーザですか？");
		nameMap.put("そば", "あなたは・・・お蕎麦大好きですね！");
		nameMap.put("蕎麦", "あなたは・・・お蕎麦大好きですね！");
		nameMap.put("soba", "あなたは・・・お蕎麦大好きですね！");

	}

	@Override
	public void dispose() {
	}
	private FrameTimeCounter waitTime1;
	private FadeEffect effect;

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		switch (stage) {
			case 4:
				waitTime1 = new FrameTimeCounter(120);
				stage++;
				break;
			case 5:
				if (waitTime1.isReaching()) {
					stage++;
				}
				break;
			case 6:
				Dialog.InputResult r = null;
				do {
					r = Dialog.input(I18N.translate("PLEASE_NAME"), 4);
				} while (r.value == null || r.value.equals("") || r.result != DialogOption.YES);
				Const.Player.pc1Name = r.value;
				stage++;
				break;
			case 7:
				if (Dialog.yesOrNo("FUZZY WORLD", DialogIcon.QUESTION, Const.Player.pc1Name + I18N.translate("YOUR_NAME_IS")) != DialogOption.YES) {
					stage--;
					break;
				}
				Dialog.info(Const.Player.pc1Name + ",\r\n" + I18N.translate("GOLDEN_RECORD"));
				stage++;
				break;
			case 8:
				if (GameSystem.isDebugMode()) {
					System.out.println("FUZZY WORLD へようこそ, " + Const.Player.pc1Name);
					if (nameMap.containsKey(Const.Player.pc1Name)) {
						System.out.println(nameMap.get(Const.Player.pc1Name));
					}
					if ("qwerty".contains(Const.Player.pc1Name)) {
						System.out.println("あなたは・・・なかなか適当な人のようですね。");
					}
					if ("asdfgh".contains(Const.Player.pc1Name)) {
						System.out.println("あなたは・・・なかなか適当な人のようですね。");
					}
					if (Const.Player.pc1Name.contains("<") || Const.Player.pc1Name.contains(">")) {
						System.out.println("フフフ・・・そのような名前にしても、この世界は壊れません。無駄ですよ。");
					}
					if (Const.Player.pc1Name.contains("\\") || Const.Player.pc1Name.contains("\"")) {
						System.out.println("フフフ・・・そのような名前にしても、この世界は壊れません。無駄ですよ。");
					}
					System.out.println("あなたには・・・このメッセージが見えているのですね。");
					System.out.println("あなたの使命は金の円盤にしたがって世界を正すこと。");
					System.out.println("我々に正しい世界をもたらしてください"
							+ "。金の円盤にしたがって・・・");
				}
				effect = new FadeEffect(gm.getWindow().getWidth(), gm.getWindow().getHeight(),
						new ColorChanger(
								ColorTransitionModel.valueOf(255),
								ColorTransitionModel.valueOf(255),
								ColorTransitionModel.valueOf(255),
								new FadeCounter(0, +6)
						));
				stage++;
				break;
			case 9:
				if (effect.isEnded()) {
					waitTime1 = new FrameTimeCounter(60);
					stage++;
				}
				break;
			case 10:
				if (waitTime1.isReaching()) {
					stage++;
				}
				break;
			case 11:
				Const.Chapter.current = "CHAPTER2";
				Const.Chapter.currentSubTitle = "CHAPTER2_SUBTITLE";
				Const.Chapter.nextLogic = Const.LogicName.FIELD;
				gls.changeTo(Const.LogicName.CHAPTER_TITLE);
				break;
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		Graphics2D g2 = g.create();
		switch (stage) {
			case 4:
			case 5:
			case 6:
			case 7:
				map.draw(g);
				break;
			case 8:
			case 9:
				map.draw(g);
				effect.draw(g);
				break;
			case 10:
			case 11:
				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, Const.Screen.WIDTH, Const.Screen.HEIGHT);
				break;
		}
		g2.dispose();
	}

}
