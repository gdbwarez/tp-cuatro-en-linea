package juego;

/**
 * Juego Cuatro en L�nea
 * 
 *	Reglas: 
 *
 *	El tablero tiene, un m�nimo de 4 filas y 4 columnas por las que caen las fichas,
 *	que se introducen por la parte superior. El juego tiene un total de 2 jugadores y a cada
 *	uno se le asigna un color diferente.
 *
 *	El objetivo de cada jugador para ganar es intentar unir cuatro fichas en linea recta
 *	del color que le corresponde, de forma vertical, horizontal o diagonal.
 *
 *	Los jugadores colocan una ficha por turno.
 *	La regla para colocar las fichas consiste en que estas siempre "caen hasta abajo". 
 *	Es decir una ficha puede ser colocada bien en la parte inferior de una columna o bien
 *	sobre otra de alguna otra columna.
 *
 *	El juego termina de dos formas: 
 *	1) Cuando un jugador logra formar una linea de cuatro fichas, de forma vertical, horizontal, o diagonal.
 *	2) Cuando no quedan casilleros vac�os. 
 */
public class CuatroEnLinea {

	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4. post: empieza el
	 * juego entre el jugador que tiene fichas rojas, identificado como
	 * 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
	 * 'jugadorAmarillo'. Todo el tablero est� vac�o.
	 * 
	 * @param filas
	 *            : cantidad de filas que tiene el tablero.
	 * @param columnas
	 *            : cantidad de columnas que tiene el tablero.
	 * @param jugadorRojo
	 *            : nombre del jugador con fichas rojas.
	 * @param jugadorAmarillo
	 *            : nombre del jugador con fichas amarillas.
	 */

	int filas = 4;
	int columnas = 4;
	String jugadorRojo = "";
	String jugadorAmarillo = "";
	String turno = "";

	Casillero[][] juego;

	public CuatroEnLinea(int filas, int columnas, String jugadorRojo,
			String jugadorAmarillo) {

		if (filas >= 4 && columnas >= 4) {

			this.filas = filas;
			this.columnas = columnas;
		} else {

			throw new Error("Coloca filas y columnas mayores o iguales a 4");
		}

		this.jugadorRojo = jugadorRojo;
		this.jugadorAmarillo = jugadorAmarillo;
		this.turno = jugadorRojo;
		juego = new Casillero[filas][columnas];

		for (int f = 0; f < juego.length; f++) {

			for (int c = 0; c < juego[f].length; c++) {

				juego[f][c] = Casillero.VACIO;
			}
		}

	}

	/**
	 * post: devuelve la cantidad m�xima de fichas que se pueden apilar en el
	 * tablero.
	 */
	public int contarFilas() {

		return filas;
	}

	/**
	 * post: devuelve la cantidad m�xima de fichas que se pueden alinear en el
	 * tablero.
	 */
	public int contarColumnas() {

		return columnas;
	}

	/**
	 * pre : fila est� en el intervalo [1, contarFilas()], columnas est� en el
	 * intervalo [1, contarColumnas()]. post: indica qu� ocupa el casillero en
	 * la posici�n dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {

		return juego[fila - 1][columna - 1];
	}

	/**
	 * pre : el juego no termin�, columna est� en el intervalo [1,
	 * contarColumnas()] y a�n queda un Casillero.VACIO en la columna indicada.
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFicha(int columna) {

		boolean jugo = false;
		
		if (columna < 1 || columna > contarColumnas()){
			
			throw new Error("Columna fuera de rango");
		}

		for (int i = 0; i < contarFilas() && !termino() && !jugo; i++) {

			jugo = juego[contarFilas() - 1 - i][columna - 1] == Casillero.VACIO;
			if (jugo && turno == jugadorRojo) {

				juego[contarFilas() - 1 - i][columna - 1] = Casillero.ROJO;
				turno = jugadorAmarillo;

			} else if (jugo && turno == jugadorAmarillo) {

				juego[contarFilas() - 1 - i][columna - 1] = Casillero.AMARILLO;
				turno = jugadorRojo;
			}
		}

	}

	/**
	 * post: indica si el juego terminó porque uno de los jugadores ganó o no
	 * existen casilleros vacíos.
	 */
	public boolean termino() {

		boolean termino = false;
		boolean noHayLibre = true;

		for (int c = 0; c < contarColumnas() && noHayLibre; c++) {

			noHayLibre = (juego[0][c] != Casillero.VACIO);

		}

		termino = hayGanador() || noHayLibre;
		return termino;
	}

	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {

		boolean hayGanador = false;

		/* Horizontal */
		for (int f = 0; f < contarFilas() && !hayGanador; f++) {

			for (int c = 0; c < contarColumnas() - 3 && !hayGanador; c++) {

				hayGanador = (juego[f][c] == juego[f][c + 1])
						&& (juego[f][c] == juego[f][c + 2])
						&& (juego[f][c] == juego[f][c + 3])
						&& (juego[f][c] != Casillero.VACIO);
			}
		}

		/* Vertical */

		for (int c = 0; c < contarColumnas() && !hayGanador; c++) {

			for (int f = 0; f < contarFilas() - 3 && !hayGanador; f++) {

				hayGanador = (juego[f][c] == juego[f + 1][c])
						&& (juego[f][c] == juego[f + 2][c])
						&& (juego[f][c] == juego[f + 3][c])
						&& (juego[f][c] != Casillero.VACIO);
			}
		}

		/* Diagonal hacia arriba */

		for (int f = 3; f < contarFilas() && !hayGanador; f++) {

			for (int c = 0; c < contarColumnas() - 3 && !hayGanador; c++) {

				hayGanador = (juego[f][c] == juego[f - 1][c + 1])
						&& (juego[f][c] == juego[f - 2][c + 2])
						&& (juego[f][c] == juego[f - 3][c + 3])
						&& (juego[f][c] != Casillero.VACIO);

			}

		}

		/* Diagonal hacia abajo */

		for (int f = 0; f < contarFilas() - 3 && !hayGanador; f++) {

			for (int c = 0; c < contarColumnas() - 3 && !hayGanador; c++) {

				hayGanador = (juego[f][c] == juego[f + 1][c + 1])
						&& (juego[f][c] == juego[f + 2][c + 2])
						&& (juego[f][c] == juego[f + 3][c + 3])
						&& (juego[f][c] != Casillero.VACIO);

			}
		}

		return hayGanador;
	}

	/**
	 * pre : el juego termin�. post: devuelve el nombre del jugador que gan�
	 * el juego.
	 */
	public String obtenerGanador() {

		String ganador = "";

		if (turno == jugadorAmarillo) {

			ganador = jugadorRojo;
		} else {

			ganador = jugadorAmarillo;
		}
		return ganador;
	}

	/*
	 * M�todos p�blicos con el prop�sito de mejorar la interfaz gr�fica
	 */
	public String obtenerTurno() {

		return turno;
	}

	public String obtenerJugadorRojo() {

		return jugadorRojo;
	}

	public String obtenerJugadorAmarillo() {

		return jugadorAmarillo;
	}
}
