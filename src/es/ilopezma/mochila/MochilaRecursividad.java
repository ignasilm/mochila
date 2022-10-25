/**
 * 
 */
package es.ilopezma.mochila;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Solución al Reto de Programacion de "El problema de la Mochila" utilizando recursividad
 * 
 * @author ilopezma
 *
 */
public class MochilaRecursividad {

	public static final String VALOR = "Valor";
	public static final String PESO = "Peso";

	/**
	 * 
	 */
	public MochilaRecursividad() {
		super();
	}

	
	/** Calcula la lista de objetos optima para llevar los objetos que sumen mayor valor,
	 *  para el peso que soporta la mochila.
	 *  
	 * @param pesoMax
	 * @param listaEntrada
	 * @return
	 */
	List<Long> calcularLlenadoMochilaRecursivo(Double pesoMax, List<Map<String, Double>> listaEntrada) {

		
		List<Long> resultado = new ArrayList<Long>(); 
		List<ObjetoMochila> listaObjetos = new ArrayList<ObjetoMochila>();
		List<ObjetoMochila> listaRecorrer = null;
		Set<ObjetoMochila> listaMochila = null;
		Set<ObjetoMochila> listaMochilaOptimizada = null;
		ObjetoMochila objetoMochila = null;
		long numero = 0;
		Double peso = null;
		Double valor = null;
		StringBuilder mensaje = null;

		
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
				listaMochila = new LinkedHashSet<ObjetoMochila>();
				listaMochilaOptimizada = new LinkedHashSet<ObjetoMochila>();
				llenarMochilaRecursivo(pesoMax, listaRecorrer, listaMochila, listaMochilaOptimizada);
				
				mensaje = new StringBuilder();
				mensaje.append("\n\nSe ha encontrado una mochila optimizada.  ");
				mensaje.append(" \nEl valor de la optimizada es: ").append(UtilsMochila.valorMochila(listaMochilaOptimizada));
				mensaje.append(" \nEl contenido optimizada es: ").append(listaMochilaOptimizada.toString());
				
				System.out.println(mensaje.toString() );

				
				//Quinto Paso: devolver lista de objetos seleccionados
				for (ObjetoMochila objetoDentroMochila : listaMochilaOptimizada) {
					resultado.add(objetoDentroMochila.getNumero());
				}
				
			}
			
			
		} catch (Exception e) {
			System.out.println("ERROR: Se ha producido una excelcion" + e.getMessage());
		}
		
		
		return resultado;
		
	}

	private Set<ObjetoMochila> llenarMochilaRecursivo(Double pesoMax, List<ObjetoMochila> listaRecorrer, Set<ObjetoMochila> listaMochila, Set<ObjetoMochila> listaMochilaOptimizada) {

		List<ObjetoMochila> nuevaListaRecorrer = null;
		Set<ObjetoMochila> nuevalistaMochila = null;
		Set<ObjetoMochila> resultadoMochila = null;
		Double pesoMochila = null;
		Double valorMochila = null;
		Double valorMochilaOptima = null;
		StringBuilder mensaje = null;

		for (ObjetoMochila objetoMochila : listaRecorrer) {
			
			pesoMochila = UtilsMochila.pesoMochila(listaMochila);
			System.out.println(UtilsMochila.pintaMochila(listaMochila));

			if (pesoMochila.doubleValue() + objetoMochila.getPeso().doubleValue() <= pesoMax.doubleValue() && !listaMochila.contains(objetoMochila)) {
				
				nuevalistaMochila = new LinkedHashSet<ObjetoMochila>();
				nuevalistaMochila.addAll(listaMochila);

				//nuevalistaMochila = UtilsMochila.copiarMochila(listaMochila);
				nuevalistaMochila.add(objetoMochila);
				
				nuevaListaRecorrer = new ArrayList<ObjetoMochila>();
				nuevaListaRecorrer.addAll(listaRecorrer);
				nuevaListaRecorrer.remove(objetoMochila);

				resultadoMochila = llenarMochilaRecursivo(pesoMax, nuevaListaRecorrer, nuevalistaMochila, listaMochilaOptimizada);
				
				valorMochila = UtilsMochila.valorMochila(resultadoMochila);
				valorMochilaOptima = UtilsMochila.valorMochila(listaMochilaOptimizada);
				if (valorMochila > valorMochilaOptima) {

					mensaje = new StringBuilder();
					mensaje.append("Se ha encontrado una mochila optimizada. El valor actiual es: ").append(valorMochila);
					mensaje.append(" \nEl valor de la optimizada es: ").append(valorMochilaOptima);
					mensaje.append(" \nEl contenido actual es: ").append(listaMochila.toString());
					mensaje.append(" \nEl contenido optimizada es: ").append(resultadoMochila.toString());
					
					System.out.println(mensaje.toString() );

					listaMochilaOptimizada.clear();
					listaMochilaOptimizada.addAll(resultadoMochila);
				}

	
			} else {
				resultadoMochila = listaMochila;


			}
			
		}
		return resultadoMochila;
		
	}

}

