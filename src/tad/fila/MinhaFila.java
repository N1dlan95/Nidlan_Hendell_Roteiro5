package tad.fila;

/**
 * Fila deve ser implementada com array fixo e estratégia circular
 * de gerenciamento de apontadores de cauda e cabeça.
 * @author fabioleite
 *
 */
public class MinhaFila implements FilaIF<Integer> {
	
	private int tamanho = 10;
	
	private int cauda = 1;
	private int cabeca = 0;
	
	private Integer[] meusDados = null;
/**
 * 	Construtores que inicializam o conjunto
 * @author Nidlan Hendell
 */
public MinhaFila(int tamanhoInicial) {
    this.tamanho = tamanhoInicial;
    this.meusDados = new Integer[tamanhoInicial];
}

public MinhaFila() {
    this.meusDados = new Integer[tamanho];
}


	@Override
	public void enfileirar(Integer item) throws FilaCheiaException{
		if (isFull()) {
			throw new FilaCheiaException();

		}else{
			this.meusDados[this.cauda-1]=item;
			this.cauda++;
		}
		if(this.cauda>this.tamanho){//Caso a cauda chegue no limite do array, mas
								//se tiver campos vazios antes da cabeça, acauda apontara para la
			this.cauda -= this.tamanho;
		}
		
	}

	@Override
	public Integer desenfileirar()throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}
			Integer auxiliar = this.meusDados[this.cabeca];
			this.meusDados[this.cabeca]=null;
			cabeca ++;
			
		if(this.cabeca>this.tamanho){//Caso a cabeca chegue no limite do array
			this.cabeca-= this.tamanho;
		}
		
		return auxiliar;
	}

	@Override
	public Integer verificarCauda() {
		return this.meusDados[this.cauda-1];
	}

	@Override
	public Integer verificarCabeca() {
		return this.meusDados[this.cabeca];
	}

	/**
 * isEmpty e isFull seguem a mesma logica, ambos verificam nos apontadores se contem elementos
 * Ex: _ _ _ _ _ 
 * 	   0 1 2 3 4
 * cauda = 1
 * cabeca = 0 
 * 
 * Ao adicionar 3 elementos, 4,1 e 7, remover 1 e adcionar mais 3, 6,2,8
 *     8 1 7 6 2 
 * 	   0 1 2 3 4
 * cauda = 2
 * cabeca = 1
 * 
 * if(2-1==1 && (this.meusDados[1]==null(1) && this.meusDados[cauda]==null(7))){
			return true;
		}
 * @author Nidlan Hendell
 *
 */
	@Override
	public boolean isEmpty() {
		if(cauda-cabeca==1 && (this.meusDados[cabeca]==null && this.meusDados[cauda]==null)){
			return true;
		}
		return false;
	}

	
	@Override
	public boolean isFull() {
		if(cauda-cabeca==1 && (this.meusDados[cabeca]!=null && this.meusDados[cauda]!=null)){
			return true;
		}
		return false;
	}

	@Override
	public int capacidade() {//quantos elementos cabem
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'capacidade'");
	}

	@Override
	public int tamanho() {// quantos elementos possuem
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'tamanho'");
	}


}
