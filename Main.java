import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

/**
 * CS 131 Final Project
 * @author shand
 * Here will be the brain of our game.
 * We will have methods that actually pass the data we created from inside the GameSquares and GameData
 * classes aswell as a main method to actually run the program itself.
 * 03/14/2023
 *
 */

public class Main extends Application//Extending the Application class from JavaFX
{

	private GameData gameData;
	private GameSquares gameSquares;
	
	public void start(Stage primaryStage)//This method with handle exceptions for our game board itself it will set the size of our actual UI itself based on the constants in InterfaceData
	{
		try
		{
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,InterfaceData.UI_WIDTH,InterfaceData.UI_HEIGHT);
			initLayout(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void initLayout(BorderPane root) //This method will initialize the layout of our game board.
	{
		initGameData(root);
		initGameSquares(root);
		
	}

	private void initGameSquares(BorderPane root)//This Method takes all of our Data from GameSquares and puts it in the game board and initializes it
	{
		gameSquares = new GameSquares(gameData);
		root.getChildren().add(gameSquares.getStackPane());
		
	}

	private void initGameData(BorderPane root) //This method uses the data from gamesquares to give the start button functionality
	{
		gameData = new GameData();
		gameData.setStartButtonOnAction(newGameStart());
		root.getChildren().add(gameData.getStackPane());
		
	}
	
	private EventHandler<ActionEvent> newGameStart()//Event Handler for Start Button
	{
		return new EventHandler<ActionEvent>()
		{
			
			public void handle(ActionEvent e)
			{
				gameData.hideStartButton();//Hides Start Button After starting game
				gameData.updateMessage("Player X's Turn");//Puts Player X's turn on the screen
				gameSquares.newGame();
				
			}
		};
	}

	public static void main(String[] args)//Main Method itself
	{
		launch(args);
	}

}
