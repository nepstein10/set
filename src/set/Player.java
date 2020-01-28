package set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player implements ActionListener {

	public int player;
	GameState state;
	
	public Player(int i, GameState s)
	{
		player = i;
		state = s;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(state.switchPlayer)
			state.activePlayer = player;
	}

}
