import java.util.NoSuchElementException;

//Utilizando encadenamiento
public class TablaHashAdyacentes<K, V> {
	private ListaEnlazada<NodoHTAd<K, V>>[] tabla;
	private final static double LOAD_FACTOR = 0.8;
	private int size;
	
	public K[] getKeys() {
		K[] keys = (K[]) new Object[this.size];
		int pos = 0;
		for(int i = 0; i < this.tabla.length; i++) {
			for(int j = 0; j < this.tabla[i].getSize(); j++) {
				keys[pos] = this.tabla[i].borrarInicio().getLlave();
			}
		}
		return keys;
	}
	
	public TablaHashAdyacentes() {
		this(17);
	}
	
	public TablaHashAdyacentes(int size) {
		this.tabla = (ListaEnlazada<NodoHTAd<K, V>>[]) new ListaEnlazada[size];
		for(int i = 0; i < this.tabla.length; i++) {
			this.tabla[i] = new ListaEnlazada<NodoHTAd<K, V>>();
		}
		this.size = 0;
	}
	
	public void put(K llave, V valor, int peso) {
		if((((double)this.size)/this.tabla.length) >= TablaHashAdyacentes.LOAD_FACTOR) {
			this.rehashing();
		}
		int hash = Math.abs(llave.hashCode())%tabla.length;
		ListaEnlazada<NodoHTAd<K, V>> bucket = this.tabla[hash];
		bucket.insertarFin(new NodoHTAd<K, V>(llave, valor, peso));
		this.size++;
	}
	
	private void rehashing() {
		TablaHashAdyacentes<K, V> tablaHashTemp = new TablaHashAdyacentes<K, V>((this.tabla.length*2)+1);
		NodoHTAd<K, V> current;
		ListaEnlazada<NodoHTAd<K, V>> bucket;
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
		ListaEnlazada<NodoHTAd<K, V>> bucket = this.tabla[hash];
		NodoHTAd<K, V> current;	
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
		ListaEnlazada<NodoHTAd<K, V>> bucket = this.tabla[hash];
		NodoHTAd<K, V> current;
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
		this.tabla = (ListaEnlazada<NodoHTAd<K, V>>[]) new ListaEnlazada[size];
		for(int i = 0; i < this.tabla.length; i++) {
			this.tabla[i] = new ListaEnlazada<NodoHTAd<K, V>>();
		}
		this.size = 0;
	}

	public int getSize() {
		return this.size;
	}

	public int getPeso(K llave) {
		int hash = Math.abs(llave.hashCode())%tabla.length;
		ListaEnlazada<NodoHTAd<K, V>> bucket = this.tabla[hash];
		NodoHTAd<K, V> current;	
		for(int i = 0; i < bucket.getSize(); i++) {
			current = bucket.getAt(i);
			if(current.getLlave().equals(llave)) {
				return bucket.getAt(i).getPeso();
			}
		}
		throw new NoSuchElementException("No se encontro la llave en get"); 
	}
}

class NodoHTAd<K, V>{
	private V valor;
	private K llave;
	private int peso;
	
	public NodoHTAd(K llave, V valor, int peso){
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
	
	public int getPeso() {
		return this.peso;
	}
	
	public void setValor(V valor) {
		this.valor = valor;
	}
	
	public void setLlave(K llave) {
		this.llave = llave;
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}
}