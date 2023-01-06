package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Implementation of the controller interface, responsible for playing the game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable readable;

  /**
   * THe constructor of the controller class that accepts the model, view and readable.
   *
   * @param model    the Marble Solitaire model
   * @param view     The visualization of the game
   * @param readable User input
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view, Readable readable)
          throws IllegalArgumentException {

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    } else if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    } else if (readable == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }

    this.model = model;
    this.view = view;
    this.readable = readable;
  }

  //#####################################################################################//

  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(readable);
    boolean quit = false;
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;

    printBasicInfo();

    while (!quit && !model.isGameOver()) {
      int i = 0;
      int[] array;
      array = new int[4];
      boolean deter = true;


      while (i < 4 && !quit) {
        try {
          String userInput = scan.next();
          if (userInput.equals("q") || userInput.equals("Q")) {
            try {
              view.renderMessage("Game quit!\n");
            } catch (IOException e) {
              throw new RuntimeException(e);
            }

            try {
              view.renderMessage("State of game when quit:\n");
            } catch (IOException e) {
              throw new RuntimeException(e);
            }

            try {
              view.renderBoard();
            } catch (IOException e) {
              throw new RuntimeException(e);
            }

            try {
              view.renderMessage("\nScore: " + model.getScore());
            } catch (IOException e) {
              throw new RuntimeException(e);
            }


            quit = true;
            deter = false;

          } else if (isInt(userInput)) {
            int num = Integer.parseInt(userInput);
            array[i] = num;
            i++;
          } else {
            try {
              view.renderMessage("Undefined instruction " + userInput + "\n");
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          }
        } catch (NoSuchElementException e) {
          throw new IllegalStateException("Runs out of inputs");
        }
      }


      if (deter) {
        fromRow = array[0];
        fromCol = array[1];
        toRow = array[2];
        toCol = array[3];

        try {
          model.move(fromRow - 1, fromCol - 1, toRow - 1, toCol - 1);
        } catch (IllegalArgumentException e) {
          try {
            view.renderMessage("Invalid move. Play again. " + e.getMessage() + ".\n");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }

        if (model.isGameOver()) {
          try {
            view.renderMessage("Game over!\n");
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          try {
            view.renderBoard();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          try {
            view.renderMessage("\nScore: " + model.getScore() + "\n");
          } catch (IOException e) {
            throw new RuntimeException(e);
          }

        } else if (!model.isGameOver()) {
          printBasicInfo();
        }
      }
    }
  }

  private boolean isInt(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private void printBasicInfo() {
    try {
      view.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      view.renderMessage("\nScore: " + model.getScore() + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
