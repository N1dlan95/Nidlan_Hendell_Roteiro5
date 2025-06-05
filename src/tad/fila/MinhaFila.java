package tad.fila;

/**
 * Implementação de uma fila utilizando um array fixo com estratégia circular.
 * Os ponteiros de cabeça e cauda ajudam a gerenciar os elementos dentro do espaço limitado do array.
 */
public class MinhaFila implements FilaIF<Integer> {

	private int tamanho = 5; // Tamanho padrão da fila
	private int cauda = 1; // Aponta para onde o próximo elemento será inserido
	private int cabeca = 0; // Aponta para o primeiro elemento da fila
	private Integer[] meusDados = null;

	/**
	 * Construtor que permite definir um tamanho inicial para a fila.
	 * @param tamanhoInicial Tamanho do array que armazenará os elementos da fila.
	 */
	public MinhaFila(int tamanhoInicial) {
		this.tamanho = tamanhoInicial;
		this.meusDados = new Integer[tamanhoInicial];
	}

	/**
	 * Construtor padrão que usa o tamanho definido na classe.
	 */
	public MinhaFila() {
		this.meusDados = new Integer[tamanho];
	}

	/**
	 * Adiciona um novo elemento à fila.
	 * Se a fila estiver cheia, lança uma exceção.
	 * Se atingir o final do array, a cauda é ajustada para a primeira posição disponível antes da cabeça.
	 * @param item Elemento a ser enfileirado.
	 * @throws FilaCheiaException Se a fila já estiver cheia.
	 */
	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		if (isFull()) {
			throw new FilaCheiaException();
		} else {
			this.meusDados[this.cauda - 1] = item;
			this.cauda++;
		}
		if (this.cauda > this.tamanho) { // Se a cauda ultrapassar o tamanho do array, reinicia no começo
			this.cauda -= this.tamanho;
		}
	}

	/**
	 * Remove e retorna o primeiro elemento da fila.
	 * Se a fila estiver vazia, lança uma exceção.
	 * @return O elemento removido.
	 * @throws FilaVaziaException Se a fila estiver vazia.
	 */
	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}
		Integer auxiliar = this.meusDados[this.cabeca];
		this.meusDados[this.cabeca] = null;
		cabeca++;

		if (this.cabeca > this.tamanho) { // Se a cabeça ultrapassar o tamanho do array, reinicia no começo
			this.cabeca -= this.tamanho;
		}

		return auxiliar;
	}

	/**
	 * Retorna o último elemento da fila.
	 * @return Elemento na cauda da fila.
	 */
	@Override
	public Integer verificarCauda() {
		return this.meusDados[this.cauda - 1];
	}

	/**
	 * Retorna o primeiro elemento da fila.
	 * @return Elemento na cabeça da fila.
	 */
	@Override
	public Integer verificarCabeca() {
		return this.meusDados[this.cabeca];
	}

	/**
	 * Verifica se a fila está vazia.
	 * A lógica compara os ponteiros de cabeça e cauda e verifica se há elementos.
	 * @return True se a fila estiver vazia, false caso contrário.
	 */
	@Override
	public boolean isEmpty() {
		return cauda - cabeca == 1 && (this.meusDados[cabeca] == null && this.meusDados[cauda] == null);
	}

	/**
	 * Verifica se a fila está cheia.
	 * A lógica compara os ponteiros de cabeça e cauda e verifica se todos os espaços estão ocupados.
	 * @return True se a fila estiver cheia, false caso contrário.
	 */
	@Override
	public boolean isFull() {
		return cauda - cabeca == 1 && (this.meusDados[cabeca] != null && this.meusDados[cauda] != null);
	}

	/**
	 * Retorna a capacidade total da fila (quantos elementos cabem no array).
	 * @return Número máximo de elementos suportados.
	 */
	@Override
	public int capacidade() {
		return this.tamanho;
	}

	/**
	 * Retorna o número atual de elementos na fila.
	 * A lógica utiliza os ponteiros para determinar quantos elementos foram enfileirados e desenfileirados.
	 * @return Quantidade de elementos armazenados na fila.
	 */
	@Override
	public int tamanho() {
		return cauda - 1 + this.tamanho - (cabeca + this.tamanho);
	}
}
