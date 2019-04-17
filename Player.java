import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * Classe joueur
 * @author Louis DESPLANCHE
 */
public class Player {
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private String aNom;
    private ItemList aItems;

    public Player(String pNom, Room pCurrentRoom)
    {
        this.aNom = pNom;
        this.aCurrentRoom = pCurrentRoom;
        this.aPreviousRooms = new Stack<>();
        this.aItems = new ItemList();
    }

    public Player(Room pCurrentRoom)
    {
        this("Player 1", pCurrentRoom);
    }

    /**
     * Recupere la salle actuelle
     * @return
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }


    /**
     * Recupere le nom du joueur
     * @return
     */
    public String getName()
    {
        return this.aNom;
    }

    /**
     * Change de salle en ajoutant la précedente dans la pile
     * @param pCurrentRoom future salle
     */
    public void changeCurrentRoom(final Room pCurrentRoom)
    {
        aPreviousRooms.push(this.aCurrentRoom);
        move(pCurrentRoom);
    }

    /**
     * Retourne dans la précédente salle et la dépile
     */
    public void goBack()
    {
        if(!aPreviousRooms.empty()) move(aPreviousRooms.pop());
    }

    /**
     * Ajoute un item
     * @param pItem
     */
    public void addItem(final Item pItem) {
        this.aItems.add(pItem);
    }

    public Item getByName(final String pName) {
        return this.aItems.getByName(pName);
    }

    /**
     * Retire un item
     * @param pItem
     */
    public void removeItem(final Item pItem) {
        this.aItems.remove(pItem);
    }

    /**
     * Change de salle
     * @param pRoom future salle
     */
    private void move(final Room pRoom)
    {
        this.aCurrentRoom = pRoom;
    }

}
