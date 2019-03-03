/**
 * @author Louis DESPLANCHE
 */
public class GameEngine
{
    private Parser aParser;
    private UserInterface aGui;
    private GameModel aModel;
    
    /**
     * Contructeur de Game
     */
    public GameEngine()
    {
        aModel = new GameModel();
        aGui = new UserInterface(aModel, this);
        aModel.addObserver(aGui);
        aParser = new Parser();
        aGui.printWelcome();
        aGui.update(aModel, null);
    }

    /**
     * Affiche l'aide
     */
    private void printHelp()
    {
        aGui.printHelp();
        aGui.println(aParser.getCommandList());
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
        if(!pCmd.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            aGui.println("Où va-t-on ?");
            return;
        }

        String direction = pCmd.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("nord"))
            nextRoom = aModel.getCurrentRoom().getExit("nord");
        if(direction.equals("est"))
            nextRoom = aModel.getCurrentRoom().getExit("est");
        if(direction.equals("sud"))
            nextRoom = aModel.getCurrentRoom().getExit("sud");
        if(direction.equals("ouest"))
            nextRoom = aModel.getCurrentRoom().getExit("ouest");
        if(direction.equals("haut"))
            nextRoom = aModel.getCurrentRoom().getExit("haut");
        if(direction.equals("bas"))
            nextRoom = aModel.getCurrentRoom().getExit("bas");
        if (nextRoom == null)
            aGui.println("Vous foncez droit dans un mur...");
        else {
            aModel.goRoom(nextRoom);
        }
    }

    /**
     * Affiche les pièces disponibles
     */
    private void printLocationInfo()
    {
        aGui.printLocationInfo();
    }

    private void look()
    {
        aGui.printLocationInfo();
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
