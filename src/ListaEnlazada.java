

import java.util.NoSuchElementException;

public class ListaEnlazada<E> {
	private NodoLE<E> inicio,
					fin;
	private int size;

	public E getAt(int pos) {
		if(this.inicio != null) {
			E dato;
			NodoLE<E> current = this.inicio;
			dato = current.getDato();
			if(pos >= 0) {
				for(int i = 0; i < pos; i++) {
					current = current.getNext();
				}
				dato = current.getDato();
			}
			return dato;
		}
		return null;
	}
	
	public boolean contiene(E dato) {
		NodoLE<E> current = this.inicio;
		while(current != null) {
			if(current.getDato() == dato) return true;
		}
		return false;
	}
	
	public int index(E dato) {
		NodoLE<E> current = this.inicio;
		int pos = 0;
		while(current != null) {
			if(current.getDato() == dato) return pos;
			pos++;
		}
		return -1;
	}
	
	public ListaEnlazada(E[] datos) {
		this();
		for(int i = datos.length-1; i > 0; i--) {
			this.insertarInicio(datos[i]);
		}
	}
	
	public ListaEnlazada() {
		this.inicio = this.fin = null;
		this.size = 0;
	}
	
	public E[] getValues(){
		NodoLE<E> current = this.inicio();
		E[] nodos = (E[]) new Object[this.size];
		for(int i = 0; i<this.size-1; i++) {
			nodos[i] = current.getDato();
			current = current.getNext();
		}
		return nodos;
	}
	
	public NodoLE<E> inicio(){
		return this.inicio;
	}
	
	public NodoLE<E> fin() {
		return this.fin;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public boolean estaVacia() {
		return this.size == 0;
	}
	
	public E borrarEn(int pos) throws Exception {
		try {
			if(pos < 0) {
				throw new Exception("La posición debe ser positiva");
			}
			if(this.size == 0 || pos == 0) {
				try {
					
				}catch(NoSuchElementException e) {
					e.printStackTrace();
					System.out.println("Hola");
				}
			}else if(this.size == 1 || pos == 1) {
				return this.borrarInicio();
			}else {
				NodoLE<E> current = this.inicio, parent = this.inicio;
				for(int i = 0; i < pos; i++) {
					parent = current;
					current = current.getNext();
				}
				E regresar = current.getDato();
				parent.setNext(current.getNext());
				return regresar;
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public E borrarInicio() throws NoSuchElementException {
		try {
			E temp = this.inicio.getDato();
			this.inicio = this.inicio.getNext();
			this.size--;
			if(this.size == 0) {
				this.fin = null;
			}
			return temp;
		}catch(NullPointerException e) {
			e.printStackTrace();
			throw new NoSuchElementException("No se puede borrar el elemento");
		}
		
	}
	
	public E borrarFin() throws NoSuchElementException {
		try {
			E temp = this.fin.getDato();
			NodoLE<E> current = this.inicio;
			for(int i = 0; i < this.size-2; i++) {
				current = current.getNext();
			}
			current.setNext(null);
			this.fin = current;
			this.size--;
			return temp;
		}catch(NullPointerException e) {
			throw new NoSuchElementException("No se puede borrar el elemento.");
		}
	}
	
	public void setAt(int pos, E dato) {
		if(pos >= 0 && pos < this.size) {
			NodoLE<E> current = this.inicio;
			for(int i = 0; i < pos; i++) {
				current = current.getNext();
			}
			current.setDato(dato);
		}else {
			throw new IllegalArgumentException("La posicion "+pos+" es invalida");
		}
	}
	
	public String toString() {
		NodoLE<E> current = this.inicio;
		String resultado = "";
		for(int i = 0; i < size; i++) {
			resultado += current.getDato() + ",";
			current = current.getNext();
		}
		return resultado;
	}

	public void insertarInicio(E dato) {
		NodoLE<E> nuevo = new NodoLE<E>(dato, this.inicio);
		this.inicio = nuevo;
		if(this.size == 0) {
			this.fin = nuevo;
		}
		this.size++;
	}
	
	public void insertarFin(E dato) {
		if(this.size > 0) {
			NodoLE<E> nuevo = new NodoLE<>(dato, null);
			NodoLE<E> current = this.inicio;
			int pos = this.size;
			for(int i = 0; i < pos-1; i++) {
				current = current.getNext();
			}
			current.setNext(nuevo);
			this.size++;
		}else {
			this.insertarInicio(dato);
		}
	}
}
