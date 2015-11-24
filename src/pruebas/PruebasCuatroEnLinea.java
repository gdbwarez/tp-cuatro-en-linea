package pruebas;

import org.junit.Assert;
import org.junit.Test;

import juego.Casillero;
import juego.CuatroEnLinea;

;

public class PruebasCuatroEnLinea {

	@Test
	public void IniciarPartidaValida() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Comprobaci�n */
		Assert.assertEquals(4, partida.contarFilas());
		Assert.assertEquals(4, partida.contarColumnas());

	}

	@Test(expected = Error.class)
	public void IniciarPartidaInvalida() {

		/* Condici�n Inicial */
		new CuatroEnLinea(2, 4, "Red", "Yellow");

	}
	
	@Test
	public void comprobarCantidadDeFilas() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		int filas = partida.contarFilas();
		
		/* Comprobaci�n */
		Assert.assertEquals(4, filas);

	}
	
	@Test
	public void comprobarCantidadDeColumnas() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(6, 7, "Red", "Yellow");

		/* Operaciones */
		int columnas = partida.contarColumnas();
		
		/* Comprobaci�n */
		Assert.assertEquals(7, columnas);

	}

	
/*
	| R | _ | _ | _ |
	| R | A | _ | _ |
	| R | A | _ | _ |
	| R | A | _ | _ |
*/
	@Test
	public void comprobarSiHayGanadorVerticalmente() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(1);
		partida.soltarFicha(2);

		boolean hayGanador = partida.hayGanador();

		/* Comprobaci�n */
		Assert.assertTrue(hayGanador);
	}

/*
	| _ | _ | _ | _ |
	| _ | _ | _ | _ |
	| A | A | A | _ |
	| R | R | R | R |
*/
	@Test
	public void comprobarSiHayGanadorHorizontalmente() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(2);
		partida.soltarFicha(3);
		partida.soltarFicha(3);
		partida.soltarFicha(4);

		boolean hayGanador = partida.hayGanador();

		/* Comprobaci�n */
		Assert.assertTrue(hayGanador);

	}

/*
	| _ | _ | _ | R |
	| _ | _ | R | A |
	| A | R | R | R |
	| R | A | A | A |
*/
	@Test
	public void comprobarSiHayGanadorEnDiagonalCreciente() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(2);
		partida.soltarFicha(3);
		partida.soltarFicha(3);
		partida.soltarFicha(1);
		partida.soltarFicha(3);
		partida.soltarFicha(4);
		partida.soltarFicha(4);
		partida.soltarFicha(4);
		partida.soltarFicha(4);
		

		boolean hayGanador = partida.hayGanador();

		/* Comprobaci�n */
		Assert.assertTrue(hayGanador);

	}

/*
	| A | R | _ | _ |
	| R | A | R | _ |
	| A | A | A | _ |
	| R | R | R | A |
*/
	@Test
	public void comprobarSiHayGanadorEnDiagonalDecreciente() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(2);
		partida.soltarFicha(3);
		partida.soltarFicha(2);
		partida.soltarFicha(2);
		partida.soltarFicha(3);
		partida.soltarFicha(3);
		partida.soltarFicha(4);

		boolean hayGanador = partida.hayGanador();

		/* Comprobaci�n */
		Assert.assertTrue(hayGanador);

	}
	
	@Test
	public void comprobarSiTerminoElJuegoPorGanador() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(1);
		partida.soltarFicha(2);

		boolean termino = partida.termino();

		/* Comprobaci�n */
		Assert.assertTrue(termino);
	}


	@Test
	public void comprobarSiTerminoElJuegoPorqueNoHayLugar() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(2);
		partida.soltarFicha(2);
		partida.soltarFicha(2);
		partida.soltarFicha(4);
		partida.soltarFicha(3);
		partida.soltarFicha(3);
		partida.soltarFicha(3);
		partida.soltarFicha(3);
		partida.soltarFicha(4);
		partida.soltarFicha(4);
		partida.soltarFicha(4);

		boolean termino = partida.termino();

		/* Comprobaci�n */
		Assert.assertTrue(termino);

	}
	
	@Test
	public void comprobarQueSoltarFichaNoSobreescribeYNoCambiaElTurno() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		
		/*Turno Rojo (intenta soltar en columna llena)*/
		partida.soltarFicha(1);
		
		Casillero CasilleroSuperiorIzquierdo = partida.obtenerCasillero(1, 1);
		
		/*Turno Rojo (suelta en la siguiente columna)*/
		partida.soltarFicha(2);
		Casillero CasilleroSegundaColumna = partida.obtenerCasillero(4, 2);


		/* Comprobaci�n */
		Assert.assertEquals(Casillero.AMARILLO, CasilleroSuperiorIzquierdo);
		Assert.assertEquals(Casillero.ROJO, CasilleroSegundaColumna);

	}
	
	@Test
	public void comprobarQuienEsElGanador() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(1);
		partida.soltarFicha(3);
		partida.soltarFicha(3);
		partida.soltarFicha(4);

		String ganador = partida.obtenerGanador();

		/* Comprobaci�n */
		Assert.assertEquals("Red", ganador);

	}
	
	@Test(expected = Error.class)
	public void soltarFichaEnColumnaNegativa() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(-1);
	}
	
	
	@Test
	public void comprobarQueNoTerminoElJuego() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(1);
	
		boolean termino = partida.termino();

		/* Comprobaci�n */
		Assert.assertFalse(termino);

	}
	
	@Test
	public void comprobarQueTerminaEnEmpate() {

		/* Condici�n Inicial */
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/* Operaciones */
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(1);
		partida.soltarFicha(2);
		partida.soltarFicha(3);
		partida.soltarFicha(4);
		partida.soltarFicha(4);
		partida.soltarFicha(4);
		partida.soltarFicha(3);
		partida.soltarFicha(2);
		partida.soltarFicha(3);
		partida.soltarFicha(2);
		partida.soltarFicha(2);
		partida.soltarFicha(3);
		partida.soltarFicha(4);
	
		boolean termino = partida.termino();
		boolean hayGanador = partida.hayGanador();

		/* Comprobaci�n */
		Assert.assertTrue(termino);
		Assert.assertFalse(hayGanador);

	}
}
