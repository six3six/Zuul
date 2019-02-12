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
    // tableau constant qui contient tous les mots de commande valides
    private static final String[] sValidCommands = {
        "go", "quit", "help", "look", "eat"
    };
    
    
    /**
     * Constructeur par defaut
     */
    public CommandWords()
    {
        // rien a faire pour le moment...
    } // CommandWords()

    /**
     * Verifie si une String donnee fait partie des commandes valides. 
     * @param pString la String a tester
     * @return true si pString est une comande valide, false sinon
     */
    public boolean isCommand( final String pString )
    {
        // pour chacune des commandes valides (du tableau constant)
        for ( int i=0; i<sValidCommands.length; i++ ) {
            // si elle est egale a pString
            if ( sValidCommands[i].equals( pString ) )
                return true;
        } // for
        // si nous arrivons la, c'est que la commande
        // n'a pas ete trouvee dans le tableau
        return false;
    } // isCommand()
    
    
    /**
     * 
     */
    public void showAll()
    {
        for(String command : sValidCommands) {
            System.out.print(command + " ");
        }
        System.out.println();
    }
} // CommandWords
