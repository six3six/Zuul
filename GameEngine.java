import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
        aModel.setGUI(aGui);
        aParser = new Parser();
        aGui.printWelcome();
        aGui.update();
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
     * @param pCmd
     */
    private boolean processCommand(final Command pCmd)
    {
        if(pCmd.isUnknown()) {
            aGui.println("Je ne comprends pas ce que vous voulez faire...");
            return false;
        }
        if(pCmd.getCommandWord().equals("aller")) this.goRoom(pCmd);
        else if(pCmd.getCommandWord().equals("retour")) this.goBack(pCmd);
        else if(pCmd.getCommandWord().equals("aide")) this.printHelp();
        else if(pCmd.getCommandWord().equals("quitter")) this.quit(pCmd);
        else if(pCmd.getCommandWord().equals("regarder")) this.look();
        else if(pCmd.getCommandWord().equals("manger")) this.eat();
        else if(pCmd.getCommandWord().equals("test")) test(pCmd);
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
     * Retourne en arrière
     * @param pCmd
     */
    private void goBack(final Command pCmd)
    {
        aModel.goBack();
    }

    /**
     * Affiche les informations de la pièce
     */
    private void look()
    {
        aGui.printLocationInfo();
    }

    /**
     * Mange
     */
    private void eat()
    {
        aGui.println("Vous avez mangé et vous n'avez plus faim");
    }

    private void test(final Command pCmd) {
        final String extension = "xml";
        if(!pCmd.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            aGui.println("Que vouler-vous tester ?");
            return;
        }

        boolean vInitialState = false;

        File vFile = new File("Test/" + pCmd.getSecondWord() + "." + extension);
        try {
            final DocumentBuilderFactory vFactory = DocumentBuilderFactory.newInstance();
            vFactory.setValidating(false);
            final DocumentBuilder vBuilder = vFactory.newDocumentBuilder();
            final Document vDocument= vBuilder.parse(vFile);
            final Element vRacine = vDocument.getDocumentElement();
            if(vRacine.hasAttribute("useInitialState")) vInitialState = vRacine.getAttribute("useInitialState").equals("true");

            vRacine.getChildNodes();
        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final FileNotFoundException e) {
            aGui.println("Fichier non trouvé : " + pCmd.getSecondWord() + "." + extension);
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Quitte le jeu
     */
    private void endGame()
    {
        aGui.println("Merci d'avoir jouer, à bientot !");
        aGui.enable(false);
    }

} // Game
