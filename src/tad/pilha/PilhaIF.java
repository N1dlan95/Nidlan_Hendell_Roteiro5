package tad.pilha;

public interface PilhaIF<E> {
	
	 void empilhar(E item) throws PilhaCheiaException;
	
	 E desempilhar() throws PilhaVaziaException;
	
	 E topo();
	
	 PilhaIF<E> multitop(int k) throws PilhaVaziaException, PilhaCheiaException;
	
	 boolean isEmpty();

     int capacidade();

	 int tamanho();
}
