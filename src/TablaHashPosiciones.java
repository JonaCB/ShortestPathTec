
import java.util.NoSuchElementException;

//Utilizando encadenamiento
public class TablaHashPosiciones<K, V> {
	private ListaEnlazada<NodoHTPos<K, V>>[] tabla;
	private final static double LOAD_FACTOR = 0.8;
	private int size;
	
	public TablaHashPosiciones() {
		this(17);
	}
	
	public TablaHashPosiciones(int size) {
		this.tabla = (ListaEnlazada<NodoHTPos<K, V>>[]) new ListaEnlazada[size];
		for(int i = 0; i < this.tabla.length; i++) {
			this.tabla[i] = new ListaEnlazada<NodoHTPos<K, V>>();
		}
		this.size = 0;
	}
	
	/*
	 * Revisar el elemento que ya esta en la lista
	 */
	public void put(K llave, V valor) {
		if((((double)this.size)/this.tabla.length) >= TablaHashPosiciones.LOAD_FACTOR) {
			this.rehashing();
		}
		int hash = Math.abs(llave.hashCode())%tabla.length;
		ListaEnlazada<NodoHTPos<K, V>> bucket = this.tabla[hash];
		bucket.insertarFin(new NodoHTPos<K, V>(llave, valor));
		this.size++;
	}
	
	private void rehashing() {
		TablaHashPosiciones<K,V> tablaHashTemp = new TablaHashPosiciones<K,V>((this.tabla.length*2)+1);
		NodoHTPos<K,V> current;
		ListaEnlazada<NodoHTPos<K,V>> bucket;
		for(int i = 0; i < this.tabla.length; i++) {
			bucket = tabla[i];
			for(int j = 0; j < bucket.getSize(); j++) {
				current = bucket.getAt(j);
				tablaHashTemp.put(current.getLlave(), current.getValor());
			}
		}
		this.tabla = tablaHashTemp.tabla;
	}

	public V get(K llave) throws NoSuchElementException{
		int hash = Math.abs(llave.hashCode())%tabla.length;
		ListaEnlazada<NodoHTPos<K,V>> bucket = this.tabla[hash];
		NodoHTPos<K, V> current;	
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
		ListaEnlazada<NodoHTPos<K,V>> bucket = this.tabla[hash];
		NodoHTPos<K, V> current;
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
		this.tabla = (ListaEnlazada<NodoHTPos<K, V>>[]) new ListaEnlazada[size];
		for(int i = 0; i < this.tabla.length; i++) {
			this.tabla[i] = new ListaEnlazada<NodoHTPos<K, V>>();
		}
		this.size = 0;
	}
	
	public String[] getKeys() {
		String[] keys = new String[this.size];
		NodoHTPos<K, V>[] tmp;
		int current = 0;
		for(ListaEnlazada<NodoHTPos<K, V>> lista: this.tabla) {
			tmp = lista.getValues();
			for(NodoHTPos<K, V> nodo: tmp) {
				keys[current]+=nodo.getValor().toString();
				current++;
			}
		}
		return keys;
	}
}

class NodoHTPos<K, V>{
	private V valor;
	private K llave;
	
	public NodoHTPos(K llave, V valor){
		this.llave = llave;
		this.valor = valor;
	}
	
	public K getLlave() {
		return this.llave;
	}
	
	public V getValor() {
		return this.valor;
	}
	
	public void setValor(V valor) {
		this.valor = valor;
	}
	
	public void setLlave(K llave) {
		this.llave = llave;
	}
	
	public String toString() {
		return "K: "+this.llave + ", V: "+this.valor;
	}
}