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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kinugasa.game.GameOption;
import kinugasa.game.GraphicsContext;
import kinugasa.game.I18N;
import kinugasa.game.NoLoopCall;
import kinugasa.game.ui.FontModel;
import kinugasa.game.ui.SimpleTextLabelModel;
import kinugasa.game.ui.TextLabelSprite;
import kinugasa.object.Drawable;

/**
 *
 * @vesion 1.0.0 - 2023/05/31_10:04:43<br>
 * @author Shinacho<br>
 */
public class OperationInfo implements Drawable {

	public enum AvalableInput {
		決定("(A)", "[ENTER]"),
		戻る("(B)", "[BACK_SPACE]"),
		キャンセル("(B)", "[BACK_SPACE]"),
		メニュー("(X)", "[M]"),
		開く("(X)", "[M]"),
		移動４方向("(↑↓←→)", "[↑↓←→]"),
		移動上下("(↑↓)", "[↑↓]"),
		戦況図("[LB]", "[SHIFT]"),
		撮影("(BACK)", "[F12]"),;
		private String gpButton, kbButton;

		private AvalableInput(String gpButton, String kbButton) {
			this.gpButton = gpButton;
			this.kbButton = kbButton;
		}

		public String getGpButton() {
			return gpButton;
		}

		public String getKbButton() {
			return kbButton;
		}

	}

	public enum Mode {
		GAME_PAD,
		KEYBOAR,
	}
	private static final OperationInfo INSTANCE = new OperationInfo();

	public static OperationInfo getInstance() {
		return INSTANCE;
	}

	private OperationInfo() {
		label = new TextLabelSprite("", new SimpleTextLabelModel(FontModel.DEFAULT.clone().setFontSize(12)),
				8,
				456,
				0, 0);
	}

	private List<AvalableInput> list = new ArrayList<>();
	private Mode mode;
	private TextLabelSprite label;

	public void set(Mode m) {
		mode = m;
		update();
	}

	public void set(AvalableInput... i) {
		list.clear();
		list.addAll(Arrays.asList(i));
		Collections.sort(list);
		//表示文言更新
		update();
	}

	@NoLoopCall
	private void update() {
		List<String> text = new ArrayList();
		for (var v : list) {
			String s = "";
			if (mode == Mode.GAME_PAD) {
				s += v.getGpButton();
			} else {
				s += v.getKbButton();
			}
			s += ":" + I18N.get(v.toString());
			text.add(s);
		}
		label.setText(String.join("／", text));
	}

	@Override
	public void draw(GraphicsContext g) {
		label.draw(g);
	}

}
