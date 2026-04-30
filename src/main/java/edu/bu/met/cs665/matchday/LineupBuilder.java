/**
 * Name: Issam Alaga
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/30/2026
 * File Name: LineupBuilder.java
 * Description: The ConcreteBuilder in the Builder pattern. Constructs
 *              a Lineup object step by step via a fluent API.
 */

package edu.bu.met.cs665.matchday;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a Lineup object step by step. Validates all required
 * components before constructing the final Lineup product.
 * Reference: https://refactoring.guru/design-patterns/builder
 */
public class LineupBuilder {

  private String formation;
  private List<Player> startingPlayers;
  private List<Player> benchPlayers;
  private Player captain;
  private String tacticName;

  /**
   * Constructs a new LineupBuilder with empty player lists.
   */
  public LineupBuilder() {
    this.startingPlayers = new ArrayList<>();
    this.benchPlayers = new ArrayList<>();
  }

  /**
   * Sets the formation for the lineup.
   *
   * @param formation formation string (e.g. "4-3-3")
   * @return this builder instance for chaining
   */
  public LineupBuilder setFormation(String formation) {
    this.formation = formation;
    return this;
  }

  /**
   * Adds a player to the starting eleven.
   *
   * @param startingPlayer the player to add as a starter
   * @return this builder instance for chaining
   */
  public LineupBuilder addStartingPlayer(Player startingPlayer) {
    this.startingPlayers.add(startingPlayer);
    return this;
  }

  /**
   * Adds a player to the bench.
   *
   * @param benchPlayer the player to add to the bench
   * @return this builder instance for chaining
   */
  public LineupBuilder addBenchPlayer(Player benchPlayer) {
    this.benchPlayers.add(benchPlayer);
    return this;
  }

  /**
   * Sets the captain of the lineup.
   *
   * @param captain the player to designate as captain
   * @return this builder instance for chaining
   */
  public LineupBuilder setCaptain(Player captain) {
    this.captain = captain;
    return this;
  }

  /**
   * Sets the tactical approach name.
   *
   * @param tacticName the tactic name (e.g. "Attacking")
   * @return this builder instance for chaining
   */
  public LineupBuilder setTacticName(String tacticName) {
    this.tacticName = tacticName;
    return this;
  }

  /**
   * Returns the current list of starting players added so far.
   *
   * @return list of starting players
   */
  public List<Player> getStartingPlayers() {
    return startingPlayers;
  }

  /**
   * Returns the current number of starting players added.
   *
   * @return starting player count
   */
  public int getStartingPlayerCount() {
    return startingPlayers.size();
  }

  /**
   * Validates all components and builds the final Lineup object.
   *
   * @return the constructed Lineup
   * @throws IllegalStateException if any required component is missing
   */
  public Lineup build() {
    if (formation == null || formation.isEmpty()) {
      throw new IllegalStateException("Cannot build Lineup: formation is not set.");
    }
    if (startingPlayers.size() != 11) {
      throw new IllegalStateException(
          "Cannot build Lineup: exactly 11 starting players required, got "
              + startingPlayers.size() + ".");
    }
    if (captain == null) {
      throw new IllegalStateException("Cannot build Lineup: captain is not set.");
    }
    if (tacticName == null || tacticName.isEmpty()) {
      throw new IllegalStateException("Cannot build Lineup: tactic name is not set.");
    }
    return new Lineup(formation, startingPlayers, benchPlayers, captain, tacticName);
  }
}