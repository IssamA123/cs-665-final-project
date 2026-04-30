/**
 * Name: Issam Alaga
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/30/2026
 * File Name: Player.java
 * Description: Represents a soccer player with attributes used
 *              to score and rank them during lineup optimization.
 */

package edu.bu.met.cs665.matchday;

/**
 * Represents a soccer player with a name, position, and five
 * skill attributes rated from 1 to 100.
 */
public class Player {

  private String name;
  private String position;
  private int speed;
  private int stamina;
  private int shooting;
  private int passing;
  private int defending;

  /**
   * Constructs a Player with a name, position, and skill attributes.
   *
   * @param name      the player's name
   * @param position  preferred position (e.g. "FWD", "MID", "DEF", "GK")
   * @param speed     speed rating 1-100
   * @param stamina   stamina rating 1-100
   * @param shooting  shooting rating 1-100
   * @param passing   passing rating 1-100
   * @param defending defending rating 1-100
   */
  public Player(String name, String position, int speed, int stamina,
                int shooting, int passing, int defending) {
    this.name = name;
    this.position = position;
    this.speed = speed;
    this.stamina = stamina;
    this.shooting = shooting;
    this.passing = passing;
    this.defending = defending;
  }

  /**
   * Returns the player's name.
   *
   * @return player name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the player's preferred position.
   *
   * @return position string
   */
  public String getPosition() {
    return position;
  }

  /**
   * Returns the player's speed rating.
   *
   * @return speed value
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Returns the player's stamina rating.
   *
   * @return stamina value
   */
  public int getStamina() {
    return stamina;
  }

  /**
   * Returns the player's shooting rating.
   *
   * @return shooting value
   */
  public int getShooting() {
    return shooting;
  }

  /**
   * Returns the player's passing rating.
   *
   * @return passing value
   */
  public int getPassing() {
    return passing;
  }

  /**
   * Returns the player's defending rating.
   *
   * @return defending value
   */
  public int getDefending() {
    return defending;
  }

  /**
   * Returns a readable string of the player's name, position, and attributes.
   *
   * @return formatted player info string
   */
  @Override
  public String toString() {
    return name + " [" + position + "] "
        + "SPD:" + speed + " STA:" + stamina
        + " SHT:" + shooting + " PAS:" + passing
        + " DEF:" + defending;
  }
}