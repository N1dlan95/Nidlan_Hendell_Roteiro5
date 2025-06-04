package tad.listasEncadeadas;
import java.util.Objects;


public class NodoListaEncadeada<T extends Comparable<T>> {
	
	protected T chave;
	protected NodoListaEncadeada<T> proximo = null;
	
	public NodoListaEncadeada() {
		this.setChave(null);
		this.setProximo(null);
	}
	
	public NodoListaEncadeada(T chave) {
		this.setChave(chave);
		this.setProximo(null);
	}
	
	public NodoListaEncadeada(T chave, NodoListaEncadeada<T> proximo) {
		this.setChave(chave);
		this.setProximo(proximo);
	}

	public T getChave() {
		return chave;
	}

	public void setChave(T chave) {
		this.chave = chave;
	}

	public NodoListaEncadeada<T> getProximo() {
		return proximo;
	}

	public void setProximo(NodoListaEncadeada<T> proximo) {
		this.proximo = proximo;
	}
	
	public boolean isNull() {
		return (chave == null);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NodoListaEncadeada<?>)) {
			return false;
		}

		@SuppressWarnings("unchecked")
		NodoListaEncadeada<T> aComparar = (NodoListaEncadeada<T>) obj;

		if (this.chave == null || aComparar.getChave() == null) {
			return false;
		}

		return this.chave.equals(aComparar.getChave()) &&
				Objects.equals(this.getProximo(), aComparar.getProximo());
	}


	@Override
	public String toString() {
		if (!this.isNull())
			return this.chave.toString();
		return null;
	}
	
	

}
