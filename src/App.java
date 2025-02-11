import java.util.Random;
import java.util.Scanner;

public class App {

  public static void main(String[] args) throws Exception {
    System.out.println("Hello world: basics & java");

    // Scanner: obtener datos del usuario
    Scanner scanner = new Scanner(System.in);
    // Random: obetner indice aleatorio
    Random random = new Random();

    final int maxTurns = 10;
    final String[] words = { "manzana", "banana", "naranja", "pera", "uva", "mango", "piña" };
    String secretWord = words[random.nextInt(words.length)];
    int turns = 0;
    boolean gameOver = false;
    
    // Asigna el tamaño de la palabra secreta
    char[] word = new char[secretWord.length()];

    // Asigna espacios _ (1 vez)
    for (int i = 0; i < word.length; i++) {
      word[i] = '_';
    }

    // Lógica, se repite hasta que el juego acaba o se acaban los turnos
    while (!gameOver && turns < maxTurns) {
      boolean existLetter = false;

      System.out.println("Adivina la palabra: " + String.valueOf(word) + " ("
          + secretWord.length() + " letras)");

      System.out.println("Introduce una letra, por favor: ");

      // Letra ingresada por usuario
      char letter = Character.toLowerCase(scanner.next().charAt(0));

      // Recorre la palabra buscando la letra
      for (int i = 0; i < secretWord.length(); i++) {
        // Si la encuentra, la guarda en el arrglo
        if (secretWord.charAt(i) == letter) {
          word[i] = letter;
          existLetter = true;
        }
      }

      // Si no la encuentra, resta un turno
      if (!existLetter) {
        turns++;
        System.out.println("¡Incorrecto!" + letter + " no existe. Quedan " + (maxTurns - turns) + " intentos.");
      }

      if (String.valueOf(word).equals(secretWord)) {
        System.out.println("¡Felicidades! Has adivinado la palabra secreta: " + secretWord);
        gameOver = true;
      }

    }

    if (!gameOver) {
      System.out.println("¡Qué pena te has quedado sin intentos! GAME OVER");
    }

    // Cerrar scanner
    scanner.close();

  }
}
