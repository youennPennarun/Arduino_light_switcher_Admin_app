package data.weather;

import java.awt.Image;
import java.nio.charset.Charset;
import java.util.Calendar;

import utils.ImageUtils;

public class WeatherData {
	public static final int ICON_WIDTH = 75;
	public static final int ICON_HEIGHT = 75;

	private int id;
	private Calendar date;
	private String weatherTitle;
	private String weatherDescription;
	private String iconId;
	private Image image;
	private Image icon;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWeatherTitle() {
		return weatherTitle;
	}
	public void setWeatherTitle(String weatherTitle) {
		this.weatherTitle = weatherTitle;
	}
	public String getWeatherDescription() {
		return weatherDescription;
	}
	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = new String(weatherDescription.getBytes(),Charset.forName("UTF-8"));
	}
	public String getIconId() {
		return iconId;
	}
	public void setIconId(String iconId) {
		iconId.replace("dd", "d");
		iconId.replace("nn", "n");
		setImage(ImageUtils.getImageByName(iconId));
		setIcon(image.getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
		this.iconId = iconId;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar calendar) {
		this.date = calendar;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Image getIcon() {
		return icon;
	}
	public void setIcon(Image icon) {
		this.icon = icon;
	}

}
