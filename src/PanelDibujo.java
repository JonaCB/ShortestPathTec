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
		this.mapita = new ImageIcon("MapaITESMPuntos.jpg").getImage();
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.mapita, 0, 0, 895, 719, this);
		g.fillRect(50, 50, 50, 50);
	}
}
