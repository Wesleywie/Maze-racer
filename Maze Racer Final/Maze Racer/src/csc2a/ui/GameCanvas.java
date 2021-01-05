package csc2a.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import csc2a.designpatterns.RenderGameObjectVisitor;
import csc2a.file.GameFileHandler;
import csc2a.model.GameObject;
import csc2a.model.GameObjectContainer;
import csc2a.model.Maze;
import csc2a.model.PlayerObject;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import csc2a.file.GameFileHandler;

/**
 * 
 * Canvas used to render all of your GameObjects using the Visitor
 * This is the Client in the Visitor Design Pattern
 * @author  <YOUR DETAILS HERE>
 *
 */
public class GameCanvas extends Canvas{
	
	//Attributes
	RenderGameObjectVisitor visitor = new RenderGameObjectVisitor();
	Maze M = null;
	
	private GameFileHandler GFL = new GameFileHandler();
	
	/* TODO: Store all of your GameObjects (Using GameObjectContainers) here */
	private GameObjectContainer<GameObject> GOC = new GameObjectContainer<GameObject>();

	private ArrayList<GameObject> items = new ArrayList<>();
	
	private int moveLevel = 0;
	
	
	private int iLevel = 0;
	private int LevelOn = 0; 
    private File file = null;
	
	public void StoreGameObject(GameObject GO)
	{
		GOC.addGameObject(GO);
		System.out.println("GO created");
		redrawCanvas();
	}
	
	
	/**
	 * Default Constructor
	 */
	public GameCanvas() 
	{
		setHeight(1000);
		setWidth(1000);
	}
	
	public void setUpGame()
	{
		StoreGameObject(GFL.getPlayerObject());
		
	}
	
	public void update(int iMoves)
	{	
		
		if(iMoves == moveLevel)
		{
			DrawFinsih(2);
		}
		StoreGameObject(GFL.getPlayerObject());
		
	}
	
	
	public void EndGame()
	{
		StoreGameObject(GFL.getPlayerObject());
		DrawFinsih(1);
	}
	
	
	/**
	 * Method used to redraw the canvas whenever called
	 */
	public void redrawCanvas()
	{
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, 10*50, 10*50);
		//level prompt
		
		
		if(LevelOn == 0)
		{
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Level Select");
			dialog.setHeaderText("A - Level 1" + '\n' + "B - Level 2");
			dialog.setContentText("Which Level would you like to try");
			
			Optional<String> result = dialog.showAndWait();
			if (result.get().equals("A"))
			{
				iLevel = 1;
				file = new File("data/Game Levels/Level 1.txt");
				moveLevel = 20;
			}
			else if(result.get().equals("B"))
			{
				iLevel = 2;
				file = new File("data/Game Levels/Level 2.txt");
				moveLevel = 35;
			}
			
			LevelOn = 1;
		}	

		
		switch(iLevel)
		{
		case 1:		
			

			GFL.readMap(file, iLevel);
			M = GFL.getMazeObject();

			for(int r = 0; r < 10; r++) {
				for(int c = 0; c < 10; c++) {

					if(M.getMap(c, r).equals("w"))
					{
						gc.drawImage(M.getWall(),c*50, r*50, 50, 50);

					}
					else if(M.getMap(c, r).equals("f"))
					{
						gc.drawImage(M.getFloor(),c*50, r*50, 50, 50);
						
					}
					else if(M.getMap(c, r).equals("x"))
					{
						gc.drawImage(M.getFinsih(),c*50, r*50, 50, 50);
					}
					
				}
			}

		break;
		
		case 2:
			
			GFL.readMap(file, iLevel);
			M = GFL.getMazeObject();

			for(int r = 0; r < 14; r++) {
				for(int c = 0; c < 14; c++) {

					if(M.getMap(c, r).equals("w"))
					{
						gc.drawImage(M.getWall(),c*50, r*50, 50, 50);

					}
					else if(M.getMap(c, r).equals("f"))
					{
						gc.drawImage(M.getFloor(),c*50, r*50, 50, 50);

					}
					else if(M.getMap(c, r).equals("x"))
					{
						gc.drawImage(M.getFinsih(),c*50, r*50, 50, 50);
					}
					
				}
			}


		break;

		}

		

		
		/* TODO: Set Visitor's GraphicsContext */
		visitor.setGraphicsContext(gc);		
		
		
		/* TODO: Iterate through ALL GameObjects (Using GameObjectContainers) */

				for(GameObject z : GOC)
				{
					/* TODO: item accepts Visitor */
					z.accept(visitor);
					System.out.println("created");
				} 
		 

		
	}
	
	public void DrawFinsih(int iWinLose)
	{
			switch(iWinLose)
			{
			case 1:
				//Player wins
				//WritePlayerScore(PlayerScore) called
				int PlayerScore = 5;
				WritePlayerScore(PlayerScore);
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("Maze Racer");
			    alert.setHeaderText("Winner");
			    alert.setContentText("That was easy");
			    alert.showAndWait();
			    Platform.exit();
			break;
			
			case 2:
				//Player Loses
				int PlayerScoreL = -5;
				WritePlayerScore(PlayerScoreL);
				
				Alert alertL = new Alert(Alert.AlertType.INFORMATION);
				alertL.setTitle("Maze Racer");
				alertL.setHeaderText("Loser");
				alertL.setContentText("You ran out of moves");
				alertL.showAndWait();
			    Platform.exit();
			break;
			}

		
	}
	
	public void WritePlayerScore(int Score)
	{
		// textFile writing
		File textFile = new File("data/PlayerScore.txt");
		PrintWriter PlayerScore = null;
		
		try
		{
			PlayerScore = new PrintWriter(textFile);
			PlayerScore.println("Player Score: " + Score + " points"); 
		}
		catch(FileNotFoundException ex)
		{
			System.err.println("File Not Found");
		}
		finally
		{
			if(PlayerScore!=null) 
			{
				PlayerScore.close();	
			}

		}
	}
	
}
