package gui.settings;

import gui.components.TopBar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.settings.Settings;

public class WeatherSettingsPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4250939651298909875L;
	private JLabel weatherCityLabel = new JLabel("City :");
	private JButton changeWeatherButton = new JButton("Change city");
	
	private EmptyBorder borders = new EmptyBorder(5, 20, 5, 5);

	private JLabel currentCityLabel;
	
	public WeatherSettingsPanel(){
		this.setBorder(borders);
		WeatherSettingsPanel weatherSettingsPane = this;
		//this.setBorder(borders);
		int width = (int) (SettingsFrame.FRAME_WIDTH-(SettingsFrame.FRAME_WIDTH*SettingsFrame.SETTINGS_MENU_HEIGHT_RATIO));
		int height = SettingsFrame.FRAME_HEIGHT-TopBar.PANEL_HEIGHT;
		this.setMinimumSize(new Dimension(width,height));
		//weatherSearchResultPane.setLayout(new BoxLayout(weatherSearchResultPane, BoxLayout.Y_AXIS));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.add(weatherCityLabel);
		currentCityLabel = new JLabel(Settings.cityName+" ("+Settings.cityCountry+")");
		p.add(currentCityLabel);
		p.add(Box.createHorizontalGlue());
		p.add(changeWeatherButton);
		this.add(p);
		this.add(Box.createVerticalGlue());
		
		this.add(new SettingsActionPanel());
		
		changeWeatherButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchCitySettings(weatherSettingsPane);
			}
		});
	}
	
	public void setCurrentCity(String name,String country){
		currentCityLabel.setText(name+" ("+country+")");
	}
	

}
