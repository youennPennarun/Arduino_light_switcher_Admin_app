package utils;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {


	private static final String IMG_PATH = "/images/";
	

	/**
	 * Loading every images
	 * @see ImageData
	 */
	public static void preload(){
		for(ImageData imgData : ImageData.values()){
			try {
				imgData.setImage(((Image) ImageIO.read(ImageUtils.class.getResource(IMG_PATH+imgData.getPath()))));
				if(imgData.isResize()){
					imgData.setImageResized(imgData.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));					
				}
			} catch (IOException e) {
				System.err.println("Unable to read "+imgData.getName()+" at'"+imgData.getPath()+"'");
				e.printStackTrace();
			}
		}
	}

	public static Image getImageByName(String name){
		for(ImageData imgData : ImageData.values()){
			if(imgData.getName().equals(name)){
				return imgData.getImage();
			}
		}
		return null;
	}
}
