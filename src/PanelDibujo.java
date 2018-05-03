import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class PanelDibujo extends JPanel{
	private Image mapita;
	private PanelControl pc;
	
	public PanelDibujo() {
		super();
		this.mapita = new ImageIcon("MapaITESM.jpg").getImage();
		this.setPreferredSize(new Dimension(895, 719));
		this.repaint();
	}
	
	public void pintaCirculo(Graphics g, Color c, int x, int y) {
		g.setColor(c);
		g.fillOval(x-12, y-12, 25, 25);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.mapita, 0, 0, 895, 719, this);
		g.fillRect(50, 50, 50, 50);
		g.setColor(Color.ORANGE);
		g.drawString(pc.getSelectedStart().getNombre(), pc.getSelectedStart().getPosX()-25, pc.getSelectedStart().getPosY()-25);
		g.drawString(pc.getSelectedEnd().getNombre(), pc.getSelectedEnd().getPosX()-25, pc.getSelectedEnd().getPosY()-25);
		if(pc.getDaleCandela()) {
			Nodo[] rutita = pc.getRutita();
			System.out.println();
			for(int i = 1; i<rutita.length;i++) {
				Nodo n1 = rutita[i-1];
				Nodo n2 = rutita[i];
				g.setColor(Color.ORANGE);
				g.drawLine(n1.getPosX(), n1.getPosY(), n2.getPosX(), n2.getPosY());
				g.drawLine(n1.getPosX()+1, n1.getPosY()+1, n2.getPosX()+1, n2.getPosY()+1);
				g.drawLine(n1.getPosX()-1, n1.getPosY()-1, n2.getPosX()-1, n2.getPosY()-1);
				if(n2.getNombre().length()>2) {
					g.setColor(Color.BLUE);
					g.fillOval(n2.getPosX()-5, n2.getPosY()-5, 10, 10);
					g.setColor(Color.MAGENTA);
					if(n2.getNombre().equals(pc.getSelectedEnd().getNombre())) {
						break;
					}
					g.drawString(n2.getNombre(), n2.getPosX()-9, n2.getPosY()-9);
					g.drawString(n2.getNombre(), n2.getPosX()-10, n2.getPosY()-10);
				}
			}
		}
		this.pintaCirculo(g, Color.GREEN, pc.getSelectedStart().getPosX(), pc.getSelectedStart().getPosY());
		this.pintaCirculo(g, Color.RED, pc.getSelectedEnd().getPosX(), pc.getSelectedEnd().getPosY());
	}
	
	public void setPanelControl(PanelControl pc) {
		this.pc = pc;
	}
	
	public PanelControl getPanelControl() {return this.pc;}
}
