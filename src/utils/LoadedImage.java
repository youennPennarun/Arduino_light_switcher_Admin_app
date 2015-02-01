package utils;

import java.awt.Image;

public class LoadedImage {
	private String id;
	private Image image;
	
	public LoadedImage(String id,Image image){
		this.id=id;
		this.image=image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	

}
