import java.util.Random;
import java.util.Scanner;

public class App {
  
    // Constantes y variables globales
    private static final int MAX_TURNS = 10;
    private static final String[] WORDS = { "manzana", "banana", "naranja", "pera", "uva", "mango", "piña" };
    private static String secretWord;
    private static char[] word;
    private static int turns = 0;
    private static boolean gameOver = false;

  public static void main(String[] args) throws Exception {
// Inicializar juego
iniciarJuego();

// Lógica del juego
jugar();

// Mensaje final
mostrarResultado();
  }

  /**
     * Método para iniciar el juego
     */
    private static void iniciarJuego() {
        Random random = new Random();
        secretWord = WORDS[random.nextInt(WORDS.length)];
        word = new char[secretWord.length()];
        inicializarGuiones();
    }

    /**
     * Inicializa el arreglo de palabra con guiones bajos
     */
    private static void inicializarGuiones() {
        for (int i = 0; i < word.length; i++) {
            word[i] = '_';
        }
    }

    /**
     * Método principal del juego
     */
    private static void jugar() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver && turns < MAX_TURNS) {
            mostrarEstado();
            char letter = pedirLetra(scanner);
            verificarLetra(letter);
            verificarVictoria();
        }

        scanner.close();
    }

    /**
     * Muestra el estado actual de la palabra y los intentos restantes
     */
    private static void mostrarEstado() {
        System.out.println("\nAdivina la palabra: " + String.valueOf(word) + " ("
                + secretWord.length() + " letras)");
    }

    /**
     * Pide una letra al usuario
     */
    private static char pedirLetra(Scanner scanner) {
        System.out.print("Introduce una letra, por favor: ");
        return Character.toLowerCase(scanner.next().charAt(0));
    }

    /**
     * Verifica si la letra ingresada existe en la palabra secreta
     */
    private static void verificarLetra(char letter) {
        boolean existLetter = false;

        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                word[i] = letter;
                existLetter = true;
            }
        }

        if (!existLetter) {
            turns++;
            System.out.println("¡Incorrecto! " + letter + " no existe. Quedan " + (MAX_TURNS - turns) + " intentos.");
        }
    }

    /**
     * Verifica si el jugador ha ganado
     */
    private static void verificarVictoria() {
        if (String.valueOf(word).equals(secretWord)) {
            System.out.println("\n¡Felicidades! Has adivinado la palabra secreta: " + secretWord);
            gameOver = true;
        }
    }

    /**
     * Muestra el resultado final del juego
     */
    private static void mostrarResultado() {
        if (!gameOver) {
            System.out.println("\n¡Qué pena, te has quedado sin intentos! GAME OVER");
            System.out.println("La palabra secreta era: " + secretWord);
        }
    }

}
