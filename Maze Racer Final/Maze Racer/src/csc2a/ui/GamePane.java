package csc2a.ui;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import csc2a.file.GameFileHandler;
import csc2a.model.GameObject;
import csc2a.model.Maze;
import csc2a.model.PlayerObject;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.awt.event.KeyListener;

import javafx.scene.layout.StackPane;
import java.io.FileInputStream;


/**
 * 
 * GamePane provides a custom container to manage all game interactions
 * and host the GameCanvas
 * @author <Wesley Wienand>
 *
 */
public class GamePane extends StackPane
{
	
	//Attributes
	private GameCanvas canvas = null; //You need the canvas so the visitor can draw on it
	private GameFileHandler GFL = new GameFileHandler();
	public static Maze maze = new Maze();
	private int Moves = 0;

	
	/**
	 * Default constructor
	 */
	public GamePane() {
		super();		
		
		canvas = new GameCanvas();	
		canvas.widthProperty().bind(this.widthProperty());
		canvas.heightProperty().bind(this.heightProperty());
		
		/* TODO: Create MenuBar with Menu and MenuItem */
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Game");
		menuBar.getMenus().add(menu);
		
		MenuItem mi1 = new MenuItem("New Game");
		MenuItem mi2 = new MenuItem("Instructions");
		menu.getItems().addAll(mi1, mi2);	
		
		mi1.setOnAction(e ->
		{
			File file = new File("data/New Game.txt");	
			GFL.ReadPlayerFile(file);
			setUpGame();
			
			
		});
		
		mi2.setOnAction(e ->
		{
			String Temp = "1. Controls... UP, DOWN, LEFT, RIGHT " + '\n' +
					      "2. You have 20 moves for Level 1 and 35 moves for Level 2" + '\n' +
					      "3. You must navigate through the maze within in the set moves or else you lose" + '\n' +
					      "4. If you manage to go through the portal you win the game and your get a score stored on a text file" + '\n' + 
					      "5. Enjoy Maze Race :)";
			
			Alert alertInst = new Alert(Alert.AlertType.INFORMATION);
			alertInst.setTitle("Maze Racer");
			alertInst.setHeaderText(Temp);
			alertInst.setContentText("Click new Game to start game");
			alertInst.showAndWait();
			
		});
		
		
		this.setOnKeyPressed((event) -> 
		{		
			
			if(event.getCode() == KeyCode.UP)
			{
				System.out.println("UP");
				maze = GFL.getMazeObject();
				
				int xPOS = GFL.getPlayerX();
				int yPOS = GFL.getPlayerY() - 50;
				
				String S = maze.getMap(Math.round(xPOS/50), Math.round(yPOS/50));
				
				System.out.println(S + "  JJJJ");
				
				if(S.equals("f"))
				{
					GFL.setPlayerObject(1, "f");
					Moves++;
					update(Moves);
					
				}
				else if(S.equals("x"))
				{
					GFL.setPlayerObject(1, "x");
					EndGame();
				}
				
				
				
			}
			else if(event.getCode() == KeyCode.RIGHT)
			{
				System.out.println("Right");
				maze = GFL.getMazeObject();
				
				int xPOS = GFL.getPlayerX() + 50;
				int yPOS = GFL.getPlayerY();
				
				System.out.println(xPOS + " " + yPOS);
				
				String S = maze.getMap(Math.round(xPOS/50), Math.round(yPOS/50));
			
				System.out.println(S + "  JJJJ");
				
				if(S.equals("f"))
				{
					GFL.setPlayerObject(2, "f");
					Moves++;
					update(Moves);
					
				}
				else if(S.equals("x"))
				{
					GFL.setPlayerObject(2, "x");
					EndGame();
				}			
				
				
			}
			else if(event.getCode() == KeyCode.DOWN)
			{
				System.out.println("Down ");
				maze = GFL.getMazeObject();
				
				int xPOS = GFL.getPlayerX();
				int yPOS = GFL.getPlayerY() + 50;
				
				String S = maze.getMap(Math.round(xPOS/50), Math.round(yPOS/50));
				
				System.out.println(S + "  JJJJ");
				
				if(S.equals("f"))
				{
					GFL.setPlayerObject(3, "f");
					Moves++;
					update(Moves);
					
				}
				else if(S.equals("x"))
				{
					GFL.setPlayerObject(3, "x");	
					EndGame();
				}
				
				
			}
			else if(event.getCode() == KeyCode.LEFT)
			{
				System.out.println("Left");
				maze = GFL.getMazeObject();
				
				int xPOS = GFL.getPlayerX() - 50;
				int yPOS = GFL.getPlayerY();
				
				String S = maze.getMap(Math.round(xPOS/50), Math.round(yPOS/50));
				
				System.out.println(S + "  JJJJ");
				
				if(S.equals("f"))
				{
					GFL.setPlayerObject(4, "f");
					Moves++;
					update(Moves);
					
				}	
				else if(S.equals("x"))
				{
					GFL.setPlayerObject(4, "x");
					EndGame();
				}
				
				
			}
			
		});	
		
		//Background 
		Image Background = new Image("file:data/Images/BackGround.jpg");
		
		BackgroundSize bgSize = new BackgroundSize(0, 0, false, false, false, true);
		
		BackgroundImage bgImg = new BackgroundImage(Background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bgSize);
		
		Background bg = new Background(bgImg);
		
		BorderPane mPane = new BorderPane();
		mPane.setTop(menuBar);
		mPane.setBottom(canvas);

		mPane.setBackground(bg);
		
		getChildren().add(mPane);
		
		
		
		/* TODO: Finish setting up your GamePane */
	}	
	
	public void setUpGame()
	{
		canvas.setUpGame();
		
	}
	
	public void update(int iMoves)
	{
		canvas.update(iMoves);
		System.out.println("Updated position");
		
	}
	
	
	public void EndGame()
	{
		canvas.EndGame();
	}

	

	
}
