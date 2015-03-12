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

	/**
	 * get and Action for Game in a State
	 * 
	 * @param game
	 *            The Game
	 * @param state
	 *            The State
	 * @return the Action
	 */
	public Action getAction(Game<State, Action> game, State state) {

		// Sets game to game
		this.game = game;

		// Holds the best value
		double bestValue = Double.NEGATIVE_INFINITY;

		// Holds the best Successor
		Successor<State, Action> bestSuccessor = null;

		// Get a List of successors from game
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
				.getSuccessors(state);

		// Iterate through successors
		for (Successor<State, Action> tmpSuccessor : successors) {

			// Get alpha
			double alpha = minMax(tmpSuccessor, bestValue,
					Double.POSITIVE_INFINITY, false);

			// Check if alpha is better than betsValue or that bestSuccessor is
			// null
			if (alpha > bestValue || bestSuccessor == null) {

				// Set bestSuccessor tp tmpSuccessor
				bestSuccessor = tmpSuccessor;

				// Set bestValue to alpha
				bestValue = alpha;
			}
		}

		// Return bestSuccessors action
		return bestSuccessor.action;
	}

	/**
	 * Returns the best utility value
	 * 
	 * @param node
	 *            The node to originate from
	 * @param alpha
	 *            The current best Max value
	 * @param beta
	 *            The current best Min value
	 * @param max
	 *            True if looking for Max else false
	 * @return The utility value of the best Successor
	 */
	@SuppressWarnings("unchecked")
	double minMax(Successor<State, Action> node, double alpha, double beta,
			boolean max) {

		// Check if nodes state is terminal
		if (game.isTerminal(node.state)) {

			// If so, return it's utility
			return game.getUtility(node.state);
		}

		// Check if max is true
		if (max) {

			// Create and set newAlpha
			double newAlpha = Double.NEGATIVE_INFINITY;

			// Get a List of successors from game
			ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
					.getSuccessors(node.state);

			// Iterate through successors
			for (Successor<State, Action> tmpSuccessor : successors) {

				// Set newAlpha with the max of newAlpha and minMax with false
				newAlpha = Math.max(newAlpha,
						minMax(tmpSuccessor, alpha, beta, false));

				// Set alpha with the max of newAlpha and alpha
				alpha = Math.max(newAlpha, alpha);

				// Check if alpha is better than beta
				if (alpha >= beta) {

					// Return alpha
					return alpha;
				}
			}

			// Return newAlpha
			return newAlpha;

		} else {

			// Create and set newBeta
			double newBeta = Double.POSITIVE_INFINITY;

			// Get a List of successors from game
			ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
					.getSuccessors(node.state);

			// Iterate through successors
			for (Successor<State, Action> tmpSuccessor : successors) {

				// Set newBeta with the min of newBeta and minMax with true
				newBeta = Math.min(newBeta,
						minMax(tmpSuccessor, alpha, beta, true));

				// Set beta with the min of newBeta and beta
				beta = Math.min(newBeta, beta);

				// Check if alpha is better than beta
				if (alpha >= beta) {

					// Return beta
					return beta;
				}
			}

			// Return newBeta
			return newBeta;
		}
	}
}
