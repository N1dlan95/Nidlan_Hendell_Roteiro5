package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;

import java.util.Objects;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer>{

	
	private Integer[] meusDados = new Integer[5];
	private int posInsercao = 0;
	
	@Override
	public void inserir(Integer item) throws IllegalArgumentException{
		if (this.posInsercao == this.capacidade()) {
			this.aumentarArray(this.posInsercao);
		}else if(item==null){
			throw new IllegalArgumentException("Elemento não pode ser null");
		}
		this.meusDados[this.posInsercao] = item;
		this.posInsercao++;
	}
	
	private Integer[] aumentarArray(int posicaoDeInserir) {
		Integer [] array_auxiliar = this.meusDados;
		this.meusDados = new Integer[this.capacidade()+(this.capacidade()*25/100)];;
		this.posInsercao=0;
			for (Integer integer : array_auxiliar) {
				if(integer != null) {
					this.inserir(integer);
				}
			}
			this.posInsercao = posicaoDeInserir;
			return this.meusDados;
		
	}



	@Override
	public Integer remover(Integer item) throws ElementoNaoEncontradoException,ConjuntoDinamicoVazioException {
		if(item==null){
			throw new IllegalArgumentException("Elemento não pode ser null");
		}
		int index = this.buscarComIndice(item);
		Integer removido =meusDados[index];
		for(int i=index; i<this.tamanho()-1; i++){
			meusDados[i] = this.meusDados[i+1];
		}
		posInsercao--;
		return removido;
	}
/**
 * 	Ambas funções abaixo recebem um valor x que está na posição n do conjunto, a função ira retornar o valor da posição n-1 ou n+1
 * @author Nidlan Hendell
 */

	@Override
	public Integer predecessor(Integer item)throws ConjuntoDinamicoVazioException{
		if(this.tamanho()==0){
			throw new ConjuntoDinamicoVazioException();
		}
		for(int i =0;i<this.tamanho();i++){
			if(Objects.equals(this.meusDados[i], item)){
				if(i==0){
					return null;
				}
				return meusDados[i-1];
			}
		}
		return null;
	}

	@Override
	public Integer sucessor(Integer item)throws ConjuntoDinamicoVazioException{
		if(this.tamanho()==0){
			throw new ConjuntoDinamicoVazioException();
		}
		for(int i =0;i<this.tamanho();i++){
			if(Objects.equals(this.meusDados[i], item)){
				if(i==this.tamanho()){
					return null;
				}
				return meusDados[i+1];
			}
		}
		return null;
	}

	@Override
	public int tamanho() {
		return posInsercao; // retorna diretamente a quantidade de elementos
	}


	public int capacidade(){
		return this.meusDados.length;
	}

	public Integer buscarComIndice(Integer item) throws ElementoNaoEncontradoException,ConjuntoDinamicoVazioException{//irá retornar o index do valor
		Integer contador = 0;
		if(this.tamanho()==0){
			throw new ConjuntoDinamicoVazioException();
		}
		for (int i = 0; i < this.posInsercao; i++) {
			if (this.meusDados[i].equals(item)) {
				return i; // Retorna o índice do elemento encontrado
			}
		}
		throw new ElementoNaoEncontradoException();
}
	@Override
	public Integer buscar(Integer item) throws ElementoNaoEncontradoException,ConjuntoDinamicoVazioException{
		boolean conjuntoVazio = true;
		if(item==null){
			throw new IllegalArgumentException("Elemento não pode ser null");
		}
		for (Integer integer : this.meusDados) {
			if (integer != null) {
				conjuntoVazio = false; // Encontrou pelo menos um elemento válido
			}
			if(Objects.equals(integer, item)){
				return integer;
			}
		}
		if (conjuntoVazio) {
			throw new ConjuntoDinamicoVazioException("O conjunto está vazio!");
		}


		throw new ElementoNaoEncontradoException();
	}

/**
 * minimum e maximun percorre todo o conjunto e procuram seu menor e maior valor respectivamente
 * @author Nidlan Hendell
 *
 */

	@Override
	public Integer minimum()throws ConjuntoDinamicoVazioException{
		if(this.tamanho()==0){
			throw new ConjuntoDinamicoVazioException();
		}
		Integer auxiliar= this.meusDados[0];
		for (int i=1;i<this.tamanho();i++){
			if(auxiliar>this.meusDados[i]){
				auxiliar = this.meusDados[i];

			}
		}
		return auxiliar;
	}

	@Override
	public Integer maximum() throws ConjuntoDinamicoVazioException{
		if(this.tamanho()==0){
			throw new ConjuntoDinamicoVazioException();
		}
		Integer auxiliar= this.meusDados[0];
		for (int i=1;i<this.tamanho();i++){
			if(auxiliar<this.meusDados[i]){
				auxiliar = this.meusDados[i];

			}
		}
		return auxiliar;
	}
}
