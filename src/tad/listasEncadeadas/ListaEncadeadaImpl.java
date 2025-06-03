package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T>{
	
//	NodoListaEncadeada<T> cabeca = null;
	
	NodoListaEncadeada<T> cabeca = null; // Estratégia usando marcação sentinela
	NodoListaEncadeada<T> cauda = null;// Estratégia usando marcação sentinela
	
	public ListaEncadeadaImpl() {// Estratégia usando marcação sentinela
		cabeca = new NodoListaEncadeada<T>();
		cauda = new NodoListaEncadeada<T>();
		cabeca.setProximo(cauda);
	}
	

	@Override
	public boolean isEmpty() {
		return cabeca.getProximo().equals(cauda);
		
	}

	@Override
	public int size() {
		if(isEmpty()){
			return 0;
		}
		int contador=1;
		NodoListaEncadeada<T> apontador = cabeca.getProximo();

		while(apontador.getProximo() != cauda) {
			apontador=apontador.getProximo();
			contador++;
		}
		return contador;
	}

	@Override
	public NodoListaEncadeada<T> search(T chave){
		NodoListaEncadeada<T> retorno = cabeca;

		while(retorno.getProximo() != cauda) {
			if(retorno.getChave() == chave){
				return retorno;
			}
			retorno=retorno.getProximo();
		}

		return null;
	}

	/**	Da maneira que compreendi a logica do codigo enviado, cabeça e cauda não tem valores nas suas respectivas chaves,
	 * assim se tendo valores apenas nos nós entre cabeca e cauda
	 *
	 * @author Nidlan hendell
	 */
	@Override
	public void insert(T chave) {
		//1. Craiar o novo registro
		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<T>(chave);
		//2. Inserir o novo nó na lista
		// se a lista estiver vazia**
		if (isEmpty()) {
			novoNo.setProximo(cauda);
			cabeca.setProximo(novoNo);
		} else { // lista não está vazia
			novoNo.setProximo(cabeca.getProximo());
			cabeca.setProximo(novoNo);
		}
		
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave)throws ListaVaziaException, ElementoNaoEncontradoException {
		if(isEmpty())
			throw new ListaVaziaException();
		NodoListaEncadeada<T> apontador = cabeca.getProximo();

		while(apontador.getProximo().getChave() != chave) {
			if(apontador.getProximo()== cauda) {
				throw new ElementoNaoEncontradoException();
			}
			apontador=apontador.getProximo();
		}
		NodoListaEncadeada<T> retorno = apontador.getProximo();
		apontador.setProximo(apontador.getProximo().getProximo());
		return retorno;
	}

	/**Nao
	 * Criar um array usando a classe utilitária conversor
	 * Conversor<T> c = new Conversor<T>();
	 * T[] meuArray = c.gerarArray(clazz, 10);
	 *
	 */
	@Override
	public T[] toArray(Class<T> clazz) {
		int tamanho = this.size();
		int controle = 0;

		Integer[] array = new Integer[tamanho];
		if(controle == tamanho){
			return (T[]) array;
		}
		NodoListaEncadeada<T> apontador = cabeca.getProximo();

		while(apontador.getProximo() != cauda) {
			array[controle]= (Integer) apontador.getChave();
			controle++;
			apontador=apontador.getProximo();
		}
		return (T[]) array;
	}


	@Override
	public String imprimeEmOrdem() {
//		throw new UnsupportedOperationException("Precisa implementar!");
		String valores = "";
		NodoListaEncadeada<T> corrente = cabeca.getProximo();
		
		while (!corrente.equals(cauda)) {
			valores += corrente.getChave() + ", ";
		}
		
		return valores.substring(0, valores.length()-2);
		
	}

	@Override
	public String imprimeInverso() {
		
		String valores = "";
		NodoListaEncadeada<T> corrente = cabeca.getProximo();
		NodoListaEncadeada<T> anterior = cabeca;
		
		while (!corrente.equals(cauda)) {
			valores += corrente.getChave() + ", ";
		}
		
		
		return valores.substring(0, valores.length()-2);
		
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) throws ElementoNaoEncontradoException {
		NodoListaEncadeada<T> retorno = this.search(chave);
		return retorno.getProximo();
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) throws ElementoNaoEncontradoException {
		NodoListaEncadeada<T> apontador = cabeca.getProximo();

		while(apontador.getProximo().getChave() != chave) {
			if(apontador.getProximo()== cauda) {
				throw new ElementoNaoEncontradoException();
			}
			apontador=apontador.getProximo();
		}
		return apontador;
	}

	@Override
	public void insert(T chave, int index) {
		//1. Craiar o novo registro
		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<T>(chave);
		//2. Inserir o novo nó na lista
		// se a lista estiver vazia**
		if (isEmpty()) {
			novoNo.setProximo(cauda);
			cabeca.setProximo(novoNo);
		} else { // lista não está vazia
			novoNo.setProximo(cabeca.getProximo());
			cabeca.setProximo(novoNo);
		}
	}

}
