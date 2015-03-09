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
		
		this.game = game;
		
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game.getSuccessors(state);
		
		for(Successor<State, Action> successor : successors){
			
			getMaxValue(successor.state);
		}
		
		
		return null; // Your code here.
	}

	@SuppressWarnings("unchecked")
	State getMaxValue(State state) {
		
		if(game.isTerminal(state)){
			return state;
		}
		
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game.getSuccessors(state);
		
		State maxState = null;
		State tmpMaxState = null; 

		//TODO: Potential comparing null problems?
		
		for(Successor<State, Action> successor : successors){
			
			tmpMaxState = getMinValue(successor.state);
			
			if(game.getUtility(tmpMaxState) > game.getUtility(maxState)){
				
				maxState = tmpMaxState;
			}
		}
		
		return maxState;
	}
	
	@SuppressWarnings("unchecked")
	State getMinValue(State state) {
		
		if(game.isTerminal(state)){
			return state;
		}
		
		ArrayList<Successor<State, Action>> successors = (ArrayList<Successor<State, Action>>) game.getSuccessors(state);
		
		State minState = null;
		State tmpMinState = null; 

		//TODO: Potential comparing null problems?
		
		for(Successor<State, Action> successor : successors){
			
			tmpMinState = getMinValue(successor.state);
			
			if(game.getUtility(tmpMinState) < game.getUtility(minState)){
				
				minState = tmpMinState;
			}
		}
		
		return minState;
	}

}
