package com.elaissoussi;

import com.elaissoussi.models.Game;
import com.elaissoussi.models.Player;


public class Morpion
{
	final private Game game;
	private final Player firstPlayer;
	private final Player secondPlayer;

	public Morpion(final String gameDimensions, final String firstPalyerDescription, final String secondPalyerDescription)
	{
		firstPlayer = new Player(firstPalyerDescription);
		secondPlayer = new Player(secondPalyerDescription);
		game = new Game(gameDimensions, firstPlayer, secondPlayer);
	}

	public void play(final String playerName, final String targetBox)
	{
		final String[][] grid = game.getMorpionGrid();

		if (playerName.equals(firstPlayer.getName()))
		{
			firstPlayer.play(grid, targetBox);
		}
		else
		{
			secondPlayer.play(grid, targetBox);
		}
	}

	public String report()
	{
		return game.report();
	}

	public String display()
	{
		return game.display();
	}

}
