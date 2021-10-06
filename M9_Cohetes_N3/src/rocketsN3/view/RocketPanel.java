package rocketsN3.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import rocketsN3.domain.*;

public class RocketPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Rocket rocket;
	private JLabel currentPowerLabel;
	private JLabel speedLabel;
	
	private class UpdateSpeed implements Runnable{
		@Override
		public void run() {
			try {
				while(true) {
					Thread.sleep(1000);
					speedLabel.setText(rocket.calculateCurrentSpeed()+"");
				}
			}catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public RocketPanel(Rocket rocket) {
		this.rocket = rocket;
		this.speedLabel = new JLabel();
		Runnable r = new UpdateSpeed();
		Thread speedLabelThread = new Thread(r);
		speedLabelThread.start();
		
		setLayout(null);
		setVisible(true);
		setSize(550,550);
		setBackground(Color.LIGHT_GRAY);
		
		//etiqueta nombre cohete
		JLabel rocketLabel = new JLabel("ROCKET " + rocket.getCode(), SwingConstants.CENTER);
		rocketLabel.setBounds(0,20,550,30);
		this.add(rocketLabel);
		
		//panel de campos de info y sus etiquetas para cada campo
		JPanel fields = new JPanel();
		fields.setLayout(null);
		fields.setBounds(0,50,70,500);
		fields.setBackground(Color.LIGHT_GRAY);
		this.add(fields);
		
		JLabel prop = new JLabel("PROP. Nº", SwingConstants.CENTER);
		prop.setBounds(0,0,70,70);
		fields.add(prop);
		
		JLabel max = new JLabel("Max.", SwingConstants.CENTER);
		max.setBounds(0,70,70,50);
		fields.add(max);
		
		JLabel current = new JLabel("Current", SwingConstants.CENTER);
		current.setBounds(0,135,70,50);
		fields.add(current);
		
		JLabel target = new JLabel("TARGET", SwingConstants.CENTER);
		target.setBounds(0,210,70,50);
		fields.add(target);
		
		JLabel target2 = new JLabel("TARGET", SwingConstants.CENTER);
		target2.setBounds(0,420,70,50);
		fields.add(target2);
		
		//panel con info concreta de cada propulsor según cohete y sus etiquetas según info
		JPanel propInfo = new JPanel();
		propInfo.setLayout(null);
		propInfo.setBounds(70,50,480,300);
		propInfo.setBackground(Color.GREEN);
		this.add(propInfo);
		
		int numProp = rocket.getNumPropellers();
		int width = 460/numProp;
		int x = 0;
		//labels para el panel de info = etiquetas con datos de cada propulsor
		for(Propeller p : rocket.getPropellers()) {
			JLabel propeller = new JLabel(p.getPropId(), SwingConstants.CENTER);
			JLabel maxPow = new JLabel("" + p.getMaxPower(), SwingConstants.CENTER);
			currentPowerLabel = p.getCurrentLabel();
			JLabel targetPow = new JLabel("" + p.getTargetPower(), SwingConstants.CENTER);
			propeller.setBounds(0+x,0,width,70);
			maxPow.setBounds(0+x,70,width,50);
			currentPowerLabel.setBounds(0+x,135,width,50);
			targetPow.setBounds(0+x,210,width,50);
			propInfo.add(propeller);
			propInfo.add(maxPow);
			propInfo.add(currentPowerLabel);
			propInfo.add(targetPow);
			x += width;
		}
			
		//panel para info velocidad cohete
		JPanel speedFields = new JPanel();
		speedFields.setLayout(null);
		speedFields.setBounds(70,350,480,200);
		speedFields.setBackground(Color.GREEN);//cambiar a green
		this.add(speedFields);
		
		JLabel speedTitle = new JLabel("VELOCIDAD (Km/h)", SwingConstants.CENTER);
		speedTitle.setFont(new Font("Arial", Font.BOLD, 20));
		speedTitle.setBounds(0,0,480,50);
		speedFields.add(speedTitle);
		
		JLabel targetSpeedLabel = new JLabel(rocket.getTargetSpeed()+"", SwingConstants.CENTER);
		targetSpeedLabel.setBounds(0, 115, 480, 50);
		targetSpeedLabel.setFont(new Font("Arial", Font.BOLD, 20));
		speedFields.add(targetSpeedLabel);
		
		speedLabel = new JLabel(rocket.calculateCurrentSpeed()+"", SwingConstants.CENTER);
		speedLabel.setBounds(0, 25, 480, 100);
		speedLabel.setFont(new Font("Arial", Font.BOLD, 24));
		speedFields.add(speedLabel);
	}
}

