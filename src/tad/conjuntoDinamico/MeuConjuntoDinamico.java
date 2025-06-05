package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;
import java.util.Objects;

/**
 * Implementação de um conjunto dinâmico que cresce conforme necessário.
 * Guarda números inteiros e expande sua capacidade automaticamente.
 * @author Nidlan Hendell
 */
public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer>{

	private Integer[] meusDados = new Integer[5]; // Array inicial com capacidade fixa
	private int posInsercao = 0; // Indica a posição onde o próximo elemento será inserido

	/**
	 * Adiciona um novo item ao conjunto.
	 * Se o array estiver cheio, aumenta a capacidade antes de inserir.
	 * @param item Elemento a ser adicionado.
	 * @throws IllegalArgumentException Se o item for null.
	 */
	@Override
	public void inserir(Integer item) throws IllegalArgumentException {
		if (this.posInsercao == this.capacidade()) {
			this.aumentarArray(this.posInsercao);
		} else if (item == null) {
			throw new IllegalArgumentException("Elemento não pode ser null");
		}
		this.meusDados[this.posInsercao] = item;
		this.posInsercao++;
	}

	/**
	 * Expande o array quando ele atinge a capacidade máxima.
	 * Aumenta em 25% e mantém os dados existentes.
	 * @param posicaoDeInserir Posição onde a inserção deve continuar.
	 * @return Novo array com capacidade expandida.
	 */
	private Integer[] aumentarArray(int posicaoDeInserir) {
		Integer[] array_auxiliar = this.meusDados;
		this.meusDados = new Integer[this.capacidade() + (this.capacidade() * 25 / 100)];
		this.posInsercao = 0;
		for (Integer integer : array_auxiliar) {
			if (integer != null) {
				this.inserir(integer);
			}
		}
		this.posInsercao = posicaoDeInserir;
		return this.meusDados;
	}

	/**
	 * Remove um item do conjunto e ajusta as posições restantes.
	 * @param item Elemento a ser removido.
	 * @return O item removido.
	 * @throws ElementoNaoEncontradoException Se o item não for encontrado.
	 * @throws ConjuntoDinamicoVazioException Se o conjunto estiver vazio.
	 */
	@Override
	public Integer remover(Integer item) throws ElementoNaoEncontradoException, ConjuntoDinamicoVazioException {
		if (item == null) {
			throw new IllegalArgumentException("Elemento não pode ser null");
		}
		int index = this.buscarComIndice(item);
		Integer removido = meusDados[index];
		for (int i = index; i < this.tamanho() - 1; i++) {
			meusDados[i] = this.meusDados[i + 1];
		}
		posInsercao--;
		return removido;
	}

	/**
	 * Retorna o número que está antes do item fornecido no conjunto.
	 * @param item Elemento de referência.
	 * @return O predecessor do item ou null se não houver um anterior.
	 * @throws ConjuntoDinamicoVazioException Se o conjunto estiver vazio.
	 */
	@Override
	public Integer predecessor(Integer item) throws ConjuntoDinamicoVazioException {
		if (this.tamanho() == 0) {
			throw new ConjuntoDinamicoVazioException();
		}
		for (int i = 0; i < this.tamanho(); i++) {
			if (Objects.equals(this.meusDados[i], item)) {
				return (i == 0) ? null : meusDados[i - 1];
			}
		}
		return null;
	}

	/**
	 * Retorna o número que está depois do item fornecido no conjunto.
	 * @param item Elemento de referência.
	 * @return O sucessor do item ou null se não houver um próximo.
	 * @throws ConjuntoDinamicoVazioException Se o conjunto estiver vazio.
	 */
	@Override
	public Integer sucessor(Integer item) throws ConjuntoDinamicoVazioException {
		if (this.tamanho() == 0) {
			throw new ConjuntoDinamicoVazioException();
		}
		for (int i = 0; i < this.tamanho(); i++) {
			if (Objects.equals(this.meusDados[i], item)) {
				return (i == this.tamanho() - 1) ? null : meusDados[i + 1];
			}
		}
		return null;
	}

	/**
	 * Retorna a quantidade de elementos armazenados no conjunto.
	 * @return Número total de elementos no conjunto.
	 */
	@Override
	public int tamanho() {
		return posInsercao;
	}

	/**
	 * Retorna a capacidade total do conjunto (tamanho máximo do array).
	 * @return O tamanho total disponível no array.
	 */
	public int capacidade() {
		return this.meusDados.length;
	}

	/**
	 * Busca um item e retorna sua posição no conjunto.
	 * @param item Elemento a ser encontrado.
	 * @return O índice do item no array.
	 * @throws ElementoNaoEncontradoException Se o item não for encontrado.
	 * @throws ConjuntoDinamicoVazioException Se o conjunto estiver vazio.
	 */
	public Integer buscarComIndice(Integer item) throws ElementoNaoEncontradoException, ConjuntoDinamicoVazioException {
		if (this.tamanho() == 0) {
			throw new ConjuntoDinamicoVazioException();
		}
		for (int i = 0; i < this.posInsercao; i++) {
			if (this.meusDados[i].equals(item)) {
				return i;
			}
		}
		throw new ElementoNaoEncontradoException();
	}

	/**
	 * Busca um item no conjunto e retorna ele mesmo.
	 * @param item Elemento a ser encontrado.
	 * @return O próprio item, se existir.
	 * @throws ElementoNaoEncontradoException Se o item não for encontrado.
	 * @throws ConjuntoDinamicoVazioException Se o conjunto estiver vazio.
	 */
	@Override
	public Integer buscar(Integer item) throws ElementoNaoEncontradoException, ConjuntoDinamicoVazioException {
		if (item == null) {
			throw new IllegalArgumentException("Elemento não pode ser null");
		}
		boolean conjuntoVazio = true;
		for (Integer integer : this.meusDados) {
			if (integer != null) {
				conjuntoVazio = false;
			}
			if (Objects.equals(integer, item)) {
				return integer;
			}
		}
		if (conjuntoVazio) {
			throw new ConjuntoDinamicoVazioException("O conjunto está vazio!");
		}
		throw new ElementoNaoEncontradoException();
	}

	/**
	 * Encontra o menor número presente no conjunto.
	 * @return O menor elemento do conjunto.
	 * @throws ConjuntoDinamicoVazioException Se o conjunto estiver vazio.
	 */
	@Override
	public Integer minimum() throws ConjuntoDinamicoVazioException {
		if (this.tamanho() == 0) {
			throw new ConjuntoDinamicoVazioException();
		}
		Integer menor = this.meusDados[0];
		for (int i = 1; i < this.tamanho(); i++) {
			if (menor > this.meusDados[i]) {
				menor = this.meusDados[i];
			}
		}
		return menor;
	}

	/**
	 * Encontra o maior número presente no conjunto.
	 * @return O maior elemento do conjunto.
	 * @throws ConjuntoDinamicoVazioException Se o conjunto estiver vazio.
	 */
	@Override
	public Integer maximum() throws ConjuntoDinamicoVazioException {
		if (this.tamanho() == 0) {
			throw new ConjuntoDinamicoVazioException();
		}
		Integer maior = this.meusDados[0];
		for (int i = 1; i < this.tamanho(); i++) {
			if (maior < this.meusDados[i]) {
				maior = this.meusDados[i];
			}
		}
		return maior;
	}
}
