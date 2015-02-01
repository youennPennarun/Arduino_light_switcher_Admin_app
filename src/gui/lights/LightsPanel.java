package gui.lights;

import gui.CustomPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import data.lights.Light;

public class LightsPanel extends CustomPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6936479454411386916L;
	private ArrayList<Light> lights;
	private ArrayList<LightStatusButton> lightList = new ArrayList<LightStatusButton>();

	private EmptyBorder emptyBorder = new EmptyBorder(0, 120, 0, 40);

	public LightsPanel(){
		this.setBorder(emptyBorder);
		this.setOpaque(false);
		lights = Light.getLightList();
		this.initComponents();


	}

	private void initComponents() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();


		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;

		for(Light light : lights){
			JPanel p = new JPanel(new BorderLayout(0,0));
			p.setPreferredSize(new Dimension(450,75));
			p.setOpaque(false);

			JLabel lightNameLabel = new JLabel(light.getLabel());
			lightNameLabel.setForeground(Color.WHITE);
			lightNameLabel.setFont(new Font("",Font.PLAIN,23));
			lightNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			p.add(lightNameLabel, BorderLayout.WEST);
			
			LightStatusButton s = new LightStatusButton(light);
			lightList.add(s);
			p.add(s, BorderLayout.EAST);

			this.add(p,gbc);
			gbc.insets = new Insets(10, 0, 0, 0);
			gbc.gridy++;
			JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
			separator.setPreferredSize(new Dimension(450,30));
			this.add(separator,gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			
		}
	}

	@Override
	public void setDefaultValues() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateValues() {
		for(LightStatusButton button : lightList){
			button.updateValues();
		}
	}

}
