package se.miun.dt015a.minimax;

import java.util.ArrayList;

/**
 * Implement the minimax algorithm with alpha-beta pruning.
 * 
 * @author Christoffer Fink
 */
public class MiniMax<State, Action> {

	Game game;
	
	public Action getAction(Game<State, Action> game, State state) {
		this.game = game;
		
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game.getSuccessors(state);
		
		for(Successor<State, Action> successor : successors){
			
			getMaxValue(successor.state);
		}
		
		
		return null; // Your code here.
	}

	State getMaxValue(State state) {
		
		if(game.isTerminal(state)){
			return state;
		}
		
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game.getSuccessors(state);
		
		for(Successor<State, Action> successor : successors){
			
			getMinValue(successor.state);
		}
	}

}
