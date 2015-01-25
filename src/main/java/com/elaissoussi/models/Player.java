package com.elaissoussi.models;

import java.util.ArrayList;
import java.util.List;

import com.elaissoussi.exceptions.BoxAlreadySelectedException;


public class Player
{

	private final String name;
	private final String shape;
	private final List<int[]> myMoves;

	public List<int[]> getMyMoves()
	{
		return myMoves;
	}

	public Player(final String palyerDescription)
	{
		final String[] splitedPlayer = palyerDescription.split(":");
		this.name = splitedPlayer[0];
		this.shape = splitedPlayer[1];
		myMoves = new ArrayList<int[]>();
	}

	public String getName()
	{
		return name;
	}

	public void play(final String[][] morpionGrid, final String moveTarget)
	{
		// parse move line and column
		final String[] splitedMove = moveTarget.split("x");
		final int columnNumber = Integer.parseInt(splitedMove[0]);
		final int lineNumber = Integer.parseInt(splitedMove[1]);

		// check to box
		if (morpionGrid[columnNumber][lineNumber] != null)
		{
			throw new BoxAlreadySelectedException();
		}
		//play
		morpionGrid[columnNumber][lineNumber] = this.shape;
		final int[] move = new int[]
		{ columnNumber, lineNumber };
		myMoves.add(move);
	}

	public String getShape()
	{
		return shape;
	}
}
