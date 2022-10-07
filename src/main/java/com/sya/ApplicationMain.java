package com.sya;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JDialog;
import java.sql.*;
import java.util.ArrayList;

public class ApplicationMain extends JDialog 
{

	public ApplicationMain() {
		String jdbcURL = "jdbc:postgresql://127.0.0.1:5432/chinook";
		String username = "postgres";
		String password = "your_password_here";

		try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
			String sql = "select * from \"Track\"";

			Statement statement = connection.createStatement();

			ResultSet result = statement.executeQuery(sql);

			ArrayList<String> trackNames = new ArrayList<>();
			ArrayList<String> trackComposers = new ArrayList<>();

			while (result.next()) {
				String trackName = result.getString("Name");
				String trackComposer = result.getString("Composer");

				trackNames.add(trackName);
				trackComposers.add(trackComposer);
			}

			Frame f = new Frame();
			f.setSize(1300, 300);

			Font font = new Font("SansSerif", Font.PLAIN, 22);

			Label label = new Label("You selected track '" + trackNames.get(0) + "' by composers " + trackComposers.get(0));
			label.setForeground(Color.BLACK);
			label.setFont(font);
			f.add(label);

			f.setVisible(true);
			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

		public static void main ( final String[] args)
		{
			new ApplicationMain();
		}
	}
