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
    
    /**
     * Créé l'interface graphique
     */
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
        aGui.println("Entrez 'aide' si vous avez besoin d'aide");
        aGui.println(" ");
        printLocationInfo();
    }

    /**
     * Affiche l'aide
     */
    private void printHelp()
    {
        aGui.println("Vous êtes chez dans un état totalitaire");
        aGui.println("Votre but, renverser le gouvernement grace à la culture");
        aGui.println(" ");
        aGui.println("Les commandes disponibles sont:");
        aGui.println(aParser.getCommandList());
    }

    /**
     * Créé les pièces de la carte
     */
    private void createRooms()
    {
        Room.CreateRoom();
        aCurrentRoom = Room.getRoom("salon");
        
    }

    /**
     * Traite les commandes précédemment parser
     * @param commande
     */
    private boolean processCommand(final Command pCmd)
    {
        if(pCmd.isUnknown()) {
            aGui.println("Je ne comprends pas ce que vous voulez faire...");
            return false;
        }
        if(pCmd.getCommandWord().equals("aller")) this.goRoom(pCmd);
        else if(pCmd.getCommandWord().equals("aide")) this.printHelp();
        else if(pCmd.getCommandWord().equals("quitter")) this.quit(pCmd);
        else if(pCmd.getCommandWord().equals("regarder")) look();
        else if(pCmd.getCommandWord().equals("manger")) eat();
        else
        {
            aGui.println("Je ne comprends pas ce que vous voulez faire...");
            return true;
        }

        return false;
    }
    
    /**
     * Affiche puis commence le traitement de la commande entrée par l'utilisateur
     */
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
            aGui.println("Qu'est-ce que vous voulez quitter ?");
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
            aGui.println("Où va-t-on ?");
            return;
        }

        Room vNextRoom = null;
        String vDirection = pCmd.getSecondWord();

        if(vDirection.equals("nord") && aCurrentRoom.getExit("nord") != null) vNextRoom = aCurrentRoom.getExit("nord");
        else if(vDirection.equals("est") && aCurrentRoom.getExit("est") != null) vNextRoom = aCurrentRoom.getExit("est");
        else if(vDirection.equals("sud") && aCurrentRoom.getExit("sud") != null) vNextRoom = aCurrentRoom.getExit("sud");
        else if(vDirection.equals("ouest") && aCurrentRoom.getExit("ouest") != null) vNextRoom = aCurrentRoom.getExit("ouest");
        else if(vDirection.equals("haut") && aCurrentRoom.getExit("haut") != null) vNextRoom = aCurrentRoom.getExit("haut");
        else if(vDirection.equals("bas") && aCurrentRoom.getExit("bas") != null) vNextRoom = aCurrentRoom.getExit("bas");
        else if(vDirection.equals("nord") || vDirection.equals("est") || vDirection.equals("sud") || vDirection.equals("ouest") || vDirection.equals("haut") || vDirection.equals("bas")) aGui.println("Il n'y a pas d'accès !"); 

        else aGui.println("Direction inconnue !");

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
        aGui.println(aCurrentRoom.getLongDescription());
        aGui.println("");
    }

    private void look()
    {
        aGui.println(aCurrentRoom.getLongDescription());
    }

    private void eat()
    {
        aGui.println("Vous avez mangé et vous n'avez plus faim");
    }
    
    private void endGame()
    {
        aGui.println("Merci d'avoir jouer, à bientot !");
        aGui.enable(false);
    }

} // Game
