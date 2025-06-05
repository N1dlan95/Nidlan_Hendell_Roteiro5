package tad.pilha;

/**
 * Implementação de uma pilha usando um array fixo.
 * Funciona com um topo que gerencia os elementos, garantindo o comportamento LIFO (Last In, First Out).
 * @author Nidlan Hendell
 */
public class MinhaPilha implements PilhaIF<Integer> {

	private int tamanho = 5; // Tamanho padrão da pilha
	private Integer[] meusDados = null; // Array onde os elementos são armazenados
	private int topo = -1; // Indica o topo da pilha (-1 significa que está vazia)

	/**
	 * Construtor que permite definir um tamanho específico para a pilha.
	 * @param _tamanho Tamanho da pilha.
	 */
	public MinhaPilha(int _tamanho) {
		this.tamanho = _tamanho;
		meusDados = new Integer[tamanho];
	}

	/**
	 * Construtor padrão que usa o tamanho fixo da classe.
	 */
	public MinhaPilha() {
		meusDados = new Integer[this.tamanho];
	}

	/**
	 * Empilha um novo item no topo da pilha.
	 * Se a pilha estiver cheia, lança uma exceção.
	 * @param item Elemento a ser adicionado.
	 * @throws PilhaCheiaException Se a pilha já estiver cheia.
	 */
	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (this.topo == tamanho - 1) {
			throw new PilhaCheiaException();
		}
		this.topo++;
		meusDados[this.topo] = item;
	}

	/**
	 * Remove e retorna o último elemento adicionado na pilha.
	 * Se a pilha estiver vazia, lança uma exceção.
	 * @return O elemento removido.
	 * @throws PilhaVaziaException Se a pilha estiver vazia.
	 */
	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (this.topo == -1) {
			throw new PilhaVaziaException();
		}
		this.topo--;
		return meusDados[this.topo + 1];
	}

	/**
	 * Retorna o elemento que está no topo da pilha sem removê-lo.
	 * @return Elemento no topo da pilha ou null se estiver vazia.
	 */
	@Override
	public Integer topo() {
		if (isEmpty())
			return null;
		return meusDados[this.topo];
	}

	/**
	 * Retorna os últimos k elementos da pilha sem removê-los.
	 * @param k Quantidade de elementos a recuperar do topo.
	 * @return Uma nova pilha contendo os últimos k elementos.
	 * @throws PilhaVaziaException Se a pilha estiver vazia.
	 * @throws PilhaCheiaException Se a pilha auxiliar não suportar os elementos.
	 */
	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaVaziaException, PilhaCheiaException {
		int topo_auxiliar = this.topo;
		if (k <= 0) {
			throw new IllegalArgumentException();
		}
		MinhaPilha pilha_auxiliar = new MinhaPilha(this.tamanho);

		for (int i = 0; i < k; i++) {
			pilha_auxiliar.empilhar(this.desempilhar());
		}
		this.topo = topo_auxiliar;
		return pilha_auxiliar;
	}

	/**
	 * Verifica se a pilha está vazia.
	 * @return True se não houver elementos, false caso contrário.
	 */
	@Override
	public boolean isEmpty() {
		return this.topo == -1;
	}

	/**
	 * Retorna a capacidade total da pilha (quantos elementos cabem nela).
	 * @return Capacidade máxima da pilha.
	 */
	@Override
	public int capacidade() {
		return this.tamanho;
	}

	/**
	 * Retorna o número atual de elementos na pilha.
	 * @return Quantidade de elementos armazenados na pilha.
	 */
	@Override
	public int tamanho() {
		return this.topo + 1;
	}
}
