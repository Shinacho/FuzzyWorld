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
		nameMap.put("��", "���Ȃ��́E�E�E�ƂĂ��}���ł���悤�ł��ˁB");
		nameMap.put("��������", "���Ȃ��́E�E�E�}���ł���悤�ł��ˁB");
		nameMap.put("a", "���Ȃ��́E�E�E�ƂĂ��}���ł���悤�ł��ˁB");
		nameMap.put("aaaa", "���Ȃ��́E�E�E�}���ł���悤�ł��ˁB");
		nameMap.put("�Ƃ�ʂ�", "���Ȃ��́E�E�E�`���̗E�҂̂悤�ł��ˁB");
		nameMap.put("�g���k��", "���Ȃ��́E�E�E�`���̗E�҂̂悤�ł��ˁB");
		nameMap.put("�ق�", "���Ȃ��́E�E�E���͑��x���l�������̂ł����H�B�͂��A��[���X�^�[�g�B");
		nameMap.put("�ق�", "���Ȃ��́E�E�E���͑��x���l�������̂ł����H�B�͂��A��[���X�^�[�g�B");
		nameMap.put("homo", "���Ȃ��́E�E�E���͑��x���l�������̂ł����H�B�͂��A��[���X�^�[�g�B");
		nameMap.put("hoyo", "���Ȃ��́E�E�E���͑��x���l�������̂ł����H�B�͂��A��[���X�^�[�g�B");
		nameMap.put("�̂�", "���Ȃ��́E�E�E�̂񂯂ł͂���܂���ˁH�͂��A��[���X�^�[�g�B");
		nameMap.put("�T�}��", "���Ȃ��́E�E�E�r���Ŏ��ꂻ���Ȗ��O�ł��ˁB");
		nameMap.put("��������", "���Ȃ��́E�E�E�r���Ŏ��ꂻ���Ȗ��O�ł��ˁB");
		nameMap.put("�������", "���Ȃ��́E�E�E���x��48�ł����H�c�O�Ȃ��炻�̂悤�ȋ@�\�͂���܂���B");
		nameMap.put("�����N", "���Ȃ��́E�E�E���̗E�҂ł����H");
		nameMap.put("hoge", "���Ȃ��́E�E�E�e�X�g���[�U�ł����H");
		nameMap.put("piyo", "���Ȃ��́E�E�E�e�X�g���[�U�ł����H");
		nameMap.put("fuga", "���Ȃ��́E�E�E�e�X�g���[�U�ł����H");
		nameMap.put("�ق�", "���Ȃ��́E�E�E�e�X�g���[�U�ł����H");
		nameMap.put("�҂�", "���Ȃ��́E�E�E�e�X�g���[�U�ł����H");
		nameMap.put("�ӂ�", "���Ȃ��́E�E�E�e�X�g���[�U�ł����H");
		nameMap.put("����", "���Ȃ��́E�E�E��������D���ł��ˁI");
		nameMap.put("����", "���Ȃ��́E�E�E��������D���ł��ˁI");
		nameMap.put("soba", "���Ȃ��́E�E�E��������D���ł��ˁI");
		
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
				Dialog.info(Const.Player.pc1Name + ",\r\n" + I18N.translate("GOLDEN_RECORD"));
				stage++;
				break;
			case 8:
				if (GameSystem.isDebugMode()) {
					System.out.println("FUZZY WORLD �ւ悤����, " + Const.Player.pc1Name);
					if (nameMap.containsKey(Const.Player.pc1Name)) {
						System.out.println(nameMap.get(Const.Player.pc1Name));
					}
					if ("qwerty".contains(Const.Player.pc1Name)) {
						System.out.println("���Ȃ��́E�E�E�Ȃ��Ȃ��K���Ȑl�̂悤�ł��ˁB");
					}
					if ("asdfgh".contains(Const.Player.pc1Name)) {
						System.out.println("���Ȃ��́E�E�E�Ȃ��Ȃ��K���Ȑl�̂悤�ł��ˁB");
					}
					if (Const.Player.pc1Name.contains("<") || Const.Player.pc1Name.contains(">")) {
						System.out.println("�t�t�t�E�E�E���̂悤�Ȗ��O�ɂ��Ă��A���̐��E�͉��܂���B���ʂł���B");
					}
					if (Const.Player.pc1Name.contains("\\") || Const.Player.pc1Name.contains("\\")) {
						System.out.println("�t�t�t�E�E�E���̂悤�Ȗ��O�ɂ��Ă��A���̐��E�͉��܂���B���ʂł���B");
					}
					System.out.println("���Ȃ��ɂ́E�E�E���̃��b�Z�[�W�������Ă���̂ł��ˁB");
					System.out.println("���Ȃ��̎g���͋��̉~�Ղɂ��������Đ��E�𐳂����ƁB");
					System.out.println("��X�ɐ��������E�������炵�Ă�������"
							+ "�B���̉~�Ղɂ��������āE�E�E");
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
