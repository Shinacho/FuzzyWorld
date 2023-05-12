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

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import kinugasa.game.input.Keys;
import kinugasa.graphics.ImageUtil;
import kinugasa.resource.sound.CachedSound;
import kinugasa.resource.sound.SoundBuilder;
import kinugasa.resource.sound.SoundLoader;
import kinugasa.resource.sound.SoundStorage;
import kinugasa.resource.text.CSVFile;
import kinugasa.resource.text.FileFormatException;

/**
 *
 * @author Shinacho
 */
public class SoundVolumeForm extends javax.swing.JFrame {

	/**
	 * Creates new form SoundVolumeForm
	 */
	private SoundVolumeForm() {
		initComponents();
		init();
	}
	private static final SoundVolumeForm INSTANCE = new SoundVolumeForm();

	public static SoundVolumeForm getInstance() {
		return INSTANCE;
	}

	private void init() {
		setIconImage(ImageUtil.load("resource/data/image/icon.png"));
		CSVFile file = new CSVFile("resource/data/volume.txt");
		file.load();
		List<String[]> data = file.getData();
		if (data.size() != 2) {
			throw new FileFormatException("volume.txt data is wrong");
		}
		for (String[] l : data) {
			if (l.length != 2) {
				throw new FileFormatException("volume.txt data is wrong");
			}
			float val = Float.parseFloat(l[1]);
			int intVal = (int) (val * 10);
			switch (l[0]) {
				case "BGM":
					jSlider1.setValue(intVal);
					jLabel4.setText((val * 100 + "").split("[.]")[0] + "%");
					mulBgm = val;
					break;
				case "SE":
					jSlider2.setValue(intVal);
					jLabel5.setText((val * 100 + "").split("[.]")[0] + "%");
					mulSe = val;
					break;
				default:
					throw new FileFormatException("volume.txt data is wrong");
			}
		}

		file.dispose();
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		center.x -= getWidth() / 2;
		center.y -= getHeight() / 2;
		setLocation(center);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fuzzy World");
        setResizable(false);

        jSlider1.setMaximum(20);
        jSlider1.setValue(10);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jSlider1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSlider1KeyPressed(evt);
            }
        });

        jLabel1.setText("Sound Volume");

        jLabel2.setText("BGM");

        jLabel3.setText("SE");

        jSlider2.setMaximum(20);
        jSlider2.setValue(10);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });
        jSlider2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSlider2KeyPressed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jLabel4.setText("100%");

        jLabel5.setText("100%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                    .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
		mulSe = (float) (jSlider2.getValue() * 0.1);
		jLabel5.setText((mulSe * 100 + "").split("[.]")[0] + "%");
    }//GEN-LAST:event_jSlider2StateChanged

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
		mulBgm = (float) (jSlider1.getValue() * 0.1);
		jLabel4.setText((mulBgm * 100 + "").split("[.]")[0] + "%");
    }//GEN-LAST:event_jSlider1StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		//テキストファイルの更新
		CSVFile file = new CSVFile("resource/data/volume.txt");
		List<String[]> data = file.getData();
		data.clear();
		data.add(new String[]{"BGM", mulBgm + ""});
		data.add(new String[]{"SE", mulSe + ""});
		file.save();
		file.dispose();
		//マップへの反映
		SoundStorage.getInstance().get("BGM").stopAll();
		SoundStorage.getInstance().get("SE").stopAll();
		SoundStorage.getInstance().get("BGM").dispose();
		SoundStorage.getInstance().get("SE").dispose();
		SoundStorage.getInstance().get("BGM").clear();
		SoundStorage.getInstance().get("SE").clear();
		SoundStorage.getInstance().clear();

		SoundLoader.loadList("resource/bgm/BGM.csv", mulBgm);
		SoundLoader.loadList("resource/se/SE.csv", mulSe);

		setVisible(false);
		SoundStorage.getInstance().get("SE").get("効果音＿選択1.wav").load().stopAndPlay();
		SoundStorage.getInstance().get("BGM").get("フィールド３.wav").load().stopAndPlay();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
		if (evt.getKeyCode() == Keys.ENTER.getKeyCode()) {
			jButton1.doClick();
		}
		if(evt.getKeyCode() == Keys.UP.getKeyCode()){
			jSlider2.grabFocus();
			return;
		}
		if(evt.getKeyCode() == Keys.DOWN.getKeyCode()){
			jSlider1.grabFocus();
			return;
		}
    }//GEN-LAST:event_jButton1KeyPressed

    private void jSlider2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider2KeyPressed
		if (evt.getKeyCode() == Keys.ENTER.getKeyCode()) {
			jButton1.doClick();
			return;
		}
		if(evt.getKeyCode() == Keys.UP.getKeyCode()){
			jSlider2.setValue(jSlider2.getValue() - 1);
			jSlider1.grabFocus();
			return;
		}
		if(evt.getKeyCode() == Keys.DOWN.getKeyCode()){
			jSlider2.setValue(jSlider2.getValue() + 1);
			jButton1.grabFocus();
			return;
		}
    }//GEN-LAST:event_jSlider2KeyPressed

    private void jSlider1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider1KeyPressed
		if (evt.getKeyCode() == Keys.ENTER.getKeyCode()) {
			jButton1.doClick();
			return;
		}
		if(evt.getKeyCode() == Keys.UP.getKeyCode()){
			jSlider1.setValue(jSlider1.getValue() - 1);
			jButton1.grabFocus();
			return;
		}
		if(evt.getKeyCode() == Keys.DOWN.getKeyCode()){
			jSlider1.setValue(jSlider1.getValue() + 1);
			jSlider2.grabFocus();
			return;
		}
    }//GEN-LAST:event_jSlider1KeyPressed

	private float mulBgm = 1.0f;
	private float mulSe = 1.0f;

	public float getMulSe() {
		return mulSe;
	}

	public float getMulBgm() {
		return mulBgm;
	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    // End of variables declaration//GEN-END:variables
}
