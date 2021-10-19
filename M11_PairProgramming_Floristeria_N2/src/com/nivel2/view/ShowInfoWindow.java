package com.nivel2.view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShowInfoWindow {

	public static void showInfo(String message){
		JOptionPane.showMessageDialog(null, message);
	}

	public static void showInfo(List<String> list) {
		String listString = "";

		for (String s : list) {
			listString += s.toString() + "\n";
		}
		JTextArea msg = new JTextArea(listString);
		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);
		msg.setRows(list.size()+2);
		msg.setSize(900, 300);

		JScrollPane scrollPane = new JScrollPane(msg);

		JOptionPane.showMessageDialog(null, scrollPane);

	}
}
