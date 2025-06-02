package tad.conjuntoDinamico;

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {
Integer valor = null;
	MeuConjuntoDinamicoEncadeado proximo = null;
	MeuConjuntoDinamicoEncadeado anterior = null;



	@Override
	public void inserir(Integer item) {
		MeuConjuntoDinamicoEncadeado auxiliar = this;


		if(this.valor==null){
			this.valor = item;
		}else{
		while(auxiliar.proximo!=null){
			auxiliar = auxiliar.proximo;
		}
			MeuConjuntoDinamicoEncadeado novo = new MeuConjuntoDinamicoEncadeado();
			novo.valor = item;
			auxiliar.proximo =novo;
			novo.anterior = auxiliar;
		}
		
	}

	@Override
	public Integer remover(Integer item) {
		MeuConjuntoDinamicoEncadeado auxiliar =this;

		Integer index=buscar(item);
		if(index !=null){
		for(int i=0;i<index;i++){
			auxiliar = auxiliar.proximo;
		}
		auxiliar.anterior.proximo = auxiliar.proximo;
		auxiliar.proximo.anterior = auxiliar.anterior;
		auxiliar.proximo= null;
		auxiliar.anterior= null;


		return auxiliar.valor;
		}else{
			return null;
		}
	}

	@Override
	public Integer predecessor(Integer item) {
		Integer index = buscar(item);
		MeuConjuntoDinamicoEncadeado auxiliar =this;

		for(int i=0;i<=index-1;i++){
			auxiliar = auxiliar.proximo;
		}
		return auxiliar.valor;
	}

	@Override
	public Integer sucessor(Integer item) {
		Integer index = buscar(item);
		MeuConjuntoDinamicoEncadeado auxiliar =this;

		for(int i=0;i<=index+1;i++){
			auxiliar = auxiliar.proximo;
		}
		return auxiliar.valor;
	}

	@Override
	public int tamanho() {
		int contador = 0;
		MeuConjuntoDinamicoEncadeado auxiliar = this;
		while(auxiliar.proximo!=null){
			contador++;
			auxiliar = auxiliar.proximo;
		}
		return contador;
	}

	@Override
	public Integer buscar(Integer item) {//retorna o index
		MeuConjuntoDinamicoEncadeado auxiliar = this;

		Integer contador = 0;

		while(auxiliar!=null){
			if(auxiliar.valor.equals(item)){
				return contador;
			}
			auxiliar = auxiliar.proximo;
			contador++;
			
		}


		return null;
	}

	@Override
	public Integer minimum() {
		MeuConjuntoDinamicoEncadeado auxiliar = this.proximo;
		MeuConjuntoDinamicoEncadeado menor = this;

		while(auxiliar!=null){
			auxiliar = auxiliar.proximo;
			if(menor.valor > auxiliar.valor ){
				menor = auxiliar;
			}
		}
		return menor.valor;
	}

	@Override
	public Integer maximum() {
		MeuConjuntoDinamicoEncadeado auxiliar = this.proximo;
		MeuConjuntoDinamicoEncadeado maior= this;
		while(auxiliar!=null){
			auxiliar = auxiliar.proximo;
			if(maior.valor < auxiliar.valor ){
				maior = auxiliar;
			}
		}
		return maior.valor;
	}


}
