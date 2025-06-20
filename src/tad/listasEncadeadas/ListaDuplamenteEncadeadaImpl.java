package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {

	
	NodoListaDuplamenteEncadeada<T> cabeca = null; // Estratégia usando marcação sentinela
	NodoListaDuplamenteEncadeada<T> cauda = null;// Estratégia usando marcação sentinela
	
	public ListaDuplamenteEncadeadaImpl() {// Estratégia usando marcação sentinela
		cabeca = new NodoListaDuplamenteEncadeada<T>();
		cauda = new NodoListaDuplamenteEncadeada<T>();
		cabeca.setProximo(cauda);
		
		// lista circular
		cabeca.setAnterior(cauda);
		cauda.setAnterior(cabeca);
		cabeca.setProximo(cauda);
		cauda.setProximo(cabeca);
		
	}

	@Override
	public boolean isEmpty() {// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(T chave) {
		//1. Craiar o novo registro
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(chave);
		if(isEmpty()) {
			novoNo.setProximo(cabeca.getProximo());
			((NodoListaDuplamenteEncadeada<T>) cabeca.getProximo()).setAnterior(novoNo);
			novoNo.setAnterior(cabeca);
			cabeca.setProximo(novoNo);
		}else{
			NodoListaDuplamenteEncadeada<T> apontador = cabeca;
			while(apontador.getProximo() != cauda) {

				apontador= (NodoListaDuplamenteEncadeada) apontador.getProximo();
			}
			cauda.setAnterior(novoNo);
			apontador.setProximo(novoNo);
			novoNo.setAnterior(apontador);
			novoNo.setProximo(cauda);
		}
		
	}

	@Override
	public NodoListaDuplamenteEncadeada remove(T chave)throws ElementoNaoEncontradoException {

		return null;
	}

	@Override
	public String imprimeEmOrdem() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String imprimeInverso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserePrimeiro(T elemento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(T chave, int index) {
		throw new UnsupportedOperationException("Precisa implementar!");
		
	}

	

}
