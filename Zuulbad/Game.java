/**
 * @author Louis DESPLANCHE
 */
public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;
 
    /**
     * Contructeur de Game
     */
    public Game()
    {
        this.aCurrentRoom = null;
        this.createRooms();
        aParser = new Parser();
    }
    
    
    /**
     * Boucle principale du jeu
     */
    public void play()
    {
        this.printWelcome();
        boolean vFinished = false;
        while(!vFinished) vFinished = this.processCommand(this.aParser.getCommand());        
        System.out.println("Thank you for playing. Good bye.");
    }
    
    /**
     * Affiche le message d'accueil du jeu
     */
    private void printWelcome()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println(" ");
        printLocationInfo();
    }
    
    /**
     * Affiche l'aide
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university");
        System.out.println(" ");
        System.out.println("Your command words are:");
        System.out.println(aParser.getCommandList());
    }
    
    /**
     * Créé les pièces de la carte
     */
    private void createRooms()
    {
        Room vOutside = new Room("outside the main entrance of the university");
        Room vTheatre = new Room("in a lecture theatre");
        Room vPub = new Room("in the campus pub");
        Room vLab = new Room("in a computing lab");
        Room vOffice = new Room("in the computing admin office");
        
        vOutside.setExits(null, vTheatre, vLab, vPub, null, null);
        vTheatre.setExits(null, null, null, vOutside, null, null);
        vPub.setExits(null, vOutside, null, null, null, null);
        vLab.setExits(vOutside, vOffice, null, null, null, null);
        vOffice.setExits(null, null, null, vLab, null, null);
        
        this.aCurrentRoom = vOutside;
    }
    
    /**
     * Traite les commandes précédemment parser
     * @param commande
     */
    private boolean processCommand(final Command pCmd)
    {
        if(pCmd.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        if(pCmd.getCommandWord().equals("go")) this.goRoom(pCmd);
        else if(pCmd.getCommandWord().equals("help")) this.printHelp();
        else if(pCmd.getCommandWord().equals("quit")) return this.quit(pCmd);
        else if(pCmd.getCommandWord().equals("look")) look();
        else if(pCmd.getCommandWord().equals("eat")) eat();
        else
        {
            System.out.println("I don't know what you mean...");
            return true;
        }
        
        return false;
    }
    
    /**
     * Quitte le jeu
     */
    private boolean quit(final Command pCmd)
    {
        if(pCmd.hasSecondWord())
        {
            System.out.println("Quit what ?");
            return false;
        }
        return true;
    }
    
    /**
     * Change de salle
     */
    private void goRoom(final Command pCmd)
    {
        if(!pCmd.hasSecondWord()) 
        {
            System.out.println("Go where ?");
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
        
        else System.out.println("Unknown direction !");
        
        if(vNextRoom != null)
        {
            this.aCurrentRoom = vNextRoom;
            printLocationInfo();
        }
    }
    
    /**
     * Affiche les pièces disponibles
     */
    private void printLocationInfo()
    {
        System.out.println("You are " + aCurrentRoom.getDescription());
        System.out.print(aCurrentRoom.getExitString());
        System.out.println();
    }
        
    private void look()
    {
        System.out.println(aCurrentRoom.getLongDescription());
    }
    
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more.");
    }
    
} // Game
