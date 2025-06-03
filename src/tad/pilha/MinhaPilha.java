package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {
	
	private int tamanho = 5;
	private Integer[] meusDados = null;
	private int topo=-1;

	public MinhaPilha(int _tamanho) {
		this.tamanho = _tamanho;
		meusDados = new Integer[tamanho];
	}
	
	public MinhaPilha() {
		meusDados = new Integer[this.tamanho];
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if(this.topo == tamanho-1){
			throw new PilhaCheiaException();
		}
		this.topo++;
		meusDados[this.topo] = item;
		
	}

	@Override
	public Integer desempilhar()throws PilhaVaziaException {
		if(this.topo == -1){
			throw new PilhaVaziaException();
		}
		this.topo--;
		return meusDados[this.topo+1];
	}

	@Override
	public Integer topo() {
		if(isEmpty())
			return null;
		return meusDados[this.topo];
	}

	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaVaziaException, PilhaCheiaException {
		int topo_auxiliar = this.topo;
		if(k<=0){
			throw new IllegalArgumentException();
		}
		MinhaPilha pilha_auxliliar = new MinhaPilha(this.tamanho);

		for(int i = 0; i < k; i++) {
			pilha_auxliliar.empilhar(this.desempilhar());
		}
		this.topo = topo_auxiliar;
		return pilha_auxliliar;
	}

	@Override
	public boolean isEmpty() {
        return this.topo == -1;
    }

	@Override
	public int capacidade() {
		return this.tamanho;
	}

	@Override
	public int tamanho() {
		return this.topo+1;
	}


}
