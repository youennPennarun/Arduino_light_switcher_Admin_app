package gui.settings;

import gui.HA_GUI;
import gui.components.TopBar;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SettingsFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5460851889373751546L;
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 400;
	
	public static final double SETTINGS_MENU_HEIGHT_RATIO = 0.2;

	
	private JPanel contentPane = new JPanel();
	
	private static SettingsFrame settingsFrame;

	private SettingsFrame(){
		settingsFrame = this;
		this.setUndecorated(true);
		this.setSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
		this.setLocationRelativeTo(HA_GUI.mainFrame);
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		Box vBox = Box.createVerticalBox();
		vBox.add(new TopBar(this));
		contentPane.add(new SettingsMenu());
		WeatherSettingsPanel weatherPane = new WeatherSettingsPanel();
		weatherPane.setAlignmentX(LEFT_ALIGNMENT);
		contentPane.add(weatherPane);
		contentPane.add(Box.createVerticalGlue());
		vBox.add(contentPane);

		this.add(vBox);


		this.setVisible(true);

	}


	public static void disposeFrame() {
		if(settingsFrame != null){
			settingsFrame.dispose();
		}
	}
	
	public static void showFrame(){
		if(settingsFrame != null){
			settingsFrame.dispose();
		}
		settingsFrame = new SettingsFrame();
	}

}
