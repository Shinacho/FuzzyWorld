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
import kinugasa.game.system.BattleCharacter;
import kinugasa.game.system.BattleCommand;
import kinugasa.game.system.BattleConfig;
import kinugasa.game.system.BattleResult;
import kinugasa.game.system.BattleResultValues;
import kinugasa.game.system.BattleSystem;
import kinugasa.game.system.Enemy;
import kinugasa.game.system.GameSystem;
import kinugasa.game.system.SpeedCalcModelStorage;
import kinugasa.game.system.Status;
import kinugasa.game.ui.Dialog;
import kinugasa.game.ui.DialogIcon;
import kinugasa.game.ui.DialogOption;
import kinugasa.object.BasicSprite;
import kinugasa.object.FourDirection;
import kinugasa.object.KVector;
import kinugasa.resource.sound.Sound;
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

		if (!loaded) {
			SpeedCalcModelStorage.getInstance().setCurrent("SPD_50%RANDOM");
			Enemy.setProgressBarKey("HP");
			BattleConfig.StatusKey.hp = "HP";
			BattleConfig.StatusKey.move = "MOV";
			BattleConfig.StatusKey.exp = "EXP";
			BattleConfig.StatusKey.str = "STR";

			BattleConfig.ActionName.avoidance = "���";
			BattleConfig.ActionName.escape = "����";
			BattleConfig.ActionName.defence = "�h��";
			BattleConfig.ActionName.move = "�ړ�";
			BattleConfig.ActionName.commit = "�m��";
			BattleConfig.ActionName.status = "���";

			BattleConfig.addUntargetConditionNames("DEAD");
			BattleConfig.addUntargetConditionNames("DESTROY");
			BattleConfig.addUntargetConditionNames("ESCAPED");
			BattleConfig.setMagicVisibleStatusKey(Arrays.asList("MP", "SAN"));
			BattleConfig.setVisibleStatus(Arrays.asList("HP", "MP", "SAN"));
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
					return BattleResult.WIN;
				}
				return BattleResult.NOT_YET;
			});
			choiceSound1 = SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��1.wav").load();
			choiceSound2 = SoundStorage.getInstance().get("SE").get("���ʉ��Q�I��2.wav").load();
			playerOpeStart = SoundStorage.getInstance().get("SE").get("���ʉ��Q�o�g���^�[���J�n.wav").load();
			loaded = true;
		}
		playerMoveInitialLocation = null;
	}

	@Override
	public void dispose() {
	}
	private boolean loaded = false;
	private BattleSystem battleSystem;
	private BattleCommand cmd;
	private Point2D.Float playerMoveInitialLocation;
	private Sound choiceSound1, choiceSound2, playerOpeStart;

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

		//--------------------------------------------------------------------------
		battleSystem.update();
		if (battleSystem.waitAction()) {
			return;
		}
		//�틵�}
		if (is.isPressed(GamePadButton.LB, Keys.SHIFT, InputType.SINGLE)) {
			battleSystem.switchShowMode();
		}
		//���[�h�ʏ���
		switch (battleSystem.getStage()) {
			case STARTUP:
			case INITIAL_MOVING:
				//�����Ȃ�
				break;
			case WAITING_EXEC_CMD:
				cmd = battleSystem.execCmd();//EXEC_ACTION��CMD�QSELECT�ɓ���
				if (cmd.isUserOperation()) {
					playerOpeStart.stopAndPlay();
				}
				break;
			case CMD_SELECT:
				//CMDW����R�}���h��I��
				if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
					choiceSound2.stopAndPlay();
					battleSystem.prevCmdType();
				} else if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
					choiceSound2.stopAndPlay();
					battleSystem.nextCmdType();
				}
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.prevCmdSelect();
				} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.nextCmdSelect();
				}
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.commitCmd();
					return;
				}
				break;
			case ESCAPING:
				//�����Ȃ��i�ړ��������ɃX�e�[�W�X�V�����
				break;
			case ITEM_CHOICE_USE:
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.prevItemChoiceUseWindowSelect();
				} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.nextItemChoiceUseWindowSelect();
				}
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.commitItemChoiceUse();
				}
				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.cancelItemChoice();
				}
				break;
			case SHOW_STATUS:
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.prevStatusWindowSelect();
				} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.nextStatusWindowSelect();
				}
				if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.SINGLE)) {
					choiceSound2.stopAndPlay();
					battleSystem.prevStatusWindowChara();
				} else if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.SINGLE)) {
					choiceSound2.stopAndPlay();
					battleSystem.nextStatusWindowChara();
				}
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.nextStatusWindowPage();
					return;
				}
				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.cancelStatusDesc();
					return;
				}
				break;
			case SHOW_ITEM_DESC:
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)
						|| is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.cancelItemDescShow();
					return;
				}
				break;
			case TARGET_SELECT:
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.prevTargetSelect();
				} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.nextTargetSelect();
				}
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.commitTargetSelect();
					return;
				}
				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.cancelTargetSelect();
					return;
				}
				break;
			case PLAYER_MOVE:
				if (playerMoveInitialLocation == null) {
					playerMoveInitialLocation = battleSystem.getCurrentCmd().getSpriteCenter();
				}
				BattleCharacter playerChara = battleSystem.getCurrentCmd().getUser();
				//�ړ���U���̔���
				int remMovPoint = (int) (playerChara.getStatus().getEffectedStatus().get("MOV").getValue()
						- Math.abs(playerMoveInitialLocation.distance(battleSystem.getCurrentCmd().getSpriteCenter())));
				//�c�|�C���g���ő�l�̔����ȉ��̏ꍇ�͍U���ł��Ȃ�
				battleSystem.setMoveAction(remMovPoint > playerChara.getStatus().getEffectedStatus().get("MOV").getValue() / 2,
						remMovPoint);

				//�m��A�L�����Z���̏���
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					playerMoveInitialLocation = null;
					battleSystem.commitPCsMove();
					return;
				}
				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					playerMoveInitialLocation = null;
					battleSystem.cancelPCsMove();
					return;
				}
				KVector v = new KVector();
				if (is.getGamePadState() != null) {
					v = new KVector(is.getGamePadState().sticks.LEFT.getLocation(VehicleStorage.getInstance().get("WALK").getSpeed()));
				}
				if (is.isPressed(GamePadButton.POV_LEFT, Keys.LEFT, InputType.CONTINUE) || is.isPressed(Keys.A, InputType.CONTINUE)) {
					v.setAngle(FourDirection.WEST);
					v.setSpeed(VehicleStorage.getInstance().getCurrentVehicle().getSpeed());
				} else if (is.isPressed(GamePadButton.POV_RIGHT, Keys.RIGHT, InputType.CONTINUE) || is.isPressed(Keys.D, InputType.CONTINUE)) {
					v.setAngle(FourDirection.EAST);
					v.setSpeed(VehicleStorage.getInstance().getCurrentVehicle().getSpeed());
				}
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.CONTINUE) || is.isPressed(Keys.W, InputType.CONTINUE)) {
					v.setAngle(FourDirection.NORTH);
					v.setSpeed(VehicleStorage.getInstance().getCurrentVehicle().getSpeed());
				} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.CONTINUE) || is.isPressed(Keys.S, InputType.CONTINUE)) {
					v.setAngle(FourDirection.SOUTH);
					v.setSpeed(VehicleStorage.getInstance().getCurrentVehicle().getSpeed());
				}
				//�ړ��ʔ���E�E�E0�̏ꍇ�ړ����s���Ȃ�
				if (v.getSpeed() == 0) {
					return;
				}
				Point2D.Float nextFrameLocation = playerChara.getSprite().simulateMoveCenterLocation(v);
				//�̈攻��
				if (!battleSystem.getBattleFieldSystem().getBattleFieldAllArea().contains(nextFrameLocation)) {
					return;
				}
				//��Q������
				if (battleSystem.getBattleFieldSystem().hitObstacle(nextFrameLocation)) {
					return;
				}
				//��������
				if (playerChara.getStatus().getEffectedStatus().get("MOV").getValue() <= playerMoveInitialLocation.distance(nextFrameLocation)) {
					return;
				}
				//�ړ����s
				playerChara.getSprite().setVector(v);
				playerChara.getSprite().move();
				playerChara.to(playerChara.getSprite().getVector().round());

				break;
			case AFTER_MOVE_CMD_SELECT:
				//�ړ���R�}���h�I��
				if (is.isPressed(GamePadButton.POV_UP, Keys.UP, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.prevCmdSelect();
				} else if (is.isPressed(GamePadButton.POV_DOWN, Keys.DOWN, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.nextCmdSelect();
				}
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.commitCmd();
					return;
				}
				break;
			case EXECUTING_ACTION:
			case EXECUTING_MOVE:
				//�����Ȃ��i�X�e�[�W�������X�V�����܂ő҂j
				break;
			case BATLE_END:
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					BattleResultValues result = GameSystem.getInstance().battleEnd();
					{
						//TODO:�h���b�v�A�C�e���A�o���l�̕��z��������
						
					}
					gls.changeTo(Const.LogicName.FIELD);
					return;
				}
				//TODO:�o�g���G���h����
				break;
			default:
				throw new AssertionError("undefined stage:" + battleSystem.getStage());
		}

	}

	@Override
	public void draw(GraphicsContext g
	) {
		battleSystem.draw(g);
	}

}
