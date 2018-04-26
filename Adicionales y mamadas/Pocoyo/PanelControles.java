package Pocoyo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*
 * Default de paneles es Flow Layout
 * Su caracteristica es que se agregan objetos horizontalmente
 * 		hasta llenar el espacio.
 * Los componentes respetan el tamaño, en cambio el border Layout
 * 		llena todo el espacio con los componentes.
 */

public class PanelControles extends JPanel implements ActionListener, ChangeListener{
	private JTextField tfNombre;
	private JButton bSaludar, 
					bCambiarColor,
					bCambiarGlobo,
					bSelArchivo;
	private JRadioButton rbRojo,
						 rbVerde,
						 rbAzul;
	private MiPanel panelDibujo;
	private JSlider slider;
	
	public PanelControles(MiPanel panelDibujo) {
		super();
		this.panelDibujo = panelDibujo;
		this.setPreferredSize(new Dimension(150, 600));
		this.tfNombre = new JTextField(10); //El parametro es el ancho de columna
		this.add(tfNombre);
		
		this.bSaludar = new JButton("Saludar");
		this.bSaludar.addActionListener(new ActionListener(){
		/*
		 * Recibe como parametro la clase que va a implementar
		 * action listener. Cambiaremos "this" por
		 * new ActionListener.
		 * 
		 * Aqui abajo implementamos una clase anonima
		 */
			public void actionPerformed(ActionEvent e) {
				panelDibujo.setNombre(tfNombre.getText());
			}
		});
		this.add(this.bSaludar);
		
		this.rbRojo = new JRadioButton("Color Rojo");
		this.rbRojo.setSelected(true);
		this.rbVerde = new JRadioButton("Color Verde");
		this.rbAzul = new JRadioButton("Color Azul");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbRojo);
		bg.add(rbVerde);
		bg.add(rbAzul);
		
		this.add(rbRojo);
		this.add(rbVerde);
		this.add(rbAzul);
		
		this.bCambiarColor = new JButton("Cambiar");
		this.bCambiarColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color tmpColor;
				if(rbRojo.isSelected()) {					
					tmpColor = Color.red;
				}else if(rbAzul.isSelected()) {
					tmpColor = Color.blue;
				}else {
					tmpColor = Color.green;
				}
				setBackground(tmpColor);
			}
		});
		
		this.add(bCambiarColor);
		
		this.bCambiarGlobo = new JButton("Color globo");
		this.bCambiarGlobo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color colorGlobo = JColorChooser.showDialog(panelDibujo, "Selecciona"
						+ "el color del globo de la nave", Color.red);
			}
		});
		this.add(bCambiarGlobo);
		
		this.slider = new JSlider(JSlider.VERTICAL, 0, 150, 0);
		this.slider.setMinorTickSpacing(5);
		this.slider.setMajorTickSpacing(20);
		this.slider.setPaintTicks(true);
		this.slider.setPaintLabels(true);
		this.slider.addChangeListener(this);
		this.add(this.slider);
		
	}//Cierra constructor default
	
	public Color getColorGlobo() {
		return Color.red;
	}
	
	public int getValorSlider() {
		return this.slider.getValue();
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		this.panelDibujo.moverVamooshVertical();
		System.out.println(this.slider.getValue());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//Cierra clase
