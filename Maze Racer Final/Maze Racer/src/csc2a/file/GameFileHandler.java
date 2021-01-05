package csc2a.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import csc2a.model.GameObject;
import csc2a.model.PlayerObject;

import java.util.StringTokenizer;
import csc2a.model.Maze;


/**
 * 
 * Class to handle all interactions with files
 * @author <YOUR DETAILS HERE>
 *
 */
public class GameFileHandler
{
	public static PlayerObject PO = null;
	public static Maze maze = new Maze();
	public int iLevel = 0;
	private int iLoop = 0;
	
	public GameFileHandler()
	{

	}
		

	public static void ReadPlayerFile(File PlayerFile)
	{

		Scanner scPlayer = null;
		
		try
		{
			scPlayer = new Scanner(PlayerFile);	
			
			while(scPlayer.hasNext())
			{
			String PlayerLine = scPlayer.nextLine();
			
			StringTokenizer PlayerTokenizer = new StringTokenizer(PlayerLine);
			String Coordinates = PlayerTokenizer.nextToken();
			
			StringTokenizer Co = new StringTokenizer(Coordinates, ":");
			int x = Integer.parseInt(Co.nextToken());
			int y = Integer.parseInt(Co.nextToken());
			
				
			PO = new PlayerObject(x,y);
			
			}
			
			
		}
		catch(FileNotFoundException ex)
		{
			System.err.println("File Not Found");
		}
		finally
		{
			if(scPlayer != null)
			{
				scPlayer.close();
			}
		}
		
		
	}
	
	public GameObject getPlayerObject()
	{
		
		return this.PO;
		
	}
	
	public int getPlayerX()
	{
		
		return (int)this.PO.getXLocation();
		
	}
	
	public int getPlayerY()
	{
		
		return (int)this.PO.getYLocation();
		
	}
	
	
	
	public static void setPlayerObject(int A, String S) //update
	{
		PO.move(A, S);

		System.out.println("PO udated");
		
	}
	
	public Maze getMazeObject()
	{
		
		return this.maze;
		
	}
	
	public void setLevel(int iL)
	{
		iLevel = iL;
	}
	
	public int getLevel()
	{
		return this.iLevel;
	}
	
	
	public void readMap (File MapFile, int iLevel)
	{
		
		Scanner scMap = null;
		
		
		
		switch(iLevel)
		{
		case 1:		
			iLoop = 10;

		break;
		
		case 2:
			iLoop = 14;

		break;

		}
		
		try
		{
			scMap = new Scanner(MapFile);
			
			while(scMap.hasNext())
			{
				for(int i = 0; i < iLoop; i++)
				{
					
					maze.Maze[i] = scMap.next();
					
					System.out.println("Mapped" + i);
					System.out.println(maze.Maze[i]);
				}
					
				
			}
			
			
		}
		catch(FileNotFoundException ex)
		{
			System.err.println("File Not Found");
		}
		finally
		{
			if(scMap != null)
			{
				scMap.close();
			}
		}		
		

		
	}
	

	
}
