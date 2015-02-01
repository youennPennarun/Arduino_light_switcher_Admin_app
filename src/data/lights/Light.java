package data.lights;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Light {
	private static final String DB_ID_NAME = "id";
	private static final String DB_NAME_NAME = "name";
	private static final String DB_IS_ON_NAME = "isOn";
	private static ArrayList<Light> lightList = new ArrayList<Light>();
	
	private int id;
	private String label;
	private boolean isOn;
	public Light(int id, String label, boolean isOn) {
		super();
		this.id = id;
		this.label = label;
		this.isOn = isOn;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isOn() {
		return isOn;
	}
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	public static ArrayList<Light> getLightList() {
		return lightList;
	}

	public static void setLightList(ArrayList<Light> lightList) {
		Light.lightList = lightList;
	}
	
	public static Light jsonToObject(JSONObject json){
		Light light = null;
		try {
			int id = json.getInt(DB_ID_NAME);
			String name = json.getString(DB_NAME_NAME);
			boolean isOn = json.getBoolean(DB_IS_ON_NAME);
			light = new Light(id,name, isOn);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return light;
	}
	

	
}
