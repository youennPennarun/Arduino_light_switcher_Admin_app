package gui.components;

import gui.Frames;
import gui.HA_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageButton extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 135046033979238129L;
	private int panelWidth = 200;
	private int panelHeight = 200;
	private int arc = 30;
	private Color backgroundColor;
	private Color backgroundColorHover = new Color(56,175,231,255);
	private Color backgroundColorIni = new Color(100,100,100);


	private JLabel icon;
	private int iconWidth = 200;
	private int iconHeight = 170;
	private JLabel label;
	private Font labelFont = new Font(" TimesRoman ", Font.PLAIN, 16);
	private Color labelColor;
	private Color labelColorInit = new Color(255,255,255,255);
	private Color labelColorHover = new Color(0,0,0,255);

	private String id;
	private Image image;
	private Frames frame;

	public ImageButton(Image img,String labelString){
		this.setImage(img);
		this.setId(labelString);
		this.iniButton();
	}

	public ImageButton(Image image, Frames frame) {
		this.setImage(image);
		this.setId(frame.getName());
		this.setFrame(frame);

		this.iniButton();		
	}
	
	private void iniButton() {

		this.setMaximumSize(new Dimension(panelWidth,panelHeight));
		labelColor=labelColorInit;
		this.setOpaque(false);
		this.backgroundColor=backgroundColorIni;
		if(image==null){
			this.icon=new JLabel();
		}else{
			this.icon = new JLabel(new ImageIcon(image.getScaledInstance(iconWidth, iconHeight,Image.SCALE_SMOOTH)));
		}
		this.label=new JLabel(this.id);
		label.setBackground(new Color(0,0,0,0));
		this.label.setForeground(labelColor);
		label.setFont(labelFont);
		this.setPreferredSize(new Dimension(panelWidth,panelHeight));
		initComponents();
		ImageButton iB = this;
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				iB.isHover(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				iB.isHover(false);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});
		
	}
	private void initComponents(){
		Box vbox = Box.createVerticalBox();
		Box hBox1 = Box.createHorizontalBox();
		hBox1.add(Box.createHorizontalGlue());
		hBox1.add(icon);
		hBox1.add(Box.createHorizontalGlue());

		Box hBox2 = Box.createHorizontalBox();
		hBox2.add(Box.createHorizontalGlue());
		hBox2.add(label);
		hBox2.add(Box.createHorizontalGlue());
		vbox.add(hBox1);
		vbox.add(hBox2);
		this.add(vbox);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(backgroundColor);
		g2.fillRoundRect(0, 0, panelWidth, panelHeight, arc, arc);
	}

	public void isHover(boolean isHover){
		if(isHover){
			backgroundColor = backgroundColorHover;
			label.setForeground(labelColorHover);
		}else{
			backgroundColor = backgroundColorIni;
			label.setForeground(labelColorInit);
		}
		this.revalidate();
		HA_GUI.mainFrame.repaint();
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

	public Frames getFrame() {
		return frame;
	}

	public void setFrame(Frames frame) {
		this.frame = frame;
	}

}
