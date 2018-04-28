import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelControl extends JPanel{
	private PanelDibujo pd;
	private JComboBox<String> lstFrom, lstTo;
	private JButton btnGo;
	private String[] nombres;
	private JLabel lbFrom, lbTo;
	//private static BufferedReader bf = null;
	
	//Metodo para leer los nombres de los lugares para los combobox
	public void readNames() {
		BufferedReader bf = null;
		int i = 0;
		try {
			bf = new BufferedReader(new FileReader("src\\Proyecto_ Lugares - Hoja 1.csv"));
			String line;
			while ((line = bf.readLine()) != null) {
		        nombres[i] = line;
		        i++;
		    }
		}
		catch(IOException e) {
			System.out.println("fail");
		}
		finally {
			 try {
			        bf.close();
			        System.out.println("arre la que barre");
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
		}
	}
	
	public PanelControl() {
		super();
		this.setPreferredSize(new Dimension(260, 719));
		this.nombres = new String[54];
		this.readNames();
		lstFrom = new JComboBox<String>(nombres);
		lstTo = new JComboBox<String>(nombres);
		lbFrom = new JLabel("¿De dónde?");
		lbTo = new JLabel("¿A dónde?");
		this.add(lbFrom,BorderLayout.LINE_START);
		this.add(lstFrom, BorderLayout.LINE_END);
		this.add(lbTo, BorderLayout.LINE_START);
		this.add(lstTo, BorderLayout.LINE_END);
		
	}
	
	public String[] getNombres() {
		return this.nombres;
	}
	
	/*public static void main(String[] args) {
		PanelControl pc = new PanelControl();
		pc.nombres = new String[54];
		pc.readNames();
		System.out.println(pc.getNombres().length);
		int i = 0;
		for(String a:pc.getNombres()) {
			System.out.println(""+i+" "+a);
			i++;
		}
	}*/
}
