

import java.util.NoSuchElementException;

public class QueueLE<E> {
	private ListaEnlazada<E> lista;
	
	public void imprimeQueue() {
		if(this.isEmpty() == false) {
			System.out.print(this.dequeue());
			this.imprimeQueue();
		}
	}
	
	public QueueLE() {
		this.lista = new ListaEnlazada<>();
	}
	
	public int size() {
		return this.lista.getSize();
	}
	
	public boolean isEmpty() {
		return this.lista.estaVacia();
	}
	
	public void enqueue(E dato) {
		this.lista.insertarFin(dato);
	}

	public E dequeue() throws NoSuchElementException{
		try {
			return this.lista.borrarInicio();
		}catch(NoSuchElementException e) {
			throw new NoSuchElementException("No se puede sacar de una cola vacia.");
		}
	}
	
	public void flush() {
		this.lista = new ListaEnlazada<>();
		System.gc();
	}
	
	public E next() throws NoSuchElementException {
		try {
			return lista.inicio().getDato();
		}catch(NoSuchElementException e) {
			throw new NoSuchElementException("No se puede hacer un next de una cola vacía.");
		}
	}
}
