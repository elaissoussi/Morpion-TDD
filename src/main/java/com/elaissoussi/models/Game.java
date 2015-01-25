package com.elaissoussi.models;



public class Game
{
	public static final String LINE_SEPARATOR = "\n";

	private static final String Column_SEPARATOR = "|";

	private final String[][] morpionGrid;
	private final Player firstPlayer;
	private final Player secondPlayer;

	public Game(final String gameDimensions, final Player firstPlayer, final Player secondPlayer)
	{
		final int[] gridDimensions = parseDimenions(gameDimensions);
		morpionGrid = new String[gridDimensions[0]][gridDimensions[1]];
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
	}



	public String report()
	{
		final String remindedGames = getRemindedGames();

		// no reminds games
		if (remindedGames == null)
		{
			final Player winner = getWinner();

			//no winner
			if (winner == null)
			{
				return "Game Over, equality";
			}
			// get the winner
			else
			{
				return "Game Over, " + winner.getName() + " is a winner";
			}

		}
		// remind games
		else
		{
			return remindedGames;
		}
	}

	private String getRemindedGames()
	{
		final int boxSum = morpionGrid.length * morpionGrid.length;
		final int numberOfMoveForFirstPlayer = firstPlayer.getMyMoves().size();
		final int numberOfMoveForSecondtPlayer = secondPlayer.getMyMoves().size();
		final int remainingGames = boxSum - numberOfMoveForFirstPlayer - numberOfMoveForSecondtPlayer;

		if (remainingGames == 0 || getWinner() != null)
		{
			return null;
		}
		else
		{
			final int remainForFirstPlayer = remainingGames / 2 + 1;
			final int remainForSecondPlayer = remainingGames / 2;

			return remainForFirstPlayer + " games for " + firstPlayer.getName() + ", " + remainForSecondPlayer + " games for "
					+ secondPlayer.getName();
		}
	}

	private Player getWinner()
	{
		final int girdLength = morpionGrid.length;
		String winnerShape = null;
		boolean isWin = false;

		// lines
		for (int i = 0; i < girdLength; i++)
		{
			for (int j = 0; j < girdLength - 1; j++)
			{
				if (morpionGrid[i][j] != null && morpionGrid[i][j + 1] != null)
				{
					if (!morpionGrid[i][j].equals(morpionGrid[i][j + 1]))
					{
						isWin = false;
						break;
					}

					isWin = true;
					winnerShape = morpionGrid[i][j];
				}
			}
		}

		//columns
		if (isWin == false)
		{
			for (int j = 0; j < girdLength; j++)
			{
				for (int i = 0; i < girdLength - 1; i++)
				{
					if (morpionGrid[i][j] != null && morpionGrid[i + 1][j] != null)
					{
						if (!morpionGrid[i][j].equals(morpionGrid[i + 1][j]))
						{
							isWin = false;
							break;
						}
						isWin = true;
						winnerShape = morpionGrid[j][i];
					}
				}
			}
		}

		// diameter from 0
		if (isWin == false)
		{
			for (int i = 0; i < girdLength - 1; i++)
			{
				for (int j = 0; j < girdLength - 1; j++)
				{
					if (i == j && morpionGrid[i][j] != null)
					{
						if (!morpionGrid[i][j].equals(morpionGrid[i + 1][j + 1]))
						{
							isWin = false;
							break;
						}

						isWin = true;
						winnerShape = morpionGrid[i][i];
					}

				}
			}
		}


		// diameter from last
		if (isWin == false)
		{
			for (int i = girdLength - 1; i > 0; i--)
			{
				for (int j = girdLength - 1; j > 0; j--)
				{
					if (i == j && morpionGrid[i][j] != null)
					{
						if (!morpionGrid[i][j].equals(morpionGrid[i - 1][j - 1]))
						{
							isWin = false;
							break;
						}
						isWin = true;
						winnerShape = morpionGrid[i][i];
					}
				}
			}
		}

		if (isWin)
		{
			return getPlayerBySahp(winnerShape);
		}
		return null;
	}


	private Player getPlayerBySahp(final String winnerShape)
	{
		if (firstPlayer.getShape().equals(winnerShape))
		{
			return firstPlayer;
		}
		else
		{
			return secondPlayer;
		}
	}


	public String[][] getMorpionGrid()
	{
		return morpionGrid;
	}


	private int[] parseDimenions(final String gameDimensions)
	{
		final String[] splitedDimentions = gameDimensions.split("x");
		// 2 dimensions
		final int[] gridDimensions = new int[2];
		// width
		gridDimensions[0] = Integer.parseInt(splitedDimentions[0]);
		// Height
		gridDimensions[1] = Integer.parseInt(splitedDimentions[1]);

		return gridDimensions;
	}



	public String display()
	{
		final StringBuilder formatedGrid = new StringBuilder();
		
		final int gridLength = morpionGrid.length;
		
		for (int i = 0; i < gridLength; i++)

			for (int j = 0; j < gridLength; j++)
			{
				formatedGrid.append(morpionGrid[i][j]);

				if (j < gridLength - 1)
				{
					formatedGrid.append(Column_SEPARATOR);
				}
			}

			formatedGrid.append(LINE_SEPARATOR);
		}

		return formatedGrid.toString();
	}
}
