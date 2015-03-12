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

		// Create a successor that isn't actually one to be the first successor
		Successor<State, Action> firstSuccessor = new Successor<State, Action>(
				state, null);

		// Call getBestSuccessor
		Successor<State, Action> successor = getBestSuccessor(firstSuccessor,
				null, true);

		// Return successor.action
		return successor.action;
	}

	@SuppressWarnings("unchecked")
	Successor<State, Action> getBestSuccessor(
			Successor<State, Action> successor,
			Successor<State, Action> currentChoice, boolean max) {

		// Check if successor.state is a terminal one
		if (game.isTerminal(successor.state)) {

			// If so, return successor
			return successor;
		}

		// Get a List of successors from game
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game
				.getSuccessors(successor.state);

		// Variables to keep track of the best successors
		Successor<State, Action> bestSuccessor = null;
		Successor<State, Action> tmpBestSuccessor = null;

		// Iterate through successors
		for (Successor<State, Action> tmpSuccessor : successors) {

			// Check if bestSuccessor equals null
			if (bestSuccessor == null) {

				// Set bestSuccessor and tmpBestSuccessor
				bestSuccessor = tmpSuccessor;
				tmpBestSuccessor = tmpSuccessor;
			}

			// Check if currentChoice is null and bestSuccessor.state is
			// terminal
			if (currentChoice != null && game.isTerminal(bestSuccessor.state)) {

				// Check if max is true
				if (max) {

					// Check if the rest of the successors should be pruned
					if (game.getUtility(bestSuccessor.state) > game
							.getUtility(currentChoice.state)) {

						break;
					}

				} else {

					// Check if the rest of the successors should be pruned
					if (game.getUtility(bestSuccessor.state) < game
							.getUtility(currentChoice.state)) {

						break;
					}
				}
			}

			// Check if bestSuccessor.state is terminal
			if (game.isTerminal(bestSuccessor.state)) {

				// Call getBestSuccessor on tmpSuccessor with inverted max
				tmpBestSuccessor = getBestSuccessor(tmpSuccessor,
						bestSuccessor, !max);

				// Check if tmpBestSuccessor.state is terminal
				if (game.isTerminal(tmpBestSuccessor.state)) {

					// Check if max is true
					if (max) {

						// Check if tmpBestSuccessor have more utility than
						// bestSuccessor
						if (game.getUtility(tmpBestSuccessor.state) > game
								.getUtility(bestSuccessor.state)) {

							// If so, update maxState with tmpMaxState
							bestSuccessor = tmpBestSuccessor;
						}

					} else {

						// Check if tmpbestSuccessor have less utility than
						// maxSuccessor
						if (game.getUtility(tmpBestSuccessor.state) < game
								.getUtility(bestSuccessor.state)) {

							// If so, update bestSuccessor with tmpBestSuccessor
							bestSuccessor = tmpBestSuccessor;
						}
					}
				}

			} else {

				// Call getBestSuccessor on tmpSuccessor with null as
				// currentChoice and inverted max as max
				tmpBestSuccessor = getBestSuccessor(tmpSuccessor, null, !max);
			}
		}

		// Return maxSuccessor
		return bestSuccessor;
	}
}
