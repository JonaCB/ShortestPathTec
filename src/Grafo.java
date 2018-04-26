
public class Grafo {
	TablaHash<String, Nodo> nodos;
	
	/*public static void main(String[] args) {
		Grafo nuevo = new Grafo();
		
		nuevo.imprimeGrafo();
	}*/
	
	public Grafo() {
		this.nodos = new TablaHash<String, Nodo>();
	}
	
	public Grafo(Nodo...nodos) {
		this.nodos = new TablaHash<String, Nodo>();
		for(Nodo n: nodos) {
			this.nodos.put(n.getNombre(), n);
		}
	}

	public void addNodo(Nodo nuevo) {
		this.nodos.put(nuevo.getNombre(), nuevo);
	}
}
