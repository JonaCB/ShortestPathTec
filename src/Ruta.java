
public class Ruta {
	private Nodo inicio,
				 destino;
	private int peso;
	
	public Ruta(Nodo inicio, Nodo destino, int peso) {
		this.setInicio(inicio);
		this.setDestino(destino);
		this.setPeso(peso);
	}

	/**
	 * @return the inicio
	 */
	public Nodo getInicio() {
		return inicio;
	}

	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(Nodo inicio) {
		this.inicio = inicio;
	}

	/**
	 * @return the destino
	 */
	public Nodo getDestino() {
		return destino;
	}

	/**
	 * @param destino the destino to set
	 */
	public void setDestino(Nodo destino) {
		this.destino = destino;
	}

	/**
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	
}
