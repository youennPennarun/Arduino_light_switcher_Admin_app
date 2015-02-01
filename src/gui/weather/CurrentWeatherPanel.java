package gui.weather;

import gui.CustomPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.weather.WeatherCurrentData;

public class CurrentWeatherPanel extends CustomPanel implements WeatherContentPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6172284678823284032L;
	private WeatherCurrentData weather;
	private JLabel weatherLabel;
	private JLabel tempLabel;
	private JLabel humidityLabel;
	private JLabel windSpeedLabel;
	private JLabel imageLabel;

	public CurrentWeatherPanel(WeatherCurrentData weather){
		this.setWeather(weather);
		this.setOpaque(false);
		initComponents();
	}
	
	private void initComponents() {
		weatherLabel = new JLabel(getWeather().getWeatherDescription()+"");
		tempLabel = new JLabel("Temperature :"+getWeather().getTempStr("C")+"°C");
		humidityLabel = new JLabel("Humidity : "+getWeather().getHumidity()+"%");
		windSpeedLabel = new JLabel("Wind speed : "+getWeather().getWindSpeed()+" m/s");
		imageLabel = new JLabel(new ImageIcon(getWeather().getImage()));
		
		weatherLabel.setFont(WeatherPanel.CONTENT_FONT);
		tempLabel.setFont(WeatherPanel.CONTENT_FONT);
		humidityLabel.setFont(WeatherPanel.CONTENT_FONT);
		windSpeedLabel.setFont(WeatherPanel.CONTENT_FONT);
		
		weatherLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		tempLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		humidityLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		windSpeedLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
	    gbc.weighty = 1.0;
	    gbc.weightx = 1.0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		this.add(weatherLabel,gbc);
		gbc.gridheight = 10;
	    gbc.weighty = 10.0;
		gbc.gridy++;
		this.add(imageLabel,gbc);
		gbc.insets = new Insets(0, 60, 0, 0);
	    gbc.weighty = 1.0;
		gbc.gridheight = 1;
		gbc.gridx+=4;
		gbc.gridy++;
		JPanel dataPanel = new JPanel();
		dataPanel.setOpaque(false);
		Box vBox = Box.createVerticalBox();
		vBox.add(tempLabel);
		vBox.add(windSpeedLabel);
		vBox.add(humidityLabel);
		dataPanel.add(vBox);
		this.add(dataPanel,gbc);
	}

	@Override
	public void setDefaultValues() {
		// TODO Auto-generated method stub
		
	}

	public WeatherCurrentData getWeather() {
		return weather;
	}

	public void setWeather(WeatherCurrentData weather) {
		this.weather = weather;
	}
}
