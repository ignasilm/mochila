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
class MochilaTestRecursividad {

	@Test
	@Order(1)
	@DisplayName("Recursivo: Test basico")
	void test() {
		MochilaRecursividad mochila = new MochilaRecursividad();
		assertNotNull(mochila);
	}

	
	@Test
	@Order(2)
	@DisplayName("Recursivo: Test cuando se pasa un peso de mochila nulo")
	void testSinPeso() {
		MochilaRecursividad mochila = new MochilaRecursividad();
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/Ejemplo1.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochilaRecursivo(null,listaEntrada);
		assertAll(
				() -> assertNotNull(resultado),
				() -> assertTrue(resultado.isEmpty())
		);
	}

	@Test
	@Order(3)
	@DisplayName("Recursivo: Test cuando se pasa una lista vacia")
	void testSinListaEntrada() {
		MochilaRecursividad mochila = new MochilaRecursividad();
		List<Long> resultado = mochila.calcularLlenadoMochilaRecursivo(12d,null);
		assertAll(
				() -> assertNotNull(resultado),
				() -> assertTrue(resultado.isEmpty())
		);
	}

	

	@Test
	@Order(4)
	@DisplayName("Recursivo: Ejemplo 1 del enunciado en recursivo")
	void testEjemplo1Recursivo() {
		MochilaRecursividad mochila = new MochilaRecursividad();
		Double pesoMochila = 12d;
		final List<Long> resultadoEsperado = Arrays.asList(1l,2l,4l,5l);
	
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/Ejemplo1.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochilaRecursivo(pesoMochila,listaEntrada);
		//ordeno la lista resultado para comparar la respuesta
		resultado.sort(null);
		assertAll(
				() -> assertEquals(4, resultado.size()),
				() -> assertIterableEquals(resultadoEsperado,resultado)
		);
	}

	@Test
	@Order(5)
	@DisplayName("Recursivo: Ejemplo 2 del enunciado")
	void testEjemplo2Recursivo() {
		MochilaRecursividad mochila = new MochilaRecursividad();
		Double pesoMochila = 6d;

		final List<Long> resultadoEsperado = Arrays.asList(2l,4l,5l);
		
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/Ejemplo1.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochilaRecursivo(pesoMochila,listaEntrada);
		//ordeno la lista resultado para comparar la respuesta
		resultado.sort(null);
		assertAll(
				() -> assertEquals(3, resultado.size()),
				() -> assertIterableEquals(resultadoEsperado,resultado)
		);
	}


	@Test
	@Order(6)
	@DisplayName("Recursivo: Caso de prueba para optimizacion, sin optimizar")
	void testCasoOptimizacionSinOptimizarRecursivo() {
		MochilaRecursividad mochila = new MochilaRecursividad();
		Double pesoMochila = 6d;
		Double valorMochilaEsperado = 15d;

		final List<Long> resultadoEsperado = Arrays.asList(1l,2l,4l);
		
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/CasoOptimizacion.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochilaRecursivo(pesoMochila,listaEntrada);
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
	@DisplayName("Recursivo: Caso de prueba para optimizacion, con optimizar")
	void testCasoOptimizacionConOptimizarRecursivo() {
		MochilaRecursividad mochila = new MochilaRecursividad();
		Double pesoMochila = 7d;
		Double valorMochilaEsperado = 16d;

		final List<Long> resultadoEsperado = Arrays.asList(2l,3l,4l);
		
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/CasoOptimizacion.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochilaRecursivo(pesoMochila,listaEntrada);
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
	@DisplayName("Recursivo: Caso 100 valores alatorios")
	void test100Aleatorios() {
		MochilaRecursividad mochila = new MochilaRecursividad();
		Double pesoMochila = 25d;

	
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/100Aleatorios.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochilaRecursivo(pesoMochila,listaEntrada);
		//ordeno la lista resultado para comparar la respuesta
		resultado.sort(null);


		assertAll(
				() -> assertNotNull(resultado),
				() -> assertFalse(resultado.isEmpty())
		);
	}
	
	//@Test
	//@Order(9)
	//@DisplayName("Caso 1000 valores alatorios")
	void test1000Aleatorios() {
		MochilaRecursividad mochila = new MochilaRecursividad();
		Double pesoMochila = 25d;

	
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/1000Aleatorios.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochilaRecursivo(pesoMochila,listaEntrada);
		//ordeno la lista resultado para comparar la respuesta
		resultado.sort(null);


		assertAll(
				() -> assertNotNull(resultado),
				() -> assertFalse(resultado.isEmpty())
		);
	}

}
