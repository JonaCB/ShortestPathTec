
public class Nodo {
	private final int posX, posY;
	private TablaHash<String, Nodo, Integer> adyacentes;
	private final String nombre;
	
	public Nodo(int posX, int posY, String nombre) {
		this.posX = posX;
		this.posY = posY;
		this.adyacentes = new TablaHash<String, Nodo, Integer>(7);
		this.nombre = nombre;
	}
	
	public void addAdyacente(Nodo nodo, int peso) {
		this.adyacentes.put(nodo.getNombre(), nodo, peso);
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public int getSize() {
		return this.adyacentes.getSize();
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
