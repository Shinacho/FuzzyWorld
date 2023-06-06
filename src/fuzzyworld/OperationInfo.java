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
