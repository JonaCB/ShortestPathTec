
public class Nodo implements Comparable<Nodo>{
	private final int posX, posY;
	private final String nombre;
	private int distancia;
	private Nodo padre;
	
	public Nodo(int posX, int posY, String nombre) {
		this.posX = posX;
		this.posY = posY;
		this.nombre = nombre;
	}
	
	public Nodo(int posX, int posY, String nombre, int distancia, Nodo padre) {
		this(posX, posY, nombre);
		this.distancia = distancia;
		this.padre = padre;
	}
	
	public int getPosX() {return this.posX;}
	
	public int getPosY() {return this.posY;}
	
	public String getNombre() {return this.nombre;}
	
	public int getDistania() {return this.distancia;}

	public Nodo getPadre() {return this.padre;}

	@Override
	public int compareTo(Nodo o) {
		return this.distancia-o.getDistania();
	}
}
