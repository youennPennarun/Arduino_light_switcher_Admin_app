package data.weather;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.DataUtils;
import exception.WeatherException;

public class WeatherHourlyData extends WeatherData{
	protected static String JSON_URL = "http://api.openweathermap.org/data/2.5/forecast?id=";
	protected static final int NB_RESULTS = 5;

	private Double temp;
	private Double pressure;
	private Double cloudsPercent;
	private Double windSpeed;
	private Double windDirection;

	public Double getTemp() {
		return temp;
	}
	public String getTempStr(String string) {
		if(string.equals("C")){
			BigDecimal val = new BigDecimal(String.valueOf(getTemp() - 273.15)).setScale(1, BigDecimal.ROUND_HALF_UP);
			return val.toString();
		}else if(string.equals("F")){
			BigDecimal val = new BigDecimal(String.valueOf(getTemp() - 457.87)).setScale(1, BigDecimal.ROUND_HALF_UP);
			return val.toString();
		}
		return getTemp().toString();
	}



	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getPressure() {
		return pressure;
	}



	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Double getCloudsPercent() {
		return cloudsPercent;
	}



	public void setCloudsPercent(Double cloudsPercent) {
		this.cloudsPercent = cloudsPercent;
	}
	
	public Double getWindSpeed() {
		return windSpeed;
	}



	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}



	public Double getWindDirection() {
		return windDirection;
	}



	public void setWindDirection(Double windDirection) {
		this.windDirection = windDirection;
	}

	public static CityData setHourlyWeather(CityData city) throws WeatherException{
		city.setHourlyWeatherList(getHourlyData(city));
		return city;
	}

	protected static ArrayList<WeatherHourlyData> getHourlyData(CityData city) throws WeatherException{
		ArrayList<WeatherHourlyData> weatherList = new ArrayList<WeatherHourlyData>();
		try {
			String source = DataUtils.readUrl(JSON_URL+city.getId()+"&cnt="+NB_RESULTS+SearchWeatherData.TYPE_PARAM+SearchWeatherData.LANG);
			if(source == null)
				throw new WeatherException();
			JSONObject json = new JSONObject(source);
			JSONArray list = json.getJSONArray("list");
			for(int i = 0 ; i < list.length() ; i ++){
				WeatherHourlyData weatherData = new WeatherHourlyData();
				JSONObject weather = list.getJSONObject(i);
				Calendar calendar = new GregorianCalendar();
				calendar.setTimeInMillis(weather.getLong("dt")*1000L);
				weatherData.setDate(calendar);
				JSONObject main = weather.getJSONObject("main");
				weatherData.setTemp(main.getDouble("temp"));
				weatherData.setPressure(main.getDouble("pressure"));
				JSONObject weatherInfo = weather.getJSONArray("weather").getJSONObject(0);
				weatherData.setId(weatherInfo.getInt("id"));
				weatherData.setWeatherTitle(weatherInfo.getString("main"));
				weatherData.setWeatherDescription(weatherInfo.getString("description"));
				weatherData.setIconId(weatherInfo.getString("icon"));
				weatherData.setCloudsPercent(weather.getJSONObject("clouds").getDouble("all"));
				weatherData.setWindSpeed(weather.getJSONObject("wind").getDouble("speed"));
				weatherData.setWindSpeed(weather.getJSONObject("wind").getDouble("deg"));
				weatherList.add(weatherData);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weatherList;
	}

}
