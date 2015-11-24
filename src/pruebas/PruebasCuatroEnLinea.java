package pruebas;
import org.junit.Assert;
import org.junit.Test;

import juego.CuatroEnLinea;;


public class PruebasCuatroEnLinea {


	@Test
	public void IniciarPartidaValida() {
		
		/*Condici�n Inicial*/
		CuatroEnLinea partida = new CuatroEnLinea(4, 4, "Red", "Yellow");

		/*Comprobaci�n*/
		Assert.assertEquals(4, partida.contarFilas());
		Assert.assertEquals(4, partida.contarColumnas());
		
	}

	@Test(expected = Error.class)
	public void IniciarPartidaInvalida() {
		
		/*Condici�n Inicial*/
		new CuatroEnLinea(2, 4, "Red", "Yellow");
		
	}
}
