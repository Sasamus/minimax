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

		// Sets game to game
		this.game = game;

		// Gets a List of the successsors from game
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
				.getSuccessors(state);

		// Iterate through the successors
		for (Successor<State, Action> successor : successors) {

			// Call getMaxValoue with successors.state
			getMaxValue(successor.state);
		}

		// TODO: Return Action here
		return null; // Your code here.
	}

	@SuppressWarnings("unchecked")
	State getMaxValue(State state) {

		// Check if state is a terminal one
		if (game.isTerminal(state)) {

			// If so, return state
			return state;
		}

		// Get a List of successors from game
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
				.getSuccessors(state);

		// Variables to keep track of max states
		State maxState = null;
		State tmpMaxState = null;

		// TODO: Potential comparing null problems?

		// Iterate through successors
		for (Successor<State, Action> successor : successors) {

			// Call getMinValue on state
			tmpMaxState = getMinValue(successor.state);

			// Check if tmpMaxState have more utility than maxState
			if (game.getUtility(tmpMaxState) > game.getUtility(maxState)) {

				// If so, update maxState with tmpMaxState
				maxState = tmpMaxState;
			}
		}

		// Return maxState
		return maxState;
	}

	@SuppressWarnings("unchecked")
	State getMinValue(State state) {

		// Check if state is a terminal one
		if (game.isTerminal(state)) {

			// If so, return state
			return state;
		}

		// Get a List of successors from game
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
				.getSuccessors(state);

		// Variables to keep track of min states
		State minState = null;
		State tmpMinState = null;

		// TODO: Potential comparing null problems?

		// Iterate through successors
		for (Successor<State, Action> successor : successors) {

			// Call getMaxValue on state
			tmpMinState = getMaxValue(successor.state);

			// Check if tmpMaxState have less utility than maxState
			if (game.getUtility(tmpMinState) < game.getUtility(minState)) {

				// If so, update minState with tmpMinState
				minState = tmpMinState;
			}
		}

		// Return minState
		return minState;
	}

}
