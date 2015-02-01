package gui.splashScreen;

import gui.Frames;
import gui.HA_GUI;

import java.awt.Frame;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import communication.LightEmitter;
import communication.SettingsEmitter;
import data.weather.CityData;

public class HASplashScreen extends Frame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2587185011927863855L;
	static final String SPLASH = "/images/HA_Splash.jpg";
	static final String SPINNER = "/images/spinner.png";
	
	public static int frameWidth;
	public static int frameHeight;
	public static String showFrame;


	private static HASplashScreen instance;
	static String currentAction;

	public boolean visible = true;
	
	public static Map<Class<?>,Integer> loadingMap = new HashMap<Class<?>, Integer>();



	private HASplashScreen(Image image) {
		super();
		HASplashScreen frame = this;
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(image));
		this.add(label);  
		Spinner sp = new Spinner();
		Thread spThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(frame.visible){
					sp.refresh();
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					frame.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		this.add(sp);
		spThread.start();
		this.setUndecorated(true);
		//this.setAlwaysOnTop(true);
		frameWidth = image.getWidth(getParent());
		frameHeight = image.getHeight(getParent());
		this.setSize(frameWidth,frameHeight);

		this.setLocationRelativeTo(null);
	}
	

	public static void splash(URL imageURL) {
		if (imageURL != null) {
			//splash(Toolkit.getDefaultToolkit().createImage(imageURL));
		}
	}

	public static void splash() {
		System.out.println("showing splash screen");
		loadingMap.put(LightEmitter.class, 0);
		loadingMap.put(SettingsEmitter.class, 0);
		loadingMap.put(CityData.class, 0);
		
		Image image=null;
		try {
			image = ImageIO.read(HASplashScreen.class.getResource(HASplashScreen.SPLASH));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (instance == null && image != null) {
			instance = new HASplashScreen(image);
			instance.setVisible(true);

		}
	}
	public static void stop(){
		instance.visible = false;
		instance.setVisible(false);
	}

	public static void done(Class<?> c) {
		boolean done = true;
		System.out.println(c+" "+loadingMap.get(c));
		loadingMap.put(c, loadingMap.get(c) + 1);
		for (Entry<Class<?>, Integer> entry : loadingMap.entrySet())
		{
		    if(entry.getValue() == 0){
		    	done = false;
		    	break;
		    }
		}
		if(done)
			done();
	}
	private static void done(){
		if(showFrame != null){
			HA_GUI.getMainFrame().changeFrame(Frames.findFrame(showFrame));
		}
		HA_GUI.getMainFrame().setVisible(true);
		instance.setVisible(false);
		
	}

	public static void setAction(String string) {
		HASplashScreen.currentAction=string;
		
	}
}