package gui;

import gui.components.ImageButton;
import gui.settings.SettingsFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;

import utils.ImageData;

public class MainMenu extends CustomPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -704078993647492945L;
	public static ImageButton[] buttonList = {
			new ImageButton(ImageData.LIGHT_ICON.getImage(),Frames.LIGHTS),
			new ImageButton(ImageData.CAMERA_ICON.getImage(),Frames.CAMERAS),
			new ImageButton(ImageData.HEATHER_ICON.getImage(),Frames.HEATERS),
			new ImageButton(ImageData.SPEAKER_ICON.getImage(),Frames.SPEAKERS),
			new ImageButton(ImageData.ALARM_ICON.getImage(),Frames.ALARMS),
			new ImageButton(ImageData.WEATHER_ICON.getImage(),Frames.WEATHER),
			new ImageButton(ImageData.SETTINGS_ICON.getImage(),Frames.SETTINGS)
	};
	
	
	public MainMenu(HA_GUI mainFrame){
		setBackground(new Color(0,0,0,0));	
		MouseListener buttonListeners = new MouseListener() {
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
				String id = ((ImageButton)e.getSource()).getId();
				mainFrame.changeFrame(((ImageButton)e.getSource()).getFrame());
				/*
				switch (((ImageButton)e.getSource()).getId()) {
				case "Lights":
					mainFrame.changeFrame(id);
					break;
				case "Weather":
					mainFrame.changeFrame(id);
					break;
				case "Settings":
					new SettingsFrame();
					break;
				default:
					break;
				}
				*/
			}
		};
		Box vBox = Box.createVerticalBox();
		Box hBox = Box.createHorizontalBox();
		int counter = 0;
		for(ImageButton imageButton : buttonList){
			counter++;
			imageButton.addMouseListener(buttonListeners);
			hBox.add(imageButton);
			if(counter == 4){
				counter = 0;
				vBox.add(hBox);
				vBox.add(Box.createRigidArea(new Dimension(0,20)));
				hBox = Box.createHorizontalBox();
			}else{

				hBox.add(Box.createRigidArea(new Dimension(10, 0)));
			}
		}
		vBox.add(hBox);
		this.setOpaque(true);
		this.add(vBox);
	}
	
	public void setDefaultValues(){
		for(ImageButton button : MainMenu.buttonList){
			button.isHover(false);
		}
	}
	
	
}
