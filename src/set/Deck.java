package set;

public class Deck 
{
	private String[] color = {"Red", "Green", "Purple"};
	private String[] shape = {"Diamond", "Oval", "Squiggle"};
	private String[] pattern = {"Empty", "Striped", "Solid"};
	private String[] number = {"one", "two", "three"};
	
	private Card[] cards;
	private int undealt;

	public Deck(GameView view, GameState state) 
	{
		cards = new Card[color.length*shape.length*pattern.length*number.length];
		int index = 0;
		for(int c = 0; c < color.length; c++)
		{
			for(int s = 0; s < shape.length; s++)
			{
				for(int p = 0; p < pattern.length; p++)
				{
					for(int n = 0; n< number.length; n++)
					{
						cards[index] = new Card(c,s,p,n, view, state);
						index++;
					}
				}
			}
		}
		undealt = cards.length;
	}
	
	public Deck() 
	{
		cards = new Card[color.length*shape.length*pattern.length*number.length];
		int index = 0;
		for(int c = 0; c < color.length; c++)
		{
			for(int s = 0; s < shape.length; s++)
			{
				for(int p = 0; p < pattern.length; p++)
				{
					for(int n = 0; n< number.length; n++)
					{
						cards[index] = new Card(c,s,p,n);
						index++;
					}
				}
			}
		}
		undealt = cards.length;
	}

	public boolean isEmpty() 
	{
		if(undealt == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getCardsLeft() 
	{
		return undealt;
	}

	public void shuffle() 
	{
		for(int index = 0; index < cards.length; index++)
			{
				int place = (int) (Math.random() * cards.length);
				Card holder = cards[place];
				cards[place] = cards[index];
				cards[index] = holder;				
			}
		undealt = cards.length;
	}

	public Card deal() 
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			Card toReturn = cards[cards.length - undealt];
			undealt--;
			return toReturn;
		}
	}
	
	public static void main(String[] args)
	{
		Deck d = new Deck();
		d.shuffle();
		for(int i = 0; i < d.cards.length; i++)
			System.out.println(d.deal());
	}
}
