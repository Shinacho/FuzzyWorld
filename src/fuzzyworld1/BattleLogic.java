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
import kinugasa.game.input.GamePadButton;
import kinugasa.game.input.GamePadButtons;
import kinugasa.game.input.InputState;
import kinugasa.game.input.InputType;
import kinugasa.game.input.Keys;
import kinugasa.game.input.MouseButtons;
import kinugasa.game.system.BattleCommand;
import kinugasa.game.system.BattleConfig;
import kinugasa.game.system.BattleResult;
import kinugasa.game.system.BattleResultValues;
import kinugasa.game.system.BattleSystem;
import kinugasa.game.system.Enemy;
import kinugasa.game.system.GameSystem;
import kinugasa.game.system.OperationResult;
import kinugasa.game.system.SpeedCalcModelStorage;
import kinugasa.game.system.Status;
import kinugasa.game.ui.Dialog;
import kinugasa.game.ui.DialogIcon;
import kinugasa.game.ui.DialogOption;
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
			SpeedCalcModelStorage.getInstance().setCurrent("SPD_20%");
			Enemy.setProgressBarKey("HP");
			BattleConfig.StatusKey.hp = "HP";
			BattleConfig.StatusKey.move = "MOV";
			BattleConfig.StatusKey.exp = "EXP";
			BattleConfig.StatusKey.str = "STR";

			BattleConfig.ActionName.avoidance = "回避";
			BattleConfig.ActionName.escape = "逃走";
			BattleConfig.ActionName.defence = "防御";
			BattleConfig.ActionName.move = "移動";
			BattleConfig.ActionName.commit = "確定";
			BattleConfig.ActionName.status = "状態";

			BattleConfig.addUntargetConditionNames("DEAD");
			BattleConfig.addUntargetConditionNames("DESTROY");
			BattleConfig.addUntargetConditionNames("ESCAPED");
			BattleConfig.setMagicVisibleStatusKey(Arrays.asList("MP", "SAN"));
			BattleConfig.setVisibleStatus(Arrays.asList("HP", "MP", "SAN"));
			BattleConfig.addWinLoseLogic((List<Status> party, List<Status> enemy) -> {
				// パーティのコンディションを確認
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
			choiceSound1 = SoundStorage.getInstance().get("SE").get("効果音＿選択1.wav").load();
			choiceSound2 = SoundStorage.getInstance().get("SE").get("効果音＿選択2.wav").load();
			playerOpeStart = SoundStorage.getInstance().get("SE").get("効果音＿バトルターン開始.wav").load();
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
	private Sound choiceSound1, choiceSound2, playerOpeStart;

	@Override
	public void update(GameTimeManager gtm, InputState is) {
		//テスト用処理---------------------------------------------------------------
		//テスト用緊急脱出装置
		if (GameSystem.isDebugMode()) {
			if (is.isPressed(Keys.ESCAPE, InputType.SINGLE)) {
				if (Dialog.yesOrNo("確認", DialogIcon.QUESTION, I18N.translate("BATTLE_CLOSE")) == DialogOption.YES) {
					battleSystem.setBattleResultValue(new BattleResultValues(BattleResult.WIN, 123, new ArrayList<>(), "FIELD"));
					BattleResultValues result = GameSystem.getInstance().battleEnd();
					gls.changeTo("FIELD");
				}
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

		//--------------------------------------------------------------------------
		battleSystem.update();
		if (battleSystem.waitAction()) {
			return;
		}
		//戦況図
		if (is.isPressed(GamePadButton.LB, Keys.SHIFT, InputType.SINGLE)) {
			battleSystem.switchShowMode();
		} 
		//モード別処理
		switch (battleSystem.getStage()) {
			case STARTUP:
			case INITIAL_MOVING:
				//処理なし
				break;
			case WAITING_EXEC_CMD:
				BattleCommand cmd = battleSystem.execCmd();//EXEC_ACTIONやCMD＿SELECTに入る
				if (cmd.isUserOperation()) {
					playerOpeStart.stopAndPlay();
				}
				break;
			case CMD_SELECT:
				//CMDWからコマンドを選択
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
					OperationResult rs = battleSystem.commitCmd();
				}
				break;
			case ESCAPING:
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
				break;
			case SHOW_ITEM_DESC:
				if (is.isPressed(GamePadButton.A, Keys.ENTER, InputType.SINGLE)
						|| is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.cancelItemDescShow();
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
				}
				if (is.isPressed(GamePadButton.B, Keys.BACK_SPACE, InputType.SINGLE)) {
					choiceSound1.stopAndPlay();
					battleSystem.cancelTargetSelect();
				}
				break;
			case PLAYER_MOVE:
				break;
			case EXECUTING_ACTION:
			case EXECUTING_MOVE:
				//処理なし（ステージが自動更新されるまで待つ）
				break;
			case BATLE_END:
				//TODO:バトルエンド処理
				break;
			default:
				throw new AssertionError("undefined stage:" + battleSystem.getStage());
		}

	}

	@Override
	public void draw(GraphicsContext g) {
		battleSystem.draw(g);
	}

}
