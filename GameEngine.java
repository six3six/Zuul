import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
    public GameEngine() {
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
    private void printHelp() {
        aGui.printHelp();
        aGui.println(aParser.getCommandList());
    }

    /**
     * Traite les commandes précédemment parser
     * @param pCmd
     */
    private boolean processCommand(final Command pCmd) {
        aGui.println("");
        switch (pCmd.getCommandWord()) {
            case GO:
                this.goRoom(pCmd);
                break;
            case EAT:
                this.eat(pCmd);
                break;
            case BACK:
                this.goBack();
                break;
            case DROP:
                this.drop(pCmd);
                break;
            case TAKE:
                this.take(pCmd);
                break;
            case HELP:
                this.printHelp();
                break;
            case LOOK:
                this.look();
                break;
            case QUIT:
                this.quit(pCmd);
                break;
            case INVENTORY:
                this.inventory(pCmd);
            case TEST:
                this.test(pCmd);
            case GIVEUP:
                this.giveup(pCmd);
            case UNKNOWN:
            default:
                aGui.println("Je ne comprends pas ce que vous voulez faire...");
                break;
        }
        return false;
    }

    /**
     * Affiche puis commence le traitement de la commande entrée par l'utilisateur
     */
    public void interpretCommand(String commandLine) {
        aGui.println(commandLine);
        processCommand(aParser.getCommand(commandLine));
    }

    /**
     * Quitte le jeu
     */
    private void quit(final Command pCmd) {
        if(pCmd.hasSecondWord())
        {
            aGui.println("Qu'est-ce que vous voulez quitter ?");
        }
        endGame();
    }

    /**
     * Change de salle
     * @param pCmd Commande à traiter
     */
    private void goRoom(final Command pCmd) {
        if(!pCmd.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            aGui.println("Où va-t-on ?");
            return;
        }

        if(aModel.getPlayer().getCurrentTime()>Player.sTimeLimit) {
            aGui.println("Impossible d'avancer le temps est écoulé");
            aGui.println("Si vous voulez abandonner tapez la commande : " + CommandWord.GIVEUP.toString());
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
     */
    private void goBack()
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
    private void eat(final Command pCmd) {
        if(!pCmd.hasSecondWord()) {
            aGui.println("Vous avez mangé et vous n'avez plus faim");
            return;
        }

        if(aModel.getItem(pCmd.getSecondWord()) == null) {
            aGui.println("Vous n'avez pas ça sur vous...");
            return;
        }

        if(pCmd.getSecondWord().equals("cookie")) {
            aModel.removeItem("cookie");
            aModel.increasePower(2);
            aGui.println("Votre force a doublé");
        }
        else  {
            aGui.println("Vous allez vous casser les dents si vous mangez ça...");
        }
    }

    /**
     * Prend un Item
     */
    private void take(final Command pCmd) {
        if(!pCmd.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            aGui.println("Prendre quoi ?");
            return;
        }

        Item vItem = aModel.getCurrentRoom().getItem(pCmd.getSecondWord());
        if(vItem == null) {
            aGui.println("L'objet que vous avez tenté de prendre n'existe pas...");
            return;
        }
        try {
            aModel.addItem(vItem);
        }
        catch (Exception e) {
            aGui.println(e.getMessage());
            return;
        }

        aModel.getCurrentRoom().removeItem(vItem);
    }

    /**
     * Jete un Item
     */
    private void drop(final Command pCmd) {
        if(!pCmd.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            aGui.println("Jeter quoi ?");
            return;
        }

        Item vItem = aModel.getItem(pCmd.getSecondWord());
        if(vItem == null) {
            aGui.println("L'objet que vous avez tenté de jeter n'existe pas...");
            return;
        }

        aModel.getCurrentRoom().addItem(vItem);
        aModel.removeItem(vItem);
    }

    /**
     * Affiche l'invetaire
     */
    private void inventory(final Command pCmd) {
        aGui.println(this.aModel.getInventoryString());
    }

    /**
     *
     */
    private void giveup(final Command pCmd){
        aGui.println("Vous avez perdu");
        quit(pCmd);
    }

    /**
     * Commande de test
     * @param pCmd
     */
    private void test(final Command pCmd) {
        if(!pCmd.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            aGui.println("Que vouler-vous tester ?");
            return;
        }


        //Les extensions sont trié par ordre de priorité
        String[] extensions = {".txt", ".xml", ""};
        File vFile = null;
        for (String extension: extensions) {
            vFile = new File("Test/" + pCmd.getSecondWord() + extension);
            if (vFile.exists()) break;
            else vFile = null;
        }

        if(vFile == null) {
            aGui.println("Fichier non trouvé : " + pCmd.getSecondWord());
            return;
        }

        if(vFile.getPath().contains(".xml")) runXMLTest(vFile);
        else runTXTTest(vFile);

    }

    /**
     * Lit le fichier texte et l'execute
     * @param pFile
     */
    private void runTXTTest(final File pFile) {
        Scanner vSc;
        try { // pour "essayer" les instructions suivantes
            vSc = new Scanner(pFile);
            while ( vSc.hasNextLine() ) {
                String vLigne = vSc.nextLine();
                if(vLigne.startsWith("!")) continue;
                interpretCommand(vLigne);
            } // while
        } // try
        catch ( final FileNotFoundException pFNFE ) {
            // traitement en cas d'exception
        } // catch
    }

    /**
     * Lit le fichier XML et l'execute
     * @param pFile
     */
    private void runXMLTest(final File pFile) {
        boolean vInitialState = false;

        try {
            final DocumentBuilderFactory vFactory = DocumentBuilderFactory.newInstance();
            vFactory.setValidating(false);
            final DocumentBuilder vBuilder = vFactory.newDocumentBuilder();
            final Document vDocument= vBuilder.parse(pFile);
            final Element vRacine = vDocument.getDocumentElement();
            if(vRacine.hasAttribute("useInitialState")) vInitialState = vRacine.getAttribute("useInitialState").equals("true");

            NodeList vOps = vRacine.getChildNodes();
            for(int vOpIndex=0; vOpIndex<vOps.getLength(); vOpIndex++)
            {
                NodeList vOpElements = vOps.item(vOpIndex).getChildNodes();
                String vCommand = null;
                String vResponse = null;
                boolean vCheckReturn = true;
                for(int vElemIndex=0; vElemIndex<vOpElements.getLength(); vElemIndex++)
                {
                    Node vElem = vOpElements.item(vElemIndex);
                    if(vElem.getNodeName().equals("Command")) vCommand = vElem.getTextContent();
                    if(vElem.getNodeName().equals("Return")) vResponse = vElem.getTextContent();
                    if(vElem.getNodeName().equals("CheckReturn")) vCheckReturn = vElem.getTextContent().equals("true");
                }
                if(vCommand!=null)
                {
                    interpretCommand(vCommand);
                }
            }

        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Quitte le jeu
     */
    private void endGame() {
        aGui.println("Merci d'avoir jouer, à bientot !");
        aGui.enable(false);
    }

} // Game
