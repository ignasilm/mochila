package es.ilopezma.mochila;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilsMochila {

	private static final String SEPARADOR = ";";

	/** Prepara una lista de entrada para el calculo de la mochila a partir de un fichero CSV
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<Map<String, Double>> crearListaEntrada(String fileName) {
		
		List<Map<String, Double>> listaEntrada = new ArrayList<Map<String,Double>>();
		Map<String, Double> objeto = null;
		String linea = "";  

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));

			while ((linea = br.readLine()) != null) {  
				String[] valores = linea.split(SEPARADOR);
				objeto = new HashMap<String, Double>();
				objeto.put(Mochila.PESO, Double.valueOf(valores[0]));
				objeto.put(Mochila.VALOR, Double.valueOf(valores[1]));
				listaEntrada.add(objeto );
				
			}

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Se ha producido una excelcion al abrir el archivo " + e.getMessage());
		} catch (IOException e) {
			System.out.println("ERROR: Se ha producido una excelcion al leer el archivo " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("ERROR: Se ha producido una excelcion la convertir un valor a Double " + e.getMessage());
		}  
		
		return listaEntrada;
	}

	/** Calcula el valor de la mochila a partir de la lista de objetos original y la lista resultado de objetos seleccionados.
	 * 
	 * @param listaEntrada
	 * @param listaSeleccionados
	 * @return
	 */
	public static Double valorMochila(List<Map<String, Double>> listaEntrada, List<Long> listaSeleccionados) {

		Double total = 0d;
		long numero = 0;
		
		for (Map<String, Double> objetoMochila : listaEntrada) {
			numero++;
			if (listaSeleccionados.contains(numero)) {
				total = total + objetoMochila.get(Mochila.VALOR);
			}
		}
		
		return total;
		
	}

	/** Calcula el valor de la mochila a partir de la lista de ObjetosMochila
	 * 
	 * @param listaMochila
	 * @return
	 */
	public static Double valorMochila(List<ObjetoMochila> listaMochila) {

		Double total = 0d;
		
		for (ObjetoMochila objetoMochila : listaMochila) {
			total = total + objetoMochila.getValor();
		}
		
		return total;
		
	}

	
	
	/** Calcula el peso de la mochila a partir de la lista de ObjetosMochila
	 * 
	 * @param listaMochila
	 * @return
	 */
	public static Double pesoMochila(List<ObjetoMochila> listaMochila) {

		Double total = 0d;
		
		for (ObjetoMochila objetoMochila : listaMochila) {
			total = total + objetoMochila.getPeso();
		}
		
		return total;
		
	}


}
