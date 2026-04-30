/**
 * Name: Issam Alaga
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/30/2026
 * File Name: AttackingStrategy.java
 * Description: A lineup selection strategy that prioritizes attacking
 *              attributes when scoring and selecting players.
 */

package edu.bu.met.cs665.matchday;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Concrete Strategy that weights shooting (50%), speed (30%),
 * and passing (20%) when scoring and selecting players.
 */
public class AttackingStrategy implements LineupStrategy {

  /**
   * Calculates an attacking score for a given player.
   *
   * @param player the player to score
   * @return the calculated attacking score
   */
  private double calculateAttackingScore(Player player) {
    return (player.getShooting() * 0.50)
        + (player.getSpeed() * 0.30)
        + (player.getPassing() * 0.20);
  }

  /**
   * Selects top players by position using attacking score weights.
   * Position slot limits are determined by the chosen formation.
   * Remainder go to bench.
   *
   * @param squadPool     the full list of available players
   * @param lineupBuilder the builder to populate with selected players
   * @param formation     the formation defining position slot limits
   */
  @Override
  public void selectLineup(List<Player> squadPool, LineupBuilder lineupBuilder,
                           Formation formation) {
    List<Player> sortedSquad = new ArrayList<>(squadPool);
    sortedSquad.sort(Comparator.comparingDouble(
        this::calculateAttackingScore).reversed());

    List<Player> selectedStarters = selectByPosition(
        sortedSquad, "GK", formation.getGoalkeeperSlots());
    selectedStarters.addAll(selectByPosition(
        sortedSquad, "FWD", formation.getForwardSlots()));
    selectedStarters.addAll(selectByPosition(
        sortedSquad, "MID", formation.getMidfielderSlots()));
    selectedStarters.addAll(selectByPosition(
        sortedSquad, "DEF", formation.getDefenderSlots()));

    for (Player starter : selectedStarters) {
      lineupBuilder.addStartingPlayer(starter);
    }

    for (Player remainingPlayer : sortedSquad) {
      if (!selectedStarters.contains(remainingPlayer)) {
        lineupBuilder.addBenchPlayer(remainingPlayer);
      }
    }
  }

  /**
   * Returns the top N players from the sorted squad matching the given position.
   *
   * @param sortedSquad the squad sorted by score
   * @param position    the position to filter by
   * @param limit       the max number of players to select
   * @return list of selected players for that position
   */
  private List<Player> selectByPosition(List<Player> sortedSquad,
                                        String position, int limit) {
    List<Player> selectedPlayers = new ArrayList<>();
    for (Player player : sortedSquad) {
      if (selectedPlayers.size() >= limit) {
        break;
      }
      if (player.getPosition().equals(position)) {
        selectedPlayers.add(player);
      }
    }
    return selectedPlayers;
  }
}