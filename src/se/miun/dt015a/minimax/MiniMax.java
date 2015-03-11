package se.miun.dt015a.minimax;

import java.util.ArrayList;

/**
 * Implement the minimax algorithm with alpha-beta pruning.
 * 
 * @author Christoffer Fink
 */
public class MiniMax<State, Action> {

	@SuppressWarnings("rawtypes")
	Game game;

	public Action getAction(Game<State, Action> game, State state) {

		// TODO: Pruning

		// Sets game to game
		this.game = game;

		// Create a successor that isn't actually one to be the first successor
		Successor<State, Action> firstSuccessor = new Successor<State, Action>(
				state, null);

		// Create a successor that isn't actually one to be the first choice
		Successor<State, Action> choiceSuccessor = new Successor<State, Action>(
				null, null);

		// Call getMaxValue with state
		Successor<State, Action> successor = getMaxValue(firstSuccessor,
				choiceSuccessor);

		// Return successor.action
		return successor.action;
	}

	@SuppressWarnings("unchecked")
	Successor<State, Action> getMaxValue(Successor<State, Action> successor,
			Successor<State, Action> currentChoice) {

		// Check if successor.state is a terminal one
		if (game.isTerminal(successor.state)) {

			// If so, return successor
			return successor;
		}

		// Get a List of successors from game
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
				.getSuccessors(successor.state);

		// Variables to keep track of max successors
		Successor<State, Action> maxSuccessor = null;
		Successor<State, Action> tmpMaxSuccessor = null;

		// Iterate through successors
		for (Successor<State, Action> tmpSuccessor : successors) {

			// Check if maxSuccessor equals null
			if (maxSuccessor == null) {

				// Set maxSuccessor
				maxSuccessor = tmpSuccessor;
			}

			// Call getMinValue on tmpSuccessor
			tmpMaxSuccessor = getMinValue(tmpSuccessor, maxSuccessor);

			// Check if tmpMaxSuccessor have more utility than maxSuccessor
			if (game.getUtility(tmpMaxSuccessor.state) > game
					.getUtility(maxSuccessor.state)) {

				// If so, update maxState with tmpMaxState
				maxSuccessor = tmpMaxSuccessor;
			}

			// Check if the rest of the sucessors should be pruned
			if (game.getUtility(maxSuccessor.state) > game
					.getUtility(currentChoice.state)) {
				break;
			}
		}

		// Return maxSuccessor
		return maxSuccessor;
	}

	@SuppressWarnings("unchecked")
	Successor<State, Action> getMinValue(Successor<State, Action> successor,
			Successor<State, Action> currentChoice) {

		// Check if successor.state is a terminal one
		if (game.isTerminal(successor.state)) {

			// If so, return successor
			return successor;
		}

		// Get a List of successors from game
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
				.getSuccessors(successor.state);

		// Variables to keep track of min successors
		Successor<State, Action> minSuccessor = null;
		Successor<State, Action> tmpMinSuccessor = null;

		// Iterate through successors
		for (Successor<State, Action> tmpSuccessor : successors) {

			// Check if minSuccessor equals null
			if (minSuccessor == null) {

				// Set minSuccessor
				minSuccessor = tmpSuccessor;
			}

			// Call getMaxValue on tmpSuccessor
			tmpMinSuccessor = getMaxValue(tmpSuccessor, minSuccessor);

			// Check if tmpMinSuccessor have less utility than minSuccessor
			if (game.getUtility(tmpMinSuccessor.state) < game
					.getUtility(minSuccessor.state)) {

				// If so, update minSuccessor with tmpMinSuccessor
				minSuccessor = tmpMinSuccessor;
			}

			// Check if the rest of the sucessors should be pruned
			if (game.getUtility(minSuccessor.state) < game
					.getUtility(currentChoice.state)) {
				break;
			}
		}

		// Return minSuccessor
		return minSuccessor;
	}

}
