/**
 * Name: Issam Alaga
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/30/2026
 * File Name: TestLineup.java
 * Description: JUnit tests for the Matchday Optimizer application.
 *              Tests verify the correctness of the Builder pattern,
 *              Strategy pattern, and lineup optimization logic.
 */

package edu.bu.met.cs665;

import edu.bu.met.cs665.matchday.AttackingStrategy;
import edu.bu.met.cs665.matchday.BalancedStrategy;
import edu.bu.met.cs665.matchday.DefensiveStrategy;
import edu.bu.met.cs665.matchday.Formation;
import edu.bu.met.cs665.matchday.Lineup;
import edu.bu.met.cs665.matchday.LineupOptimizer;
import edu.bu.met.cs665.matchday.Player;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the Matchday Optimizer to verify that the Builder
 * and Strategy patterns produce correct and valid lineups.
 */
public class TestLineup {

  /**
   * Tests that the attacking strategy selects Viktor Gyokeres
   * (highest shooting rating) as a starter.
   */
  @Test
  public void testAttackingStrategyPicksHighestShooter() {
    List<Player> squadPool = Main.buildSquadPool();
    LineupOptimizer optimizer = new LineupOptimizer(new AttackingStrategy());
    Lineup attackingLineup = optimizer.optimizeLineup(
        squadPool, Formation.F_4_3_3, "Attacking");

    boolean gyokeresIsStarter = false;
    for (Player starter : attackingLineup.getStartingPlayers()) {
      if (starter.getName().equals("Viktor Gyokeres")) {
        gyokeresIsStarter = true;
        break;
      }
    }
    assertTrue(
        "Attacking strategy should start Viktor Gyokeres (highest shooter)",
        gyokeresIsStarter);
  }

  /**
   * Tests that the defensive strategy selects Gabriel
   * (highest defending rating) as a starter.
   */
  @Test
  public void testDefensiveStrategyPicksHighestDefender() {
    List<Player> squadPool = Main.buildSquadPool();
    LineupOptimizer optimizer = new LineupOptimizer(new DefensiveStrategy());
    Lineup defensiveLineup = optimizer.optimizeLineup(
        squadPool, Formation.F_4_3_3, "Defensive");

    boolean gabrielIsStarter = false;
    for (Player starter : defensiveLineup.getStartingPlayers()) {
      if (starter.getName().equals("Gabriel")) {
        gabrielIsStarter = true;
        break;
      }
    }
    assertTrue(
        "Defensive strategy should start Gabriel (highest defender)",
        gabrielIsStarter);
  }

  /**
   * Tests that every optimized lineup contains exactly 11 starting players.
   */
  @Test
  public void testLineupAlwaysHasElevenStarters() {
    List<Player> squadPool = Main.buildSquadPool();
    LineupOptimizer optimizer = new LineupOptimizer(new BalancedStrategy());
    Lineup balancedLineup = optimizer.optimizeLineup(
        squadPool, Formation.F_4_4_2, "Balanced");

    assertEquals(
        "Lineup must always contain exactly 11 starting players",
        11, balancedLineup.getStartingPlayers().size());
  }

  /**
   * Tests that the LineupBuilder correctly sets the formation
   * string on the built Lineup object.
   */
  @Test
  public void testBuilderSetsFormationCorrectly() {
    List<Player> squadPool = Main.buildSquadPool();
    LineupOptimizer optimizer = new LineupOptimizer(new AttackingStrategy());
    Lineup lineup = optimizer.optimizeLineup(
        squadPool, Formation.F_3_5_2, "Attacking");

    assertEquals(
        "Builder should set formation to 3-5-2",
        "3-5-2", lineup.getFormation());
  }

  /**
   * Tests that the captain assigned to the lineup is never
   * the goalkeeper, regardless of strategy or formation.
   */
  @Test
  public void testCaptainIsNeverGoalkeeper() {
    List<Player> squadPool = Main.buildSquadPool();
    LineupOptimizer optimizer = new LineupOptimizer(new DefensiveStrategy());
    Lineup lineup = optimizer.optimizeLineup(
        squadPool, Formation.F_4_3_3, "Defensive");

    assertNotEquals(
        "Captain should never be the goalkeeper",
        "GK", lineup.getCaptain().getPosition());
  }
}