package cs3500.marblesolitaire;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * The class that stores the main method as the entrance of the program.
 */
public class MarbleSolitaireProgram {
  /**
   * The entrance of the program.
   *
   * @param args a string type parameter
   * @throws IOException transmission failure
   */
  public static void main(String[] args) throws IOException {
    Readable rd = new InputStreamReader(System.in);
    MarbleSolitaireModel board = null;
    MarbleSolitaireView view = null;

    Scanner sc = new Scanner(System.in);
    String input = sc.next();

    switch (input) {
      case "eng":
        board = new EnglishSolitaireModel();
        view = new MarbleSolitaireTextView(board);
        break;
      case "euro":
        board = new EuropeanSolitaireModel();
        view = new MarbleSolitaireTextView(board);
        break;
      case "triangle":
        board = new TriangleSolitaireModel();
        view = new TriangleSolitaireTextView(board);
        break;
      default:
        System.out.println(String.format("Unknown command %s"));
        break;
    }

    MarbleSolitaireController con = new MarbleSolitaireControllerImpl(board, view, rd);
    con.playGame();
  }
}
