package gui.lights;

import gui.components.StatusButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import communication.HASocket;

import data.lights.Light;

public class LightStatusButton extends StatusButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2325766663144645868L;
	private Light light;
	LightStatusButton(Light light){
		super();
		this.light = light;
		setOn(light.isOn());
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				light.setOn(!light.isOn());
				HASocket.getLightEmitter().setLightState(light);
			}
		});
	}

	protected void setOn(boolean isOn){
		if(isOn){
			isOn=true;
			statusIconLabel.setIcon(imageOn);
			statusLabel.setForeground(onColor);
			statusLabel.setText("ON");
			
		}else{
			isOn=false;
			statusIconLabel.setIcon(imageOff);
			statusLabel.setForeground(offColor);
			statusLabel.setText("OFF");
		}
	}

	public void updateValues() {
		setOn(light.isOn());
		
	}

}
