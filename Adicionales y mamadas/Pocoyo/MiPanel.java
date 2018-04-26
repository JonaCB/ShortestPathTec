package Pocoyo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MiPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener{
	private int xV, yV;
	//Crear imagenes
	private Image fondo;
	private String nombre = "Pocoyo";
	private boolean moverVamoosh;
	private PanelControles controles;
	 
	
	public MiPanel() {
		super();
		this.fondo = new ImageIcon("fondo.jpg").getImage();
		this.xV = this.yV = 0;
		this.setPreferredSize(new Dimension(1000, 800));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				moverVamoosh = true;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.addMouseMotionListener(this);
		this.moverVamoosh = false;
		
		Thread hilo = new Thread(this); //Thread es una cosa rara
										//que sirve para separar procesos.
		hilo.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.fondo, 0, 0, this.getWidth(), this.getHeight(), this);
		
		g.setColor(Color.orange);
		g.fillOval(50+this.xV, 500-this.yV, 200, 100);
		
		g.setColor(Color.white);
		g.drawLine(100+this.xV, 508-this.yV, 100+this.xV, 400-this.yV);;
		
		g.setColor(new Color(153,0,0));
		g.fillOval(50+this.xV, 300-this.yV, 100, 100);
		
		g.setColor(Color.cyan);
		g.fillArc(50+this.xV, 500-this.yV, 200, 100, 0, 100);
		
		g.setColor(Color.BLACK);
		g.drawString("Vamos " + nombre + "!", 110+this.xV, 575-this.yV);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
		this.repaint();
	}
	
	public void setControles(PanelControles controles) {
		this.controles = controles;
	}

	@Override
	public void run() {
		try {
			while(this.xV < 550) {
				if(this.moverVamoosh == true) {
					this.xV+=2;
					this.yV++;
					this.repaint();	
				}
				Thread.sleep(20);
				
			}
		}catch(InterruptedException ex) {
			System.out.println("No se pudo ejecutar" + ex);
		}
	}

	public void moverVamooshVertical() {
		this.yV = controles.getValorSlider();
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("Pressed en "+e.getX()+","+e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("Released en "+e.getX()+","+e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("Entered en "+e.getX()+","+e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("Exited en "+e.getX()+","+e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
