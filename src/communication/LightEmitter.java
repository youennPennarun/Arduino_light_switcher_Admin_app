package communication;

import javax.swing.JOptionPane;

import gui.HA_GUI;
import gui.lights.LightsPanel;
import gui.splashScreen.HASplashScreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.nkzawa.emitter.Emitter;

import data.lights.Light;

/**
 * Handle lights update on the server
 */
public class LightEmitter {
	
	/**
	 * Instance of HASocket
	 * @see HASocket
	 */
	private HASocket haSocket;

	public LightEmitter(HASocket haSocket){
		this.haSocket=haSocket;
		setListeners();
	}

	/**
	 * 
	 */
	private void setListeners() {
		haSocket.getSocket().on("lightStateChanged" ,new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject json = (JSONObject) args[0];
				try {
					for(Light light:Light.getLightList()){
						if(json.getInt("id") == light.getId()){
							if(json.getBoolean("isOn")){
								light.setOn(true);
							}else{
								light.setOn(false);
							}
						}
					}
					if(HA_GUI.getMainFrame().getCurrentContent() instanceof LightsPanel){
						HA_GUI.refreshView();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).on("resLampesStates", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray arg = (JSONArray)args[0];
				for(int i = 0; i < arg.length();i++){
					try {
						JSONObject json = (JSONObject) arg.get(i);
						Light.getLightList().add(Light.jsonToObject(json));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				if(HA_GUI.getMainFrame().getCurrentContent() instanceof LightsPanel){
					HA_GUI.refreshView();
				}
				HASplashScreen.done(LightEmitter.class);

			}
		});
	}

	public void setLightState(Light light){
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", light.getId());
			obj.put("isOn", light.isOn());
			HASocket.getHaSocket().getSocket().emit("setLampeState", obj);
		} catch (JSONException e) {
			JOptionPane.showMessageDialog(null,
				    "Unable to send message",
				    "Socket error",
				    JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void getLightStates(){
		haSocket.getSocket().emit("reqLampesStates");
	}

}
