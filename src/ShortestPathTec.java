import java.awt.Dimension;

import javax.swing.JFrame;

public class ShortestPathTec extends JFrame{
	
	public ShortestPathTec() {
		super("Bienvenidos");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setPreferredSize(new Dimension(1000,720));
		PanelDibujo pd = new PanelDibujo();
		this.add(pd);
		this.pack();
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		ShortestPathTec window = new ShortestPathTec();
		
	}

}
