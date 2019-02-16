/**
 * @author Louis DESPLANCHE
 */
public class GameEngine
{
    private Room aCurrentRoom;
    private Parser aParser;
    private UserInterface aGui;
    
    /**
     * Contructeur de Game
     */
    public GameEngine()
    {
        this.aCurrentRoom = null;
        this.createRooms();
        aParser = new Parser();
    }
    
    public void setGUI(UserInterface userInterface)
    {
        aGui = userInterface;
        printWelcome();
        aGui.showImage(aCurrentRoom.getImageName());
    }

    /**
     * Affiche le message d'accueil du jeu
     */
    private void printWelcome()
    {
        aGui.println("Bienvenue dans Antisophia");
        aGui.println("World of Zuul is a new, incredibly boring adventure game.");
        aGui.println("Type 'help' if you need help.");
        aGui.println(" ");
        printLocationInfo();
    }

    /**
     * Affiche l'aide
     */
    private void printHelp()
    {
        aGui.println("You are lost. You are alone.");
        aGui.println("You wander around at the university");
        aGui.println(" ");
        aGui.println("Your command words are:");
        aGui.println(aParser.getCommandList());
    }

    /**
     * Créé les pièces de la carte
     */
    private void createRooms()
    {
        Room.CreateRoom();
        aCurrentRoom = Room.getRoom("outside");
        
    }

    /**
     * Traite les commandes précédemment parser
     * @param commande
     */
    private boolean processCommand(final Command pCmd)
    {
        if(pCmd.isUnknown()) {
            aGui.println("I don't know what you mean...");
            return false;
        }
        if(pCmd.getCommandWord().equals("go")) this.goRoom(pCmd);
        else if(pCmd.getCommandWord().equals("help")) this.printHelp();
        else if(pCmd.getCommandWord().equals("quit")) this.quit(pCmd);
        else if(pCmd.getCommandWord().equals("look")) look();
        else if(pCmd.getCommandWord().equals("eat")) eat();
        else
        {
            aGui.println("I don't know what you mean...");
            return true;
        }

        return false;
    }
    
    public void interpretCommand(String commandLine) 
    {
        aGui.println(commandLine);
        processCommand(aParser.getCommand(commandLine));
    }

    /**
     * Quitte le jeu
     */
    private void quit(final Command pCmd)
    {
        if(pCmd.hasSecondWord())
        {
            aGui.println("Quit what ?");
        }
        endGame();
    }

    /**
     * Change de salle
     */
    private void goRoom(final Command pCmd)
    {
        if(!pCmd.hasSecondWord()) 
        {
            aGui.println("Go where ?");
            return;
        }

        Room vNextRoom = null;
        String vDirection = pCmd.getSecondWord();

        if(vDirection.equals("north") && aCurrentRoom.getExit("north") != null) vNextRoom = aCurrentRoom.getExit("north");
        else if(vDirection.equals("east") && aCurrentRoom.getExit("east") != null) vNextRoom = aCurrentRoom.getExit("east");
        else if(vDirection.equals("south") && aCurrentRoom.getExit("south") != null) vNextRoom = aCurrentRoom.getExit("south");
        else if(vDirection.equals("west") && aCurrentRoom.getExit("west") != null) vNextRoom = aCurrentRoom.getExit("west");
        else if(vDirection.equals("top") && aCurrentRoom.getExit("top") != null) vNextRoom = aCurrentRoom.getExit("top");
        else if(vDirection.equals("down") && aCurrentRoom.getExit("down") != null) vNextRoom = aCurrentRoom.getExit("down");
        else if(vDirection.equals("north") || vDirection.equals("east") || vDirection.equals("south") || vDirection.equals("west") || vDirection.equals("top") || vDirection.equals("down")) System.out.println("There is no door !"); 

        else aGui.println("Unknown direction !");

        if(vNextRoom != null)
        {
            this.aCurrentRoom = vNextRoom;
            if(aCurrentRoom.getImageName() != null)
                aGui.showImage(aCurrentRoom.getImageName());
            printLocationInfo();
        }
    }

    /**
     * Affiche les pièces disponibles
     */
    private void printLocationInfo()
    {
        aGui.println("You are " + aCurrentRoom.getDescription());
        aGui.print(aCurrentRoom.getExitString());
        aGui.println("");
    }

    private void look()
    {
        aGui.println(aCurrentRoom.getLongDescription());
    }

    private void eat()
    {
        aGui.println("You have eaten now and you are not hungry any more.");
    }
    
    private void endGame()
    {
        aGui.println("Thank you for playing.  Good bye.");
        aGui.enable(false);
    }

} // Game
