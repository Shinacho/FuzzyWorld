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

/**
 *
 * @vesion 1.0.0 - 2022/11/12_21:37:50<br>
 * @author Shinacho<br>
 */
public class Const {

	public static class LogicName {

		public static final String TITLE = "TITLE_LOGIC";
		public static final String OP = "OP";
		public static final String LOAD_GAME = "LOAD_GAME";
		public static final String MUSIC_ROOM = "MUSIC_ROOM";
		public static final String GAMEPAD_TEST = "GAMEPAD_TEST";
		public static final String CHAPTER_TITLE = "CHAPTER_TITLE";
		public static final String FIELD = "FIELD";
		public static final String BATTLE = "BATTLE";
		public static final String SS = "SS";
		public static final String GAME_OVER = "GAME_OVER";
		public static final String SAVE_DATA_SELECT = "SAVE_DATA_SELECT";
	}

	public static boolean LOADING = false;

	public static class Save {

		public static final int SAVE_DATA_NUM = 16;
		public static int dataNo;
	}

	public static class Screen {

		public static int WIDTH;
		public static int HEIGHT;
	}

	public static class Player {

		public static String pc1Name;
	}

	public static class Chapter {

		public static String currentI18NKey;
		public static String currentSubTitleI18NKey;
		public static String nextLogic;

	}

	public static class MenuIdx {

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
