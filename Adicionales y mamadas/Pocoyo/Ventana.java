package Pocoyo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Ventana extends JFrame{
	
	
	/*
	 * Si no se especifica su layout, tiene border layput con Norte, Sur, este, oeste y centro.
	 * Si no se especifica en donde se agrega, por default se va al centro.
	 */
	public Ventana() {
		super("Mi primer ventana");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sirve para cerrar la ventna de verdad.
		MiPanel panelDibujo = new MiPanel();
		PanelControles controles = new PanelControles(panelDibujo);
		this.add(controles, BorderLayout.WEST);
		panelDibujo.setControles(controles);
		this.add(panelDibujo);
		this.pack();
		
		//Debe escribirse al final
		this.setVisible(true);
	}//Cierra constructor default.
	
	public static void main(String[] args) {
		Ventana ventana = new Ventana();
	}//Cierra main.
}
