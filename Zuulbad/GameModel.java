import java.util.Observable;

/**
 * Write a description of class GameModel here.
 *
 * @author Louis DESPLANCHE
 * @version 1.0
 */
public class GameModel extends Observable
{
    private Room aCurrentRoom;
    
    public GameModel()
    {
        Room.CreateRoom();
        aCurrentRoom = Room.getRoom("jardin");
    }

    public Room getCurrentRoom()
    {
        return aCurrentRoom;
    }

    public void goRoom(Room nextRoom)
    {
        aCurrentRoom = nextRoom;
        setChanged();
        notifyObservers();
    }

    public String getImageName()
    {
        return aCurrentRoom.getImageName();
    }

    public String getWelcomeString() 
    {
        return "Bienvenue dans Antisophia" + "\n" + 
        "Si besoin, appuyez sur 'Aide' \n";
    }

    public String getGoodByeString()
    {
        return "Thank you for playing.  Good bye.";
    }

    public String getHelpString()
    {
        return "Vous êtes dans un état totalitaire" + "\n" +
        "Votre but, renverser le gouvernement grace à la culture";
    }

    public String getLocationInfo() {
        return "Vous êtes " + getCurrentRoom().getDescription() + "\n" +
        getCurrentRoom().getExitString();
    }

    public String getLongDescription() {
        return "Vous êtes " + getCurrentRoom().getLongDescription();
    }
}