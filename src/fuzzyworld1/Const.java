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

/**
 *
 * @vesion 1.0.0 - 2022/11/12_21:37:50<br>
 * @author Dra211<br>
 */
public class Const {

	public static class LogicName {

		public static final String TITLE_LOGIC = "TITLE_LOGIC";
		public static final String OP = "OP";
		public static final String LOAD_GAME = "LOAD_GAME";
		public static final String MUSIC_ROOM = "MUSIC_ROOM";
		public static final String GAMEPAD_TEST = "GAMEPAD_TEST";
		public static final String E1 = "E1";
		public static final String FIELDMAP = "FIELDMAP";
		public static final String CHAPTER_TITLE = "CHAPTER_TITLE";
		public static final String FIELD = "CHAPTER_1";
	}

	public static class Screen {

		public static int WIDTH = 1440;
		public static int HEIGHT = 960;
	}

	public static class Input {

		public static boolean gamepad;
	}

	public static class Player {

		public static String pc1Name;
	}

	public static class Chapter {

		public static String current;
		public static String currentSubTitle;
		public static String nextLogic;

	}
	
	public static class MenuIdx{
		public static final int STATUS = 0;
		public static final int ORDER = 1;
		public static final int ITEM = 2;
		public static final int MAGIC = 3;
		public static final int BOOK = 4;
		public static final int MATERIAL = 5;
		public static final int INFO = 6;
		public static final int MAP = 7;
		
	}

}
