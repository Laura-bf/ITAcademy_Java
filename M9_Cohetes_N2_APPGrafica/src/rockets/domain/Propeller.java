package rockets.domain;

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
			System.err.println("El objetivo no puede superar la potencia m?xima");
		}else if(targetPower<0)
			this.targetPower = 0;
		else
			this.targetPower = targetPower;
	}
	
	public boolean isStoppedProp() {
		return stoppedProp;
	}
	
	public void setStoppedProp(boolean stopProp) {
		this.stoppedProp = stopProp;
	}
	
	//para aumentar la potencia
	public int powerUp() {
		if(currentPower < targetPower)
			currentPower++;
			currentLabel.setText(currentPower+"");
		return currentPower;
	}
	
	//para bajar la potencia
	public int powerDown() {
		if(currentPower > 0)
			currentPower--;
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
			if (this.getCurrentPower() != this.getTargetPower()) {
					this.currentLabel.setText(this.currentPower+"");
			}else {
				this.currentLabel.setText("HECHO!");
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.currentLabel.setText("OFF");
	}

}


