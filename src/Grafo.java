
public class Grafo {
	private Nodo[] nodos;
	private int size;
	
	public Grafo() {
		this.nodos = new Nodo[10];
	}
	
	public void addNodo(Nodo nuevo) {
		if(this.nodos.length == this.size) {
			this.ampliarArreglo();
		}
		this.nodos[this.size] = nuevo;
		this.size++;
	}
	
	private void ampliarArreglo() {
		Nodo[] nuevoArreglo = new Nodo[this.size + 10];
		for(int i = 0; i < this.size-1; i++) {
			nuevoArreglo[i] = this.nodos[i];
		}
		this.nodos = nuevoArreglo;
	}
	
	public void rutaMasCorta(Nodo inicio, Nodo destino) {
		int[] menorPeso = new int[this.size];
		boolean[] pesoEncontrado = new boolean[this.size];
		for(int i = 0; i<this.size-1;i++) {
			
		}
	}
	
	public void imprimeRutaMasCorta() {
		
	}
}
