
public class Nodo{
	private final int posX, posY;
	private Nodo[] adyacentes;
	private int[] pesos;
	private int size;
	private final String nombre;
	
	public Nodo(int posX, int posY, String nombre) {
		this.posX = posX;
		this.posY = posY;
		this.adyacentes = new Nodo[5];
		this.pesos = new int[5];
		this.size = 0;
		this.nombre = nombre;
	}
	
	public void ampliarNodos() {
		Nodo[] nuevo = new Nodo[this.size*2];
		for(int i = 0; i < this.size; i++) {
			nuevo[i] = this.adyacentes[i];
		}
		this.adyacentes = nuevo;
		this.ampliarPesos();
	}
	
	private void ampliarPesos() {
		int[] nuevo = new int[this.size*2];
		for(int i = 0; i < this.size; i++) {
			nuevo[i] = this.pesos[i];
		}
		this.pesos = nuevo;
	}

	public void addAdyacente(Nodo nodo, int peso) {
		if(this.size == this.adyacentes.length) {
			this.ampliarNodos();
		}
		this.adyacentes[this.size] = nodo;
		this.pesos[this.size] = peso;
		this.size++;
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
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Nodo[] getAdyacentes() {
		return this.adyacentes;
	}
	
	public int[] getPesos() {
		return this.pesos;
	}
}
