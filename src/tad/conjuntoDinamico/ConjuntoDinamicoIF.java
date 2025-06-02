package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;

public interface ConjuntoDinamicoIF<E> {
	
	public void inserir(E item);
	
	public E remover(E item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException;
	
	public E predecessor(E item) throws ConjuntoDinamicoVazioException;
	
	public E sucessor(E item) throws ConjuntoDinamicoVazioException;
	
	public int tamanho();
	
	public E buscar(E item) throws ElementoNaoEncontradoException, ConjuntoDinamicoVazioException;
	
	public E minimum()throws ConjuntoDinamicoVazioException;//tivemos q modificar a interface para que faça sentido no teste;
	//não havia como ter a execeção no teste se nao tinha na interface
	public E maximum()throws ConjuntoDinamicoVazioException;

}
