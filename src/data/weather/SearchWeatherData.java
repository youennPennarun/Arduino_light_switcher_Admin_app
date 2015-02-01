package data.weather;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.DataUtils;

public class SearchWeatherData {
	public  final static String API_KEY= "0fbac62bdfac776aa7516f7fb0541fe3";
	public final static String LANG = "&lang=fr";
	public final static String TYPE_PARAM = "&mode=json&APPID="+API_KEY;
	public  final static String SEARCH_CITY_BY_NAME= "http://api.openweathermap.org/data/2.5/find?q=";
	public  final static String SEARCH_CITY_BY_ID= "http://api.openweathermap.org/data/2.5/find?id=";
	public  final static String SEARCH_WEATHER_BY_CITY_ID= "http://api.openweathermap.org/data/2.5/weather?id=";

	private String message;
	private int code;
	private int resultCount;
	private ArrayList<ResultSearch> resultList = new ArrayList<ResultSearch>();




	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getResultCount() {
		return resultCount;
	}
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
	public ArrayList<ResultSearch> getResultList() {
		return resultList;
	}
	public void setResultList(ArrayList<ResultSearch> resultList) {
		this.resultList = resultList;
	}


	public static SearchWeatherData jsonSearchCity(String name){
		SearchWeatherData searchData = new SearchWeatherData();
		try {
			String source = DataUtils.readUrl(SEARCH_CITY_BY_NAME+name+TYPE_PARAM+LANG);
			if(source == null)
				return null;
			JSONObject json = new JSONObject(source);
			searchData.setMessage(json.getString("message"));
			searchData.setCode(json.getInt("cod"));
			searchData.setResultCount(json.getInt("count"));
			JSONArray resultArray = json.getJSONArray("list");
			for(int i = 0 ; i < resultArray.length() ; i++){
				JSONObject result = (JSONObject)resultArray.get(i);
				CityData cityData = new CityData();
				cityData.setId(result.getInt("id"));
				cityData.setName(result.getString("name"));
				cityData.setLon(result.getJSONObject("coord").getDouble("lon"));
				cityData.setLat(result.getJSONObject("coord").getDouble("lat"));
				cityData.setCountry(result.getJSONObject("sys").getString("country"));
				JSONArray resultWeatherArray = result.getJSONArray("weather");
				for(int o = 0 ; o < resultWeatherArray.length() ; o++){
					JSONObject resultWeather = (JSONObject)resultWeatherArray.get(o);
					WeatherCurrentData wD = new WeatherCurrentData();
					wD.setId(resultWeather.getInt("id"));
					wD.setWeatherTitle(resultWeather.getString("main"));
					wD.setWeatherDescription(resultWeather.getString("description"));
					wD.setIconId(resultWeather.getString("icon"));
					cityData.setCurrentWeather(wD);
				}
				searchData.resultList.add(cityData);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchData;
	}
	public static SearchWeatherData jsonSearchCity(int cityId) {
		SearchWeatherData searchData = new SearchWeatherData();
		try {
			String source = DataUtils.readUrl(SEARCH_CITY_BY_ID+cityId+TYPE_PARAM+LANG);
			if(source == null)
				return null;
			JSONObject json = new JSONObject(source);
			searchData.setMessage(json.getString("message"));
			searchData.setCode(json.getInt("cod"));
			searchData.setResultCount(json.getInt("count"));
			JSONArray resultArray = json.getJSONArray("list");
			for(int i = 0 ; i < resultArray.length() ; i++){
				JSONObject result = (JSONObject)resultArray.get(i);
				CityData cityData = new CityData();
				cityData.setId(result.getInt("id"));
				cityData.setName(result.getString("name"));
				cityData.setLon(result.getJSONObject("coord").getDouble("lon"));
				cityData.setLat(result.getJSONObject("coord").getDouble("lat"));
				cityData.setCountry(result.getJSONObject("sys").getString("country"));
				JSONArray resultWeatherArray = result.getJSONArray("weather");
				for(int o = 0 ; o < resultWeatherArray.length() ; o++){
					JSONObject resultWeather = (JSONObject)resultWeatherArray.get(o);
					WeatherCurrentData wD = new WeatherCurrentData();
					wD.setId(resultWeather.getInt("id"));
					wD.setWeatherTitle(resultWeather.getString("main"));
					wD.setWeatherDescription(resultWeather.getString("description"));
					wD.setIconId(resultWeather.getString("icon"));
					cityData.setCurrentWeather(wD);
				}
				searchData.resultList.add(cityData);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchData;
	}
}
