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

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.List;
import javax.swing.ImageIcon;
import kinugasa.game.input.Keys;
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
		setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
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
		SoundStorage.volumeBgm = mulBgm;
		SoundStorage.volumeSe = mulSe;
		SoundStorage.getInstance().dispose();
		SoundStorage.getInstance().rebuild();

		setVisible(false);
		SoundStorage.getInstance().get("SD1008").load().stopAndPlay();
		if (kinugasa.util.Random.percent(0.5f)) {
			SoundStorage.getInstance().get("SD0026").load().play();
		} else {
			SoundStorage.getInstance().get("SD0027").load().play();
		}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
		if (evt.getKeyCode() == Keys.ENTER.getKeyCode()) {
			jButton1.doClick();
		}
		if (evt.getKeyCode() == Keys.UP.getKeyCode()) {
			jSlider2.grabFocus();
			return;
		}
		if (evt.getKeyCode() == Keys.DOWN.getKeyCode()) {
			jSlider1.grabFocus();
			return;
		}
		if (evt.getKeyCode() == Keys.BACK_SPACE.getKeyCode()) {
			setVisible(false);
			return;
		}
    }//GEN-LAST:event_jButton1KeyPressed

    private void jSlider2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider2KeyPressed
		if (evt.getKeyCode() == Keys.ENTER.getKeyCode()) {
			jButton1.doClick();
			return;
		}
		if (evt.getKeyCode() == Keys.UP.getKeyCode()) {
			jSlider2.setValue(jSlider2.getValue() - 1);
			jSlider1.grabFocus();
			return;
		}
		if (evt.getKeyCode() == Keys.DOWN.getKeyCode()) {
			jSlider2.setValue(jSlider2.getValue() + 1);
			jButton1.grabFocus();
			return;
		}
		if (evt.getKeyCode() == Keys.BACK_SPACE.getKeyCode()) {
			setVisible(false);
			return;
		}
    }//GEN-LAST:event_jSlider2KeyPressed

    private void jSlider1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider1KeyPressed
		if (evt.getKeyCode() == Keys.ENTER.getKeyCode()) {
			jButton1.doClick();
			return;
		}
		if (evt.getKeyCode() == Keys.UP.getKeyCode()) {
			jSlider1.setValue(jSlider1.getValue() - 1);
			jButton1.grabFocus();
			return;
		}
		if (evt.getKeyCode() == Keys.DOWN.getKeyCode()) {
			jSlider1.setValue(jSlider1.getValue() + 1);
			jSlider2.grabFocus();
			return;
		}
		if (evt.getKeyCode() == Keys.BACK_SPACE.getKeyCode()) {
			setVisible(false);
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
