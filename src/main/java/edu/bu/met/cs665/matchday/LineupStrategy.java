/**
 * Name: Issam Alaga
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/30/2026
 * File Name: LineupStrategy.java
 * Description: The Strategy interface for lineup selection algorithms
 *              in the Matchday Optimizer.
 */

package edu.bu.met.cs665.matchday;

import java.util.List;

/**
 * Strategy interface declaring the common method for all
 * lineup selection algorithms. Concrete strategies implement
 * this with different player scoring and selection logic.
 */
public interface LineupStrategy {

  /**
   * Selects the optimal starting eleven and bench from the squad pool
   * and populates the provided LineupBuilder based on the given formation.
   *
   * @param squadPool     the full list of available players
   * @param lineupBuilder the builder to populate with selected players
   * @param formation     the formation defining position slot limits
   */
  void selectLineup(List<Player> squadPool, LineupBuilder lineupBuilder,
                    Formation formation);

}