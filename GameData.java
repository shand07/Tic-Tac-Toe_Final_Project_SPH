import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * CS 131 Final Project
 * @author shand
 * 03/14/23
 * 
 * This will be our actual game data itself.
 * There will be methods for our start button, for setting the size of our UI, and for the messages 
 * we will post across the UI itself.
 *
 */

public class GameData 
{
	private StackPane pane;
	private Label message;
	private Button startGameButton;
	
	public GameData()//The default constructor. We will use this to set the size of the game board.
	{
		pane = new StackPane();//This Sets the size of the actual game Pane itself
		pane.setMinSize(InterfaceData.UI_WIDTH, InterfaceData.GAME_DATA_HEIGHT);
		pane.setTranslateX(InterfaceData.UI_WIDTH/2);
		pane.setTranslateY(InterfaceData.GAME_DATA_HEIGHT/2);
		
		message = new Label("Tic-Tac-Toe");//This is the label of our game at the top setters the alignment and font
		message.setMinSize(InterfaceData.UI_WIDTH, InterfaceData.GAME_DATA_HEIGHT);
		message.setFont(Font.font(24));
		message.setAlignment(Pos.CENTER);
		message.setTranslateY(-20);
		pane.getChildren().add(message);
		
		startGameButton = new Button("Start New Game");//This is the start game button has setters for size aswell
		startGameButton.setMinSize(135, 30);
		startGameButton.setTranslateY(20);
		pane.getChildren().add(startGameButton);
		
	}
	
	public StackPane getStackPane()//Getter for our pane size
	{
		return pane;
	}
	
	public void updateMessage(String message)//Setter for the messages on our UI like Start Game, Player Turn, or Winner/Tie
	{
		this.message.setText(message);
	}
	
	public void showStartButton()//This Method will show the start button
	{
		startGameButton.setVisible(true);
	}
	
	public void hideStartButton()//This Method will hide the start button
	{
		startGameButton.setVisible(false);
	}
	
	public void setStartButtonOnAction(EventHandler<ActionEvent> onAction)//Event handler for our start button, this will give the button itself funcionality
	{
		startGameButton.setOnAction(onAction);
	}
}
