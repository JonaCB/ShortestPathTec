import java.awt.Color;
import java.awt.Dimension;
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
		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}			
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + "," + e.getY());
			}
		});
		this.repaint();
	}
	
	public void pintaCirculo(Graphics g, Color c, int x, int y) {
		g.setColor(c);
		g.fillOval(x-25, y-25, 25, 25);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.mapita, 0, 0, 895, 719, this);
		g.fillRect(50, 50, 50, 50);
		this.pintaCirculo(g, Color.GREEN, pc.getSelectedStart().getPosX(), pc.getSelectedStart().getPosY());
		this.pintaCirculo(g, Color.RED, pc.getSelectedEnd().getPosX(), pc.getSelectedEnd().getPosY());
	}
	
	public void setPanelControl(PanelControl pc) {
		this.pc = pc;
	}
}
