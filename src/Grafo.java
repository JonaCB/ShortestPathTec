
public class Grafo {
	private Nodo[] nodos;
	private TablaHashPosiciones<String, Integer> posiciones;
	private int size;
	
	public static void main(String[] args) throws Exception {
		Grafo g = new Grafo();
		Nodo cero = new Nodo(5, 5, "cero");
		Nodo uno = new Nodo(5, 5, "uno");
		Nodo dos = new Nodo(5, 5, "dos");
		Nodo tres = new Nodo(5, 5, "tres");
		Nodo cuatro = new Nodo(5, 5, "cuatro");
		cero.addAdyacente(uno, 16);
		cero.addAdyacente(tres, 2);
		cero.addAdyacente(cuatro, 3);
		uno.addAdyacente(dos, 5);
		dos.addAdyacente(uno, 3);
		tres.addAdyacente(uno, 3);
		tres.addAdyacente(cuatro, 7);
		cuatro.addAdyacente(uno, 10);
		cuatro.addAdyacente(dos, 4);
		cuatro.addAdyacente(tres, 5);
		g.addNodo(cero);
		g.addNodo(uno);
		g.addNodo(dos);
		g.addNodo(tres);
		g.addNodo(cuatro);
		g.rutaMasCorta(cero, dos);
		
	}
	
	public Grafo() {
		this.nodos = new Nodo[10];
		this.posiciones = new TablaHashPosiciones<>();
		this.size = 0;
	}
	
	public void addNodo(int x, int y, String nombre) {
		this.addNodo(new Nodo(x,y,nombre));
	}
	
	public void addNodo(Nodo nuevo) {
		if(this.nodos.length == this.size) {
			this.ampliarArreglo();
		}
		this.nodos[this.size] = nuevo;
		this.posiciones.put(nuevo.getNombre(), this.size);
		this.size++;
	}

	private void ampliarArreglo() {
		Nodo[] nuevoArreglo = new Nodo[this.size + 10];
		for(int i = 0; i < this.size-1; i++) {
			nuevoArreglo[i] = this.nodos[i];
		}
		this.nodos = nuevoArreglo;
	}
	
	public Nodo[] getNodos() {
		return this.nodos;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getPosicion(String llave) {
		return this.posiciones.get(llave);
	}
	
	public void rutaMasCorta(Nodo inicio, Nodo destino) {
		Dijkstra d = new Dijkstra(this, inicio, destino);
		d.rutaMasCorta();
	}
	
	public Nodo getNodo(int pos) {
		return nodos[pos];
	}
}
