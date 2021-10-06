package rocketsN3.domain;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Propeller implements Runnable {  
	
	private String propId;
	private int maxPower;
	private int currentPower;
	private int targetPower;
	private boolean stoppedProp;
	private JLabel currentLabel;
	
	public Propeller(String propId, int maxPower) {
		this.propId = propId;
		this.maxPower = maxPower;
		currentPower = 0;
		targetPower = 0;
		stoppedProp = false;
		currentLabel = new JLabel("OFF", SwingConstants.CENTER);
	}
	
	public String getPropId() {
		return propId;
	}
	
	public void setPropId(String propId) {
		this.propId = propId;
	}

	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) {
		if (maxPower > 0)
			this.maxPower = maxPower;
		else
			this.maxPower = 0;
	}
	
	public int getCurrentPower() {
		return currentPower;
	}
	
	public void setCurrentPower(int currentPower) {
		this.currentPower = currentPower;
	}
	
	public int getTargetPower() {
		return targetPower;
	}
	
	public void setTargetPower(int targetPower) {
		if(targetPower>maxPower) {
			this.targetPower = maxPower;
			System.err.println("El objetivo no puede superar la potencia máxima");
		}else if(targetPower<0)
			this.targetPower = 0;
		else
			this.targetPower = targetPower;
	}
	
	public boolean isStoppedProp() {
		return stoppedProp;
	}
	
	public void setStoppedProp(boolean stopProp) {
		if(stopProp) {
			this.currentLabel.setForeground(Color.RED);
		}else
			this.currentLabel.setForeground(Color.BLACK);
		this.stoppedProp = stopProp;
	}
	
	//para aumentar la potencia
	public int powerUp(int gear) {
		if(currentPower + gear < targetPower)
			currentPower += gear;
		else currentPower = targetPower;
		
		currentLabel.setText(currentPower+"");
		
		return currentPower;
	}
	
	//para bajar la potencia
	public int powerDown(int gear) {
		if(currentPower > 0)
			currentPower -= gear;
		currentLabel.setText(currentPower+"");
		return currentPower;
	}
	
	public void setCurrentLabel(int currentPow) {
		this.currentLabel.setText(currentPow + ""); 
	}
	public JLabel getCurrentLabel() {
		return currentLabel;
	}
	
	@Override
	public String toString() {
		return propId + ": Actual=" + currentPower + " Objetivo=" + targetPower + " Max.="+ maxPower;
	}

	@Override
	public void run() {
		
		while (!this.isStoppedProp()) {
			this.currentLabel.setText(this.currentPower+"");
			if (this.getCurrentPower() != this.getTargetPower()) {
					this.currentLabel.setText(this.currentPower+"");
					this.currentLabel.setForeground(Color.BLACK);
			}else {
				this.currentLabel.setText("HECHO! " + this.currentPower);
				this.currentLabel.setForeground(Color.RED);
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.currentLabel.setText("PAUSED");
		this.currentLabel.setForeground(Color.RED);
	}

}


