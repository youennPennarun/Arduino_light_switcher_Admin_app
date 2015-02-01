package gui.settings;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.ImageData;

public class SettingsMenuButton extends JPanel{
	private Image image;
	private String label;

	public SettingsMenuButton(Image image,String label){
		this.image=image;
		this.label=label;
		
		this.initComponents();
	}

	private void initComponents() {
		this.setBackground(SettingsMenu.BACKGROUND_COLOR.brighter());
		
		int width = (int) (Math.round(SettingsFrame.FRAME_WIDTH*SettingsFrame.SETTINGS_MENU_HEIGHT_RATIO));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel imgLabel = new JLabel(new ImageIcon(image));
		imgLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(imgLabel);
		JLabel labelLabel = new JLabel(label);
		labelLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(labelLabel);
		this.setMinimumSize(new Dimension(width,80));
		this.setMaximumSize(new Dimension(width,80));
		
		SettingsMenuButton panel = this;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(getBackground().darker());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setBackground(getBackground().brighter());
			}
		});
	}
}
