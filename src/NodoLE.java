

public class NodoLE<E> {
	private E dato;
	private NodoLE<E> next;
	
	public NodoLE(E dato, NodoLE<E> next) {
		this.dato = dato;
		this.next = next;
	}
	
	public NodoLE(E dato) {
		this(dato, null);
	}

	public E getDato() {
		return dato;
	}

	public void setDato(E dato) {
		this.dato = dato;
	}

	public NodoLE<E> getNext() {
		return next;
	}

	public void setNext(NodoLE<E> next) {
		this.next = next;
	}
	
	public String toString() {
		return this.dato.toString();
	}
	
}
