import java.util.NoSuchElementException;

//Utilizando encadenamiento
public class TablaHash<K, V, W> {
	private ListaEnlazada<NodoHT<K, V, W>>[] tabla;
	private final static double LOAD_FACTOR = 0.8;
	private int size;
	
	public TablaHash() {
		this(17);
	}
	
	public TablaHash(int size) {
		this.tabla = (ListaEnlazada<NodoHT<K, V, W>>[]) new ListaEnlazada[size];
		for(int i = 0; i < this.tabla.length; i++) {
			this.tabla[i] = new ListaEnlazada<NodoHT<K, V, W>>();
		}
		this.size = 0;
	}
	
	public V[] getValues() {
		V[] values = (V[]) new Object[this.size];
		NodoHT<K,V,W>[] nodos;
		for(int i = 0; i<this.size; i++) {
			nodos = tabla[i].getValues();
			for(int j = 0; j<this.size-1; j++) {
				values[(this.size*i)+j] = nodos[j].getValor();
			}
		}
		return values;
	}
	
	public void put(K llave, V valor, W peso) {
		if((((double)this.size)/this.tabla.length) >= TablaHash.LOAD_FACTOR) {
			this.rehashing();
		}
		int hash = Math.abs(llave.hashCode())%tabla.length;
		ListaEnlazada<NodoHT<K, V, W>> bucket = this.tabla[hash];
		bucket.insertarFin(new NodoHT<K, V, W>(llave, valor, peso));
		this.size++;
	}
	
	private void rehashing() {
		TablaHash<K, V, W> tablaHashTemp = new TablaHash<K, V, W>((this.tabla.length*2)+1);
		NodoHT<K, V, W> current;
		ListaEnlazada<NodoHT<K, V, W>> bucket;
		for(int i = 0; i < this.tabla.length; i++) {
			bucket = tabla[i];
			for(int j = 0; j < bucket.getSize(); j++) {
				current = bucket.getAt(j);
				tablaHashTemp.put(current.getLlave(), current.getValor(), current.getPeso());
			}
		}
		this.tabla = tablaHashTemp.tabla;
	}

	public V get(K llave) throws NoSuchElementException{
		int hash = Math.abs(llave.hashCode())%tabla.length;
		ListaEnlazada<NodoHT<K, V, W>> bucket = this.tabla[hash];
		NodoHT<K, V, W> current;	
		for(int i = 0; i < bucket.getSize(); i++) {
			current = bucket.getAt(i);
			if(current.getLlave().equals(llave)) {
				return bucket.getAt(i).getValor();
			}
		}
		throw new NoSuchElementException("No se encontro la llave en get");
	}
	
	public V delete(K llave) throws Exception {
		int hash = Math.abs(llave.hashCode())%tabla.length;
		ListaEnlazada<NodoHT<K, V, W>> bucket = this.tabla[hash];
		NodoHT<K, V, W> current;
		for(int i = 0; i < bucket.getSize(); i++) {
			current = bucket.getAt(i);
			if(current.getLlave().equals(llave)) {
				return bucket.borrarEn(i).getValor();
			}
			
		}
		throw new NoSuchElementException("No se encontro la llave en delete");
	}
	
	public boolean containsKey(K llave) {
		try {
			this.get(llave);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public void clear() {
		this.tabla = (ListaEnlazada<NodoHT<K, V, W>>[]) new ListaEnlazada[size];
		for(int i = 0; i < this.tabla.length; i++) {
			this.tabla[i] = new ListaEnlazada<NodoHT<K, V, W>>();
		}
		this.size = 0;
	}

	public int getSize() {
		return this.size;
	}
}

class NodoHT<K, V, W>{
	private V valor;
	private K llave;
	private W peso;
	
	public NodoHT(K llave, V valor, W peso){
		this.llave = llave;
		this.valor = valor;
		this.peso = peso;
	}
	
	public K getLlave() {
		return this.llave;
	}
	
	public V getValor() {
		return this.valor;
	}
	
	public W getPeso() {
		return this.peso;
	}
	
	public void setValor(V valor) {
		this.valor = valor;
	}
	
	public void setLlave(K llave) {
		this.llave = llave;
	}
	
	public void setPeso(W peso) {
		this.peso = peso;
	}
}