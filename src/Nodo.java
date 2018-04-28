
public class Nodo {
	private final int posX, posY;
	private Arista[] adyacentes;
	private int size;
	private final String nombre;
	
	public Nodo(int posX, int posY, String nombre) {
		this.posX = posX;
		this.posY = posY;
		this.adyacentes = new Arista[5];
		this.size = 0;
		this.nombre = nombre;
	}
	
	public void addAdyacente(Nodo nodo, int peso) {
		if(this.size == this.adyacentes.length) {
			this.ampliarArreglo();
		}
		this.adyacentes[this.size+1] = new Arista(nodo, peso);
		this.size++;
	}
	
	private void ampliarArreglo() {
		Arista[] nuevoArray = new Arista[this.adyacentes.length+1];
		for(int i = 0; i < this.adyacentes.length; i++) {
			nuevoArray[i] = this.adyacentes[i];
		}
		this.adyacentes = nuevoArray;
	}

	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public Arista[] getAdyacentes() {
		return this.adyacentes;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
