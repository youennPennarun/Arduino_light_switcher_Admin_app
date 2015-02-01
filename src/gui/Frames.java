package gui;

import gui.components.ImageButton;
import utils.ImageData;

public enum Frames {
	LIGHTS("Lights"),
	CAMERAS("Cameras"),
	HEATERS("Heaters"),
	SPEAKERS("Speakers"),
	ALARMS("Alarms"),
	WEATHER("Weather"),
	SETTINGS("Settings"),
	
	PREVIOUS("previous");
	
	
	private String name = "";
	Frames(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public static Frames findFrame(String frameName){
		for(Frames f : Frames.values()){
			if(f.getName().equals(frameName)){
				return f;
			}
		}
		return null;
	}
}
