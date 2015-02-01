package communication;

import java.net.SocketTimeoutException;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

import main.HAMain;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.EngineIOException;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

/**
 * 
 * @author garbage
 */
public class HASocket {
	
	/**
	 * url: server URL
	 */
	private final static String url = "https://home-automation-server.herokuapp.com/";
	//private final static String url = "http://localhost:8080/";

	/**
	 * SocketIO Client
	 * @see com.github.nkzawa.socketio.client.Socket
	 */
	private Socket socket;
	
	/**
	 * Current instance of HASocket
	 * @see HASocket
	 */
	private static HASocket haSocket;

	/**
	 * Instance of LightEmiter
	 * @see LightEmitter
	 */
	private static LightEmitter lightEmitter;
	/**
	 * Instance of SettingsEmitter
	 * @see SettingsEmitter
	 */
	private static SettingsEmitter settingsEmitter;
	public boolean ready = false;
	private HASocket() {
	}
	
	
	/**
	 * Connect to the server
	 * @see Socket
	 */
	public void connect(){
		try {
			IO.Options opts = new IO.Options();
			opts.timeout=HAMain.CONNECTION_TIMEOUT;
			socket = IO.socket(HASocket.url,opts);
			socket.on(
					Socket.EVENT_CONNECT, new Emitter.Listener() {
						@Override
						public void call(Object... args) {
							System.out.println("connected");
							getLightEmitter().getLightStates();
							getSettingsEmitter().getSettings();
						}
					}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
						@Override
						public void call(Object... args) {
							JOptionPane.showMessageDialog(null,
								    "Disconnected from server",
								    "Socket error",
								    JOptionPane.ERROR_MESSAGE);
							System.exit(0);
						}
					}).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
						@Override
						public void call(Object... args) {
							JOptionPane.showMessageDialog(null,
								    "Timeout exception",
								    "Socket error",
								    JOptionPane.ERROR_MESSAGE);
							System.exit(0);
						}
					}).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
						@Override
						public void call(Object... args) {
							String errorMessage = "";
							for(Object arg : args){
								Exception e = (Exception)arg;
								errorMessage = e.getLocalizedMessage();
							}
							JOptionPane.showMessageDialog(null,
								    "Connection failed ("+errorMessage+")",
								    "Socket error",
								    JOptionPane.ERROR_MESSAGE);
							System.exit(0);
						}
					}).on(Socket.EVENT_ERROR, new Emitter.Listener() {
						@Override
						public void call(Object... args) {
							JOptionPane.showMessageDialog(null,
								    "Connection failed",
								    "Socket error",
								    JOptionPane.ERROR_MESSAGE);
							System.exit(0);
						}
					}).on(Socket.EVENT_RECONNECT_ERROR, new Emitter.Listener() {
						@Override
						public void call(Object... args) {
							JOptionPane.showMessageDialog(null,
								    "Connection failed",
								    "Socket error",
								    JOptionPane.ERROR_MESSAGE);
							System.exit(0);
						}
					});

			lightEmitter = (new LightEmitter(this));
			settingsEmitter = ((new SettingsEmitter(this)));
			socket.connect();
			
		} catch (URISyntaxException e) {
			System.exit(0);
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}
	
	public static HASocket getHaSocket() {
		return haSocket;
	}
	public static LightEmitter getLightEmitter() {
		return lightEmitter;
	}
	public static SettingsEmitter getSettingsEmitter() {
		return settingsEmitter;
	}
	
	
	/**
	 * Create a new instance of HASocket
	 * @return The new instance of HASocket
	 */
	public static HASocket newHASocket(){
		if(haSocket != null){
			haSocket.getSocket().disconnect();
		}
		haSocket = new HASocket();
		return haSocket;
	}
	
	
}