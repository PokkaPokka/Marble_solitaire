package cs3500.marblesolitaire.controller;

import java.util.Objects;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * A class created to confirm input for test purpose.
 */
public class ConfirmInputs implements MarbleSolitaireModel {
  final StringBuilder log;

  public ConfirmInputs(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append("move");
  }

  @Override
  public boolean isGameOver() {
    log.append("isGameOver");
    return false;
  }

  @Override
  public int getBoardSize() {
    log.append("getBoardSize");
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    log.append("getSlotAt");
    return null;
  }

  @Override
  public int getScore() {
    log.append("getScore");
    return 0;
  }
}
