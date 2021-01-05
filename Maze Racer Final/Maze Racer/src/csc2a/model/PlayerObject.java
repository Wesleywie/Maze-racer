package csc2a.model;


import csc2a.designpatterns.iMovement;
import csc2a.designpatterns.iRenderVisitor;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class PlayerObject extends GameObject implements iMovement
{
	public Image Player;
	
	public PlayerObject()
	{
		super();
		Player = new Image("file:data/Images/PlayerChar.png");
	}
	
	public Image getPlayerImage()
	{
		return this.Player;
	}
	
	public PlayerObject(int x, int y)
	{
		super(x,y);
		Player = new Image("file:data/Images/PlayerChar.png");
	}
	
	
	public Point2D getPlayerPosition()
	{
		return super.getLocation();
	}

	@Override
	public void accept(iRenderVisitor visitor) 
	{
		visitor.render(this);
	}

	@Override
	public void move(int A, String C) 
	{
		
		int iMove = A;
		
		Point2D PL;
		
		switch(iMove)
		{
		case 1:			
			//UP
			if(C == "f")
			{
				PL = new Point2D(super.getXLocation(),super.getYLocation() - 50);
				
				super.setLocation(PL);
			}
			else if(C == "x")
			{
				PL = new Point2D(super.getXLocation(),super.getYLocation() - 50);
				
				super.setLocation(PL);
			}


		break;
		
		case 2:
			//Right
			if(C == "f")
			{
				PL = new Point2D(super.getXLocation() + 50,super.getYLocation());
				super.setLocation(PL);	
			}
			else if(C == "x")
			{
				PL = new Point2D(super.getXLocation() + 50,super.getYLocation());
				
				super.setLocation(PL);
			}

		break;
		
		case 3:
			//Down
			if(C == "f")
			{
				PL = new Point2D(super.getXLocation(),super.getYLocation() + 50);
				super.setLocation(PL);
			}
			else if(C == "x")
			{
				PL = new Point2D(super.getXLocation(),super.getYLocation() + 50);
				
				super.setLocation(PL);
			}

		break;
		
		case 4:
			//Left
			if(C == "f")
			{
				PL = new Point2D(super.getXLocation() - 50,super.getYLocation());
				super.setLocation(PL);
			}
			else if(C == "x")
			{
				PL = new Point2D(super.getXLocation() - 50,super.getYLocation());
				
				super.setLocation(PL);
			}

		break;
		}
		
		
	}



}
