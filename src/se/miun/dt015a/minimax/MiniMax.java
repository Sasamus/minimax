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

		Successor<State, Action> firstSuccessor = new Successor<State, Action>(state, null);
		
		// Call getMaxValue with state
		Successor<State, Action> successor = getMaxValue(firstSuccessor);


		return successor.action;
	}

	@SuppressWarnings("unchecked")
	Successor<State, Action> getMaxValue(Successor<State, Action> successor) {

		// Check if state is a terminal one
		if (game.isTerminal(successor.state)) {

			// If so, return state
			return successor;
		}

		// Get a List of successors from game
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
				.getSuccessors(successor.state);

		// Variables to keep track of max successors
		Successor<State, Action> maxSuccessor = null;
		Successor<State, Action> tmpMaxSuccesor = null;

		// TODO: Potential comparing null problems?

		// Iterate through successors
		for (Successor<State, Action> tmpSuccessor : successors) {

			// Call getMinValue on state
			tmpMaxSuccesor = getMinValue(tmpSuccessor);

			// Check if maxState equals null
			if (maxSuccessor == null) {

				// Set maxState
				maxSuccessor = tmpMaxSuccesor;
			}
			// Check if tmpMaxState have more utility than maxState
			else if (game.getUtility(tmpMaxSuccesor) > game
					.getUtility(maxSuccessor)) {

				// If so, update maxState with tmpMaxState
				maxSuccessor = tmpMaxSuccesor;
			}
		}

		// Return maxSuccessor
		return maxSuccessor;
	}

	@SuppressWarnings("unchecked")
	Successor<State, Action> getMinValue(Successor<State, Action> successor) {

		// Check if state is a terminal one
		if (game.isTerminal(successor.state)) {

			// If so, return state
			return successor;
		}

		// Get a List of successors from game
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
				.getSuccessors(successor.state);

		// Variables to keep track of min successors
		Successor<State, Action> minSuccessor = null;
		Successor<State, Action> tmpMinSuccessor = null;

		// TODO: Potential comparing null problems?

		// Iterate through successors
		for (Successor<State, Action> tmpSuccessor : successors) {

			// Call getMaxValue on state
			tmpMinSuccessor = getMaxValue(tmpSuccessor);

			// Check if minState equals null
			if (minSuccessor == null) {

				// Set minState
				minSuccessor = tmpMinSuccessor;
			}
			// Check if tmpMaxState have less utility than maxState
			else if (game.getUtility(tmpMinSuccessor) < game.getUtility(minSuccessor)) {

				// If so, update minState with tmpMinState
				minSuccessor = tmpMinSuccessor;
			}
		}

		// Return minSuccessor
		return minSuccessor;
	}

}
