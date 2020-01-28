package set;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameState 
{
	private Card[] grid;
	public String status;
	public int score1;
	public int score2;
	public Deck deck;
	public int activePlayer;
	public boolean switchPlayer;
	private GameView view;
	private Card[] selected;
	
	public GameState()
	{
		score1 = 0;
		score2 = 0;
		status = "";
		grid = new Card[21];
		selected = new Card[3];
		for(int i = 0; i < 21; i++)
		{
			grid[i] = null;
		}
	}
	
	public void playGame()
	{
		newGame();
		while(status != "Game Over")
		{
			round();
		}
		return;
	}
	
	public void newGame()
	{
		view = new GameView(this);
		deck = new Deck(view, this);
		deck.shuffle();
		deal(12);
		status = "New game started!";
	}

	public void round()
	{
		System.out.println("round");
		while(!deck.isEmpty() && !setPossible())
		{
			deal(3);
		}
		if(deck.isEmpty() && !setPossible())
		{
			status = "Game Over";
			if(score1 > score2)
				status += ": Player 1 wins!";
			else if(score2 > score1)
				status += ": Player 2 wins!";
			else
				status += ": it's a tie.";
			updateGrid();
			view.updateLabels();
			view.layCards();
			status = "Game Over";
			System.out.println("Player 1: "+score1+"  Player 2: "+score2);
			return;
		}
		view.layCards();
		getClicks();
		handleClicked();
	}
	
//	public void show()
//	{
//		int max = findMax();
//		for(int i = 0; i < max/3 + 1; i++)
//		{
//			for(int j = 0; j < 3; j++)
//			{
//				System.out.print(grid[i*3 + j] + "  ");
//			}
//			System.out.println();
//		}
//		view.layCards();
//		
//	}
	
	public void getClicks()
	{
		System.out.println("getClicks");
		activePlayer = 0;
		switchPlayer = true;
		selected = new Card[3];
		while(selected[2] == null)
		{
//			status = "click cards to make a set!";
			view.updateLabels();
			try {Thread.sleep(300);} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	public void handleClicked()
	{
		System.out.println("handleClicked");
		boolean set = false;
		if(isSet(selected[0], selected[1], selected[2]))
		{
			set = true;
			if(activePlayer == 1)
			{
				score1++;
				status = "Player 1 found a set!";
			}
			else if(activePlayer == 2)
			{
				score2++;
				status = "Player 2 found a set!";
			}
			for(int i = 0; i <= findMax(); i++)
			{
				if(grid[i].equals(selected[0]) || grid[i].equals(selected[1]) || grid[i].equals(selected[2]))
				{
					grid[i] = null;
				}
			}
			if(cardsOut() < 12)
			{
				deal(3);
			}
			updateGrid();
			view.lastSetPanel = new JPanel();
			for(int i = 0; i < 3; i++)
			{
				view.lastSetPanel.add(new JLabel(selected[i].uPNG));
				selected[i].click.clicked = false;
				selected[i] = null;
			}
		}
		else
		{
			status = "Oops, not quite a set. Click to find a set!";
			for(int i = 0; i < 3; i++)
			{
				selected[i].click.clicked = !selected[i].click.clicked;
			}
			view.layCards();
		}
	}
	
	public int findMax()
	{
		int max = 0;
		for(int m = 0; m < 21; m++)
			if(grid[m] != null)
				max = m;
		return max;
	}
	
	public int cardsOut()
	{
		int cards = 0;
		for(int i = 0; i <= findMax(); i++)
		{
			if(grid[i] != null)
			{
				cards++;
			}
		}
		return cards;
	}
	
	public boolean addToSelected(Card c)
	{
		System.out.println("add");
		for(int i = 0; i < 3; i++)
		{
			if(selected[i] == null)
			{
				selected[i] = c;
				System.out.println(Arrays.toString(selected));
				return true;
			}
		}
		return false;
	}
	
	public boolean outOfSelected(Card c)
	{
		System.out.println("remove");
		for(int i = 0; i < 3; i++)
		{
			if(selected[i] != null && selected[i].equals(c))
			{
				selected[i] = null;
				System.out.println(Arrays.toString(selected));
				return true;
			}
		}
		return false;
	}
	
	public Card getCard(int n)
	{
		Card[] copy = grid.clone();
		Card c = copy[n];
		return c;
	}
	
	public void deal(int n)
	{
		System.out.println("deal " + n);
		int x = 0;
		for(int i = 0; i < n; i++)
		{
			while(grid[x] != null)
			{
				x++;
			}
			if(grid[x] == null)
				grid[x] = deck.deal();
			else
				System.out.print(x+"!");
		}
	}
	
	public void updateGrid()
	{
		for(int i = 0; i <= findMax(); i++)
		{
			if(grid[i] == null)
			{
				grid[i] = grid[findMax()];
				grid[findMax()] = null;
			}
		}
	}
	
	public boolean isSet(Card x, Card y, Card z)
	{
		if(((x.getC()==y.getC()&&x.getC()==z.getC())
			||(x.getC()!=y.getC()&&x.getC()!=z.getC()&&y.getC()!=z.getC()))
		 &&((x.getS()==y.getS()&&x.getS()==z.getS())
			||(x.getS()!=y.getS()&&x.getS()!=z.getS()&&y.getS()!=z.getS()))
		 &&((x.getP()==y.getP()&&x.getP()==z.getP())
			||(x.getP()!=y.getP()&&x.getP()!=z.getP()&&y.getP()!=z.getP()))
		 &&((x.getN()==y.getN()&&x.getN()==z.getN())
			||(x.getN()!=y.getN()&&x.getN()!=z.getN()&&y.getN()!=z.getN())))
			return true;
		else
			return false;
	}
	
	public Card find3rd(Card x, Card y)
	{
		int c = 0;
		int s = 0;
		int p = 0;
		int n = 0;
		
		if(x.getC() == y.getC())
			c = x.getC();
		else if(x.getC()+y.getC()==1)
			c = 2;
		else if(x.getC() + y.getC() == 2)
			c = 1;
		else if(x.getC() + y.getC() == 3)
			c = 0;
		if(x.getS() == y.getS())
			s = x.getS();
		else if(x.getS()+y.getS()==1)
			s = 2;
		else if(x.getS() + y.getS() == 2)
			s = 1;
		else if(x.getS() + y.getS() == 3)
			s = 0;
		if(x.getP() == y.getP())
			p = x.getP();
		else if(x.getP()+y.getP()==1)
			p = 2;
		else if(x.getP() + y.getP() == 2)
			p = 1;
		else if(x.getP() + y.getP() == 3)
			p = 0;
		if(x.getN() == y.getN())
			n = x.getN();
		else if(x.getN()+y.getN()==1)
			n = 2;
		else if(x.getN() + y.getN() == 2)
			n = 1;
		else if(x.getN() + y.getN() == 3)
			n = 0;
		
		return new Card(c,s,p,n);
	}
	
	public boolean setPossible()
	{
		for(int i = 0; i < 20; i++)
		{
			while(i < 21 && grid[i] == null)
				i++;
			for(int j = i + 1; j < 21; j++)
			{
				while(j < 21 && grid[j] == null)
					j++;
				if(j < 21)
				{
					Card check = find3rd(grid[i], grid[j]);
					for(int k = 0; k < 21; k++)
					{
						while(k < 21 && grid[k] == null)
						{
							k++;
						}
						if(k < 21 && grid[k].equals(check))
						{
							System.out.println("setPossible");
							return true;
						}
					}
				}
			}
		}
		System.out.println("!setPossible");
		return false;
	}
	//check setlogic on first 3 cards
	public static void main(String[] args)
	{
		GameState play = new GameState();
		play.playGame();
		return;
	}
}
