package juego;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Representaci�n gr�fica del Tablero del Juego Cuatro en L�nea.
 * 
 */
public class Tablero {

	private static final int ALTO_FILA = 80;
	private static final int ANCHO_COLUMNA = 80;
	private static final int ALTURA_BOTON = 40;
	private static final double RADIO = Math.min(ALTO_FILA - 1,
			ANCHO_COLUMNA - 1) / 2;

	private CuatroEnLinea juego;
	private GridPane grilla;
	private Stage escenario;
	private Group root;
	private Text turno;

	/**
	 * post: asocia el Tablero a 'nuevoJuego' y lo inicializa a partir de su
	 * estado.
	 * 
	 * @param nuevoJuego
	 */
	public Tablero(CuatroEnLinea nuevoJuego) {

		juego = nuevoJuego;
		escenario = new Stage();
		root = new Group();
		grilla = new GridPane();

		turno = new Text("Turno: " + juego.obtenerTurno());
		turno.setX(ANCHO_COLUMNA * juego.contarColumnas() / 2 - 25);
		turno.setY(((juego.contarFilas() * ALTO_FILA) + ALTURA_BOTON) + (40));

		turno.setFont(Font.font("System", FontWeight.BOLD, 16.0));

		root.getChildren().add(grilla);
		root.getChildren().add(turno);

	}

	/**
	 * post: muestra el Tablero en pantalla.
	 */
	public void mostrar() {

		dibujarBotones();

		double ancho = juego.contarColumnas() * ANCHO_COLUMNA;
		double alto = (juego.contarFilas() * ALTO_FILA) + ALTURA_BOTON;

		/* Color Fondo */
		grilla.setStyle("-fx-background-color: #018DEF;");

		Color celesteConnect4 = Color.web("#018DEF");
		Scene escena = new Scene(root, ancho, alto + 60, celesteConnect4);

		escenario.setScene(escena);
		escenario.setResizable(false);
		escenario.setTitle(Aplicacion.TITULO);

		dibujar();

		escenario.show();
	}

	/**
	 * post: agrega los botones para soltar una ficha en cada columna del
	 * Tablero.
	 */
	private void dibujarBotones() {

		Image imageDecline = new Image("file:res/down.png");

		for (int columna = 1; columna <= juego.contarColumnas(); columna++) {

			Button botonSoltarFicha = new Button();

			/* Flecha abajo */
			botonSoltarFicha.setGraphic(new ImageView(imageDecline));

			// botonSoltarFicha.setStyle("-fx-focus-color: transparent; -fx-background-insets: 0, 0, 1, 2;");

			botonSoltarFicha.setMinHeight(ALTURA_BOTON);

			botonSoltarFicha.setOnAction(new SoltarFicha(this, juego, columna));
			botonSoltarFicha.setMinWidth(ANCHO_COLUMNA);
			grilla.add(botonSoltarFicha, columna - 1, 0);
		}
	}

	/**
	 * post: actualiza el Tablero a partir del estado del juego asociado.
	 */
	public void dibujar() {

		for (int fila = 1; fila <= juego.contarFilas(); fila++) {

			for (int columna = 1; columna <= juego.contarColumnas(); columna++) {

				Casillero casillero = juego.obtenerCasillero(fila, columna);

				Circle dibujoCasillero = dibujarCasillero(casillero);

				grilla.add(dibujoCasillero, columna - 1, fila);
			}
		}

	}

	/**
	 * post: dibuja y devuelve el casillero dado.
	 * 
	 * @param casillero
	 * @return representaci�n gr�fica del Casillero.
	 */
	private Circle dibujarCasillero(Casillero casillero) {

		Circle dibujoCasillero = new Circle(RADIO, obtenerPintura(casillero));

		dibujoCasillero.setStroke(new Color(0, 0, 0, 1.0));
		dibujoCasillero.setScaleX(0.95);
		dibujoCasillero.setScaleY(0.95);
		dibujoCasillero.setStrokeWidth(3.0);
		
		turno.setText("Turno: " + juego.obtenerTurno());
		
		if (juego.obtenerTurno() == juego.obtenerJugadorRojo()) {
			turno.setFill(Color.RED);
		}

		if (juego.obtenerTurno() == juego.obtenerJugadorAmarillo()) {
			turno.setFill(Color.YELLOW);
		}
		
		return dibujoCasillero;
	}

	/**
	 * post: determina la pintura a utilizar para 'casillero'.
	 * 
	 * @param casillero
	 * @return pintura a utilizar para identificar el Casillero.
	 */
	private Paint obtenerPintura(Casillero casillero) {

		Paint pintura;

		switch (casillero) {

		case AMARILLO:
			pintura = Color.YELLOW;

			break;

		case ROJO:
			pintura = Color.RED;
			break;

		default:
			pintura = Color.WHITE;
		}

		return pintura;
	}

	/**
	 * pre : el juego asociado termin�. post: muestra un mensaje indicando el
	 * resultado del juego.
	 */
	public void mostrarResultado() {

		Stage dialogo = new Stage();

		BorderPane panelGanador = new BorderPane();
		dialogo.setTitle("Resultado");
		panelGanador.setPadding(new Insets(10.0));
		Text textoResultado;
		Font fuente = new Font(40.0);

		if (juego.hayGanador()) {

			textoResultado = new Text("Gan\u00f3: " + juego.obtenerGanador());

		} else {

			textoResultado = new Text("Empataron");
		}

		textoResultado.setFont(fuente);
		panelGanador.setCenter(textoResultado);

		Scene escenaGanador = new Scene(panelGanador);

		dialogo.setScene(escenaGanador);
		dialogo.initOwner(escenario);
		dialogo.initModality(Modality.WINDOW_MODAL);
		dialogo.setResizable(false);

		dialogo.showAndWait();
	}
}
