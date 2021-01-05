package csc2a.designpatterns;

import csc2a.model.Maze;
import csc2a.model.PlayerObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * ConcreteVisitor class
 * Used to visit each GameObject and render them onto the GameCanvas
 * @author  <YOUR DETAILS HERE>
 *
 */
public class RenderGameObjectVisitor implements iRenderVisitor{
	
	//Attributes
	private GraphicsContext gc;
	
	public GraphicsContext getGraphicsContext()
	{
		return gc;
	}
	
	/**
	 * Mutator for the GraphicsContext from the GameCanvas
	 * Used to set gc so the Visitor can draw things on the Canvas
	 * @param gc the GraphicsContext from the GameCanvas
	 */
	public void setGraphicsContext(GraphicsContext gc) {
		this.gc = gc;
	}

	@Override
	public void render(PlayerObject PO) 
	{
		double x = PO.getXLocation();
		double y = PO.getYLocation();	
		
		gc.drawImage(PO.getPlayerImage(), x, y, 50, 30);
		System.out.println("Drawing imple");
		
	}

	@Override
	public void render(Maze M) 
	{

		}
		
	}
	
	

