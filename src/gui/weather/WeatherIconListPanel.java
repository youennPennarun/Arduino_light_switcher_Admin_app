package gui.weather;

import gui.CustomPanel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;

import data.weather.WeatherCurrentData;
import data.weather.WeatherDailyData;
import data.weather.WeatherData;

public class WeatherIconListPanel extends CustomPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7967587368839475657L;
	private MouseListener buttonListeners;
	private DayWeatherIconPanel selected=null;
	private ArrayList<DayWeatherIconPanel> iconList = new ArrayList<DayWeatherIconPanel>();
	private WeatherPanel weatherPanel;

	public WeatherIconListPanel(WeatherPanel weatherPanel, WeatherCurrentData weatherCurrentData, ArrayList<WeatherDailyData> weatherList){
		this.weatherPanel = weatherPanel;
		this.weatherPanel = weatherPanel;
		this.setListener();
		this.setOpaque(false);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		Box hBox = Box.createHorizontalBox();
		int counter = 0;
		DayWeatherIconPanel icon;
		icon = new DayWeatherIconPanel(counter,weatherCurrentData);
		icon.addMouseListener(buttonListeners);
		iconList.add(icon);
		hBox.add(icon);
		counter++;
		weatherList.remove(0);
		for(WeatherDailyData weather : weatherList){
			icon = new DayWeatherIconPanel(counter,weather);
			icon.addMouseListener(buttonListeners);
			iconList.add(icon);
			hBox.add(icon);
			counter++;
		}
		this.add(hBox);


	}

	private void setListener() {
		buttonListeners = new MouseListener() {
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
				if(selected != null)
					selected.isSelected(false);
				DayWeatherIconPanel clicked = ((DayWeatherIconPanel)e.getSource());
				clicked.isSelected(true);
				WeatherData weather = clicked.getWeather();
				if(weather instanceof WeatherCurrentData){
					selected = iconList.get(clicked.getId());
					weatherPanel.setContentPanel(weatherPanel.getCurrentWeatherPanel());
				}else{
					selected = iconList.get(clicked.getId());
					weatherPanel.setContentPanel(weatherPanel.getDailyWeatherPanelList().get(clicked.getId()-1));
				}
			}
		};
	}

	@Override
	public void setDefaultValues() {
		// TODO Auto-generated method stub

	}
}
