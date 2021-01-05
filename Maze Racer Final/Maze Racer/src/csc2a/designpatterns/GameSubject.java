package csc2a.designpatterns;

public interface GameSubject 
{
	public void attach(Observer o);
	public void detach(Observer o);
	public void notifyObserver();
}
