package gui.settings;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.settings.Settings;
import data.weather.CityData;
import data.weather.ResultSearch;
import data.weather.SearchWeatherData;

public class SearchCitySettings extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2954507945668092927L;
	private JLabel weatherSearchLabel = new JLabel("Search city");
	private JTextField weatherSearchTF = new JTextField(5);
	private JButton weatherSearchButton = new JButton("Search");

	private JPanel resultPane = new JPanel();

	SearchCitySettings(WeatherSettingsPanel weatherSettingsPane){
		SearchCitySettings thisPane = this;
		resultPane.setLayout(new BoxLayout(resultPane, BoxLayout.Y_AXIS));
		weatherSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!weatherSearchTF.getText().equals("")){
					SearchWeatherData search = SearchWeatherData.jsonSearchCity(weatherSearchTF.getText());
					resultPane.removeAll();
					resultPane.setAlignmentX(LEFT_ALIGNMENT);
					for(ResultSearch result : search.getResultList()){
						CityData city = (CityData) result; 
						JLabel resultLabel = new JLabel(city.getName()+"("+city.getCountry()+")");
						resultLabel.addMouseListener(new MouseAdapter()  
						{  
							public void mouseClicked(MouseEvent e)  
							{  
								Settings.cityIdNew = city.getId();
								Settings.cityNameNew = city.getName();
								Settings.cityCountryNew = city.getCountry();
								weatherSettingsPane.setCurrentCity(city.getName(),city.getCountry());
								thisPane.dispose();
							}  
						}); 
						resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
						resultPane.add(resultLabel);
					}
					resultPane.revalidate();
				}
			}
		});

		this.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		Box vBox = Box.createVerticalBox();
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.add(weatherSearchLabel);
		p.add(weatherSearchTF);
		p.add(weatherSearchButton);
		vBox.add(p);
		vBox.add(resultPane);
		this.add(vBox);
		


		this.setSize(new Dimension(300,350));
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
}
