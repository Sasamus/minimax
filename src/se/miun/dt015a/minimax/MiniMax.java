package se.miun.dt015a.minimax;

import java.util.ArrayList;

/**
 * Implement the minimax algorithm with alpha-beta pruning.
 * 
 * @author Christoffer Fink
 */
public class MiniMax<State, Action> {

	@SuppressWarnings("rawtypes")
	private Game game;

	public Action getAction(Game<State, Action> game, State state) {

		// TODO: Pruning

		// Sets game to game
		this.game = game;

		// Create a successor that isn't actually one to be the first successor
		Successor<State, Action> firstSuccessor = new Successor<State, Action>(
				state, null);

		// Call getMaxValue with firstSuccessor
		Successor<State, Action> successor = getMinValue(firstSuccessor, null);

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

				// Set maxSuccessor and tmpMaxSuccessor
				maxSuccessor = tmpSuccessor;
				tmpMaxSuccessor = tmpSuccessor;
			}

			// Check if currentChoice is null
			if (currentChoice != null) {

				// Check if the rest of the successors should be pruned
				if (game.getUtility(maxSuccessor.state) > game
						.getUtility(currentChoice.state)) {

					break;
				}
			}

			if (game.isTerminal(maxSuccessor.state)) {

				// Check if tmpMaxSuccessor have more utility than maxSuccessor
				if (game.getUtility(tmpMaxSuccessor.state) > game
						.getUtility(maxSuccessor.state)) {

					// If so, update maxState with tmpMaxState
					maxSuccessor = tmpMaxSuccessor;
				}
			} else {

				// Call getMinValue on tmpSuccessor
				tmpMaxSuccessor = getMinValue(tmpSuccessor, null);
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

				// Set minSuccessor and tmpMinSuccessor
				minSuccessor = tmpSuccessor;
				tmpMinSuccessor = tmpSuccessor;
			}

			// Check if currentChoice is null
			if (currentChoice != null) {

				// Check if the rest of the successors should be pruned
				if (game.getUtility(minSuccessor.state) < game
						.getUtility(currentChoice.state)) {

					break;
				}
			}

			if (game.isTerminal(minSuccessor.state)) {

				// Call getMaxValue on tmpSuccessor
				tmpMinSuccessor = getMaxValue(tmpSuccessor, minSuccessor);

				// Check if tmpMinSuccessor have less utility than minSuccessor
				if (game.getUtility(tmpMinSuccessor.state) < game
						.getUtility(minSuccessor.state)) {

					// If so, update minSuccessor with tmpMinSuccessor
					minSuccessor = tmpMinSuccessor;
				}
			} else {

				// Call getMaxValue on tmpSuccessor
				tmpMinSuccessor = getMaxValue(tmpSuccessor, null);
			}

		}

		// Return minSuccessor
		return minSuccessor;
	}

}
