package utils;

import gui.Frames;
import gui.HA_GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class DataUtils {


	public static String readUrl(String urlString){
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read); 

			return buffer.toString();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,
				    "Unable to get weather data. Please, try later",
				    "Server error",
				    JOptionPane.ERROR_MESSAGE);
			System.err.println(e.getLocalizedMessage());
			return null;
		}finally {
			if (reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static Double kelvinToCelsius(Double kelvin){
		return (kelvin - 273.15);
	}
	public static String roundDouble(Double valDouble) {
		BigDecimal val = new BigDecimal(String.valueOf(valDouble)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return val.toString();
	}
	public static String roundDouble(Double valDouble,int nb) {
		BigDecimal val = new BigDecimal(String.valueOf(valDouble)).setScale(nb, BigDecimal.ROUND_HALF_UP);
		return val.toString();
	}

	public static String dateToDay(Calendar calendar){
		SimpleDateFormat formatter = new SimpleDateFormat("E");
		return formatter.format(calendar.getTime());
	}
}
