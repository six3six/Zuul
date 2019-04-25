import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2013.09.15
 */
public class CommandWords
{
    HashMap<String, CommandWord> aValidCommands;
    
    /**
     * Constructeur par defaut
     */
    public CommandWords()
    {
        this.aValidCommands = new HashMap<>();
        for (CommandWord vCW: CommandWord.values()) {
            if(vCW == CommandWord.UNKNOWN) continue;
            aValidCommands.put(vCW.toString(), vCW);
        }
    } // CommandWords()

    /**
     * Verifie si une String donnee fait partie des commandes valides. 
     * @param pString la commande a tester
     * @return true si pString est une comande valide, false sinon
     */
    public boolean isCommand( final String pString )
    {
        return aValidCommands.containsKey(pString);
    } // isCommand()

    /**
     * Renvoie la commande correspondante
     * @param pString la commande à renvoyer
     * @return
     */
    public CommandWord getCommandWord( final String pString )
    {
        CommandWord vCommands = aValidCommands.get(pString);
        if(vCommands == null) return CommandWord.UNKNOWN;
        else return vCommands;
    } // isCommand()
    
    
    /**
     * @return Liste des commandes
     */
    public String getCommandList()
    {
        StringBuilder vResult = new StringBuilder(aValidCommands.size() * 10);
        for(String command: aValidCommands.keySet()) {
            vResult.append(command + " ");
        }
        return vResult.toString();
    }
} // CommandWords
