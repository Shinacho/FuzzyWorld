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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kinugasa.game.GameManager;
import kinugasa.game.GameOption;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.LockUtil;
import kinugasa.game.input.InputState;

/**
 *
 * @vesion 1.0.0 - May 26, 2023_10:03:31 AM<br>
 * @author Shinacho<br>
 */
public class Test extends GameManager {

	public static void main(String[] args) {

		saiki(new File("D:/Project/"));

		if (1 == 1) {
			return;
		}
		LockUtil.deleteAllLockFile();
		new Test().gameStart();
	}
	final String newLicence
			= "/*\n"
			+ " * Copyright (C) 2023 Shinacho\n"
			+ " *\n"
			+ " * This program is free software: you can redistribute it and/or modify\n"
			+ " * it under the terms of the GNU General Public License as published by\n"
			+ " * the Free Software Foundation, either version 3 of the License, or\n"
			+ " * (at your option) any later version.\n"
			+ " *\n"
			+ " * This program is distributed in the hope that it will be useful,\n"
			+ " * but WITHOUT ANY WARRANTY; without even the implied warranty of\n"
			+ " * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"
			+ " * GNU General Public License for more details.\n"
			+ " *\n"
			+ " * You should have received a copy of the GNU General Public License\n"
			+ " * along with this program.  If not, see <http://www.gnu.org/licenses/>.\n"
			+ " */\n"
			+ "\n";

	static void saiki(File file) {
		File[] list = file.listFiles();
		for (File f : list) {
			if (f.isDirectory()) {
				saiki(f);
			}
			if (f.getName().endsWith(".java")
					|| f.getName().endsWith(".xml")) {
				System.out.println(f.getName());
				try {
					List<String> data = Files.readAllLines(f.toPath(), Charset.forName("UTF-8"));
					
					
					
					
					Files.write(f.toPath(), data, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
					
				} catch (IOException ex) {
					Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	private Test() {
		super(GameOption.defaultOption().setUseMouse(true));
	}

	@Override
	protected void startUp() {
	}

	@Override
	protected void dispose() {
	}

	@Override
	protected void update(GameTimeManager gtm, InputState is) {
	}

	@Override
	protected void draw(GraphicsContext gc) {
	}

}
