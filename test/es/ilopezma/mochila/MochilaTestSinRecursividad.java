package es.ilopezma.mochila;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MochilaTestSinRecursividad {

	@Test
	@Order(1)
	@DisplayName("Sin Recursividad: Test basico")
	void test() {
		MochilaSinRecursividad mochila = new MochilaSinRecursividad();
		assertNotNull(mochila);
	}

	
	@Test
	@Order(2)
	@DisplayName("Sin Recursividad: Test cuando se pasa un peso de mochila nulo")
	void testSinPeso() {
		MochilaSinRecursividad mochila = new MochilaSinRecursividad();
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/Ejemplo1.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochila(null,listaEntrada);
		assertAll(
				() -> assertNotNull(resultado),
				() -> assertTrue(resultado.isEmpty())
		);
	}

	@Test
	@Order(3)
	@DisplayName("Sin Recursividad: Test cuando se pasa una lista vacia")
	void testSinListaEntrada() {
		MochilaSinRecursividad mochila = new MochilaSinRecursividad();
		List<Long> resultado = mochila.calcularLlenadoMochila(12d,null);
		assertAll(
				() -> assertNotNull(resultado),
				() -> assertTrue(resultado.isEmpty())
		);
	}

	
	@Test
	@Order(4)
	@DisplayName("Sin Recursividad: Ejemplo 1 del enunciado")
	void testEjemplo1() {
		MochilaSinRecursividad mochila = new MochilaSinRecursividad();
		Double pesoMochila = 12d;
		final List<Long> resultadoEsperado = Arrays.asList(1l,2l,4l,5l);
	
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/Ejemplo1.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochila(pesoMochila,listaEntrada);
		//ordeno la lista resultado para comparar la respuesta
		resultado.sort(null);
		assertAll(
				() -> assertEquals(4, resultado.size()),
				() -> assertIterableEquals(resultadoEsperado,resultado)
		);
	}


	@Test
	@Order(5)
	@DisplayName("Sin Recursividad: Ejemplo 2 del enunciado")
	void testEjemplo2() {
		MochilaSinRecursividad mochila = new MochilaSinRecursividad();
		Double pesoMochila = 6d;

		final List<Long> resultadoEsperado = Arrays.asList(2l,4l,5l);
		
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/Ejemplo1.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochila(pesoMochila,listaEntrada);
		//ordeno la lista resultado para comparar la respuesta
		resultado.sort(null);
		assertAll(
				() -> assertEquals(3, resultado.size()),
				() -> assertIterableEquals(resultadoEsperado,resultado)
		);
	}

	@Test
	@Order(6)
	@DisplayName("Sin Recursividad: Caso de prueba para optimizacion, sin optimizar")
	void testCasoOptimizacionSinOptimizar() {
		MochilaSinRecursividad mochila = new MochilaSinRecursividad();
		Double pesoMochila = 6d;
		Double valorMochilaEsperado = 15d;

		final List<Long> resultadoEsperado = Arrays.asList(1l,2l,4l);
		
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/CasoOptimizacion.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochila(pesoMochila,listaEntrada);
		//ordeno la lista resultado para comparar la respuesta
		resultado.sort(null);
		
		Double valorMochila = UtilsMochila.valorMochila(listaEntrada, resultado);
		
		assertAll(
				() -> assertEquals(3, resultado.size()),
				() -> assertEquals(valorMochilaEsperado, valorMochila),
				() -> assertIterableEquals(resultadoEsperado,resultado)
		);
	}


	
	@Test
	@Order(7)
	@DisplayName("Sin Recursividad: Caso de prueba para optimizacion, con optimizar")
	void testCasoOptimizacionConOptimizar() {
		MochilaSinRecursividad mochila = new MochilaSinRecursividad();
		Double pesoMochila = 7d;
		Double valorMochilaEsperado = 16d;

		final List<Long> resultadoEsperado = Arrays.asList(2l,3l,4l);
		
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/CasoOptimizacion.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochila(pesoMochila,listaEntrada);
		//ordeno la lista resultado para comparar la respuesta
		resultado.sort(null);

		Double valorMochila = UtilsMochila.valorMochila(listaEntrada, resultado);

		assertAll(
				() -> assertEquals(3, resultado.size()),
				() -> assertEquals(valorMochilaEsperado, valorMochila),
				() -> assertIterableEquals(resultadoEsperado,resultado)
		);
	}


	@Test
	@Order(8)
	@DisplayName("Sin Recursividad: Caso 100 valores alatorios")
	void test100Aleatorios() {
		MochilaSinRecursividad mochila = new MochilaSinRecursividad();
		Double pesoMochila = 25d;

	
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/100Aleatorios.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochila(pesoMochila,listaEntrada);
		//ordeno la lista resultado para comparar la respuesta
		resultado.sort(null);

		assertAll(
				() -> assertNotNull(resultado),
				() -> assertFalse(resultado.isEmpty())
		);
	}

	
	@Test
	@Order(9)
	@DisplayName("Sin Recursividad: Caso 1000 valores alatorios")
	void test1000Aleatorios() {
		MochilaSinRecursividad mochila = new MochilaSinRecursividad();
		Double pesoMochila = 25d;

	
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/1000Aleatorios.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochila(pesoMochila,listaEntrada);
		//ordeno la lista resultado para comparar la respuesta
		resultado.sort(null);

		assertAll(
				() -> assertNotNull(resultado),
				() -> assertFalse(resultado.isEmpty())
		);
	}

}
