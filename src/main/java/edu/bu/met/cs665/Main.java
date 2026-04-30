/**
 * Name: Issam Alaga
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/30/2026
 * File Name: Main.java
 * Description: Entry point for the Matchday Optimizer application.
 *              Allows a coach to interactively select a tactical strategy
 *              and formation, then optimizes the lineup from a squad pool
 *              loaded from an external CSV file.
 */

package edu.bu.met.cs665;

import edu.bu.met.cs665.matchday.AttackingStrategy;
import edu.bu.met.cs665.matchday.BalancedStrategy;
import edu.bu.met.cs665.matchday.DefensiveStrategy;
import edu.bu.met.cs665.matchday.Formation;
import edu.bu.met.cs665.matchday.Lineup;
import edu.bu.met.cs665.matchday.LineupOptimizer;
import edu.bu.met.cs665.matchday.LineupStrategy;
import edu.bu.met.cs665.matchday.Player;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class that runs the Matchday Optimizer interactively.
 * The coach selects a formation and tactical strategy via console
 * input, and the system optimizes the best lineup from the squad
 * pool loaded from squad.csv.
 */
public class Main {

  /**
   * Loads and returns the Arsenal squad pool from squad.csv.
   * Each line contains: name, position, speed, stamina,
   * shooting, passing, defending.
   *
   * @return list of Player objects loaded from squad.csv
   */
  public static List<Player> buildSquadPool() {
    List<Player> squadPool = new ArrayList<>();
    try {
      InputStream inputStream = Main.class
          .getClassLoader()
          .getResourceAsStream("squad.csv");
      BufferedReader squadFileReader = new BufferedReader(
          new InputStreamReader(inputStream));
      String currentLine;
      while ((currentLine = squadFileReader.readLine()) != null) {
        String[] playerAttributes = currentLine.split(",");
        squadPool.add(new Player(
            playerAttributes[0].trim(),
            playerAttributes[1].trim(),
            Integer.parseInt(playerAttributes[2].trim()),
            Integer.parseInt(playerAttributes[3].trim()),
            Integer.parseInt(playerAttributes[4].trim()),
            Integer.parseInt(playerAttributes[5].trim()),
            Integer.parseInt(playerAttributes[6].trim())
        ));
      }
      squadFileReader.close();
    } catch (Exception squadLoadException) {
      System.out.println("Error loading squad: "
          + squadLoadException.getMessage());
    }
    return squadPool;
  }

  /**
   * Prompts the coach to select a formation from the available options.
   *
   * @param scanner the scanner reading console input
   * @return the selected Formation
   */
  public static Formation selectFormation(Scanner scanner) {
    System.out.println("Select formation:");
    System.out.println("  1. 4-3-3");
    System.out.println("  2. 4-4-2");
    System.out.println("  3. 3-5-2");
    System.out.print("Enter choice (1-3): ");

    int formationChoice = scanner.nextInt();
    switch (formationChoice) {
      case 1: return Formation.F_4_3_3;
      case 2: return Formation.F_4_4_2;
      case 3: return Formation.F_3_5_2;
      default:
        System.out.println("Invalid choice, defaulting to 4-3-3.");
        return Formation.F_4_3_3;
    }
  }

  /**
   * Prompts the coach to select a tactical strategy.
   *
   * @param scanner the scanner reading console input
   * @return the selected LineupStrategy
   */
  public static LineupStrategy selectStrategy(Scanner scanner) {
    System.out.println("\nSelect tactical strategy:");
    System.out.println("  1. Attacking  (weights shooting + speed)");
    System.out.println("  2. Defensive  (weights defending + stamina)");
    System.out.println("  3. Balanced   (weights all attributes equally)");
    System.out.print("Enter choice (1-3): ");

    int strategyChoice = scanner.nextInt();
    switch (strategyChoice) {
      case 1: return new AttackingStrategy();
      case 2: return new DefensiveStrategy();
      case 3: return new BalancedStrategy();
      default:
        System.out.println("Invalid choice, defaulting to Balanced.");
        return new BalancedStrategy();
    }
  }

  /**
   * Main entry point. Interactively prompts the coach to select
   * a formation and strategy, then prints the optimized lineup.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    Scanner coachInputScanner = new Scanner(System.in);
    List<Player> squadPool = buildSquadPool();

    System.out.println("\n           MATCHDAY OPTIMIZER");
    System.out.println("        Arsenal FC — Season 2025/26\n");

    Formation selectedFormation = selectFormation(coachInputScanner);
    LineupStrategy selectedStrategy = selectStrategy(coachInputScanner);

    String tacticName = selectedStrategy.getClass().getSimpleName()
        .replace("Strategy", "");

    LineupOptimizer optimizer = new LineupOptimizer(selectedStrategy);
    Lineup optimizedLineup = optimizer.optimizeLineup(
        squadPool, selectedFormation, tacticName);

    System.out.println("\n" + optimizedLineup);

    coachInputScanner.close();
  }
}