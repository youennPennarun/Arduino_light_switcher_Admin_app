package gui.components;

import gui.CustomPanel;
import gui.HA_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TopBar extends CustomPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9223054177567219659L;
	public static final int PANEL_HEIGHT = 50;
	private JLabel timeLabel = new JLabel("");
	private Font timeFont = new Font("Arial", Font.BOLD, 34);
	private Color timeColor = new Color(255,255,255);
	private SimpleDateFormat sDate = new SimpleDateFormat("HH:mm");
	private static Date date;

	private Point initialClick;
	private JFrame parentFrame;

	private static Color backgroundColor = new Color(150,150,150);

	public TopBar(JFrame parentFrame){
		this.parentFrame=parentFrame;
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(parentFrame.getWidth(),PANEL_HEIGHT));
		this.setMinimumSize(new Dimension(parentFrame.getWidth(),PANEL_HEIGHT));
		this.setMaximumSize(new Dimension(parentFrame.getWidth(),PANEL_HEIGHT));
		this.setBackground(backgroundColor);
		this.setOpaque(false);
		timeLabel.setForeground(timeColor);
		timeLabel.setFont(timeFont);

		this.initComponents();
		this.setDragWindow();


		updateTime();

	}

	private void initComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;

		gbc.weightx = 100.0;
		gbc.weighty = 1.0;

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0,20,00,00);
		this.add(timeLabel,gbc);
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		gbc.weighty = 1;
		gbc.gridx = 2;
		gbc.gridy = 1;
		JLabel minimizeButton = new JLabel("_",JLabel.CENTER);
		minimizeButton.addMouseListener(new MouseAdapter()  
		{  
			public void mouseClicked(MouseEvent e)  
			{  
				parentFrame.setState(Frame.ICONIFIED);
			}  
		}); 
		gbc.insets = new Insets(0,00,00,00);
		minimizeButton.setOpaque(true);
		minimizeButton.setBackground(Color.BLUE);
		minimizeButton.setAlignmentX(CENTER_ALIGNMENT);
		minimizeButton.setPreferredSize(new Dimension(50,25));
		this.add(minimizeButton, gbc);


		JButton closeButton = new JButton("X");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(parentFrame instanceof HA_GUI){
					HA_GUI.closeApplication();
				}else{
					parentFrame.dispose();
				}
			}
		});
		gbc.gridx = 3;
		closeButton.setBackground(Color.RED);
		this.add(closeButton, gbc);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Color color1 = getBackground();
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
