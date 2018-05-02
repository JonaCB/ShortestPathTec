import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Grafo {
	private Nodo[] nodos;
	private TablaHashPosiciones<String, Integer> posiciones;
	private int[][] grafo;
	private List<Nodo> nodosListos;
	private Nodo[] rutaMasCorta;
	private int posRuta = 0;
	private int longitudMin;
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
		g.rutaMasCorta(A, D);
		g.imprimeRuta();
	}
	
	public Grafo(int numNodos) {
		this.nodos = new Nodo[numNodos];
		this.posiciones = new TablaHashPosiciones<>();
		this.grafo = new int[numNodos][numNodos];
		this.rutaMasCorta = new Nodo[numNodos];
	}
	
	public void addNodoRuta(Nodo nodo) {
		this.rutaMasCorta[this.posRuta] = nodo;
		this.posRuta++;
	}
	
	public void addNodo(int x, int y, String nombre) {
		this.addNodo(new Nodo(x,y,nombre));
	}
	
	public void addNodo(Nodo nuevo) {
		if(this.nodos.length == this.size) {
			this.ampliarNodos();
			System.out.println("Ampliar");
		}
		this.nodos[this.size] = nuevo;
		this.posiciones.put(nuevo.getNombre(), this.size);
		this.size++;
	}
	
	private void ampliarNodos() {
		Nodo[] nuevoArreglo = new Nodo[this.size + 10];
		for(int i = 0; i < this.size; i++) {
			nuevoArreglo[i] = this.nodos[i];
		}
		this.nodos = nuevoArreglo;
	}
	
	public void addRuta(Nodo origen, Nodo destino, int peso) {
		int posOrigen = getPosNodo(origen);
		int posDestino = getPosNodo(destino);
		this.grafo[posOrigen][posDestino] = peso;
		this.grafo[posDestino][posOrigen] = peso;
	}
	
	public int getPosNodo(String llave) {
		return this.posiciones.get(llave);
	}
	
	public int getPosNodo(Nodo nodo) {
		return this.posiciones.get(nodo.getNombre());
	}
	
	private void rutaMasCorta(Nodo inicio, Nodo fin) {
		this.rutaMasCorta(inicio);
		System.out.println("Paso primera ruta");
		Nodo tmp = this.nodosListos.get(this.nodosListos.indexOf(fin));
		this.longitudMin = tmp.getDistania();
		Stack<Nodo> pila = new Stack<Nodo>();
		while(tmp != null) {
			pila.add(tmp);
			tmp = tmp.getPadre();
		}
		while(!pila.isEmpty()) {this.addNodoRuta(pila.pop());}
	}

	private void rutaMasCorta(Nodo inicio) {
		PriorityQueue<Nodo> cola = new PriorityQueue<Nodo>();
		this.nodosListos = new LinkedList<>();
		cola.add(inicio);
		while(!cola.isEmpty()) {
			System.out.println("Ruta 1");
			Nodo tmp = cola.poll();
			this.nodosListos.add(tmp);
			System.out.println(tmp.getNombre());
			int pos = this.getPosNodo(tmp);
			System.out.println("Pos: " + pos);
			for(int i=0; i<this.grafo[pos].length; i++) {
				if(this.grafo[pos][i]==0) continue;
				if(this.isListo(nodos[i])) continue;
				Nodo nodo = new Nodo(nodos[i].getPosX(), nodos[i].getPosY(), nodos[i].getNombre(), tmp.getDistania()+this.grafo[pos][i], tmp);
				if(!cola.contains(nodo)) {
					cola.add(nodo);
					continue;
				}
				for(Nodo n: cola) {
					if(n == nodo && n.getDistania() > nodo.getDistania()) {
						cola.remove(n);
						cola.add(nodo);
						break;
					}
				}
			}
			System.out.println("Termina While");
			System.out.println();
		}
		System.out.println("Salio while");
	}

	private boolean isListo(Nodo nodo) {
		return this.nodosListos.contains(nodo);
	}

	public void imprimeRuta() {
		System.out.println("Distancia: " + this.longitudMin);
		for(Nodo n: this.rutaMasCorta) {
			System.out.print(n.getNombre() + " ");
		}
	}
	
	public Nodo getNodo(int pos) {
		return nodos[pos];
	}
}
