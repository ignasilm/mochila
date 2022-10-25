package es.ilopezma.mochila;

public class ObjetoMochila implements Comparable<ObjetoMochila>, Cloneable {

	private Long numero;
	private Double valor;
	private Double peso;
	private Double relacion;
	
	
	/**
	 * @return the numero
	 */
	public Long getNumero() {
		return numero;
	}


	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Long numero) {
		this.numero = numero;
	}


	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}


	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}


	/**
	 * @return the peso
	 */
	public Double getPeso() {
		return peso;
	}


	/**
	 * @param peso the peso to set
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}


	/**
	 * @return the relacion
	 */
	public Double getRelacion() {
		return relacion;
	}


	@Override
	protected ObjetoMochila clone() throws CloneNotSupportedException {
		ObjetoMochila nuevoObjMochila = (ObjetoMochila) super.clone();
		
		nuevoObjMochila.setNumero(this.numero != null ? this.numero.longValue(): null);
		nuevoObjMochila.setPeso(this.peso != null ? this.peso.doubleValue(): null);
		nuevoObjMochila.setRelacion(this.relacion != null ? this.relacion.doubleValue():null);
		nuevoObjMochila.setValor(this.valor != null ? this.valor.doubleValue():null);
		return nuevoObjMochila;
	}


	/**
	 * @param relacion the relacion to set
	 */
	public void setRelacion(Double relacion) {
		this.relacion = relacion;
	}


	public ObjetoMochila() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public int compareTo(ObjetoMochila otroObjeto) {
		int comparacion = 0;
		
		//Primer critero: relacion
		comparacion = (-1) * this.getRelacion().compareTo(otroObjeto.getRelacion());
		if (comparacion == 0) {
			//Segundo criterio: peso en orden inverso
			comparacion =  (-1) * this.getPeso().compareTo(otroObjeto.getPeso());
		}
		
		return comparacion;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ObjetoMochila [numero=").append(numero).append(", valor=").append(valor).append(", peso=")
				.append(peso).append(", relacion=").append(relacion).append("]");
		return builder.toString();
	}

	

	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
	}


}
