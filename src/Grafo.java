
public class Grafo {
	private TablaHash<String, Nodo> nodos;
	private int size;
	
	public Grafo() {
		this.nodos = new TablaHash<String, Nodo>();
	}
	
	public void addNodo(Nodo nuevo) {
		this.nodos.put(nuevo.getNombre(), nuevo);
		this.size++;
	}
	
	public Grafo(Nodo...nodos) {
		this.nodos = new TablaHash<String, Nodo>();
		for(Nodo n: nodos) {
			this.nodos.put(n.getNombre(), n);
			this.size++;
		}
	}
	
	public void rutaMasCorta(Nodo inicio, Nodo destino) {
		int[] menorPeso = new int[this.size];
		boolean[] pesoEncontrado = new boolean[this.size];
		
	}
	
	private int contiene() {
		
		return 0;
	}
	
	public void imprimeRutaMasCorta() {
		
	}
}
