import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelControl extends JPanel{
	private PanelDibujo pd;
	private JComboBox<String> lstFrom, lstTo;
	private JButton btnGo;
	private String[] nombres, 
					allNodos; //Todos los nodos, incluyendo A - AD
	private int[] allX,allY; //Coordenadas X y Y de todos los nodos
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
	
	public void getNodeProperties() {
		BufferedReader bf = null;
		StringTokenizer st = null;
		int i = 0;
		try {
			bf = new BufferedReader(new FileReader("src\\Proyecto-coordenadas.csv"));
			String line;
			while ((line = bf.readLine()) != null) {
				st = new StringTokenizer(line, ",");
		        allNodos[i] = st.nextToken();
		        allX[i] = Integer.parseInt(st.nextToken());
		        allY[i] = Integer.parseInt(st.nextToken());
		        i++;
		    }
		}
		catch(IOException e) {
			System.out.println("fail");
		}
		finally {
			 try {
			        bf.close();
			        System.out.println("sí jaló alv");
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
		}
	}
	
	public PanelControl() {
		super();
		this.setPreferredSize(new Dimension(260, 719));
		this.nombres = new String[54];
		this.allNodos = new String[84];
		this.allX = new int[84];
		this.allY = new int[84];
		this.readNames();
		lstFrom = new JComboBox<String>(nombres);
		lstTo = new JComboBox<String>(nombres);
		lbFrom = new JLabel("¿De dónde?");
		lbTo = new JLabel("¿A dónde?");
		btnGo = new JButton("Dale");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		this.add(lbFrom,BorderLayout.LINE_START);
		this.add(lstFrom, BorderLayout.LINE_END);
		this.add(lbTo, BorderLayout.LINE_START);
		this.add(lstTo, BorderLayout.LINE_END);
		this.add(btnGo);
	}
	
	public String[] getNombres() {
		return this.nombres;
	}
	
	public int[] getAllX() {
		return this.allX;
	}
	
	public int[] getAllY() {
		return this.allY;
	}
	
	public String[] getAllNodos() {
		return this.allNodos;
	}
	
	/*public static void main(String[] args) {
		PanelControl pc = new PanelControl();
		pc.getNodeProperties();
		int[] x = pc.getAllX();
		int[] y = pc.getAllY();
		String[] nombres = pc.getAllNodos();
		
		for(int i = 0; i<x.length; i++) {
			System.out.print(nombres[i] + ", x:" + x[i] + ", y:"+y[i]);
			System.out.println();
		}
	}*/
}
