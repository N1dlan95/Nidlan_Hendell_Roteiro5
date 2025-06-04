package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;
import java.lang.reflect.Array;
import tad.pilha.MinhaPilha;
import tad.pilha.PilhaCheiaException;
import tad.pilha.PilhaVaziaException;

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
		return cabeca.getProximo()==cauda;
		
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

		while(retorno != null && retorno != cauda) {
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
			NodoListaEncadeada<T> apontador = cabeca;
			while (apontador.getProximo() != null && apontador.getProximo() != cauda) {

				apontador=apontador.getProximo();
				}
			novoNo.setProximo(cauda);
			apontador.setProximo(novoNo);
		}
		
	}


	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException, ElementoNaoEncontradoException {
		if (isEmpty()) {
			throw new ListaVaziaException();
		}
		NodoListaEncadeada<T> apontador = cabeca;
		while (apontador.getProximo() != null && apontador.getProximo() != cauda) {
			if (apontador.getProximo().getChave().equals(chave)) {

				apontador.setProximo(apontador.getProximo().getProximo());
				return apontador.getProximo();
			}
			apontador = apontador.getProximo();
		}

		throw new ElementoNaoEncontradoException();
	}


	/**Nao soube implementar este metodo, peguei da internet
	 *
	 *
	 */
	@Override
	public T[] toArray(Class<T> clazz) {
		int tamanho = this.size();
		int controle = 0;

		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(clazz, tamanho);

		NodoListaEncadeada<T> apontador = cabeca.getProximo();

		while (apontador != null && apontador != cauda) {
			array[controle] = clazz.cast(apontador.getChave()); // Cast seguro
			controle++;
			apontador = apontador.getProximo();
		}

		return array;
	}


	@Override
	public String imprimeEmOrdem() {
		String valores = "";
		NodoListaEncadeada<T> corrente = cabeca.getProximo();

		while (corrente != null && corrente != cauda) {
			valores += corrente.getChave() + ", ";
			corrente = corrente.getProximo();
		}

		return valores.isEmpty() ? "" : valores.substring(0, valores.length() - 2);
	}


	@Override
	public String imprimeInverso() throws PilhaCheiaException, PilhaVaziaException {

		String valores = "";
		NodoListaEncadeada<T> corrente = cabeca.getProximo();
		NodoListaEncadeada<T> anterior = cabeca;
		MinhaPilha pilha = new MinhaPilha(this.size());
		if (isEmpty()) return "";

		while (corrente!=null && !corrente.getProximo().equals(cauda)) {
			pilha.empilhar((Integer) corrente.getChave());
			corrente = corrente.getProximo();
		}
		while(!pilha.isEmpty()){
			valores += pilha.desempilhar() + ", ";
		}
		return valores.substring(0, valores.length()-2);

	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) throws ElementoNaoEncontradoException {
		NodoListaEncadeada<T> retorno = cabeca;
		while(retorno != null && retorno != cauda) {
			if(retorno.getChave() == chave){
				return retorno.getProximo();
			}
			retorno=retorno.getProximo();
		}
			throw new ElementoNaoEncontradoException();
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) throws ElementoNaoEncontradoException {
		NodoListaEncadeada<T> apontador = cabeca.getProximo();

		while(apontador.getProximo() != null && apontador.getProximo()!=cauda) {
			if(apontador.getProximo().getChave() == chave) {
				return apontador;
			}
			apontador=apontador.getProximo();
		}
		throw new ElementoNaoEncontradoException();
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
