
public class Arista {
	 private Nodo destino;
	 private double peso;
	 
	 public Arista(Nodo destino, double peso) {
		 this.destino = destino;
		 this.peso = peso;
	 }
	 
	 public Nodo getDestino() {
		 return this.destino;
	 }
	 
	 public double getPeso() {
		 return this.peso;
	 }
	 
	 public void setPeso(double peso) {
		 this.peso = peso;
	 }
}
