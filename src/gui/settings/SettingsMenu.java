package gui.settings;

import gui.components.TopBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.ImageData;

public class SettingsMenu extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4352794161842274362L;
	public static final Color BACKGROUND_COLOR = new Color(140,140,140);

	public SettingsMenu(){
		this.setBackground(BACKGROUND_COLOR);
		
		int width = (int) (Math.round(SettingsFrame.FRAME_WIDTH*SettingsFrame.SETTINGS_MENU_HEIGHT_RATIO));
		this.setPreferredSize(new Dimension(width,SettingsFrame.FRAME_HEIGHT-TopBar.PANEL_HEIGHT));
		this.setMinimumSize(new Dimension(width,SettingsFrame.FRAME_HEIGHT-TopBar.PANEL_HEIGHT));
		this.setMaximumSize(new Dimension(width,SettingsFrame.FRAME_HEIGHT-TopBar.PANEL_HEIGHT));
		this.setBackground(getBackground().brighter());
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		SettingsMenuButton settingsButton = new SettingsMenuButton(ImageData.SETTINGS_ICON.getImageResized(),"General");
		SettingsMenuButton weatherButton = new SettingsMenuButton(ImageData.WEATHER_ICON.getImageResized(),"Weather");
		
		this.add(settingsButton);
		this.add(weatherButton);
		
	}
}
