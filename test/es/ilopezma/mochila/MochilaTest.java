package es.ilopezma.mochila;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MochilaTest {

	@Test
	void test() {
		Mochila mochila = new Mochila();
		assertNotNull(mochila);
	}

	
	@Test
	@DisplayName("Test cuando se pasa un peso de mochila nulo")
	void testSinPeso() {
		Mochila mochila = new Mochila();
		List<Map<String, Double>> listaEntrada = null;
		
		listaEntrada = UtilsMochila.crearListaEntrada("datos/Ejemplo1.csv");
		
		List<Long> resultado = mochila.calcularLlenadoMochila(null,listaEntrada);
		assertAll(
				() -> assertNotNull(resultado),
				() -> assertTrue(resultado.isEmpty())
		);
	}

	@Test
	@DisplayName("Test cuando se pasa una lista vacia")
	void testSinListaEntrada() {
		Mochila mochila = new Mochila();
		List<Long> resultado = mochila.calcularLlenadoMochila(12d,null);
		assertAll(
				() -> assertNotNull(resultado),
				() -> assertTrue(resultado.isEmpty())
		);
	}

	
	@Test
	@DisplayName("Ejemplo 1 del enunciado")
	void testEjemplo1() {
		Mochila mochila = new Mochila();
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
	@DisplayName("Ejemplo 2 del enunciado")
	void testEjemplo2() {
		Mochila mochila = new Mochila();
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
	@DisplayName("Caso de prueba para optimizacion, sin optimizar")
	void testCasoOptimizacionSinOptimizar() {
		Mochila mochila = new Mochila();
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
	@DisplayName("Caso de prueba para optimizacion, con optimizar")
	void testCasoOptimizacionConOptimizar() {
		Mochila mochila = new Mochila();
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

}
