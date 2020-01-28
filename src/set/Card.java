package set;

import javax.swing.ImageIcon;

import sun.tools.jar.resources.jar;

public class Card {
//	private String[] color = {"Red", "Green", "Purple"};
//	private String[] shape = {"Diamond", "Oval", "Squiggle"};
//	private String[] pattern = {"Empty", "Striped", "Solid"};
//	private String[] number = {"one", "two", "three"};
	private int icolor;
	private int ishape;
	private int ipattern;
	private int inumber;
	public ImageIcon cPNG;
	public ImageIcon uPNG;
	public Clicker click;
	
	public Card(int ic, int is, int ip, int in, GameView view, GameState state)
	{
		icolor = ic;
		ishape = is;
		ipattern = ip;
		inumber = in;
		String path = "cards/"+ic+is+ip+in+".png";
		uPNG = new ImageIcon(path);
		path = "cards/"+ic+is+ip+in+"c.png";
		cPNG = new ImageIcon(path);
		click = new Clicker(view, state, this);
	}
	
	public Card(int ic, int is, int ip, int in)
	{
		icolor = ic;
		ishape = is;
		ipattern = ip;
		inumber = in;
		String path = "cards/"+ic+is+ip+in+".png";
		uPNG = new ImageIcon(path);
		click = new Clicker();
	}

	public String toString()
	{
//		return number[inumber]+" "+pattern[ipattern]+" "+color[icolor]+" "+shape[ishape];
		return ""+inumber+ipattern+icolor+ishape;
	}
	
	public int getC()
	{
		return icolor;
	}
	
	public int getS()
	{
		return ishape;
	}
	
	public int getP()
	{
		return ipattern;
	}
	
	public int getN()
	{
		return inumber;
	}
	
	public boolean equals(Card x)
	{
		boolean ans = true;
		if(icolor != x.getC())
			ans = false;
		if(ishape != x.getS())
			ans = false;
		if(ipattern != x.getP())
			ans = false;
		if(inumber != x.getN())
			ans = false;
		return ans;
	}
}
