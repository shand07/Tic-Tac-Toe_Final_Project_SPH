import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
/**
 * CS 131 Final Project
 * @author shand
 * 03/26/23
 * Here we will create the actual squares that will be used on the UI itself to separate the the
 * spots where we will place X's and O's during the game.
 * There will be nested forloops to check for winners, to change the turn, aswell as a method to see if 
 * the game is over.
 *
 */

public class GameSquares 
{
	private StackPane pane;
	private GameData gameData;
	private Square[][] mySquares = new Square[3][3];//Array for Placing Squares
	
	private char playerTurn = 'X';
	private boolean isGameOver = false;
	
	public GameSquares(GameData gameData)//Default Constructor, we will be passing GameData to return data back to the game data class 
	{
		this.gameData = gameData;
		pane = new StackPane();
		pane.setMinSize(InterfaceData.UI_WIDTH, InterfaceData.TILE_BOARD_HEIGHT);
		pane.setTranslateX(InterfaceData.UI_WIDTH/2);
		pane.setTranslateY(InterfaceData.TILE_BOARD_HEIGHT/2 +InterfaceData.GAME_DATA_HEIGHT);
		
		addMySquares();
	}
	
	private void addMySquares() //Method for Placing our Squares
	{
		for(int row = 0; row < 3; row++)
		{
			for(int col = 0; col < 3; col++)
			{
				Square square = new Square();
				square.getStackPane().setTranslateX(col*100-100);
				square.getStackPane().setTranslateY(row*100-100);
				pane.getChildren().add(square.getStackPane());
				mySquares[row][col] = square;
				
			}
		}
		
	}
	
	public void newGame()//This method will clear the board after a winner or tie has been found
	{
		
		isGameOver = false;
		playerTurn = 'X';
		for(int row = 0; row < 3; row++)
		{
			for(int col = 0; col < 3; col++)
			{
				mySquares[row][col].setLabel("");//Goes through the forloop and resets the value of each square to null
			}
		}
		
	}
	
	public void changeTurn()//This Method Changes who's turn it is
	{
		if(playerTurn =='X')
		{
			playerTurn = 'O';
		}
		else
		{
			playerTurn = 'X';
		}
		gameData.updateMessage("Player " + playerTurn + "'s turn");//Tells who's turn it is
	}
	
	public String getTurn()//Getter for Who's turn it is
	{
		return String.valueOf(playerTurn);
	}

	public StackPane getStackPane()//Getter for the pane we will be using in our game squares
	{
		return pane;
	}
	
	public void isWinner()//Method for checking for winner
	{
		isRowsWinner();
		isColsWinner();
		isDiagLeftToRightWinner();
		isDiagRightToLeftWinner();
		isTie();
	}
	
	private void isRowsWinner() //Method for Checking Row Winner
	{
		if(!isGameOver)
		{
			
			for(int row = 0; row < 3; row++)//ForLoop to run through each Row
			{
				if(mySquares[row][0].getLabel().equals(mySquares[row][1].getLabel()) && 
						mySquares[row][0].getLabel().equals(mySquares[row][2].getLabel()) &&
						!mySquares[row][0].getLabel().isEmpty())
				{
					String winner = mySquares[row][0].getLabel();
					gameOver(winner);
					return;
				}
			}
		}
	}

	private void isColsWinner() //Method for Checking Column Winner
	{
		if(!isGameOver)
		{
			
			for(int col = 0; col < 3; col++)//ForLoop to run through each Column
			{
				if(mySquares[0][col].getLabel().equals(mySquares[1][col].getLabel()) && 
						mySquares[0][col].getLabel().equals(mySquares[2][col].getLabel()) &&
						!mySquares[0][col].getLabel().isEmpty())
				{
					String winner = mySquares[0][col].getLabel();
					gameOver(winner);
					return;
				}
			}
		}
	}

	private void isDiagLeftToRightWinner() //Method for Checking Diagonal Left to Right Winner
	{
		if(!isGameOver)
		{
			if(mySquares[0][0].getLabel().equals(mySquares[1][1].getLabel()) &&
					mySquares[0][0].getLabel().equals(mySquares[2][2].getLabel()) &&
							!mySquares[0][0].getLabel().isEmpty())
					{
						String winner = mySquares[0][0].getLabel();
						gameOver(winner);
						return;
					}
		}
		
	}

	private void isDiagRightToLeftWinner() //Method for Checking Diagonal Right to Left Winner
	{
		if(!isGameOver)
		{
			if(mySquares[0][2].getLabel().equals(mySquares[1][1].getLabel()) &&
					mySquares[0][2].getLabel().equals(mySquares[2][0].getLabel()) &&
							!mySquares[0][2].getLabel().isEmpty())
					{
						String winner = mySquares[0][2].getLabel();
						gameOver(winner);
						return;
					}
		}
		
	}
	
	private void isTie() //Method for Displaying game is a tie
	{
		if(!isGameOver)
		{
			for(int row = 0; row < 3 ; row++)
			{
				for(int col = 0; col < 3; col++)
				{
					if(mySquares[row][col].getLabel().isEmpty())
					{
						return;
					}
				}
			}
		
				isGameOver = true;//Sets Game over to true
				gameData.updateMessage("The Game is a Tie");
				gameData.showStartButton();//Allows for new game
			
		}
		
		
	}
	
	private void gameOver(String winner)//Method for ending the game
	{
		isGameOver = true;
		gameData.updateMessage("Player" +winner + " wins!");
		gameData.showStartButton();//Allows for new game
	}


	private class Square//This Square class will have the data for the actuals squares on the board
	{
		
		private StackPane pane;
		private Label label;
		
		public Square()
		{
			
			pane = new StackPane();//Our Actual Squares
			pane.setMinSize(100, 100);
			
			Rectangle border = new Rectangle();//This will create the borders on each square
			border.setHeight(100);
			border.setWidth(100);
			border.setFill(Color.TRANSPARENT);
			border.setStroke(Color.BLACK);
			pane.getChildren().add(border);//Adds the border to the Squares themselves
			
			label = new Label("");//Labels for each tile X or O
			label.setAlignment(Pos.CENTER);
			label.setFont(Font.font(24));
			pane.getChildren().add(label);//Adds Label to each Square
			
			pane.setOnMouseClicked(event ->{//This will allow tiles to be changes to X or O
				if(label.getText().isEmpty() && !isGameOver)
				{
					label.setText(getTurn());//Sets X or O if square is empty
					changeTurn();//Changes Turn
					isWinner();//Checks for Winner
				}
			});
			
		}
		
		public StackPane getStackPane()//Getter for Pane inside Square Class
		{
			return pane;
		}
		
		public String getLabel()//Getter for our label
		{
			return label.getText();
		}
		
		public void setLabel(String label)//Setter for our label
		{
			this.label.setText(label);
		}
	}
}
