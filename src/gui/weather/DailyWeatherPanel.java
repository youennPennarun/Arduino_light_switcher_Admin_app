package gui.weather;

import gui.CustomPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.weather.WeatherDailyData;
import data.weather.WeatherData;

public class DailyWeatherPanel extends CustomPanel implements WeatherContentPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8842806840478976298L;

	private WeatherDailyData weather;
	
	private JLabel weatherLabel;
	private JLabel tempLabel;
	private JLabel humidityLabel;

	private JLabel imageLabel;

	public DailyWeatherPanel(WeatherDailyData weather){
		this.weather=weather;
		this.setOpaque(false);
		initComponents();
	}
	
	private void initComponents() {
		weatherLabel = new JLabel(weather.getWeatherDescription()+"");
		tempLabel = new JLabel(weather.getTempMinStr("C")+"-"+weather.getTempMaxStr("C")+"°C");
		humidityLabel = new JLabel("Humidity : "+weather.getHumidity()+"%");
		imageLabel = new JLabel(new ImageIcon(getWeather().getImage()));
		
		weatherLabel.setFont(WeatherPanel.CONTENT_FONT);
		tempLabel.setFont(WeatherPanel.CONTENT_FONT);
		humidityLabel.setFont(WeatherPanel.CONTENT_FONT);
		
		weatherLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		tempLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		humidityLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		
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
		vBox.add(humidityLabel);
		dataPanel.add(vBox);
		this.add(dataPanel,gbc);
	}
	
	public void updateWeather(WeatherDailyData weather){
		this.weather = weather;
		weatherLabel.setText(weather.getWeatherDescription()+"");
		tempLabel.setText(weather.getTempMinStr("C")+"-"+weather.getTempMaxStr("C")+"°C");
		humidityLabel.setText("Humidity : "+weather.getHumidity()+"%");
		
		
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
