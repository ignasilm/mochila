/**
 * 
 */
package es.ilopezma.mochila;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ilopezma
 *
 */
public class Mochila {

	public static final String VALOR = "Valor";
	public static final String PESO = "Peso";

	/**
	 * 
	 */
	public Mochila() {
		super();
	}

	/** Calcula la lista de objetos optima para llevar los objetos que sumen mayor valor,
	 *  para el peso que soporta la mochila.
	 *  
	 * @param pesoMax
	 * @param listaEntrada
	 * @return
	 */
	List<Long> calcularLlenadoMochila(Double pesoMax, List<Map<String, Double>> listaEntrada) {
		
		List<Long> resultado = new ArrayList<Long>(); 
		List<ObjetoMochila> listaObjetos = new ArrayList<ObjetoMochila>();
		List<ObjetoMochila> listaRecorrer = null;
		List<ObjetoMochila> listaMochila = null;
		List<ObjetoMochila> listaDescartados = null;
		List<ObjetoMochila> listaMochilaOptimizada = null;
		ObjetoMochila objetoMochila = null;
		long numero = 0;
		Double peso = null;
		Double valor = null;
		
		try {
			
			if(pesoMax != null && listaEntrada != null && !listaEntrada.isEmpty()) {

				//Primer paso: convertir la lista a mi bean con la relacion valor/peso y añadirlos a mi lista
				for (Map<String, Double> objetoEntrada : listaEntrada) {
					
					//Siempre incremento el numero de objeto aunque lo descarte posteriormente, porque si viene en la lista el objeto existe
					numero++;
					
					peso = objetoEntrada.get(PESO);
					valor = objetoEntrada.get(VALOR);
					
					// Valido que no venga el peso o valor a null, ni que el valor sea 0 porque lo descarto de inicio					
					if (peso!= null && valor != null && valor.doubleValue() > 0) {
					
						objetoMochila = new ObjetoMochila();
						objetoMochila.setNumero(numero);
						objetoMochila.setPeso(peso);
						objetoMochila.setValor(valor);
						if (peso.doubleValue() > 0) {
							objetoMochila.setRelacion(valor / peso);
						} else {
							//Si no tiene peso, la relacion será todo el valor. No divido porque dara error
							objetoMochila.setRelacion(valor);
						}
						
						listaObjetos.add(objetoMochila);
						
					}
				}
				
				//Segundo paso: ordenar la lista
				listaObjetos.sort(null);
				listaRecorrer = new ArrayList<ObjetoMochila>();
				listaRecorrer.addAll(listaObjetos);
				
				//Tercer paso: llenar la mochila
				Double pesoMochila = 0d;
				Double valorMochila = 0d;
				listaMochila = new ArrayList<ObjetoMochila>();
				listaDescartados = new ArrayList<ObjetoMochila>();
				StringBuilder mensaje = null;
				System.out.println("---------------------------------------------" );
				System.out.println("LLenamos nueva mochila de peso maximo "+ pesoMax );
				
				//recorro la lista ordenada para ir llenando la mochila con los objetos de la lista
				while (pesoMochila.doubleValue() < pesoMax.doubleValue() && !listaRecorrer.isEmpty()) {
					objetoMochila = listaRecorrer.get(0);
					if (pesoMochila.doubleValue() + objetoMochila.getPeso().doubleValue() <= pesoMax.doubleValue()) {
						listaMochila.add(objetoMochila);
						pesoMochila = pesoMochila + objetoMochila.getPeso();
						valorMochila = valorMochila + objetoMochila.getValor();
						
						mensaje = new StringBuilder();
						mensaje.append("Se añade el objeto ").append(objetoMochila);
						mensaje.append(". El valor actual es: ").append(valorMochila);
						mensaje.append(" y el peso actual es: ").append(pesoMochila);
						mensaje.append(". Peso libre en la mochila: ").append(pesoMax - pesoMochila);
						
						System.out.println(mensaje.toString() );

					} else {
						listaDescartados.add(objetoMochila);

						mensaje = new StringBuilder();
						mensaje.append("Se descarta el objeto ").append(objetoMochila);
						
						System.out.println(mensaje.toString() );

					}
					listaRecorrer.remove(0);
				}
				
				//Cuarto Paso: optimizar resultado
				if(pesoMochila.doubleValue() < pesoMax.doubleValue() && !listaDescartados.isEmpty()) {
					//sobra espacio, puede haber una optimizacion del espacio en la mochila
					System.out.println("La mochila no se lleno, puede haber una optimizacion." );

					// creo nueva lista de objetos para optimazacion con los objetos de la mochila menos el ultimo
					listaMochilaOptimizada = new ArrayList<ObjetoMochila>();
					listaMochilaOptimizada.addAll(listaMochila);
					listaMochilaOptimizada.remove(listaMochilaOptimizada.size()-1);

					//Calculos el peso y valor de la mochila provisional
					Double pesoMochilaOpt = UtilsMochila.pesoMochila(listaMochilaOptimizada);
					Double valorMochilaOpt = UtilsMochila.valorMochila(listaMochilaOptimizada);
					
					Double pesoMochilaProv = 0d;
					Double valorMochilaProv = 0d;
					
					// recorro los objetos descartados por si cambiando alguno por el último mejor el valor
					for (ObjetoMochila objetoDescartado : listaDescartados) {
						pesoMochilaProv = pesoMochilaOpt + objetoDescartado.getPeso();
						valorMochilaProv = valorMochilaOpt + objetoDescartado.getValor();
						
						// Si el peso con el nuevo objeto esta dentro y el importe es mayor, añado el objeto
						if (pesoMochilaProv <= pesoMax.doubleValue()
								&& valorMochilaProv > valorMochila ) {
							listaMochilaOptimizada.add(objetoDescartado);
							
							pesoMochilaOpt = pesoMochilaOpt + objetoDescartado.getPeso();
							valorMochilaOpt = valorMochilaOpt + objetoDescartado.getValor();

							mensaje = new StringBuilder();
							mensaje.append("Se añade el objeto ").append(objetoDescartado);
							mensaje.append(". El valor actual es: ").append(valorMochilaOpt);
							mensaje.append(" y el peso actual es: ").append(pesoMochilaOpt);
							mensaje.append(". Peso libre en la mochila: ").append(pesoMax - pesoMochilaOpt);
							
							System.out.println(mensaje.toString() );
						} else {
							mensaje = new StringBuilder();
							mensaje.append("Se descarta el objeto ").append(objetoDescartado);
							
							System.out.println(mensaje.toString() );
						}
					}
					
					// si se ha optimizado cambio la lista por la optimizada
					if (valorMochilaOpt > valorMochila) {
						mensaje = new StringBuilder();
						mensaje.append("Se ha encontrado una optimizacion. ");
						mensaje.append("El valor anterior era: ").append(valorMochila);
						mensaje.append(" . Y el nuevo valor es: ").append(valorMochilaProv);
						System.out.println(mensaje.toString() );

						listaMochila.clear();
						listaMochila.addAll(listaMochilaOptimizada);
					}
				}
				
				//Quinto Paso: devolver lista de objetos seleccionados
				for (ObjetoMochila objetoDentroMochila : listaMochila) {
					resultado.add(objetoDentroMochila.getNumero());
				}
				
			}
			
			
		} catch (Exception e) {
			System.out.println("ERROR: Se ha producido una excelcion" + e.getMessage());
		}
		
		
		
		return resultado;
		
	}
	
}

