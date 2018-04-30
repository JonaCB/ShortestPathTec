
public class Dijkstra {
	private int[] pesos;
	private boolean[] encontrado;
	private Grafo grafo;
	private Nodo inicio,
				 fin;
	
	public Dijkstra(Grafo grafo, Nodo inicio, Nodo fin) {
		this.grafo = grafo;
		this.pesos = new int[this.grafo.getSize()];
		this.encontrado = new boolean[this.grafo.getSize()];
		for(int i = 0; i < this.grafo.getSize(); i++) {
			pesos[i] = -1;
			encontrado[i] = false;
		}
		this.inicio = inicio;
		this.fin = fin;
	}

	public void rutaMasCorta() {
		int pos = grafo.getPosicion(this.inicio.getNombre());
		this.minEncontrado(pos, 0, true);
		Nodo[] adyacentes = this.inicio.getAdyacentes();
		int[] pesoAdyacentes = this.inicio.getPesos();
		int sizeNodo = this.inicio.getSize();
		Nodo min = null;
		int pesoMin = 1000;
		for(int i = 0; i<sizeNodo; i++) {
			pos = grafo.getPosicion(adyacentes[i].getNombre());
			this.pesos[pos] = pesoAdyacentes[i];
			if(pesoAdyacentes[i] < pesoMin) {
				min = grafo.getNodos()[pos];
				pesoMin = pesoAdyacentes[i];
			}
		}
		this.minEncontrado(pos, pesoMin, true);
		this.imprimeRutaMasCorta(min, this.pesos[pos]);
	}
	
	public void imprimeRutaMasCorta(Nodo n, int peso) {
		System.out.println("Nodo: " + n.getNombre() + " peso: " + peso);
	}
	
	private void minEncontrado(int pos, int peso, boolean encontrado) {
		this.pesos[pos] = peso;
		this.encontrado[pos] = encontrado;
	}
	
	private void pesoCambia(int pos, int peso) {
		this.pesos[pos] = peso;
	}
}
