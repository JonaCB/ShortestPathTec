import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Stack;

public class Grafo {
	private Nodo[] nodos;
	private int[][] grafo;
	private List<Nodo> nodosListos = null;
	private int size;
	
	public static void main(String[] args) throws Exception {
		Grafo g = new Grafo(6);
		Nodo A = new Nodo(5, 5, "A");
		Nodo B = new Nodo(5, 5, "B");
		Nodo C = new Nodo(5, 5, "C");
		Nodo D = new Nodo(5, 5, "D");
		Nodo E = new Nodo(5, 5, "E");
		Nodo F = new Nodo(5, 5, "F");
		g.addNodo(A);
		g.addNodo(B);
		g.addNodo(C);
		g.addNodo(D);
		g.addNodo(E);
		g.addNodo(F);
		g.addRuta(A, B, 3);
		g.addRuta(A, E, 6);
		g.addRuta(A, F, 10);
		g.addRuta(B, C, 5);
		g.addRuta(B, E, 2);
		g.addRuta(C, D, 8);
		g.addRuta(C, E, 9);
		g.addRuta(C, F, 7);
		g.addRuta(E, F, 4);
		String respuesta = g.rutaMasCorta(A, F);
		System.out.println(respuesta);
		//g.imprimeRuta();
	}
	
	public Grafo(int numNodos) {
		this.nodos = new Nodo[numNodos];
		//this.posiciones = new TablaHashPosiciones<>();
		this.grafo = new int[numNodos][numNodos];
		this.size = 0;
	}
	
	public void addNodo(int x, int y, String nombre) {
		this.addNodo(new Nodo(x,y,nombre));
	}
	
	public void addNodo(Nodo nuevo) {
//		if(this.nodos.length == this.size) {
//			this.ampliarNodos();
//			System.out.println("Ampliar");
//		}
		this.nodos[this.size] = nuevo;
		this.size++;
		//this.posiciones.put(nuevo.getNombre(), this.size);
	}
	
//	private void ampliarNodos() {
//		Nodo[] nuevoArreglo = new Nodo[this.size + 10];
//		for(int i = 0; i < this.size; i++) {
//			nuevoArreglo[i] = this.nodos[i];
//		}
//		this.nodos = nuevoArreglo;
//	}
	
	public void addRuta(Nodo origen, Nodo destino, int peso) {
		int posOrigen = posicionNodo(origen);
		int posDestino = posicionNodo(destino);
		this.grafo[posOrigen][posDestino] = peso;
		this.grafo[posDestino][posOrigen] = peso;
	}
	
	public int posicionNodo(Nodo nodo) {
		for(int i = 0; i<nodos.length; i++) {
			if(this.nodos[i].getNombre().equals(nodo.getNombre())) return i;
		}
		throw new NoSuchElementException("Fallo PosNodo");
	}
	
	private String rutaMasCorta(Nodo inicio, Nodo fin) {
		this.rutaMasCorta(inicio);
		Nodo tmp = this.nodosListos.get(this.nodosListos.indexOf(fin));
		int distancia = tmp.getDistania();
		Stack<Nodo> pila = new Stack<Nodo>();
		while(tmp != null) {
			pila.add(tmp);
			tmp = tmp.getPadre();
		}
		String ruta = "";
		while(!pila.isEmpty()) {
			ruta+=(pila.pop().getNombre() + " ");
		}
		return distancia + ": " + ruta;
	}

	private void rutaMasCorta(Nodo inicio) {
		PriorityQueue<Nodo> cola = new PriorityQueue<Nodo>();
		this.nodosListos = new LinkedList<>();
		cola.add(inicio);
		while(!cola.isEmpty()) {
			System.out.println("while");
			Nodo tmp = cola.poll();
			this.nodosListos.add(tmp);
			int pos = this.posicionNodo(tmp);
			for(int i=0; i<this.grafo[pos].length; i++) {
				if(this.grafo[pos][i]==0) continue;
				if(this.isListo(nodos[i])) continue;
				Nodo nodo = new Nodo(nodos[i].getPosX(), nodos[i].getPosY(), nodos[i].getNombre(), tmp.getDistania()+this.grafo[pos][i], tmp);
				if(!cola.contains(nodo)) {
					cola.add(nodo);
					continue;
				}
				for(Nodo n: cola) {
					if(n.getNombre().equals(nodo.getNombre()) && n.getDistania() > nodo.getDistania()) {
						cola.remove(n);
						cola.add(nodo);
						break;
					}
				}
			}
		}
	}

	private boolean isListo(Nodo nodo) {
		return this.nodosListos.contains(nodo);
	}

//	public void imprimeRuta() {
//		System.out.println("Distancia: " + this.longitudMin);
//		for(Nodo n: this.rutaMasCorta) {
//			System.out.print(n.getNombre() + " ");
//		}
//	}
	
	public Nodo getNodo(int pos) {
		return nodos[pos];
	}
}
