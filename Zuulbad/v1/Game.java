package v1;

public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;
    
    public Game()
    {
        this.aCurrentRoom = null;
        this.createRooms();
        aParser = new Parser();
    }
    
    public void play()
    {
        this.printWelcome();
        boolean vFinished = false;
        while(!vFinished) vFinished = this.processCommand(this.aParser.getCommand());        
        System.out.println("Thank you for playing. Good bye.");
    }
    
    private void printWelcome()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println(" ");
        System.out.println("You are outside the main entrance of the university");
        System.out.println("Exits: east south west");
    }
    
    private void printHelp()
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university");
        System.out.println(" ");
        System.out.println("Your command words are:");
        System.out.println("  go quit help");
    }
    
    private void createRooms()
    {
        Room vOutside = new Room("outside the main entrance of the university");
        Room vTheatre = new Room("in a lecture theatre");
        Room vPub = new Room("in the campus pub");
        Room vLab = new Room("in a computing lab");
        Room vOffice = new Room("in the computing admin office");
        
        vOutside.setExits(null, vTheatre, vLab, vPub);
        vTheatre.setExits(null, null, null, vOutside);
        vPub.setExits(null, vOutside, null, null);
        vLab.setExits(vOutside, vOffice, null, null);
        vOffice.setExits(vOutside, vLab, null, null);
        
        this.aCurrentRoom = vOutside;
    }
    
    private boolean processCommand(final Command pCmd)
    {
        if(pCmd.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        if(pCmd.getCommandWord().equals("go")) this.goRoom(pCmd);
        else if(pCmd.getCommandWord().equals("help")) this.printHelp();
        else if(pCmd.getCommandWord().equals("quit")) return this.quit(pCmd);
        else
        {
            System.out.println("I don't know what you mean...");
            return true;
        }
        
        return false;
    }
    
    private boolean quit(final Command pCmd)
    {
        if(!pCmd.hasSecondWord())
        {
            System.out.println("Quit what ?");
            return true;
        }
        return false;
    }
    
    private void goRoom(final Command pCmd)
    {
        if(!pCmd.hasSecondWord()) 
        {
            System.out.println("Go where ?");
            return;
        }
        
        Room vNextRoom = null;
        String vDirection = pCmd.getSecondWord();
        
        if(vDirection.equals("north") && this.aCurrentRoom.aNorthExit != null) vNextRoom = aCurrentRoom.aNorthExit;
        else if(vDirection.equals("east") && this.aCurrentRoom.aEastExit != null) vNextRoom = aCurrentRoom.aEastExit;
        else if(vDirection.equals("south") && this.aCurrentRoom.aSouthExit != null) vNextRoom = aCurrentRoom.aSouthExit;
        else if(vDirection.equals("west") && this.aCurrentRoom.aWestExit != null) vNextRoom = aCurrentRoom.aWestExit;
        else if(!(vDirection.equals("north") || vDirection.equals("east") || vDirection.equals("south") || vDirection.equals("west"))) System.out.println("There is no door !"); 
        else System.out.println("Unknown direction !");
        
        if(vNextRoom != null)
        {
            this.aCurrentRoom = vNextRoom;
            System.out.println("Curreent Room: ");
            if(this.aCurrentRoom != null) System.out.println(this.aCurrentRoom.getDescription());
            System.out.println(" ");
            System.out.println("Exits : ");
            if(this.aCurrentRoom.aNorthExit != null) System.out.println("north");
            if(this.aCurrentRoom.aEastExit != null) System.out.println("east");
            if(this.aCurrentRoom.aSouthExit != null) System.out.println("south");
            if(this.aCurrentRoom.aWestExit != null) System.out.println("west");
        }
        
        
    }
    
    
} // Game
