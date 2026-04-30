/**
 * Name: Issam Alaga
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/30/2026
 * File Name: LineupOptimizer.java
 * Description: The Context class in the Strategy pattern and Director
 *              in the Builder pattern. Holds a reference to a LineupStrategy
 *              and uses it to optimize and construct a final Lineup.
 */

package edu.bu.met.cs665.matchday;

import java.util.List;

/**
 * Acts as both the Context (Strategy pattern) and Director (Builder pattern).
 * Holds a reference to a LineupStrategy and delegates player selection to it.
 * The selected players are then assembled into a Lineup via the LineupBuilder.
 */
public class LineupOptimizer {

  private LineupStrategy lineupStrategy;

  /**
   * Constructs a LineupOptimizer with a given lineup strategy.
   *
   * @param lineupStrategy the strategy to use for player selection
   */
  public LineupOptimizer(LineupStrategy lineupStrategy) {
    this.lineupStrategy = lineupStrategy;
  }

  /**
   * Swaps the current lineup strategy with a new one at runtime.
   * Mirrors the HAS-A composition principle — the optimizer is not
   * tied to any specific strategy implementation.
   *
   * @param lineupStrategy the new strategy to apply
   */
  public void setLineupStrategy(LineupStrategy lineupStrategy) {
    this.lineupStrategy = lineupStrategy;
  }

  /**
   * Selects the captain from the starting players list.
   * Skips the goalkeeper and returns the first outfield starter,
   * who is the highest scored player for the chosen strategy.
   *
   * @param startingPlayers the list of selected starting players
   * @return the designated captain Player
   */
  private Player selectCaptain(List<Player> startingPlayers) {
    for (Player startingPlayer : startingPlayers) {
      if (!startingPlayer.getPosition().equals("GK")) {
        return startingPlayer;
      }
    }
    return startingPlayers.get(0);
  }

  /**
   * Optimizes and builds a lineup from the given squad pool.
   * Delegates player selection to the current strategy, then
   * uses the LineupBuilder to construct the final Lineup.
   *
   * @param squadPool  the full list of available players
   * @param formation  the formation defining position slot limits
   * @param tacticName the tactic name (e.g. "Attacking")
   * @return the fully constructed and optimized Lineup
   */
  public Lineup optimizeLineup(List<Player> squadPool,
                               Formation formation, String tacticName) {
    LineupBuilder lineupBuilder = new LineupBuilder();
    lineupBuilder.setFormation(formation.getFormationName());
    lineupBuilder.setTacticName(tacticName);

    lineupStrategy.selectLineup(squadPool, lineupBuilder, formation);

    Player captain = selectCaptain(lineupBuilder.getStartingPlayers());
    lineupBuilder.setCaptain(captain);

    return lineupBuilder.build();
  }
}