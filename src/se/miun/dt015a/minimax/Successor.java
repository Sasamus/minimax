package se.miun.dt015a.minimax;

/**
 * A successor to some state. Contains the new state and the action that leads
 * from the previous state to the new state.
 * @author Christoffer Fink
 */
public class Successor<S,A> {

  public final S state;
  public final A action;

  public Successor(S state, A action) {
    this.state = state;
    this.action = action;
  }
}
