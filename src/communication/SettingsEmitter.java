package communication;

import gui.HA_GUI;
import gui.splashScreen.HASplashScreen;

import javax.swing.JOptionPane;

import main.HAMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.nkzawa.emitter.Emitter;

import data.settings.Settings;
import data.weather.CityData;
import exception.WeatherException;

public class SettingsEmitter {


	private HASocket haSocket;

	public SettingsEmitter(HASocket haSocket){
		this.haSocket=haSocket;

		setListeners();
	}

	private void setListeners() {
		haSocket.getSocket().on("settingsChanged" ,new Emitter.Listener() {
			@Override
			public void call(Object... args) {

			}
		}).on("resSettings", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray arg = (JSONArray)args[0];
				try {
					if (arg.length() > 0) {
						Settings.jsonToObject(arg.getJSONObject(0));
						HASplashScreen.setAction("Settings weather data");
						if(HAMain.INIT_WEATHER){
							System.out.println("set weather");
							CityData city = null;
							try {
								city = new CityData(Settings.cityId,Settings.cityName,Settings.cityCountry,true);
							} catch (WeatherException e) {
								System.out.println(city);
							}
							System.out.println(city);
							CityData.setCity(city);
						}
						HASplashScreen.done(CityData.class);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				HASplashScreen.done(SettingsEmitter.class);
			}
		});
	}

	public void saveSettings(){
		JSONObject obj = new JSONObject();
		try {
			obj.put("cityId", Settings.cityId);
			obj.put("cityName", Settings.cityName);
			obj.put("cityCountry", Settings.cityCountry);
			HASocket.getHaSocket().getSocket().emit("setSettings", obj);
		} catch (JSONException e) {
			JOptionPane.showMessageDialog(null,
					"Unable to send message",
					"Socket error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void getSettings(){
		haSocket.getSocket().emit("reqSettings");
	}

}