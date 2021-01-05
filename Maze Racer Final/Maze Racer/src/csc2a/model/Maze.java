package csc2a.model;

import csc2a.designpatterns.iRenderVisitor;
import csc2a.designpatterns.iRenderable;
import javafx.scene.image.Image;

public class Maze extends GameObject
{
	
	public String Maze[] = new String[15];
	private Image floor, wall, finish;
	
	public Maze()
	{
		floor = new Image("file:data/Images/floor.png");
		wall = new Image("file:data/Images/wall.png");
		finish = new Image("file:data/Images/Portal.png");
	}
	
	public Image getFloor()
	{
		return this.floor;
	}
	
	public Image getWall()
	{
		return this.wall;
	}
	
	public Image getFinsih()
	{
		return this.finish;
	}
	
	public String getMap(int x, int y)
	{
		String index = Maze[y].substring(x, x + 1 ); //y pos ... rows .. gets x pos 
		return index; //used to see which tile must be placed down
	}

	@Override
	public void accept(iRenderVisitor visitor) {
		visitor.render(this);
		
	}
	
	
}
