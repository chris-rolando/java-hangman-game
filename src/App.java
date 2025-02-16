// Librerías {palabra aleatoria, ingreso de letras}
import java.util.Random;
import java.util.Scanner;

public class App {

    // Constantes y variables globales
    private static final int GUESSES_REMAINING = 8;
    private static final String[] WORDLIST = { "manzana", "banana", "naranja", "pera", "uva", "mango", "piña" };
    private static String secretWord;
    private static char[] guessedLetters;
    private static int turns = 0;
    private static boolean gameOver = false;

    public static void main(String[] args) throws Exception {
        // Inicializar juego
        gameStart();

        // Lógica del juego
        gameLogic();

        // Mensaje final
        showMessage();
    }

    private static void gameStart() {
        Random random = new Random();
        // obtener palabra aleatoria en cada juego 
        secretWord = WORDLIST[random.nextInt(WORDLIST.length)];
        // mostrar el tamaño de la palabra 
        guessedLetters = new char[secretWord.length()];
        wordSize();
    }

    private static void wordSize() {
        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
    }

    private static void gameLogic() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver && turns < GUESSES_REMAINING) {
            updateGame();
            char letter = requestLetter(scanner);
            isLetterExists(letter);
            checkWin();
        }

        scanner.close();
    }

    private static void updateGame() {
        System.out.println("\nAdivina la palabra: " + String.valueOf(guessedLetters) + " ("
                + secretWord.length() + " letras)");
    }

    private static char requestLetter(Scanner scanner) {
        System.out.print("Introduce una letra, por favor: ");
        return Character.toLowerCase(scanner.next().charAt(0));
    }

    private static void isLetterExists(char letter) {
        boolean existLetter = false;

        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                guessedLetters[i] = letter;
                existLetter = true;
            }
        }

        if (!existLetter) {
            turns++;
            System.out.println("¡Incorrecto! " + letter + " no existe. Quedan " + (GUESSES_REMAINING - turns) + " intentos.");
        }
    }

    /**
     * Verifica si el jugador ha ganado
     */
    private static void checkWin() {
        if (String.valueOf(guessedLetters).equals(secretWord)) {
            System.out.println("\n¡Felicidades! Has adivinado la palabra secreta: " + secretWord);
            gameOver = true;
        }
    }

    /**
     * Muestra el resultado final del juego
     */
    private static void showMessage() {
        if (!gameOver) {
            System.out.println("\n¡Qué pena, te has quedado sin intentos! GAME OVER");
            System.out.println("La palabra secreta era: " + secretWord);
        }
    }

}
