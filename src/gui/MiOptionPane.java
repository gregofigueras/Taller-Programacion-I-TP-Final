package gui;

import java.awt.Component;

import javax.swing.JOptionPane;

import vista.IOptionPane;

public class MiOptionPane implements IOptionPane {
    public MiOptionPane() {
        super();
    }

    public void ShowMessage(Component parent, String mensaje) {
        JOptionPane.showMessageDialog(parent, mensaje);
    }


	@Override
	public void ShowMessage(String arg0) {
		// TODO Auto-generated method stub
		
	}
}
