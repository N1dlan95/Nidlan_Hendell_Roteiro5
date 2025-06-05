package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;
import java.lang.reflect.Array;
import tad.pilha.MinhaPilha;
import tad.pilha.PilhaCheiaException;
import tad.pilha.PilhaVaziaException;

/**
 * Implementação de uma lista encadeada utilizando sentinelas para cabeça e cauda.
 * Garante fácil gerenciamento dos elementos sem a necessidade de referências diretas à cabeça e à cauda.
 * @author Nidlan Hendell
 */
public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

	NodoListaEncadeada<T> cabeca = null; // Sentinela da cabeça
	NodoListaEncadeada<T> cauda = null; // Sentinela da cauda

	/**
	 * Construtor da lista que inicializa os nós sentinelas de cabeça e cauda.
	 * A cabeça aponta diretamente para a cauda no início.
	 */
	public ListaEncadeadaImpl() {
		cabeca = new NodoListaEncadeada<T>();
		cauda = new NodoListaEncadeada<T>();
		cabeca.setProximo(cauda);
	}

	/**
	 * Verifica se a lista está vazia.
	 * @return True se a lista não contém elementos, false caso contrário.
	 */
	@Override
	public boolean isEmpty() {
		return cabeca.getProximo() == cauda;
	}

	/**
	 * Retorna o número total de elementos na lista.
	 * @return Quantidade de elementos armazenados.
	 */
	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		}
		int contador = 1;
		NodoListaEncadeada<T> apontador = cabeca.getProximo();

		while (apontador.getProximo() != cauda) {
			apontador = apontador.getProximo();
			contador++;
		}
		return contador;
	}

	/**
	 * Procura um elemento na lista baseado na chave.
	 * @param chave Valor a ser buscado.
	 * @return O nó correspondente ou null se não for encontrado.
	 */
	@Override
	public NodoListaEncadeada<T> search(T chave) {
		NodoListaEncadeada<T> retorno = cabeca;

		while (retorno != null && retorno != cauda) {
			if (retorno.getChave().equals(chave)) {
				return retorno;
			}
			retorno = retorno.getProximo();
		}

		return null;
	}

	/**
	 * Insere um novo elemento na lista.
	 * Se a lista estiver vazia, ele se torna o primeiro nó entre cabeça e cauda.
	 * @param chave Valor a ser inserido.
	 */
	@Override
	public void insert(T chave) {
		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<T>(chave);

		if (isEmpty()) {
			novoNo.setProximo(cauda);
			cabeca.setProximo(novoNo);
		} else {
			NodoListaEncadeada<T> apontador = cabeca;
			while (apontador.getProximo() != cauda) {
				apontador = apontador.getProximo();
			}
			novoNo.setProximo(cauda);
			apontador.setProximo(novoNo);
		}
	}

	/**
	 * Insere um novo elemento na lista em uma posição específica.
	 * Se a lista estiver vazia, o elemento é simplesmente inserido como o primeiro.
	 * Caso contrário, o elemento será colocado na posição indicada.
	 * @param chave Valor a ser inserido.
	 * @param index Posição onde o elemento deve ser inserido.
	 */
	@Override
	public void insert(T chave, int index) {
		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<T>(chave);

		if (isEmpty()) {
			// Se a lista estiver vazia, apenas insere o elemento entre cabeça e cauda
			novoNo.setProximo(cauda);
			cabeca.setProximo(novoNo);
		} else {
			// Percorre até a posição correta
			NodoListaEncadeada<T> apontador = cabeca;
			for (int i = 0; i < index && apontador.getProximo() != cauda; i++) {
				apontador = apontador.getProximo();
			}
			// Ajusta as referências para inserir o novo nó no meio
			novoNo.setProximo(apontador.getProximo());
			apontador.setProximo(novoNo);
		}
	}

	/**
	 * Remove um nó baseado na chave fornecida.
	 * Ajusta as referências para garantir a continuidade da lista.
	 * @param chave Valor do nó a ser removido.
	 * @return O nó removido.
	 * @throws ListaVaziaException Se a lista estiver vazia.
	 * @throws ElementoNaoEncontradoException Se o nó não for encontrado.
	 */
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException, ElementoNaoEncontradoException {
		if (isEmpty()) {
			throw new ListaVaziaException();
		}
		NodoListaEncadeada<T> apontador = cabeca;
		while (apontador.getProximo() != cauda) {
			if (apontador.getProximo().getChave().equals(chave)) {
				NodoListaEncadeada<T> retorno = apontador.getProximo();
				apontador.setProximo(retorno.getProximo());
				return retorno;
			}
			apontador = apontador.getProximo();
		}
		throw new ElementoNaoEncontradoException();
	}

	/**
	 * Converte a lista encadeada para um array.
	 * @param clazz Classe do tipo de dado a ser armazenado no array.
	 * @return Array contendo os elementos da lista.
	 */
	@Override
	public T[] toArray(Class<T> clazz) {
		int tamanho = this.size();
		int controle = 0;

		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(clazz, tamanho);
		NodoListaEncadeada<T> apontador = cabeca.getProximo();

		while (apontador != cauda) {
			array[controle] = clazz.cast(apontador.getChave());
			controle++;
			apontador = apontador.getProximo();
		}

		return array;
	}

	/**
	 * Retorna uma string contendo os elementos da lista em ordem.
	 * @return String com os valores separados por vírgula.
	 */
	@Override
	public String imprimeEmOrdem() {
		String valores = "";
		NodoListaEncadeada<T> corrente = cabeca.getProximo();

		while (corrente != cauda) {
			valores += corrente.getChave() + ", ";
			corrente = corrente.getProximo();
		}

		return valores.isEmpty() ? "" : valores.substring(0, valores.length() - 2);
	}

	/**
	 * Retorna uma string contendo os elementos da lista em ordem inversa.
	 * @return String com os valores separados por vírgula.
	 * @throws PilhaCheiaException Se houver erro na pilha usada para armazenar os valores.
	 * @throws PilhaVaziaException Se a pilha estiver vazia antes do desempilhamento.
	 */
	@Override
	public String imprimeInverso() throws PilhaCheiaException, PilhaVaziaException {
		String valores = "";
		NodoListaEncadeada<T> corrente = cabeca.getProximo();
		MinhaPilha pilha = new MinhaPilha(this.size());

		if (isEmpty()) return "";

		while (corrente != cauda) {
			pilha.empilhar((Integer) corrente.getChave());
			corrente = corrente.getProximo();
		}

		while (!pilha.isEmpty()) {
			valores += pilha.desempilhar() + ", ";
		}

		return valores.substring(0, valores.length() - 2);
	}

	/**
	 * Retorna o nó sucessor da chave fornecida.
	 * @param chave Valor de referência.
	 * @return O próximo nó na sequência.
	 * @throws ElementoNaoEncontradoException Se o elemento não for encontrado na lista.
	 */
	@Override
	public NodoListaEncadeada<T> sucessor(T chave) throws ElementoNaoEncontradoException {
		NodoListaEncadeada<T> retorno = cabeca;
		while (retorno != cauda) {
			if (retorno.getChave().equals(chave)) {
				return retorno.getProximo();
			}
			retorno = retorno.getProximo();
		}
		throw new ElementoNaoEncontradoException();
	}

	/**
	 * Retorna o nó predecessor da chave fornecida.
	 * @param chave Valor de referência.
	 * @return O nó anterior na sequência.
	 * @throws ElementoNaoEncontradoException Se o elemento não for encontrado na lista.
	 */
	@Override
	public NodoListaEncadeada<T> predecessor(T chave) throws ElementoNaoEncontradoException {
		NodoListaEncadeada<T> apontador = cabeca.getProximo();

		while (apontador.getProximo() != cauda) {
			if (apontador.getProximo().getChave().equals(chave)) {
				return apontador;
			}
			apontador = apontador.getProximo();
		}
		throw new ElementoNaoEncontradoException();
	}
}
