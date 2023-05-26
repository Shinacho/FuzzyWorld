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

import java.util.List;
import kinugasa.resource.db.DBConnection;
import kinugasa.resource.db.DBValue;
import kinugasa.resource.db.KResultSet;

/**
 *
 * @vesion 1.0.0 - May 26, 2023_10:03:31 AM<br>
 * @author Shinacho<br>
 */
public class Test {

	public static void main(String[] args) {
		DBConnection.getInstance().init("file:./resource/data/data1", "sa", "adm");
		KResultSet rs = DBConnection.getInstance().execDirect("select * from test");
		for (List<DBValue> line : rs) {
			for (DBValue v : line) {
				System.out.print(v);
			}
			System.out.println("");
		}
	}
}
