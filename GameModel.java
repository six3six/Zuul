import java.util.Observable;

/**
 * Model
 *
 * @author Louis DESPLANCHE
 * @version 1.0
 */
public class GameModel
{
    private Room aCurrentRoom;
    private UserInterface aGui;
    
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
        sendSignal();
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
        return "Merci d'avoir joué, au revoir";
    }

    public String getHelpString()
    {
        return "Vous êtes dans un état totalitaire" + "\n" +
        "Votre but, renverser le gouvernement grace à la culture";
    }

    public String getLocationInfo() {
        return "Vous êtes " + getCurrentRoom().getDescription() + "\n" +
        getCurrentRoom().getExitString() + "\n" +
        getCurrentRoom().getItemString();
    }

    public void setGUI(final UserInterface pGui)
    {
        this.aGui = pGui;
    }

    //Signal au GUI qu'une modification vient d'être effectué
    private void sendSignal()
    {
        if(aGui != null)
            aGui.update();
    }
}