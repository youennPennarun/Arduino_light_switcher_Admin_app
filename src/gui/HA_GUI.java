package gui;
import gui.components.TopBar;
import gui.components.TopMenu;
import gui.lights.LightsPanel;
import gui.settings.SettingsFrame;
import gui.weather.WeatherPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

import communication.HASocket;

public class HA_GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1452875404419875756L;
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 700;
	private JPanel content = new JPanel();
	private TopMenu topMenu;
	private JPanel mainPanel;
	private TopBar topBar;
	public static Color backgroundColor = new Color(50,50,50);
	private CustomPanel currentContent;
	private CustomPanel previousPanel = null;


	public static HA_GUI mainFrame;

	private HA_GUI(){
		this.setUndecorated(true);
		this.setContentPane(content);
		this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.content.setBackground(new Color(50,50,50));
		this.initComponent();
		this.setLocationRelativeTo(null);
		mainFrame = this;

	}
	private void initComponent(){
		topBar = new TopBar(this);
		topMenu = new TopMenu(this);
		topMenu.setVisible(false);
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setOpaque(false);
		currentContent = (new MainMenu(this));

		Box vBox = Box.createVerticalBox();
		vBox.add(topBar);
		vBox.add(topMenu);
		vBox.add(Box.createRigidArea(new Dimension(0, 40)));
		mainPanel.add(getCurrentContent());
		vBox.add(mainPanel);

		content.add(vBox);

	}

	public void changeFrame(Frames frame){
		boolean refresh = true;
		switch (frame) {
		case WEATHER:
			mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			previousPanel=getCurrentContent();
			currentContent = (new WeatherPanel());
			break;
		case LIGHTS:
			mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			previousPanel=getCurrentContent();
			currentContent = (new LightsPanel());
			break;
		case PREVIOUS:
			CustomPanel tmp = previousPanel;
			previousPanel=getCurrentContent();
			currentContent = (tmp);
			break;
		case SETTINGS:
			refresh = false;
			SettingsFrame.showFrame();
			break;
		default:
			refresh = false;
			break;
		}
		if(refresh && getCurrentContent() != null){
			mainPanel.removeAll();
			getCurrentContent().setDefaultValues();
			mainPanel.add(getCurrentContent());

			if((getCurrentContent() instanceof MainMenu)){
				mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				topMenu.setVisible(false);
			}else{
				topMenu.setVisible(true);
			}

			this.revalidate();
			this.repaint();
		}
	}
	public static void refreshView(){
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.getCurrentContent().updateValues();
	}
	public static void closeApplication() {
		if(HASocket.getHaSocket().getSocket() != null)
			HASocket.getHaSocket().getSocket().disconnect();
		System.exit(0);
	}


	public CustomPanel getCurrentContent() {
		return currentContent;
	}
	
	public static HA_GUI newGUI(){
		if(mainFrame != null){
			mainFrame.dispose();
		}
		mainFrame = new HA_GUI();
		return mainFrame;
	}
	public static HA_GUI getMainFrame(){
		return mainFrame;
	}
	
	

}