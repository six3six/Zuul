import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * Classe joueur
 * @author Louis DESPLANCHE
 */
public class Player {
    final static int sTimeLimit = 15;


    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private String aNom;
    private ItemList aItems;
    private double aPoidMax;
    private int aCurrentTime;



    public Player(String pNom, Room pCurrentRoom)
    {
        this.aNom = pNom;
        this.aCurrentRoom = pCurrentRoom;
        this.aPreviousRooms = new Stack<>();
        this.aItems = new ItemList();
        this.aPoidMax = 10;
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
    public String goBack()
    {
        if(!aPreviousRooms.empty()) {
            Room vRoom = aPreviousRooms.pop();
            if (getCurrentRoom().isExit(vRoom)) move(vRoom);
            else return "Vous ne pouvez pas revenir en arrière car cette sortie n'éxiste pas";
        }
        else return "Vous ne pouvez pas revenir plus en arrière";
        return "";
    }

    public boolean canBack() {
        if(!aPreviousRooms.empty()){
            return aCurrentRoom.isExit(aPreviousRooms.peek());
        }
        return false;
    }

    /**
     *
     * @return current time
     */
    public int getCurrentTime() {
        return this.aCurrentTime;
    }

    /**
     * Ajoute un item
     * @param pItem
     */
    public void addItem(final Item pItem) throws Exception {
        if(pItem.getPoid() + aItems.getWeight() > this.aPoidMax) {
            throw new Exception("Poid dépassé");
        }
        this.aItems.add(pItem);
    }

    /**
     * Designe un item par son nom
     * @param pName
     * @return
     */
    public Item getItemByName(final String pName) {
        return this.aItems.getByName(pName);
    }

    /**
     * Retourne la liste des items du joueur
     */
    public String itemList() {
        return this.aItems.toString();
    }

    /**
     * Retire un item
     * @param pItem
     */
    public void removeItem(final Item pItem) {
        this.aItems.remove(pItem);
    }

    /**
     * Retire un item
     * @param pName
     */
    public void removeItem(final String pName) {
        this.aItems.removeByName(pName);
    }

    /**
     * Retourne la valeur du poid max
     * @return
     */
    public double getMaxWeigth() {
        return this.aPoidMax;
    }

    /**
     * Enregistre la valeur maximal de ce que peut transporter un joueur
     * @param pMaxWeigth
     */
    public void setMaxWeigth(final double pMaxWeigth) {
        this.aPoidMax = pMaxWeigth;
    }

    public void incrementTime(){
        aCurrentTime++;
    }

    /**
     * Change de salle
     * @param pRoom future salle
     */
    private void move(final Room pRoom)
    {
        this.aCurrentRoom = pRoom;
        incrementTime();
    }



}
