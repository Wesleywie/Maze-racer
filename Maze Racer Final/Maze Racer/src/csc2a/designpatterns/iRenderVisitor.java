package csc2a.designpatterns;

import csc2a.model.Maze;
import csc2a.model.PlayerObject;

/**
 * 
 * AbstractVisitor interface
 * Used to define all of the render functions for your different GameObjects
 * @author  <YOUR DETAILS HERE>
 *
 */
public interface iRenderVisitor {
	/* TODO: render(YourGameObjectA a); method */
	void render(PlayerObject PO); 
	
	void render(Maze M);
	

}
