package gui.components;

import gui.CustomPanel;
import gui.Frames;
import gui.HA_GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.ImageData;

public class TopMenu extends CustomPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3202429898555358683L;
	public static final int PANEL_HEIGHT = 50;
	private JLabel timeLabel = new JLabel("");
	private Font timeFont = new Font("Arial", Font.BOLD, 34);
	private Color timeColor = new Color(255,255,255);
	private SimpleDateFormat sDate = new SimpleDateFormat("HH:mm");
	private static Date date;

	private Point initialClick;
	private HA_GUI parentFrame;

	private static Color backgroundColor = new Color(150,150,150);
	private ImageIcon arrowBack;

	private JLabel lights = new JLabel(new ImageIcon(ImageData.LIGHT_ICON.getImageResized()));
	private JLabel cameras = new JLabel(new ImageIcon(ImageData.CAMERA_ICON.getImageResized()));
	private JLabel heaters = new JLabel(new ImageIcon(ImageData.HEATHER_ICON.getImageResized()));
	private JLabel speakers = new JLabel(new ImageIcon(ImageData.SPEAKER_ICON.getImageResized()));
	private JLabel alarm = new JLabel(new ImageIcon(ImageData.ALARM_ICON.getImageResized()));
	private JLabel weather = new JLabel(new ImageIcon(ImageData.WEATHER_ICON.getImageResized()));
	private JLabel settings = new JLabel(new ImageIcon(ImageData.SETTINGS_ICON.getImageResized()));


	public TopMenu(HA_GUI parentFrame){
		this.parentFrame=parentFrame;
		this.setLayout(new BorderLayout());

		this.setPreferredSize(new Dimension(parentFrame.getWidth(),PANEL_HEIGHT));
		this.setBackground(backgroundColor);
		this.setOpaque(false);
		timeLabel.setForeground(timeColor);
		timeLabel.setFont(timeFont);


		arrowBack = new ImageIcon(ImageData.ARRAOW_BACK.getImage().getScaledInstance(75, 50,Image.SCALE_SMOOTH));
		this.initComponents();
		this.setDragWindow();


		updateTime();

	}

	private void initComponents() {
		MouseAdapter mA = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == lights){
					parentFrame.changeFrame(Frames.LIGHTS);
				}else if(e.getSource() == cameras){
					
				}else if(e.getSource() == heaters){
					
				}else if(e.getSource() == speakers){
					
				}else if(e.getSource() == alarm){
					
				}else if(e.getSource() == weather){
					parentFrame.changeFrame(Frames.WEATHER);
				}else if(e.getSource() == settings){
					parentFrame.changeFrame(Frames.SETTINGS);
				}
			}
		};
		
		
		JLabel arrowLabel = new JLabel(arrowBack);
		arrowLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentFrame.changeFrame(Frames.PREVIOUS);
			}
		});
		JPanel pLeft = new JPanel();
		pLeft.setOpaque(false);
		pLeft.add(arrowLabel);
		this.add(pLeft,BorderLayout.WEST);

		JPanel pCenter = new JPanel();
		pCenter.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		lights.addMouseListener(mA);
		pCenter.add(lights);
		cameras.addMouseListener(mA);
		pCenter.add(cameras);
		heaters.addMouseListener(mA);
		pCenter.add(heaters);
		speakers.addMouseListener(mA);
		pCenter.add(speakers);
		alarm.addMouseListener(mA);
		pCenter.add(alarm);
		weather.addMouseListener(mA);
		pCenter.add(weather);
		settings.addMouseListener(mA);
		pCenter.add(settings);
		this.add(pCenter,BorderLayout.CENTER);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Color color1 = getBackground().darker();
		Color color2 = color1.darker();
		int w = getWidth();
		int h = getHeight();
		GradientPaint gp = new GradientPaint(
				0, 0, color1, 0, h, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);

	}

	public void updateTime(){
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					date = new Date();
					timeLabel.setText(sDate.format(date));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
	private void setDragWindow() {
		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
				getComponentAt(initialClick);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				// get location of Window
				int thisX = parentFrame.getLocation().x;
				int thisY = parentFrame.getLocation().y;

				// Determine how much the mouse moved since the initial click
				int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
				int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

				// Move window to this position
				int X = thisX + xMoved;
				int Y = thisY + yMoved;
				parentFrame.setLocation(X, Y);
			}
		});
	}

	@Override
	public void setDefaultValues() {
		// TODO Auto-generated method stub

	}

}
