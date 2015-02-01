package data.weather;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.DataUtils;
import exception.WeatherException;

public class WeatherDailyData extends WeatherData{
	protected static String JSON_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?id=";
	protected static final int NB_RESULTS = 8;

	private Double tempMin;
	private Double tempMax;
	private Double pressure;
	private Double humidity;
	private Double cloudsPercent;
	private Double cloudsSpeed;
	private Double cloudsDirection;
	private Double rainPercent;

	public Double getTempMin() {
		return tempMin;
	}
	public String getTempMinStr(String string) {
		if(string.equals("C")){
			BigDecimal val = new BigDecimal(String.valueOf(getTempMin() - 273.15)).setScale(1, BigDecimal.ROUND_HALF_UP);
			return val.toString();
		}else if(string.equals("F")){
			BigDecimal val = new BigDecimal(String.valueOf(getTempMin() - 457.87)).setScale(1, BigDecimal.ROUND_HALF_UP);
			return val.toString();
		}
		return getTempMin().toString();
	}



	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}



	public Double getTempMax() {
		return tempMax;
	}
	public String getTempMaxStr(String string) {
		if(string.equals("C")){
			BigDecimal val = new BigDecimal(String.valueOf(getTempMax() - 273.15)).setScale(1, BigDecimal.ROUND_HALF_UP);
			return val.toString();
		}else if(string.equals("F")){
			BigDecimal val = new BigDecimal(String.valueOf(getTempMax() - 457.87)).setScale(1, BigDecimal.ROUND_HALF_UP);
			return val.toString();
		}
		return getTempMax().toString();
	}



	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}



	public Double getPressure() {
		return pressure;
	}



	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}



	public Double getHumidity() {
		return humidity;
	}



	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}



	public Double getCloudsPercent() {
		return cloudsPercent;
	}



	public void setCloudsPercent(Double cloudsPercent) {
		this.cloudsPercent = cloudsPercent;
	}



	public Double getCloudsSpeed() {
		return cloudsSpeed;
	}



	public void setCloudsSpeed(Double cloudsSpeed) {
		this.cloudsSpeed = cloudsSpeed;
	}



	public Double getCloudsDirection() {
		return cloudsDirection;
	}



	public void setCloudsDirection(Double cloudsDirection) {
		this.cloudsDirection = cloudsDirection;
	}



	public Double getRainPercent() {
		return rainPercent;
	}



	public void setRainPercent(Double rainPercent) {
		this.rainPercent = rainPercent;
	}

	
	
	public static CityData setDailyWeather(CityData city) throws WeatherException{
		city.setDailyWeatherList(getDailyData(city));
		return city;
	}
	

	private static ArrayList<WeatherDailyData> getDailyData(CityData city) throws WeatherException{
		ArrayList<WeatherDailyData> weatherList = new ArrayList<WeatherDailyData>();
		try {
			String source = DataUtils.readUrl(JSON_URL+city.getId()+"&cnt="+NB_RESULTS+SearchWeatherData.TYPE_PARAM+SearchWeatherData.LANG);
			if(source == null)
				throw new WeatherException();
			JSONObject json = new JSONObject(source);
			JSONArray list = json.getJSONArray("list");
			for(int i = 0 ; i < list.length() ; i ++){
				WeatherDailyData weatherData = new WeatherDailyData();
				JSONObject weather = list.getJSONObject(i);
				Calendar calendar = new GregorianCalendar();
				calendar.setTimeInMillis(weather.getLong("dt")*1000L);
				weatherData.setDate(calendar);
				JSONObject temp = weather.getJSONObject("temp");
				weatherData.setTempMin(temp.getDouble("min"));
				weatherData.setTempMax(temp.getDouble("max"));
				weatherData.setPressure(weather.getDouble("pressure"));
				weatherData.setHumidity(weather.getDouble("humidity"));
				JSONObject main = weather.getJSONArray("weather").getJSONObject(0);
				weatherData.setId(main.getInt("id"));
				weatherData.setWeatherTitle(main.getString("main"));
				weatherData.setWeatherDescription(main.getString("description"));
				weatherData.setIconId(main.getString("icon"));
				weatherData.setCloudsSpeed(weather.getDouble("speed"));
				weatherData.setCloudsDirection(weather.getDouble("deg"));
				weatherData.setCloudsPercent(weather.getDouble("clouds"));
				if(weather.has("rain")){
					weatherData.setRainPercent(weather.getDouble("rain"));
				}else{
					weatherData.setRainPercent(0.0);					
				}
				weatherList.add(weatherData);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weatherList;
	}

}
