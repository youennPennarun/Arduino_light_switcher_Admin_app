package gui;

import javax.swing.JPanel;

public abstract class CustomPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1350328332627073310L;
	public abstract void setDefaultValues();
	public void updateValues(){};

}
