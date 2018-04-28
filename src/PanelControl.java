import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PanelControl extends JPanel{
	private PanelDibujo pd;
	private JComboBox<String> lstFrom, lstTo;
	private JButton btnGo;
	private static BufferedReader bf = null;
	
	//Metodo para leer los nombres de los lugares para los combobox
	public static void readNames() {
		//File nombres = new File();
		try {
			bf = new BufferedReader(new FileReader("Proyecto_Lugares - Hoja 1.csv"));
			String line;
			while ((line = bf.readLine()) != null) {
		        System.out.println(line);
		    }
		}
		catch(IOException e) {
			System.out.println("fail");
		}
		finally {
			 try {
			        bf.close();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
		}
	}
	
	public PanelControl() {
		super();
		this.setPreferredSize(new Dimension(260, 719));
		
	}
	
	public static void main(String[] args) {
		readNames();
	}
}
