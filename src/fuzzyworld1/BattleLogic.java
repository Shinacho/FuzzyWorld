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
import java.util.List;
import kinugasa.game.GameLogic;
import kinugasa.game.GameManager;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.I18N;
import kinugasa.game.input.InputState;
import kinugasa.game.input.InputType;
import kinugasa.game.input.Keys;
import kinugasa.game.system.BattleCommand;
import kinugasa.game.system.BattleConfig;
import kinugasa.game.system.BattleResult;
import kinugasa.game.system.BattleResultValues;
import kinugasa.game.system.BattleSystem;
import kinugasa.game.system.GameSystem;
import kinugasa.game.system.Status;
import kinugasa.game.ui.Dialog;
import kinugasa.game.ui.DialogIcon;
import kinugasa.game.ui.DialogOption;

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
					return BattleResult.LOSE;
				}
				return BattleResult.NOT_YET;
			});
		}

		loaded = true;
	}

	@Override
	public void dispose() {
	}
	private boolean loaded = false;
	private BattleSystem battleSystem;
	private BattleCommand cmd;
	private Point2D.Float playerMoveInitialLocation;
	private int stage;

	@Override
	public void update(GameTimeManager gtm, InputState is) {
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

		switch (stage) {
			case 0:

				break;
			case 1:

			default:
				throw new AssertionError();
		}

	}

	@Override
	public void draw(GraphicsContext g) {
		battleSystem.draw(g);
	}

}
