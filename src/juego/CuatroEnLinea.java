package juego;

/**
 * Juego Cuatro en L�nea
 * 
 * Reglas:
 * 
 * ...
 *
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
	String turno;

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
	 * post: indica si el juego termin� porque uno de los jugadores gan� o no
	 * existen casilleros vac�os.
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
	 * post: indica si el juego termin� y tiene un ganador.
	 */
	public boolean hayGanador() {

		boolean hayGanador = false;

		/* Horizontal */
		for (int f = 0; f < contarFilas() && !hayGanador; f++) {

			for (int i = 0; i < contarColumnas() - 4 && !hayGanador; i++) {

				hayGanador = (juego[f][i] == juego[f][i + 1])
						&& (juego[f][i] == juego[f][i + 2])
						&& (juego[f][i] == juego[f][i + 3])
						&& (juego[f][i] != Casillero.VACIO);
			}
		}

		return hayGanador;
	}

	/**
	 * pre : el juego termin�. post: devuelve el nombre del jugador que gan� el
	 * juego.
	 */
	public String obtenerGanador() {

		return "Hola";
	}
}
