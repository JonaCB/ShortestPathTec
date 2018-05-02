import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	private JLabel lbFrom, lbTo;
	private Grafo grafos;
	
	public void nodeProperties() {
		BufferedReader bf = null;
		StringTokenizer st = null;
		int i = 0,
				x,
				y;
		try {
			bf = new BufferedReader(new FileReader("src\\Proyecto-coordenadas.csv"));
			String line;
			while ((line = bf.readLine()) != null) {
				st = new StringTokenizer(line, ",");
		        allNodos[i] = st.nextToken();
		        x = Integer.parseInt(st.nextToken());
		        y = Integer.parseInt(st.nextToken());
		        grafos.addNodo(x, y, allNodos[i]);
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
	
	public PanelControl(PanelDibujo pd) {
		super();
		this.setPreferredSize(new Dimension(260, 719));
		//INICIALIZAR TODITO
		this.nombres = new String[56];
		this.allNodos = new String[86];
		this.grafos = new Grafo(86);
		this.nodeProperties();
		for(int i = 0; i<56;i++) {
			this.nombres[i] = this.allNodos[i];
		}
		lstFrom = new JComboBox<String>(nombres);
		lstTo = new JComboBox<String>(nombres);
		lbFrom = new JLabel("¿De dónde?");
		lbTo = new JLabel("¿A dónde?");
		btnGo = new JButton("Dale");
		lstFrom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					pd.paintImmediately(0, 0, 895, 719);
					Color c = new Color(255, 255, 135);
					int pos = grafos.getPosNodo((String) e.getItem());
					Nodo n = grafos.getNodo(pos);
					int x = n.getPosX();
					int y = n.getPosY();
					pd.pintaCirculo(pd.getGraphics(),c, x, y);
				}
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
