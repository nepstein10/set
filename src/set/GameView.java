package set;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameView 
{
	private GameState state;
	private JPanel gamePanel;
	private JPanel statusPanel;
	private JPanel scorePanel;
	private JPanel playerPanel;
	private JPanel playerButtons;
	private JPanel playerScore;
	private JPanel cardPanel;
	public JPanel lastSetPanel;
	private JPanel southPanel;
	private JLabel scores;
	private JLabel cardsLeft;
	private JLabel statusLabel;
	private JFrame frame;
	private BorderLayout border;
	private BorderLayout playerBorder;
	private BorderLayout southBorder;
	private GridLayout grid;
	public GridLayout lastGrid;
	
	public GameView(GameState s)
	{
		state = s;
		frame = new JFrame();
		gamePanel = new JPanel();
//		statusPanel = new JPanel();
		scorePanel = new JPanel();
		playerPanel = new JPanel();
		playerButtons = new JPanel();
		playerScore = new JPanel();
		cardPanel = new JPanel();
		lastSetPanel = new JPanel();
		southPanel = new JPanel();
		scores = new JLabel();
		cardsLeft = new JLabel();
		border = new BorderLayout();
		playerBorder = new BorderLayout();
		southBorder = new BorderLayout();
		lastGrid = new GridLayout(1, 3);
		grid = new GridLayout(4, 3);
		statusLabel = new JLabel(state.status);
		
		frame.setLayout(border);
		frame.add(gamePanel, border.CENTER);
		frame.add(southPanel, border.SOUTH);
		frame.add(playerPanel, border.NORTH);
		gamePanel.setLayout(grid);
		southPanel.setLayout(southBorder);
		southPanel.add(statusLabel, southBorder.NORTH);
		lastSetPanel.setLayout(lastGrid);
//		JLabel toAdd = new JLabel();
//		BufferedImage img = null;
//		try 
//		{
//			img = ImageIO.read(new File("cards/SetLogo.png"));
//		} catch (IOException e) {}
//		Image dimg = img.getScaledInstance(toAdd.getWidth(), toAdd.getHeight(), Image.SCALE_SMOOTH);
//		toAdd.setIcon(new ImageIcon(dimg));
//		for(int i = 0; i < 3; i++)
//		{
//			lastSetPanel.add(toAdd);
//		}
		southPanel.add(lastSetPanel, southBorder.SOUTH);
		playerPanel.setLayout(playerBorder);
		playerPanel.add(scores, playerBorder.EAST);
		playerPanel.add(cardsLeft, playerBorder.WEST);
		playerPanel.add(playerButtons, playerBorder.CENTER);
		int p = 2;
		for(int i = 0; i < p; i++)
		{
			JButton u = new JButton("Player "+(i+1));
			u.addActionListener(new Player((i+1), state));
			playerButtons.add(u);
		}
	}
	
	public void layCards()
	{
		System.out.println("layCards");
		frame.setSize(450, 100 * (state.findMax() + 2) / 3 + 200);
		frame.remove(gamePanel);
		gamePanel = new JPanel();
		frame.add(gamePanel, border.CENTER);
		GridLayout grid = new GridLayout((state.findMax() + 2) / 3, 3);
		gamePanel.setLayout(grid);
		for(int i = 0; i <= state.findMax(); i++)
		{
			Card cardi = state.getCard(i);
			if(cardi != null)
			{
				System.out.print(cardi + ";");
				ImageIcon u = cardi.uPNG;
				ImageIcon c = cardi.cPNG;
				if(cardi.click.clicked)
					u = cardi.cPNG;
				JButton b = new JButton(u);
				b.addActionListener(cardi.click);
				b.setSelectedIcon(c);
				gamePanel.add(i+"", b);
			}
		}
		System.out.println(state.cardsOut());
		statusLabel.setText(state.status);
		frame.add(southPanel, border.SOUTH);
		southPanel.setLayout(southBorder);
		southPanel.add(statusLabel, southBorder.NORTH);
		lastSetPanel.setLayout(lastGrid);
		southPanel.add(lastSetPanel, southBorder.SOUTH);
		southPanel.setVisible(true);
		frame.setVisible(true);
		updateLabels();
	}
	
	public void updateLabels() 
	{
		statusLabel.setText(state.status);
		scores.setText("Player 1: "+state.score1+"  Player 2: "+state.score2 );
		cardsLeft.setText(" Cards left: "+state.deck.getCardsLeft());
	}
	
	public static void main(String[] args)
	{
		JFrame fred = new JFrame();
		JPanel panel = new JPanel();
		BorderLayout border = new BorderLayout();
		fred.setLayout(border);
		fred.add(panel, border.CENTER);
		panel.setLayout(new GridLayout(7, 3));		
		for(int i = 0; i < 10; i++)
		{
			JLabel label = new JLabel(i + "");
			panel.add(label);
		}
		fred.setVisible(true);
	}
}
