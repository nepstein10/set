package set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clicker implements ActionListener
{
	public GameState state;
	public GameView view;
	public boolean clicked;
	public Card myCard;
	
	public Clicker(GameView v, GameState s, Card c)
	{
		view = v;
		state = s;
		myCard = c;
		clicked = false;
	}
	
	public Clicker()//GameState s, GameView v)
	{
//		state = s;
//		view = v;
		clicked = false;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(state.activePlayer == 0)
		{
			state.status = "Pick a player to claim a set.";
			return;
		}
		state.switchPlayer = false;
		if(clicked)
		{
			clicked = false;
			state.outOfSelected(myCard);
		}
		else
		{
			clicked = true;
			state.addToSelected(myCard);
		}
		view.layCards();
	}
}
