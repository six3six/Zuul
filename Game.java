public class Game
{
	private UserInterface aGui;
	private GameEngine aEngine;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        aEngine = new GameEngine();
    }
    
    public static void main(String[] args)
    {
        Game vGame = new Game();
    }
}
