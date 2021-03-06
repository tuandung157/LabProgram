/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogram;

import ProgramLab.Police;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author tuandung
 */
public class ClientGUI extends javax.swing.JFrame {

    /**
     * Creates new form ClientGUI
     */
    private Client client;
    private ArrayList<Circle> circles = null;
    private List<Thread> circles_animation = null;
    private static final String[] langString = {"Russian", "Serbian", "Hungarian", "English(Canada)"};
    private static final String[][] langData = {
        {"Атака", "Скорость", "Язык", "Имя", "Старт", "Стоп"}, //rus
        {"Аттацк", "Спеед", "Лангуаге", "Наме", "Старт", "Стоп"}, //serb
        {"Támadás", "Sebesség", "Nyelv", "Név", "Rajt", "Állj meg"}, //hung
        {"Attack", "Speed", "Language", "Name", "Start", "Stop"} //eng
    };
    private LocalDateTime time = LocalDateTime.now(); 
    public ClientGUI() {
        
        try {
            initComponents();
            this.setSize(960, 600);
//            this.setBounds(200, 400, 1280, 960);
            this.setTitle("Client");
            client = new Client();
            client.connect();
            circles_animation = new ArrayList<>();
            
            
            //comboBoxLang
            comboBoxLang.removeAllItems();
            for (int i = 0; i < langString.length; i++) {
                comboBoxLang.addItem(langString[i]);
            }
            //timeCurrent
        
           
            Container contentPane = this.getContentPane();
            contentPane.setLayout(new BorderLayout());
            
            JStatusBar statusBar = new JStatusBar();
            JLabel leftLabel = new JLabel("Your application is running.");
            statusBar.setLeftComponent(leftLabel);
            
            final JLabel dateLabel = new JLabel();
            dateLabel.setHorizontalAlignment(JLabel.CENTER);
            statusBar.addRightComponent(dateLabel);
            
            final JLabel timeLabel = new JLabel();
            timeLabel.setHorizontalAlignment(JLabel.CENTER);
            statusBar.addRightComponent(timeLabel);
            
            contentPane.add(statusBar, BorderLayout.SOUTH);
            
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
            TimerThread timerThread = new TimerThread(dateLabel, timeLabel);
            timerThread.start();
            
            
            
            comboBoxLang.setSelectedIndex(0);
            Timer t = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cleanUpAnimation();
                    if (circles_animation.size() > 0) {
                        return;
                    }
                    try {
                        List<Police> data = (List<Police>) client.request("print");
                        // System.out.println(client);
                        synchronized (data) {
                            circles = new ArrayList<Circle>();
                            comboBoxName.removeAllItems();
                            for (Police p : data) {
                                Circle c = new Circle(ClientGUI.this, p);
                                ClientGUI.this.addMouseMotionListener(c);
                                circles.add(c);
                                comboBoxName.addItem(p.getName());
                                
                            }
                        }
                        ClientGUI.this.repaint();
                    } catch (IOException ex) {
                        Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            t.setInitialDelay(0);
            t.start();
        } catch (IOException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        if (circles != null) {
            //draw circles
            for (Circle c : circles) {
                c.draw(g);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        bntStart = new javax.swing.JButton();
        bntStop = new javax.swing.JButton();
        comboBoxName = new javax.swing.JComboBox<>();
        sliderSpeed = new javax.swing.JSlider();
        labelName = new javax.swing.JLabel();
        labelSpeed = new javax.swing.JLabel();
        labelAtk = new javax.swing.JLabel();
        sliderAttack = new javax.swing.JSlider();
        labelLang = new javax.swing.JLabel();
        comboBoxLang = new javax.swing.JComboBox<>();
        currentTime = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(153, 153, 255));

        bntStart.setText("Start");
        bntStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntStartActionPerformed(evt);
            }
        });

        bntStop.setText("Stop");
        bntStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntStopActionPerformed(evt);
            }
        });

        comboBoxName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxNameActionPerformed(evt);
            }
        });

        sliderSpeed.setMaximum(10);

        labelName.setText("Name");

        labelSpeed.setText("Speed");

        labelAtk.setText("Attack");

        sliderAttack.setMaximum(10);

        labelLang.setText("Language");

        comboBoxLang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxLang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxLangActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(bntStart, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(bntStop, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(comboBoxName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(sliderSpeed, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(labelName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(labelSpeed, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(labelAtk, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(sliderAttack, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(labelLang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(comboBoxLang, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(bntStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntStop)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBoxName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(comboBoxLang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sliderSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSpeed, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAtk, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliderAttack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLang, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelName)
                .addGap(10, 10, 10)
                .addComponent(comboBoxName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSpeed)
                .addGap(18, 18, 18)
                .addComponent(sliderSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelAtk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelLang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxLang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntStart, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(bntStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        currentTime.setText("Time");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(704, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDesktopPane1)
                    .addComponent(currentTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(currentTime, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cleanUpAnimation() {
        circles_animation.removeIf(new Predicate<Thread>() {
            @Override
            public boolean test(Thread t) {
                // System.out.println(!t.isAlive());
                return !t.isAlive();
            }
        });

    }

    private void bntStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntStartActionPerformed
        // TODO add your handling code here:
        cleanUpAnimation();
        if (circles_animation.size() > 0) {

            for (Thread ca : circles_animation) {
                ca.resume();
            }
        } else {
            for (Circle c : circles) {
                if (c.data.getAtk() <= sliderAttack.getValue() && c.data.getSpeed() <= sliderSpeed.getValue()) {
                    Thread ca = new Thread(c);
                    circles_animation.add(ca);
                    ca.start();
                }
            }
        }

    }//GEN-LAST:event_bntStartActionPerformed

    private void bntStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntStopActionPerformed
        for (Thread ca : circles_animation) {
            if (ca.isAlive()) {
                ca.suspend();
            } else {
                circles_animation.remove(ca);
            }
        }

    }//GEN-LAST:event_bntStopActionPerformed

    private void comboBoxNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxNameActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboBoxNameActionPerformed

    private void comboBoxLangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxLangActionPerformed
        // TODO add your handling code here:
        int lang = comboBoxLang.getSelectedIndex();
        if (lang != -1) {
            labelAtk.setText(langData[lang][0]);
            labelSpeed.setText(langData[lang][1]);
            labelLang.setText(langData[lang][2]);
            labelName.setText(langData[lang][3]);
            bntStart.setText(langData[lang][4]);
            bntStop.setText(langData[lang][5]);
            
        }
    }//GEN-LAST:event_comboBoxLangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientGUI().setVisible(true);
                
            }
        });
    }
    public class TimerThread extends Thread {

        protected boolean isRunning;

        protected JLabel dateLabel;
        protected JLabel timeLabel;

        protected SimpleDateFormat dateFormat = 
                new SimpleDateFormat("EEE, d MMM yyyy");
        protected SimpleDateFormat timeFormat =
                new SimpleDateFormat("h:mm a");

        public TimerThread(JLabel dateLabel, JLabel timeLabel) {
            this.dateLabel = dateLabel;
            this.timeLabel = timeLabel;
            this.isRunning = true;
        }

        @Override
        public void run() {
            while (isRunning) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Calendar currentCalendar = Calendar.getInstance();
                        Date currentTime = currentCalendar.getTime();
                        dateLabel.setText(dateFormat.format(currentTime));
                        timeLabel.setText(timeFormat.format(currentTime));
                        
                    }
                });

                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                }
            }
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

    }

        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntStart;
    private javax.swing.JButton bntStop;
    private javax.swing.JComboBox<String> comboBoxLang;
    private javax.swing.JComboBox<String> comboBoxName;
    private javax.swing.JLabel currentTime;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel labelAtk;
    private javax.swing.JLabel labelLang;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelSpeed;
    private javax.swing.JSlider sliderAttack;
    private javax.swing.JSlider sliderSpeed;
    // End of variables declaration//GEN-END:variables

    
}