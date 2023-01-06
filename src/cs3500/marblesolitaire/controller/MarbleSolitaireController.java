package cs3500.marblesolitaire.controller;

/**
 * THe interface works to communicate between the model and the view.
 */
public interface MarbleSolitaireController {

  /**
   * Plays the Marble Solitaire game.
   * @throws IllegalStateException any exceptions caught while running the game
   */
  void playGame() throws IllegalStateException;
}
