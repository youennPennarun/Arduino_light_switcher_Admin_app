package gui.weather;

import gui.CustomPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.DataUtils;
import data.weather.WeatherData;

public class DayWeatherIconPanel extends CustomPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7215399477811286872L;
	private static final int ICON_HEIGHT = 125;
	private Color backgroundColor = null;
	private Color backgroundColorHover = new Color(40,40,40);
	private Color backgroundColorSelected = new Color(45,45,45);
	private WeatherData weather;
	private boolean selected;
	private int id;

	public DayWeatherIconPanel(int id, WeatherData weather){
		this.setId(id);
		this.setWeather(weather);
		this.setOpaque(false);
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		this.setPreferredSize(new Dimension(weather.getImage().getWidth(getParent()),ICON_HEIGHT));
		JLabel dayLabel = new JLabel(DataUtils.dateToDay(weather.getDate()));
		JLabel weatherLabel = new JLabel(weather.getWeatherTitle());
		JLabel iconImgLabel = new JLabel(new ImageIcon(weather.getIcon()));
		iconImgLabel.setBackground(Color.WHITE);

		dayLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		weatherLabel.setForeground(WeatherPanel.CONTENT_COLOR);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		this.add(dayLabel,Component.LEFT_ALIGNMENT);
		gbc.gridy = 1;
		this.add(iconImgLabel);
		gbc.gridy = 2;
		this.add(weatherLabel,Component.LEFT_ALIGNMENT);

		setListener();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if(backgroundColor != null){
			g2.setColor(backgroundColor );
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}


	private void setListener() {
		DayWeatherIconPanel pane = this;
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				pane.isHover(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pane.isHover(false);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});

	}

	protected void isHover(boolean hover) {
		if(hover){
			backgroundColor = backgroundColorHover;
		}else if(!selected){
			backgroundColor = null;
		}
		this.repaint();
	}

	public void isSelected(boolean selected){
		this.selected = selected;
		if(selected){
			backgroundColor = backgroundColorSelected;
		}else{
			backgroundColor = null;
		}
		this.repaint();
	}

	public WeatherData getWeather() {
		return weather;
	}

	public void setWeather(WeatherData weather) {
		this.weather = weather;
	}

	@Override
	public void setDefaultValues() {
		// TODO Auto-generated method stub
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

