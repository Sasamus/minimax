package se.miun.dt015a.minimax;

import java.util.Collection;

/**
 * A model of the game. This is similar to the SearchProblem class in the
 * A* assignment.
 * @author Christoffer Fink
 */
public interface Game<State,Action> {

  /** Returns a collection of successors to the given state. */
  public Collection<Successor<State, Action>> getSuccessors(State s);
  /** Gets the utility of this state provided that it is a terminal state. */
  public int getUtility(State s);
  /** Checks whether the state is a terminal state (leaf node). */
  public boolean isTerminal(State s);
}
