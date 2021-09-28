package com.video.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class VideoPlayerWindow extends JFrame{
	private JButton bcontinue;
	private JLabel display;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JButton pause;
	private JButton start;
	private JButton stop;
	  
	private PlayVideoTimer videoTimer;
    private Object source;

    public VideoPlayerWindow(int videoId, int userId) {
        initComponents();
        videoTimer = new PlayVideoTimer(this, videoId, userId);
    }

 
    private void initComponents() {

        jPanel1 = new JPanel();
        start = new JButton();
        pause = new JButton();
        bcontinue = new JButton();
        stop = new JButton();
     
        jPanel2 = new JPanel();
        display = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Video Player");
        setBackground(new Color(255, 255, 255));

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setBorder(BorderFactory.createEtchedBorder(new Color(0, 0, 0), null));
        jPanel1.setPreferredSize(new Dimension(300, 45));

        start.setText("Play");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                startActionPerformed(event);
            }
        });
        jPanel1.add(start);

        pause.setText("Pause");
        pause.setEnabled(false);
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                pauseActionPerformed(event);
            }
        });
        jPanel1.add(pause);

        bcontinue.setText("Continue");
        bcontinue.setEnabled(false);
        bcontinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                bcontinueActionPerformed(event);
            }
        });
        jPanel1.add(bcontinue);

        stop.setText("Stop");
        stop.setEnabled(false);
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                stopActionPerformed(event);
            }
        });
        jPanel1.add(stop);

        getContentPane().add(jPanel1, BorderLayout.PAGE_END);

        jPanel2.setBackground(new Color(255, 255, 255));
        jPanel2.setPreferredSize(new Dimension(400, 130));
        jPanel2.setLayout(new BorderLayout());

        display.setFont(new Font("Times New Roman", 0, 50)); 
        display.setHorizontalAlignment(SwingConstants.CENTER);
        display.setText("0 : 0 : 0");
        jPanel2.add(display, BorderLayout.CENTER);

        getContentPane().add(jPanel2, BorderLayout.CENTER);

        pack();
    }

    public JLabel getDisplay() {
        return display;
    }
    
    public void setDisplay(JLabel display) {
        this.display = display;
    }
    
    public PlayVideoTimer getVideoTimer() {
    	return videoTimer;
    }

    private void startActionPerformed(ActionEvent event) {
        source = event.getSource();
        if (source == start) {
            videoTimer.createThread();
            videoTimer.setLive(true);
            videoTimer.setGo(true);
            start.setEnabled(false);
            pause.setEnabled(true);
            stop.setEnabled(true);
        }
    }

    private void pauseActionPerformed(ActionEvent event) {
        pause.setEnabled(false);
        bcontinue.setEnabled(true);
        videoTimer.pauseThread();
    }

    private void bcontinueActionPerformed(ActionEvent event) {
        pause.setEnabled(true);
        videoTimer.continueThread();
        bcontinue.setEnabled(false);
    }

    private void stopActionPerformed(ActionEvent event) {
        start.setEnabled(true);
        stop.setEnabled(false);
        pause.setEnabled(false);
        videoTimer.setLive(false);
        videoTimer.setGo(false);
        videoTimer.setSeconds(0);
    }

}

