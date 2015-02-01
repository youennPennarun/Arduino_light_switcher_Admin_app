package main;

import gui.HA_GUI;
import gui.splashScreen.HASplashScreen;
import utils.ImageUtils;
import utils.PropertiesUtils;
import communication.HASocket;

public class HAMain {
	/**
	 * Connection timeout in ms
	 */
	public static final int CONNECTION_TIMEOUT = 30000;
	public static boolean INIT_WEATHER = true;

	private static void setParams(String[] args) {
		int i;
		for(i = 0 ; i < args.length ; i++){
			String arg = args[i];
			arg.replaceAll(" ", "");
			if(arg.equals("--set-frame")){
				i++;
				if(args[i] != null){
					HASplashScreen.showFrame = args[i];
				}
				break;
			}else if(arg.equals("--init-weather")){
				i++;
				if(args[i] != null){
					if(args[i].equals("0")){
						HAMain.INIT_WEATHER = false;
					}else if(args[i].equals("1")){
						HAMain.INIT_WEATHER = true;
					}
				}
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HAMain.setParams(args);
		System.out.println(System.getProperty("user.home"));
		//PropertiesUtils.init();

		Thread loadingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				HASplashScreen.setAction("Loading images");
				ImageUtils.preload();
				HASplashScreen.setAction("Connecting to server");
				HASocket socket = HASocket.newHASocket();
				HA_GUI.newGUI();
				socket.connect();
				
			}
		});
		loadingThread.start();
		HASplashScreen.splash();
	}

}
