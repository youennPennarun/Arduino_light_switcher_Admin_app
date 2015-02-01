package gui.settings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import communication.HASocket;

import data.settings.Settings;

public class SettingsActionPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2980595936240344914L;
	private JButton okButton = new JButton("Apply");
	private JButton cancelButton = new JButton("Cancel");
	
	SettingsActionPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalGlue());
		this.add(okButton);
		this.add(Box.createRigidArea(new Dimension(10,0)));
		this.add(cancelButton);
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.saveChanges();
				HASocket.getSettingsEmitter().saveSettings();
				SettingsFrame.disposeFrame();
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.discardChanges();
				SettingsFrame.disposeFrame();
			}
		});
	}

}
