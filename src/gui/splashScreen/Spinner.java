package gui.splashScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Spinner extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6668456977517673435L;
	private BufferedImage spinnerImage;
	private BufferedImage splashImage;
	private int spinnerPosX=10;
	private double spinnerPosY=10;
	private double scaleHeightBy=0.1;
	private double scaleWidthBy=0.1;
	private double rotateBy=0;
	private int spinnerWidth;
	private int spinnerHeight;

	public Spinner(){
		try {
			splashImage = ImageIO.read(this.getClass().getResource(HASplashScreen.SPLASH));
			spinnerImage = ImageIO.read(this.getClass().getResource(HASplashScreen.SPINNER));

			spinnerWidth = (int) Math.round(spinnerImage.getWidth()*scaleWidthBy);
			spinnerHeight = (int) Math.round(spinnerImage.getHeight()*scaleHeightBy);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
    public void paintComponent(Graphics g) {
        AffineTransform at = new AffineTransform();
	    Graphics2D g2 =(Graphics2D) g;
	    
	    at.rotate(rotateBy,spinnerWidth/2+spinnerPosX, spinnerHeight/2+spinnerPosY);
	    at.translate(spinnerPosX, spinnerPosY);
	    at.scale(scaleHeightBy, scaleWidthBy);
		g2.drawImage(splashImage,0, 0, null);
		g2.drawImage(spinnerImage,at, null);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("",Font.PLAIN,15));
		g2.drawString(HASplashScreen.currentAction, 10, HASplashScreen.frameHeight - 12);
    }
	public void refresh(){
		this.repaint();
	    rotateBy += Math.toRadians(5);	    
	}

}
