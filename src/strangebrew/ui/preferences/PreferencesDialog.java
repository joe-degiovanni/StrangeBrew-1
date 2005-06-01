/**
    StrangeBrew Java - a homebrew recipe calculator
    Copyright (C) 2005  Drew Avis
 
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package strangebrew.ui.preferences;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import strangebrew.Options;

/**
 * @author zymurgist
 * 
 * This class creates a tabbed dialog box with all the preferences
 * used by the application.  The constructor will initialize all the
 * UI components to values from the Options object in the constructor.
 * 
 * If the dialog box is closed with the OK button then the Options object
 * given in the constructor will be updated with new values entered by
 * the user.  If the dialog box is closed any other way then no changes will
 * be made to the Options object.
 */
public class PreferencesDialog extends JDialog
{
	private JTabbedPane tabs = null;
	private boolean m_savePreferences = false;
	private Options m_preferences = null;
	private TimePanel m_time = null;
	
	public PreferencesDialog(Dialog owner, Options preferences)
	{
		super(owner, "Recipe Preferences", true);
		m_preferences = preferences;
		layoutUi();
		setLocation(owner.getLocation());
	}

	public PreferencesDialog(Frame owner, Options preferences)
	{
		super(owner, "Recipe Preferences", true);
		m_preferences = preferences;
		layoutUi();
		setLocation(owner.getLocation());
	}

	private void layoutUi()
	{
		m_time = initializeTimePanel();
		
		tabs = new JTabbedPane();
		tabs.addTab("Time", m_time);
		
		JPanel buttons = new JPanel();
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttons.add(cancelButton);
		buttons.add(okButton);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(BorderLayout.CENTER, tabs);
		getContentPane().add(BorderLayout.SOUTH, buttons);
		
		setSize(400, 400);
		
		okButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				updateTime();
				m_preferences.saveProperties();
				hide();
			}
		});
		
		cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				hide();
			}
		});
	}
	
	private TimePanel initializeTimePanel()
	{
		TimePanel time = new TimePanel();
		time.setDayStart(m_preferences.getProperty("optBrewDayStart"));
		time.setPrep(m_preferences.getProperty("optPrepTime"));
		time.setSparge(m_preferences.getProperty("optSpargeTime"));
		time.setTimeToBoil(m_preferences.getProperty("optGetToBoilTime"));
		time.setBoilTime(m_preferences.getProperty("optBoilTime"));
		time.setChill(m_preferences.getProperty("optChillTime"));
		time.setCleanup(m_preferences.getProperty("optCleanTime"));
		
		return time;
	}
	
	private void updateTime()
	{
		m_preferences.setProperty("optBrewDayStart", m_time.getDayStart());
		m_preferences.setProperty("optPrepTime", m_time.getPrep());
		m_preferences.setProperty("optSpargeTime", m_time.getSparge());
		m_preferences.setProperty("optGetToBoilTime", m_time.getTimeToBoil());
		m_preferences.setProperty("optBoilTime", m_time.getBoilTime());
		m_preferences.setProperty("optChillTime", m_time.getChill());
		m_preferences.setProperty("optCleanTime", m_time.getCleanup());
	}
	
	public void show()
	{
		throw new RuntimeException("Call showPreferences() instead.");
	}
	
	public void showPreferences()
	{
		super.show();
	}
}
