package data.weather;

import java.util.ArrayList;
import java.util.Date;

import exception.WeatherException;

public class CityData extends ResultSearch{
	private static CityData cityData;
	private int id;
	private String name;
	private String country;
	private Double lon;
	private Double lat;
	private Date sunrise;
	private Date sunset;
	private WeatherCurrentData currentWeather=null;
	private ArrayList<WeatherDailyData> dailyWeatherList = new ArrayList<WeatherDailyData>(); 
	private ArrayList<WeatherHourlyData> hourlyWeatherList = new ArrayList<WeatherHourlyData>();

	public CityData(){}
	public CityData(int id,String name,String country){
		this.id=id;
		this.name=name;
		this.country=country;
	}
	public CityData(int id,String name,String country,boolean loadWeatherData) throws WeatherException{
		this.id=id;
		this.name=name;
		this.country=country;
		if(loadWeatherData){
			System.out.println("set data");
			WeatherCurrentData.setCurrentWeather(this);
			WeatherDailyData.setDailyWeather(this);
			WeatherHourlyData.setHourlyWeather(this);

		}
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public WeatherCurrentData getCurrentWeather() {
		return currentWeather;
	}

	public void setCurrentWeather(WeatherCurrentData currentWeather) {
		this.currentWeather = currentWeather;
	}public Date getSunrise() {
		return sunrise;
	}

	public void setSunrise(Date sunrise) {
		this.sunrise = sunrise;
	}

	public Date getSunset() {
		return sunset;
	}

	public void setSunset(Date sunset) {
		this.sunset = sunset;
	}

	public ArrayList<WeatherDailyData> getDailyWeatherList() {
		return dailyWeatherList;
	}

	public void setDailyWeatherList(ArrayList<WeatherDailyData> dailyWeatherList) {
		this.dailyWeatherList = dailyWeatherList;
	}

	public ArrayList<WeatherHourlyData> getHourlyWeatherList() {
		return hourlyWeatherList;
	}

	public void setHourlyWeatherList(ArrayList<WeatherHourlyData> hourlyWeatherList) {
		this.hourlyWeatherList = hourlyWeatherList;
	}
	public static void setCity(CityData cityData) {
		CityData.cityData = cityData;
	}
	public static CityData getCityData(){
		return cityData;
	}
}
