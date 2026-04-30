/**
 * Name: Issam Alaga
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/30/2026
 * File Name: Lineup.java
 * Description: Represents the final product constructed by the LineupBuilder.
 *              Immutable once built.
 */

package edu.bu.met.cs665.matchday;

import java.util.List;

/**
 * The Product in the Builder pattern. Holds the completed lineup
 * including formation, starters, bench, captain, and tactic.
 * Only constructable via LineupBuilder.
 */
public class Lineup {

  private final String formation;
  private final List<Player> startingPlayers;
  private final List<Player> benchPlayers;
  private final Player captain;
  private final String tacticName;

  /**
   * Package-private constructor — only LineupBuilder can instantiate this.
   *
   * @param formation       formation string (e.g. "4-3-3")
   * @param startingPlayers list of 11 starting players
   * @param benchPlayers    list of bench players
   * @param captain         the designated captain
   * @param tacticName      the tactical approach name
   */
  Lineup(String formation, List<Player> startingPlayers,
         List<Player> benchPlayers, Player captain, String tacticName) {
    this.formation = formation;
    this.startingPlayers = startingPlayers;
    this.benchPlayers = benchPlayers;
    this.captain = captain;
    this.tacticName = tacticName;
  }

  /**
   * Returns the lineup formation.
   *
   * @return formation string
   */
  public String getFormation() {
    return formation;
  }

  /**
   * Returns the list of starting players.
   *
   * @return starting players list
   */
  public List<Player> getStartingPlayers() {
    return startingPlayers;
  }

  /**
   * Returns the list of bench players.
   *
   * @return bench players list
   */
  public List<Player> getBenchPlayers() {
    return benchPlayers;
  }

  /**
   * Returns the captain of this lineup.
   *
   * @return captain Player object
   */
  public Player getCaptain() {
    return captain;
  }

  /**
   * Returns the tactical approach name.
   *
   * @return tactic name string
   */
  public String getTacticName() {
    return tacticName;
  }

  /**
   * Returns a formatted summary of the full lineup.
   *
   * @return lineup summary string
   */
  @Override
  public String toString() {
    StringBuilder lineupSummary = new StringBuilder();
    lineupSummary.append("=== Matchday Lineup ===\n");
    lineupSummary.append("Formation : ").append(formation).append("\n");
    lineupSummary.append("Tactic    : ").append(tacticName).append("\n");
    lineupSummary.append("Captain   : ").append(captain.getName()).append("\n");
    lineupSummary.append("\nStarters:\n");
    for (Player startingPlayer : startingPlayers) {
      lineupSummary.append("  ").append(startingPlayer.toString()).append("\n");
    }
    lineupSummary.append("\nBench:\n");
    for (Player benchPlayer : benchPlayers) {
      lineupSummary.append("  ").append(benchPlayer.toString()).append("\n");
    }
    return lineupSummary.toString();
  }
}