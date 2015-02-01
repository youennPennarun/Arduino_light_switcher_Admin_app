package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import utils.ImageData;

public class StatusButton extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5940951653182000102L;
	private static int buttonWidth = 50;
	private static int buttonHeight = 75;
	protected boolean isOn = true;
	protected ImageIcon imageOn = new ImageIcon(ImageData.STATUS_ON.getImage());
	protected ImageIcon imageOff = new ImageIcon(ImageData.STATUS_OFF.getImage());

	protected JTextArea mainLabel = new JTextArea("");
	protected Color mainLabelColor = new Color(255,255,255);
	protected Font mainLabelFont = new Font("",Font.PLAIN,16);
	
	protected JLabel statusLabel = new JLabel("ON");

	private Color backgroundColor;
	private Color backgroundColorHover = new Color(120,120,120);
	private Color backgroundColorIni = new Color(100,100,100);

	protected JLabel statusIconLabel = new JLabel(imageOn);

	protected Color onColor = new Color(255,255,255);
	protected Color offColor = new Color(200,200,200);
	private int arc = 5;

	public StatusButton(){
		this.setOpaque(false);
		initComponents();
	}

	private void initComponents(){
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonWidth = imageOn.getIconWidth()+30;
		this.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
		SimpleAttributeSet attrib = new SimpleAttributeSet();
		StyleConstants.setAlignment(attrib, StyleConstants.ALIGN_CENTER);
		DefaultStyledDocument lsd = new DefaultStyledDocument ();
		try {
		lsd.insertString(0,mainLabel.getText(),attrib);
		} catch (Exception ex) {}
		mainLabel = new JTextArea(lsd);
		mainLabel.setForeground(mainLabelColor);
		mainLabel.setFont(mainLabelFont);
		mainLabel.setLineWrap(true);
		mainLabel.setWrapStyleWord(true);
		mainLabel.setOpaque(false);

		backgroundColor = backgroundColorIni;
		this.setOpaque(false);
		statusLabel.setForeground(onColor);
		statusLabel.setFont(new Font("",Font.PLAIN,30));
		Box vBox = Box.createVerticalBox();
		vBox.add(statusLabel);
		vBox.add(statusIconLabel);
		this.add(vBox);
		

		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				backgroundColor=backgroundColorIni;
				repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				backgroundColor=backgroundColorHover;
				repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	protected void setOn(){
		if(isOn){
			isOn=false;
			statusIconLabel.setIcon(imageOff);
			statusLabel.setForeground(offColor);
			statusLabel.setText("OFF");
		}else{
			isOn=true;
			statusIconLabel.setIcon(imageOn);
			statusLabel.setForeground(onColor);
			statusLabel.setText("ON");
		}
	}
	protected void setOn(boolean isOn){
		if(isOn){
			isOn=true;
			statusIconLabel.setIcon(imageOn);
			statusLabel.setForeground(onColor);
			statusLabel.setText("ON");
			
		}else{
			isOn=false;
			statusIconLabel.setIcon(imageOff);
			statusLabel.setForeground(offColor);
			statusLabel.setText("OFF");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(backgroundColor);
		g2.fillRoundRect(0, 0, buttonWidth, buttonHeight, arc , arc);
	}

}
