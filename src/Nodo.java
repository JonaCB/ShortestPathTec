
public class Nodo implements Comparable<Nodo>{
	private final int posX, posY;
	private final String nombre;
	private int distancia = 0;
	private Nodo padre = null;
	
	public Nodo(int posX, int posY, String nombre) {
		this.posX = posX;
		this.posY = posY;
		this.nombre = nombre;
	}
	
	public Nodo(int posX, int posY, String nombre, int distancia, Nodo padre) {
		this.posX = posX;
		this.posY = posY;
		this.nombre = nombre;
		this.distancia = distancia;
		this.padre = padre;
	}
	
	public int getPosX() {return this.posX;}
	
	public int getPosY() {return this.posY;}
	
	public String getNombre() {return this.nombre;}
	
	public int getDistania() {return this.distancia;}

	public Nodo getPadre() {return this.padre;}

	public int compareTo(Nodo tmp) {
		return this.distancia-tmp.getDistania();
	}
	
	public boolean equals(Object o) {
		Nodo tmp = (Nodo) o;
		if(tmp.getNombre().equals(this.getNombre())) return true;
		return false;
	}
}
