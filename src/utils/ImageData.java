package utils;

import java.awt.Image;

public enum ImageData {
	LIGHT_ICON("light icon","light-icon.png",true),
	CAMERA_ICON("camera icon","camera-icon.png",true),
	HEATHER_ICON("heater icon","heater-icon.png",true),
	SPEAKER_ICON("speaker icon","speaker-icon.png",true),
	ALARM_ICON("alarm icon","clock-icon.png",true),
	WEATHER_ICON("weather icon","weather-icon.png",true),
	SETTINGS_ICON("settings icon","settings-icon.png",true),
	ARRAOW_BACK("arrow back","arrow-back.png",false),
	STATUS_OFF("status off","state-off-icon.png",false),
	STATUS_ON("status on","state-on-icon.png",false),
	
	WEATHER_01D("01d","weather icon/01d.png",false),
	WEATHER_01N("01n","weather icon/01n.png",false),
	WEATHER_02D("02d","weather icon/02d.png",false),
	WEATHER_02N("02n","weather icon/02n.png",false),
	WEATHER_03D("03d","weather icon/03d.png",false),
	WEATHER_03N("03n","weather icon/03n.png",false),
	WEATHER_04D("04d","weather icon/04d.png",false),
	WEATHER_04N("04n","weather icon/04n.png",false),
	WEATHER_09D("09d","weather icon/09d.png",false),
	WEATHER_09N("09n","weather icon/09n.png",false),
	WEATHER_10D("10d","weather icon/10d.png",false),
	WEATHER_10N("10n","weather icon/10n.png",false),
	WEATHER_11D("11d","weather icon/11d.png",false),
	WEATHER_11N("11n","weather icon/11n.png",false),
	WEATHER_13D("13d","weather icon/13d.png",false),
	WEATHER_13N("13n","weather icon/13n.png",false),
	WEATHER_50D("50d","weather icon/50d.png",false),
	WEATHER_50N("50n","weather icon/50n.png",false);
	
	private String name = "";
	private String path = "";
	private boolean resize = false;
	private Image image = null;
	private Image imageResized = null;
	ImageData(String name,String path,boolean resize){
		this.setName(name);
		this.setPath(path);
		this.setResize(resize);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isResize() {
		return resize;
	}
	public void setResize(boolean resize) {
		this.resize = resize;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Image getImageResized() {
		return imageResized;
	}
	public void setImageResized(Image imageResized) {
		this.imageResized = imageResized;
	}
}
