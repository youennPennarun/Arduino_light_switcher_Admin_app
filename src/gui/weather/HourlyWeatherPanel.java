package gui.weather;

import gui.CustomPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import data.weather.WeatherData;
import data.weather.WeatherHourlyData;
import utils.DataUtils;

public class HourlyWeatherPanel extends CustomPanel implements WeatherContentPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 461671225239785189L;
	private WeatherHourlyData weather;

	public HourlyWeatherPanel(WeatherHourlyData weather){
		this.weather=weather;
		this.setOpaque(false);
		initComponents();
	}

	private void initComponents() {
		JLabel weatherLabel = new JLabel(weather.getWeatherDescription()+"");
		JLabel tempLabel = new JLabel(weather.getTempStr("C")+"°C");
		JLabel windSpeedLabel = new JLabel("Wind speed : "+DataUtils.roundDouble(weather.getWindSpeed(),1)+" m/s");
		weatherLabel.setFont(WeatherPanel.CONTENT_FONT);
		tempLabel.setFont(WeatherPanel.CONTENT_FONT);
		windSpeedLabel.setFont(WeatherPanel.CONTENT_FONT);
		
		weatherLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		tempLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		windSpeedLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridheight=1;
		gbc.gridwidth=2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(weatherLabel,gbc);
		gbc.gridwidth=1;
		gbc.gridheight=2;
		gbc.gridx = 0;
		gbc.gridy = 2;

		this.add(new JLabel(new ImageIcon(weather.getImage())),gbc);

		gbc.gridx = 1;
		this.add(tempLabel,gbc);

		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridheight=1;
		gbc.insets = new Insets(0, 20, 0, 0);
		this.add(windSpeedLabel,gbc);
	}

	@Override
	public void setDefaultValues() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WeatherData getWeather() {
		return weather;
	}
}
