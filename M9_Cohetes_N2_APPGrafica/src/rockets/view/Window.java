package rockets.view;

import javax.swing.*;

import rockets.domain.Rocket;


public class Window extends JFrame{
	private static final long serialVersionUID = 1L;
	
	RocketPanel rocketPanel1, rocketPanel2;
	ControlPanel control1, control2;
	
	public Window(Rocket r1, Rocket r2) {
		
		setBounds(100,100,1100,750);
		setTitle("ROCKETS SPEED CONTROLLER");
		setLayout(null);
//		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		rocketPanel1 = new RocketPanel(r1);
		rocketPanel1.setLocation(0,0);
		this.add(rocketPanel1);
		rocketPanel2 = new RocketPanel(r2);
		rocketPanel2.setLocation(550,0);
		this.add(rocketPanel2);
		
		control1 = new ControlPanel(r1);
		control1.setLocation(0,550);
		this.add(control1);
		control2 = new ControlPanel(r2);
		control2.setLocation(550,550);
		this.add(control2);
		
		
		
	}

}
