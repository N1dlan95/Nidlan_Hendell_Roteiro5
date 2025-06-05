package tad.conjuntoDinamico;

/**
 * Estrutura de conjunto dinâmico encadeado.
 * Funciona como uma lista encadeada para armazenar números inteiros.
 */
public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {
	Integer valor = null; // Guarda o valor do nó atual
	MeuConjuntoDinamicoEncadeado proximo = null; // Referência para o próximo nó
	MeuConjuntoDinamicoEncadeado anterior = null; // Referência para o nó anterior

	/**
	 * Adiciona um novo item ao conjunto encadeado.
	 * Se for o primeiro elemento, ele é armazenado diretamente.
	 * Caso contrário, percorre até o último nó e adiciona o novo item no final.
	 * @param item Elemento a ser inserido.
	 */
	@Override
	public void inserir(Integer item) {
		MeuConjuntoDinamicoEncadeado auxiliar = this;

		if (this.valor == null) { // Se for o primeiro elemento
			this.valor = item;
		} else {
			while (auxiliar.proximo != null) { // Percorre até o último nó
				auxiliar = auxiliar.proximo;
			}
			MeuConjuntoDinamicoEncadeado novo = new MeuConjuntoDinamicoEncadeado();
			novo.valor = item;
			auxiliar.proximo = novo;
			novo.anterior = auxiliar;
		}
	}

	/**
	 * Remove um item do conjunto encadeado.
	 * Percorre até encontrar o item e ajusta as ligações dos nós vizinhos.
	 * @param item Elemento a ser removido.
	 * @return O elemento removido, ou null se ele não existir.
	 */
	@Override
	public Integer remover(Integer item) {
		MeuConjuntoDinamicoEncadeado auxiliar = this;
		Integer index = buscar(item);

		if (index != null) {
			for (int i = 0; i < index; i++) {
				auxiliar = auxiliar.proximo;
			}
			auxiliar.anterior.proximo = auxiliar.proximo;
			auxiliar.proximo.anterior = auxiliar.anterior;
			auxiliar.proximo = null;
			auxiliar.anterior = null;

			return auxiliar.valor;
		} else {
			return null;
		}
	}

	/**
	 * Retorna o número antes do item fornecido no conjunto.
	 * @param item Elemento de referência.
	 * @return O predecessor do item.
	 */
	@Override
	public Integer predecessor(Integer item) {
		Integer index = buscar(item);
		MeuConjuntoDinamicoEncadeado auxiliar = this;

		for (int i = 0; i <= index - 1; i++) {
			auxiliar = auxiliar.proximo;
		}
		return auxiliar.valor;
	}

	/**
	 * Retorna o número depois do item fornecido no conjunto.
	 * @param item Elemento de referência.
	 * @return O sucessor do item.
	 */
	@Override
	public Integer sucessor(Integer item) {
		Integer index = buscar(item);
		MeuConjuntoDinamicoEncadeado auxiliar = this;

		for (int i = 0; i <= index + 1; i++) {
			auxiliar = auxiliar.proximo;
		}
		return auxiliar.valor;
	}

	/**
	 * Retorna a quantidade de elementos armazenados no conjunto encadeado.
	 * @return Número total de elementos.
	 */
	@Override
	public int tamanho() {
		int contador = 0;
		MeuConjuntoDinamicoEncadeado auxiliar = this;

		while (auxiliar.proximo != null) {
			contador++;
			auxiliar = auxiliar.proximo;
		}
		return contador;
	}

	/**
	 * Procura um item no conjunto e retorna seu índice.
	 * @param item Elemento a ser encontrado.
	 * @return Índice do elemento ou null se ele não existir.
	 */
	@Override
	public Integer buscar(Integer item) {
		MeuConjuntoDinamicoEncadeado auxiliar = this;
		Integer contador = 0;

		while (auxiliar != null) {
			if (auxiliar.valor.equals(item)) {
				return contador; // Retorna a posição do item encontrado
			}
			auxiliar = auxiliar.proximo;
			contador++;
		}
		return null; // Retorna null se o item não for encontrado
	}

	/**
	 * Encontra o menor número presente no conjunto.
	 * Percorre toda a estrutura e mantém o menor valor encontrado.
	 * @return O menor elemento do conjunto.
	 */
	@Override
	public Integer minimum() {
		MeuConjuntoDinamicoEncadeado auxiliar = this.proximo;
		MeuConjuntoDinamicoEncadeado menor = this;

		while (auxiliar != null) {
			auxiliar = auxiliar.proximo;
			if (menor.valor > auxiliar.valor) {
				menor = auxiliar;
			}
		}
		return menor.valor;
	}

	/**
	 * Encontra o maior número presente no conjunto.
	 * Percorre toda a estrutura e mantém o maior valor encontrado.
	 * @return O maior elemento do conjunto.
	 */
	@Override
	public Integer maximum() {
		MeuConjuntoDinamicoEncadeado auxiliar = this.proximo;
		MeuConjuntoDinamicoEncadeado maior = this;

		while (auxiliar != null) {
			auxiliar = auxiliar.proximo;
			if (maior.valor < auxiliar.valor) {
				maior = auxiliar;
			}
		}
		return maior.valor;
	}
}
