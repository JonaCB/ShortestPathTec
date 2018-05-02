public class Heap {
	private Nodo[] cola;
	private Integer[] pesos;
	private int size;
	
	public Heap() {
		this.cola = new Nodo[5];
		this.pesos = new Integer[5];
		for(int i = 0; i < 5; i++) {
			this.pesos[i] = 5000;
		}
		this.size = 0;
	}
	
	public Heap(Nodo[] array) {
		this.cola = array;
		this.setSize(array);
		this.heapify();
	}
	
	private void heapify() {
		int index = ((this.size/2)-1);
		this.heapify(index, true);
	}

	private void heapify(int num, boolean up) {
		if(num < 0) {
			return;
		}else if(num >= this.size/2){
			return;
		}else {
			if(k(num) > k1(num) && k1(num) <= k2(num)) {
				this.swap(num, ((2*num)+1));
				this.heapify((num*2)+1, false);
			}else if(k(num) > k2(num) && k2(num) < k1(num)) {
				this.swap(num, ((2*num)+2));
				this.heapify((num*2)+2, false);
			}
			
			if(up = true) {
				this.heapify(num-1, true);
			}else {
				return;
			}
		}
	}
	
	private void swap(int num1, int num2) {
		Nodo tmp = this.cola[num1];
		Integer tmpPesos = this.pesos[num1];
		this.cola[num1] = this.cola[num2];
		this.pesos[num1] = this.pesos[num2];
		this.cola[num2] = tmp;
		this.pesos[num2] = tmpPesos;
	}

	public int k(int num) {
		return this.pesos[num];
	}
	
	public int k1(int num) {
		return this.pesos[(2*num)+1];
	}
	
	public int k2(int num) {
		return this.pesos[(2*num)+2];
	}
	
	public int getSize() {
		return this.size;
	}
	
	private void setSize(Nodo[] array) {
		for(Object in: array) {
			if(in != null) {
				this.size++;
			}
		}
	}

	public void insert(Nodo dato, Integer peso){
		if(this.cola.length == this.size) {
			this.expandir();
		}
		this.cola[this.size] = dato;
		this.pesos[this.size] = peso;
		this.heapify();
		this.size++;
	}
	
	private void expandir() {
		Nodo[] nuevoArreglo = new Nodo[this.size+5];
		Integer[] nuevoPesos = new Integer[this.size+5];
		for(int i = 0; i < this.size; i++) {
			nuevoArreglo[i] = this.cola[i];
			nuevoPesos[i] = this.pesos[i];
			nuevoPesos[this.size+i] = 5000;
		}
		this.cola = nuevoArreglo;
		this.pesos = nuevoPesos;
	}

	public Nodo delete() {
		int posFinal = 0;
		int aux = 0;
		while(aux < this.size) {
			aux = (aux*2)+1;
			if(aux < this.size) {
				posFinal = aux;
			}
		}
		Nodo tmp = this.cola[0];
		Integer tmpPeso = this.pesos[0];
		this.cola[0] = this.cola[posFinal];
		this.pesos[0] = this.pesos[posFinal];
		this.cola[posFinal] = null;
		this.pesos[posFinal] = 5000;
		this.heapify();
		this.size--;
		return tmp;
	}
	
	public void imprimeArray() {
		for(int i = 0; i < this.size; i++) {
			System.out.print(this.cola[i] + ", ");
		}
		System.out.println();
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
}