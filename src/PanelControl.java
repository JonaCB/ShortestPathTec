import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelControl extends JPanel{
	private PanelDibujo pd;
	private JComboBox<String> lstFrom, lstTo;
	private JButton btnGo,
					btnInvertir;
	private String[] nombres, 
					allNodos; //Todos los nodos, incluyendo A - AD
	private JLabel lbFrom, lbTo;
	private Grafo grafos;
	private Nodo selectedStart,
				selectedEnd;
	private boolean daleCandela; //Booleano para saber si pintar o no las l�neas 
	private Nodo[] rutita;
	private JTextArea ruta;
	
	public PanelControl(PanelDibujo pd) {
		super();
		this.setPreferredSize(new Dimension(260, 719));
		//INICIALIZAR TODITO
		this.nombres = new String[57];
		this.allNodos = new String[87];
		this.grafos = new Grafo(87);
		this.daleCandela=false;
		this.nodeProperties();
		for(int i = 0; i<57;i++) {
			this.nombres[i] = this.allNodos[i];
		}
		this.adyacencias();
		this.ruta = new JTextArea();
		ruta.setVisible(daleCandela);
		ruta.setLineWrap(true);
		ruta.setEditable(false);
		ruta.setRows(8);
		ruta.setPreferredSize(new Dimension(220,30));
		lstFrom = new JComboBox<String>(nombres);
		lstTo = new JComboBox<String>(nombres);
		lbFrom = new JLabel("�De d�nde?");
		lbTo = new JLabel("�A d�nde?");
		btnGo = new JButton("Dale");
		btnInvertir = new JButton("Invierte");
		this.selectedStart = grafos.getNodo(55);
		this.selectedEnd = grafos.getNodo(22);
		lstFrom.setSelectedItem(grafos.getNodo(55).getNombre());
		lstTo.setSelectedItem(grafos.getNodo(22).getNombre());
		lstFrom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					int pos = grafos.posicionNodo((String) e.getItem());
					selectedStart = grafos.getNodo(pos);
					daleCandela = false;
					ruta.setVisible(daleCandela);
					pd.paintImmediately(0, 0, 895, 719);
				}
			}
		});
		lstTo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					int pos = grafos.posicionNodo((String) e.getItem());
					selectedEnd = grafos.getNodo(pos);
					daleCandela = false;
					ruta.setVisible(daleCandela);
					pd.paintImmediately(0, 0, 895, 719);
				}
			}
		});
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rutita = grafos.rutaMasCorta(selectedStart, selectedEnd);
				daleCandela = true;
				String s = "";
				for(Nodo n:rutita) {
					if(n.getNombre().length()>2) {
						s=s+n.getNombre()+", ";
					}
				}
				ruta.setVisible(daleCandela);
				ruta.setText(s);
				pd.paintImmediately(0, 0, 895, 719);
			}
		});
		btnInvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tmp = selectedEnd.getNombre();
				lstTo.setSelectedItem(selectedStart.getNombre());
				lstFrom.setSelectedItem(tmp);
				pd.paintImmediately(0, 0, 895, 719);
			}
		});
		this.add(lbFrom,BorderLayout.LINE_START);
		this.add(lstFrom, BorderLayout.LINE_END);
		this.add(lbTo, BorderLayout.LINE_START);
		this.add(lstTo, BorderLayout.LINE_END);
		this.add(btnGo, BorderLayout.LINE_START);
		this.add(btnInvertir, BorderLayout.LINE_END);
		this.add(ruta, BorderLayout.LINE_END);
	}
	
	public Nodo getNodo(int pos) {
		return this.grafos.getNodo(pos);
	}
	
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void adyacencias() {
		BufferedReader bf = null;
		StringTokenizer st = null;
		try {
			bf = new BufferedReader(new FileReader("src\\Adyacencias.csv"));
			String line = bf.readLine();
			while(line!=null) {
				st = new StringTokenizer(line, ",");
				String nombreRef = st.nextToken();
				int posRef = grafos.posicionNodo(nombreRef);
				Nodo ref = grafos.getNodo(posRef);
				while(st.hasMoreTokens()) {
					String nombre = st.nextToken();
					int posNombre = grafos.posicionNodo(nombre);
					Nodo lugar = grafos.getNodo(posNombre);
					String peso = st.nextToken();
					int numPeso = (int) Double.parseDouble(peso);
					grafos.addRuta(ref, lugar, numPeso);
				}
				line = bf.readLine();
			}
		}
		catch(IOException e) {}
		finally {
			 try {
			        bf.close();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
		}
	}
	
	public String[] getNombres() {
		return this.nombres;
	}
	
	public String[] getAllNodos() {
		return this.allNodos;
	}
	
	public Nodo getSelectedStart() {return this.selectedStart;}
	
	public Nodo getSelectedEnd() {return this.selectedEnd;}
	
	public boolean getDaleCandela() {return this.daleCandela;}
	
	public Nodo[] getRutita() {return this.rutita;}
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
