import java.util.Random;
import java.util.Scanner;

public class App {

    private static final String[] WORDLIST = { "manzana", "banana", "naranja", "pera", "uva", "mango", "piña" };
    private static String secretWord;
    private static char[] guessedLetters;
    private static int guessesRemaining = 8;
    private static boolean gameOver = false;

    public static void main(String[] args) throws Exception {
        // Inicializar juego
        secretWord = gettingSecretWord(WORDLIST);
        sizeGuessedLetters(secretWord.length());
        hideLetters();

        // Lógica del juego
        logic();

        // Mensaje final
        gameOver();
    }

    private static String gettingSecretWord(String[] wordList) {
        Random random = new Random();
        return wordList[random.nextInt(wordList.length)];
    }

    private static void sizeGuessedLetters(int newSize) {
        guessedLetters = new char[newSize];
    }

    private static void hideLetters() {
        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
    }

    private static void logic() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver && guessesRemaining > 0) {
            updateGame();
            char letter = requestLetter(scanner);
            isLetterExists(letter);
            win();
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
            guessesRemaining--;
            System.out.println("¡Incorrecto! " + letter + " no existe. Quedan " + guessesRemaining + " intentos.");
        }
    }

    private static void win() {
        if (String.valueOf(guessedLetters).equals(secretWord)) {
            System.out.println("\n¡Felicidades! Has adivinado la palabra secreta: " + secretWord);
            gameOver = true;
        }
    }

    private static void gameOver() {
        if (!gameOver) {
            System.out.println("\n¡Qué pena, te has quedado sin intentos! GAME OVER");
            System.out.println("La palabra secreta era: " + secretWord);
        }
    }

}
