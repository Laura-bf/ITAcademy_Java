package rockets.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import rockets.domain.*;

public class ControlPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	Rocket rocket;
	JButton speedUp = new JButton("ACELERAR");
	JButton speedDown = new JButton("FRENAR");
	JButton startButton = new JButton("START");
	boolean isON = false;
	
	public ControlPanel(Rocket rocket) {
		this.rocket = rocket;
		setLayout(null);
		setVisible(true);
		setSize(550,150);
		setBackground(Color.RED);
		
		startButton.setBounds(30,50,80,50);
		startButton.setBackground(Color.ORANGE);
		this.add(startButton);
		
		speedUp.setBounds(150,25,125,100);
		speedUp.setBackground(Color.GREEN);
		if(!isON)
			speedUp.setEnabled(false);
		this.add(speedUp);
		
		speedDown.setBounds(350,25,125,100);
		speedDown.setBackground(Color.RED);
		speedDown.setEnabled(false);
		this.add(speedDown);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonActions(e);
			}
		});
		
		speedUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				speedDown.setEnabled(true);
				buttonActions(e);
			}
		});
		
		speedDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonActions(e);
			}
		});
		
	}
	//un mismo actionevent e (click raton) hara distintas cosas según venga de button acelerar o button frenar (getsource())
	protected void buttonActions(ActionEvent e) {
		if(e.getSource().equals(startButton)) {
			if(!isON) {
				rocket.rocketON();
				isON = true;
				startButton.setText("STOP");
//				JOptionPane.showMessageDialog(startButton,"Cohete "+rocket.getCode()+ " ACTIVADO!!",null,2,null);
				speedUp.setEnabled(true);
			} else {
				rocket.rocketOFF();
				isON = false;
				startButton.setText("START");
//				JOptionPane.showMessageDialog(startButton,"Cohete "+rocket.getCode()+ " APAGADO!!",null,2,null);
				speedUp.setEnabled(false);
				speedDown.setEnabled(false);
			}
		}
		
		if(e.getSource().equals(speedUp)) {
			rocket.powerUp();
		} else {
			rocket.powerDown();
		}
		
	}

}
