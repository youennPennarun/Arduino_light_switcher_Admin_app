package data.settings;

import org.json.JSONException;
import org.json.JSONObject;

public class Settings {
	private static final String DB_CITY_ID = "weatherCityId";
	private static final String DB_CITY_NAME = "weatherCityName";
	private static final String DB_CITY_COUNTRY = "weatherCityCountry";

	public static int cityId;
	public static String cityName;
	public static String cityCountry;
	public static int cityIdNew;
	public static String cityNameNew;
	public static String cityCountryNew;
	

	public static void jsonToObject(JSONObject json){
		try {
			cityId = json.getInt(DB_CITY_ID);
			cityName = json.getString(DB_CITY_NAME);
			cityCountry = json.getString(DB_CITY_COUNTRY);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void saveChanges(){
		if(cityIdNew != 0){
			cityId=cityIdNew;
		}
		if(cityNameNew != null && !cityNameNew.equals("")){
			cityName=cityNameNew;
		}
		if(cityCountryNew != null && !cityCountryNew.equals("")){
			cityCountry=cityCountryNew;
		}
	}

	public static void discardChanges() {
		cityIdNew = 0;
		cityNameNew = null;
		cityCountryNew = null;
	}

}
