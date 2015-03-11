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

		// Create a successor that isn't actually one
		Successor<State, Action> firstSuccessor = new Successor<State, Action>(
				state, null);

		// Call getMaxValue with state
		Successor<State, Action> successor = getMaxValue(firstSuccessor);

		// Return successor.action
		return successor.action;
	}

	@SuppressWarnings("unchecked")
	Successor<State, Action> getMaxValue(Successor<State, Action> successor) {

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
		Successor<State, Action> tmpMaxSuccesor = null;

		// Iterate through successors
		for (Successor<State, Action> tmpSuccessor : successors) {

			// Call getMinValue on tmpSuccessor
			tmpMaxSuccesor = getMinValue(tmpSuccessor);

			// Check if maxSuccessor equals null
			if (maxSuccessor == null) {

				// Set maxSuccessor
				maxSuccessor = tmpMaxSuccesor;
			}
			// Check if tmpMaxSuccessor have more utility than maxSuccessor
			else if (game.getUtility(tmpMaxSuccesor.state) > game
					.getUtility(maxSuccessor.state)) {

				// If so, update maxState with tmpMaxState
				maxSuccessor = tmpMaxSuccesor;
			}
		}

		// Return maxSuccessor
		return maxSuccessor;
	}

	@SuppressWarnings("unchecked")
	Successor<State, Action> getMinValue(Successor<State, Action> successor) {

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

			// Call getMaxValue on tmpSuccessor
			tmpMinSuccessor = getMaxValue(tmpSuccessor);

			// Check if minSuccessor equals null
			if (minSuccessor == null) {

				// Set minSuccessor
				minSuccessor = tmpMinSuccessor;
			}
			// Check if tmpMinSuccessor have less utility than minSuccessor
			else if (game.getUtility(tmpMinSuccessor.state) < game
					.getUtility(minSuccessor.state)) {

				// If so, update minSuccessor with tmpMinSuccessor
				minSuccessor = tmpMinSuccessor;
			}
		}

		// Return minSuccessor
		return minSuccessor;
	}

}
