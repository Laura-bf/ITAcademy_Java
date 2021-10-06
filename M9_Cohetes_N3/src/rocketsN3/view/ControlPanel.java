package rocketsN3.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import rocketsN3.domain.*;

public class ControlPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	Rocket rocket;
	JButton speedUp = new JButton("ACELERAR");
	JButton speedDown = new JButton("FRENAR");
	JButton startButton = new JButton("START");
	JList<String> gearBox;
	int gear = 1;
	boolean isON = false;
	
	public ControlPanel(Rocket rocket) {
		this.rocket = rocket;
		setLayout(null);
		setVisible(true);
		setSize(550,150);
		setBackground(Color.LIGHT_GRAY);
		
		startButton.setBounds(30,50,80,50);
		startButton.setBackground(Color.ORANGE);
		this.add(startButton);
		
		speedUp.setBounds(150,25,125,100);
		speedUp.setBackground(Color.GREEN);
		speedUp.setEnabled(false);
		this.add(speedUp);
		
		speedDown.setBounds(385,25,125,100);
		speedDown.setBackground(Color.RED);
		speedDown.setEnabled(false);
		this.add(speedDown);
		
		String[] gears = {"x1", "x2", "x3", "x4", "x5"};
		gearBox = new JList<String>(gears);
		gearBox.setBounds(305, 25, 50, 100);
		gearBox.setBackground(Color.LIGHT_GRAY);
		gearBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gearBox.setSelectedIndex(0);
		gearBox.setVisible(true);
		gearBox.setEnabled(false);
		this.add(gearBox);
		
		//se ponen los botones y la lista de selección a la escucha
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
		
		gearBox.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList<?> source = (JList<?>) e.getSource();
				gear = source.getSelectedIndex() + 1;
			}
		});
		
	}
	//un mismo actionevent e (click raton) hara distintas cosas según venga de button acelerar o button frenar (getsource())
	protected void buttonActions(ActionEvent e) {
		if(e.getSource().equals(startButton)) {
			if(!isON) {
				rocket.rocketON();
				isON = true;
				startButton.setText("PAUSE");
				speedUp.setEnabled(true);
				speedDown.setEnabled(true);
				gearBox.setEnabled(true);
			} else {
				rocket.rocketOFF();
				isON = false;
				startButton.setText("START");
				speedUp.setEnabled(false);
				speedDown.setEnabled(false);
				gearBox.setEnabled(false);
			}
		} else if(e.getSource().equals(speedUp)) {
				rocket.powerUp(gear);
		} else {
			rocket.powerDown(gear);
		}
		
	}

}
