package gui.weather;

import gui.CustomPanel;
import gui.Frames;
import gui.HA_GUI;
import gui.components.TopBar;
import gui.components.TopMenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.settings.Settings;
import data.weather.CityData;
import data.weather.SearchWeatherData;
import data.weather.WeatherCurrentData;
import data.weather.WeatherDailyData;
import data.weather.WeatherHourlyData;
import exception.WeatherException;


public class WeatherPanel extends CustomPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1266112957440084203L;
	public static final Font CITY_LABEL_FONT = new Font("",Font.BOLD,26);
	public static final Color CITY_LABEL_COLOR = new Color(56,175,231,255);

	public static final Font CONTENT_FONT = new Font("",Font.PLAIN,20);
	public static final Color CONTENT_COLOR = Color.WHITE;

	private CityData city;
	private JSlider selectHours;
	private int selectedHour=0;
	private JPanel weatherPanel;
	private JLabel cityLabel = new JLabel();

	private CurrentWeatherPanel currentWeatherPanel;
	private JPanel panel;
	private EmptyBorder emptyBorder = new EmptyBorder(0, 120, 0, 20);
	private JLabel dateLabel = new JLabel();
	private WeatherIconListPanel dailyWeatherList;
	private ArrayList<DailyWeatherPanel> DailyWeatherPanelList;
	private ArrayList<HourlyWeatherPanel> hourlyWeatherPanelList;
	private JPanel slidePanel;



	public WeatherPanel(){
		this.setBorder(emptyBorder);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(HA_GUI.FRAME_WIDTH,HA_GUI.FRAME_HEIGHT-TopBar.PANEL_HEIGHT-TopMenu.PANEL_HEIGHT));
		JLabel loadingLabel = new JLabel("Loading...");
		loadingLabel.setForeground(CONTENT_COLOR);
		this.add(loadingLabel);
		this.setOpaque(false);
		WeatherPanel wP = this;
		this.city = CityData.getCityData();
		Thread getResults = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(city);
				if( city == null){
					if( Settings.cityId != 0 ){
						try {
							city = new CityData(Settings.cityId,Settings.cityName,Settings.cityCountry);
							WeatherCurrentData.setCurrentWeather(city);
							WeatherDailyData.setDailyWeather(city);
							WeatherHourlyData.setHourlyWeather(city);
						} catch (WeatherException e) {
							city = null;
						}
					} else{
						removeAll();
						wP.add(new JLabel("Please, chose a city in the settings"));
					}
				}
				if(city != null){
					removeAll();
					initComponents();
					generatePanel();
					setCurrentWeatherPanel(currentWeatherPanel);
					setContentPanel(getCurrentWeatherPanel());
					setHourSelection();
				}else{
					HA_GUI.getMainFrame().changeFrame(Frames.PREVIOUS);
				}
			}
		});
		getResults.start();
	}


	private void setHourSelection() {
		Calendar calendar = city.getHourlyWeatherList().get(0).getDate();
		selectHours = new JSlider(0,7,selectedHour);

		Dictionary<Integer, Component> dict = new Hashtable<Integer, Component>();
		int hours=calendar.get(Calendar.HOUR_OF_DAY);
		for (int i=0; i<=7; i += 1) {  
			hours = hours+3;
			if(hours >= 24){
				hours-=24;
			}
			String str = hours+":00";
			JLabel label = new JLabel(str);
			label.setForeground(CONTENT_COLOR);
			dict.put(i, label);
		}
		selectHours.setLabelTable(dict);
		selectHours.setMajorTickSpacing(1);
		selectHours.setMinorTickSpacing(1);
		selectHours.setPaintTicks(true);
		selectHours.setPaintLabels(true);
		selectHours.setPreferredSize(new Dimension(550,50));
		selectHours.setOpaque(false);

		selectHours.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				if (!slider.getValueIsAdjusting()){
					selectedHour = (slider.getValue());
					setContentPanel(getHourlyWeatherPanelList().get(selectedHour));

				}
				/*
				JSlider source = (JSlider)e.getSource();
				if(!source.getValueIsAdjusting())
				{
					selectedHour = selectHours.getValue();
					System.out.println(selectHours.getValue());
					//setContentPanel(getHourlyWeatherPanelList().get(selectedHour));
				}
				 */
			}
		});
	}

	public void setContentPanel(WeatherContentPane panel){
		weatherPanel.removeAll();
		if(panel instanceof DailyWeatherPanel){
			slidePanel.setVisible(false);
		}else{
			slidePanel.setVisible(true);
		}

		dateLabel.setText(getDateString(panel.getWeather().getDate()));
		weatherPanel.add((JPanel) panel);
		this.revalidate();
		HA_GUI.refreshView();		
	}
	public void initComponents(){
		setHourSelection();

		weatherPanel = new JPanel();
		weatherPanel.setOpaque(false);
		dailyWeatherList = new WeatherIconListPanel(this,city.getCurrentWeather(),city.getDailyWeatherList());

		dateLabel.setFont(CONTENT_FONT);
		dateLabel.setForeground(CONTENT_COLOR);

		cityLabel.setText(city.getName()+" ("+city.getCountry()+")");
		cityLabel.setFont(CITY_LABEL_FONT);
		cityLabel.setForeground(CITY_LABEL_COLOR);

		this.setOpaque(false);
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridBagLayout());
		this.add(panel);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.PAGE_START;

		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(cityLabel,gbc);
		gbc.gridy=1;
		panel.add(dateLabel,gbc);
		gbc.gridheight = 1;
		gbc.gridwidth = 4;
		gbc.gridx=1;
		gbc.gridy=2;
		panel.add(weatherPanel,gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 4;
		gbc.gridx = 1;
		gbc.gridy = 3;
		slidePanel = new JPanel();
		slidePanel.setOpaque(false);
		slidePanel.add(selectHours);
		panel.add(slidePanel,gbc);
		gbc.gridwidth = 5;
		gbc.gridy = 4;
		panel.add(dailyWeatherList,gbc);
	}

	private void generatePanel(){
		int counter = 0;
		currentWeatherPanel = new CurrentWeatherPanel(city.getCurrentWeather());
		setDailyWeatherPanelList(new ArrayList<DailyWeatherPanel>());
		setHourlyWeatherPanelList(new ArrayList<HourlyWeatherPanel>());

		for(WeatherDailyData weather : city.getDailyWeatherList()){
			getDailyWeatherPanelList().add(new DailyWeatherPanel(weather));
		}
		counter=0;

		for(WeatherHourlyData weather : city.getHourlyWeatherList()){
			if(counter != 0){
				getHourlyWeatherPanelList().add(new HourlyWeatherPanel(weather));
			}
			counter++;
		}
	}



	public CityData getCityById(){
		return (CityData) SearchWeatherData.jsonSearchCity(Settings.cityId).getResultList().get(0);
	}

	private String getDateString(Calendar calendar){
		SimpleDateFormat formatter = new SimpleDateFormat("E H:mm");
		return formatter.format(calendar.getTime());
	}


	public CityData getCity() {
		return city;
	}


	public void setCity(CityData city) {
		this.city = city;
	}


	@Override
	public void setDefaultValues() {
		// TODO Auto-generated method stub

	}


	public CurrentWeatherPanel getCurrentWeatherPanel() {
		return currentWeatherPanel;
	}


	public void setCurrentWeatherPanel(CurrentWeatherPanel currentWeatherPanel) {
		this.currentWeatherPanel = currentWeatherPanel;
	}


	public ArrayList<DailyWeatherPanel> getDailyWeatherPanelList() {
		return DailyWeatherPanelList;
	}


	public void setDailyWeatherPanelList(ArrayList<DailyWeatherPanel> dailyWeatherPanelList) {
		DailyWeatherPanelList = dailyWeatherPanelList;
	}


	public ArrayList<HourlyWeatherPanel> getHourlyWeatherPanelList() {
		return hourlyWeatherPanelList;
	}


	public void setHourlyWeatherPanelList(ArrayList<HourlyWeatherPanel> hourlyWeatherPanelList) {
		this.hourlyWeatherPanelList = hourlyWeatherPanelList;
	}
}
