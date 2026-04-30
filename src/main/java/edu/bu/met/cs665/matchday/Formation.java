/**
 * Name: Issam Alaga
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/30/2026
 * File Name: Formation.java
 * Description: Enum representing available soccer formations.
 *              Each formation defines position slot limits used
 *              by lineup strategies during player selection.
 */

package edu.bu.met.cs665.matchday;

/**
 * Enum of supported formations with their position slot limits.
 */
public enum Formation {

  F_4_3_3("4-3-3", 1, 4, 3, 3),
  F_4_4_2("4-4-2", 1, 4, 4, 2),
  F_3_5_2("3-5-2", 1, 3, 5, 2);

  private final String formationName;
  private final int goalkeeperSlots;
  private final int defenderSlots;
  private final int midfielderSlots;
  private final int forwardSlots;

  /**
   * Constructs a Formation enum with position slot limits.
   *
   * @param formationName    the display name (e.g. "4-3-3")
   * @param goalkeeperSlots  number of goalkeeper slots
   * @param defenderSlots    number of defender slots
   * @param midfielderSlots  number of midfielder slots
   * @param forwardSlots     number of forward slots
   */
  Formation(String formationName, int goalkeeperSlots, int defenderSlots,
            int midfielderSlots, int forwardSlots) {
    this.formationName = formationName;
    this.goalkeeperSlots = goalkeeperSlots;
    this.defenderSlots = defenderSlots;
    this.midfielderSlots = midfielderSlots;
    this.forwardSlots = forwardSlots;
  }

  /**
   * Returns the display name of the formation.
   *
   * @return formation name string
   */
  public String getFormationName() {
    return formationName;
  }

  /**
   * Returns the number of goalkeeper slots.
   *
   * @return goalkeeper slot count
   */
  public int getGoalkeeperSlots() {
    return goalkeeperSlots;
  }

  /**
   * Returns the number of defender slots.
   *
   * @return defender slot count
   */
  public int getDefenderSlots() {
    return defenderSlots;
  }

  /**
   * Returns the number of midfielder slots.
   *
   * @return midfielder slot count
   */
  public int getMidfielderSlots() {
    return midfielderSlots;
  }

  /**
   * Returns the number of forward slots.
   *
   * @return forward slot count
   */
  public int getForwardSlots() {
    return forwardSlots;
  }
}