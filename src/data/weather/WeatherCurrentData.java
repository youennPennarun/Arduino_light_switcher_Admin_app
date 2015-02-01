package data.weather;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import utils.DataUtils;
import exception.WeatherException;

public class WeatherCurrentData extends WeatherData{
	private Double temp;
	private Double pressure;
	private Double humidity;
	private Double tempMin;
	private Double tempMax;
	private Double windSpeed;
	private Double windDirection;
	private Double cloudPercent;



	public Double getTemp() {
		return temp;
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


	public Double getHumidity() {
		return humidity;
	}


	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}


	public Double getTempMin() {
		return tempMin;
	}


	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	public Double getTempMax() {
		return tempMax;
	}

	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
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

	public Double getCloudPercent() {
		return cloudPercent;
	}


	public void setCloudPercent(Double cloudPercent) {
		this.cloudPercent = cloudPercent;
	}
	public static CityData setCurrentWeather(CityData city) throws WeatherException{
		try {
			String source = DataUtils.readUrl(SearchWeatherData.SEARCH_WEATHER_BY_CITY_ID+city.getId()+SearchWeatherData.LANG+SearchWeatherData.TYPE_PARAM);
			if(source == null){
				throw new WeatherException("no json");
			}else{
				JSONObject json = new JSONObject(source);
				city.setSunrise(new Date(json.getJSONObject("sys").getLong("sunrise")));
				city.setSunset(new Date(json.getJSONObject("sys").getLong("sunset")));
				JSONObject currentWeatherJson = ((JSONObject)(json.getJSONArray("weather").get(0)));
				WeatherCurrentData wD = new WeatherCurrentData();
				wD.setId(currentWeatherJson.getInt("id"));
				wD.setWeatherTitle(currentWeatherJson.getString("main"));
				wD.setWeatherDescription(currentWeatherJson.getString("description"));
				wD.setIconId(currentWeatherJson.getString("icon"));
				JSONObject main = json.getJSONObject("main");
				wD.setTemp(main.getDouble("temp"));
				wD.setPressure(main.getDouble("pressure"));
				wD.setHumidity(main.getDouble("humidity"));
				wD.setTempMax(main.getDouble("temp_max"));
				wD.setTempMin(main.getDouble("temp_max"));
				JSONObject wind = json.getJSONObject("wind");
				wD.setWindSpeed(wind.getDouble("speed"));
				wD.setWindDirection(wind.getDouble("deg"));
				JSONObject clouds = json.getJSONObject("clouds");
				wD.setCloudPercent(clouds.getDouble("all"));
				Calendar calendar = new GregorianCalendar();
				calendar.setTimeInMillis(json.getLong("dt")*1000L);
				wD.setDate(calendar);
				city.setCurrentWeather(wD);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return city;
	}
	public Double getTemp(String string) {
		if(string.equals("C")){
			return getTemp() - 273.15;
		}else if(string.equals("F")){
			return getTemp() - 457.87;
		}
		return getTemp();
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

}
