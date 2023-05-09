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
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.I18N;
import kinugasa.game.field4.VehicleStorage;
import kinugasa.game.input.GamePadButton;
import kinugasa.game.input.InputState;
import kinugasa.game.input.InputType;
import kinugasa.game.input.Keys;
import kinugasa.game.system.AfterMoveActionMessageWindow;
import kinugasa.game.system.BattleCharacter;
import kinugasa.game.system.BattleCommand;
import kinugasa.game.system.BattleCommandMessageWindow;
import kinugasa.game.system.BattleConfig;
import kinugasa.game.system.BattleResult;
import kinugasa.game.system.BattleResultValues;
import kinugasa.game.system.BattleSystem;
import kinugasa.game.system.BattleTargetSystem;
import kinugasa.game.system.Enemy;
import kinugasa.game.system.GameSystem;
import kinugasa.game.system.OperationResult;
import kinugasa.game.system.SpeedCalcModelStorage;
import kinugasa.game.system.Status;
import kinugasa.game.ui.Dialog;
import kinugasa.game.ui.DialogIcon;
import kinugasa.game.ui.DialogOption;
import kinugasa.object.KVector;
import kinugasa.resource.sound.SoundStorage;

/**
 *
 * @vesion 1.0.0 - 2023/05/07_12:06:58<br>
 * @author Shinacho<br>
 */
public class BattleLogic extends GameLogic {

	public BattleLogic(GameManager gm) {
		super(Const.LogicName.BATTLE, gm);
	}

	@Override
	public void load() {
		battleSystem = GameSystem.getInstance().getBattleSystem();
		stage = 0;

		if (!loaded) {
			SpeedCalcModelStorage.getInstance().setCurrent("SPD_20%");
			Enemy.setProgressBarKey("HP");
			BattleConfig.StatusKey.hp = "HP";
			BattleConfig.StatusKey.move = "MOV";
			BattleConfig.StatusKey.exp = "EXP";

			BattleConfig.ActionName.avoidance = "���";
			BattleConfig.ActionName.escape = "������";
			BattleConfig.ActionName.defence = "�h��";
			BattleConfig.ActionName.move = "�ړ�";
			BattleConfig.ActionName.commit = "�m��";
			BattleConfig.ActionName.status = "���";

			BattleConfig.addUntargetConditionNames("DEAD");
			BattleConfig.addUntargetConditionNames("DESTROY");
			BattleConfig.addUntargetConditionNames("ESCAPED");
			BattleConfig.setMagicVisibleStatusKey(Arrays.asList("MP", "SAN"));
			BattleConfig.setVisibleStatus(Arrays.asList("HP ", "MP ", "SAN"));
			BattleConfig.addWinLoseLogic((List<Status> party, List<Status> enemy) -> {
				// �p�[�e�B�̃R���f�B�V�������m�F
				boolean lose = true;
				for (Status s : party) {
					lose &= (s.hasCondition("DEAD") || s.hasCondition("DESTROY"));
				}
				if (lose) {
					return BattleResult.LOSE;
				}
				boolean win = true;
				for (Status s : enemy) {
					win &= (s.hasCondition("DEAD") || s.hasCondition("DESTROY"));
				}
				if (win) {
					return BattleResult.LOSE;
				}
				return BattleResult.NOT_YET;
			});
			loaded = true;
		}

	}

	@Override
	public void dispose() {
	}
	private boolean loaded = false;
	private BattleSystem battleSystem;
	private BattleCommand cmd;
	private Point2D.Float playerMoveInitialLocation;
	private int stage, prev;

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		//�e�X�g�p����---------------------------------------------------------------
		//�e�X�g�p�ً}�E�o���u
		if (GameSystem.isDebugMode()) {
			if (is.isPressed(Keys.ESCAPE, InputType.SINGLE)) {
				if (Dialog.yesOrNo("�m�F", DialogIcon.QUESTION, I18N.translate("BATTLE_CLOSE")) == DialogOption.YES) {
					battleSystem.setBattleResultValue(new BattleResultValues(BattleResult.WIN, 123, new ArrayList<>(), "FIELD"));
					BattleResultValues result = GameSystem.getInstance().battleEnd();
					gls.changeTo("FIELD");
				}
			}
		}
		//�`�[�g�R���\�[��
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

		//--------------------------------------------------------------------------
		battleSystem.update();

		//�o�g�������{��
		switch (stage) {
			case 0:
				//BS�̏�������������܂őҋ@
				if (battleSystem.waitAction()) {
					stage = 1;
				}
				break;
			case 1:
				//�R�}���h�I���i1�񂾂�
				cmd = battleSystem.execCmd();
				if (cmd.isUserOperation()) {
					//�R�}���h�E�C���h�E�\���A�R�}���h�I��
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�o�g���^�[���J�n.wav").load().stopAndPlay();
					stage = 2;
				} else {
					//NPC�A�N�V�������I���܂őҋ@
					stage = 0;
				}
				break;
			case 2:
				//�R�}���h�E�C���h�E�\���A�R�}���h�I��
				BattleCommandMessageWindow mw = battleSystem.getMessageWindowSystem().getCommandWindow();
				if (!mw.isVisible()) {
					mw.setVisible(true);
				}
				if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					mw.prevType();
				} else if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					mw.nextType();
				}
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					mw.prevAction();
				} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					mw.nextAction();
				}

				//�틵�}
				if (is.isPressed(GamePadButton.LB, Keys.SHIFT, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					battleSystem.getMessageWindowSystem().switchVisible();
				}

				//����
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					//�^�[�Q�b�g�I��
					OperationResult result = battleSystem.execPCAction();
					switch (result) {
						case MISS:
							//���̃R�}���h��
							stage = 0;
							break;
						case SUCCESS:
							//���̃R�}���h��
							stage = 0;
							break;
						case MOVE:
							playerMoveInitialLocation = cmd.getUser().getCenter();
							stage = 4;
							break;
						case CANCEL:
							//�������Ȃ��i�Ď��s�\
							break;
						case SHOW_STATUS:
							stage = 5;
							break;
						case TO_TARGET_SELECT:
							prev = 2;
							stage = 3;
							break;
					}
				}
				//�L�����Z���i�J�[�\����߂�
				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					mw.resetSelect();
				}
				break;
			case 3:
				//�^�[�Q�b�g�Z���N�g
				BattleTargetSystem targetSystem = battleSystem.getTargetSystem();

				if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					targetSystem.prev();
				} else if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					targetSystem.next();
				}
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					targetSystem.prev();
				} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					targetSystem.next();
				}

				//�틵�}
				if (is.isPressed(GamePadButton.LB, Keys.SHIFT, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					battleSystem.getMessageWindowSystem().switchVisible();
				}

				//����
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					OperationResult result = battleSystem.execPCAction();
					switch (result) {
						case CANCEL:
						case TO_TARGET_SELECT:
							//�čs���\
							break;
						case SHOW_STATUS:
						case MOVE:
							throw new AssertionError("�s���Ȗ߂�l : " + result);
						default:
							//�čs���s�\
							stage = 0;
							break;
					}
				}
				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					battleSystem.cancelTargetSelect();
					stage = prev;
					break;
				}
				break;
			case 4:
				//�ړ��t�F�[�Y
				AfterMoveActionMessageWindow mw2 = battleSystem.getMessageWindowSystem().getAfterMoveCommandWindow();

				BattleCharacter playerChara = cmd.getUser();

				//�틵�}
				if (is.isPressed(GamePadButton.LB, Keys.SHIFT, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					battleSystem.getMessageWindowSystem().switchVisible();
				}
				//�L�����Z��
				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					battleSystem.cancelPCsMove();
					stage = 2;
					break;
				}

				//����
				//�m��̏ꍇ�A���̃R�}���h�ցA�U���̏ꍇ�A�^�[�Q�b�g�I���Ɉړ�����
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					OperationResult res = battleSystem.execPCAction();
					if (res == OperationResult.TO_TARGET_SELECT) {
						prev = 4;
						stage = 3;
						break;
					}
					if (res == OperationResult.SUCCESS) {
						stage = 0;
						break;
					}
					if (res == OperationResult.MISS) {
						break;
					}
				}
				//�ړ���U���̔���
				int remMovPoint = (int) (playerChara.getStatus().getEffectedStatus().get("MOV").getValue()
						- playerMoveInitialLocation.distance(cmd.getUser().getSprite().getCenter()));
				//�c�|�C���g���ő�l�̔����ȉ��̏ꍇ�͍U���ł��Ȃ�
				battleSystem.setAftedMoveAction(remMovPoint > playerChara.getStatus().getEffectedStatus().get("MOV").getValue() / 2);

				//�R�}���h�I��
				if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					mw2.prevAction();
				} else if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					mw2.nextAction();
				}
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					mw2.prevAction();
				} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					mw2.nextAction();
				}

				//�V�~�����[�g���[�u�N�����Ď��t���[���̈ʒu�擾
				KVector v = new KVector(is.getGamePadState().sticks.LEFT.getLocation(VehicleStorage.getInstance().get("WALK").getSpeed()));
				if (v.getSpeed() <= 0) {
					break;
				}
				Point2D.Float nextFrameLocation = playerChara.getSprite().simulateMoveCenterLocation(v);
				//�̈攻��
				if (!battleSystem.getBattleFieldSystem().getBattleFieldAllArea().contains(nextFrameLocation)) {
					break;
				}
				//��Q������
				if (battleSystem.getBattleFieldSystem().hitObstacle(nextFrameLocation)) {
					break;
				}
				//��������
				if (playerChara.getStatus().getEffectedStatus().get("MOV").getValue() <= playerMoveInitialLocation.distance(nextFrameLocation)) {
					break;
				}
				//�ړ����s
				playerChara.getSprite().setVector(v);
				playerChara.getSprite().move();
				playerChara.to(playerChara.getSprite().getVector().round());

				break;
			case 5:
				//�X�e�[�^�X�Q��
				//����ȊO���ɂȂ��B

				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load().stopAndPlay();
					stage = 2;
					break;
				}

				break;
			default:
				throw new AssertionError("undefined Test2.BattleLogic s stage");
		}

	}

	@Override
	public void draw(GraphicsContext g) {
		battleSystem.draw(g);
	}

}
