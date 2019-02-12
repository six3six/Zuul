import java.util.*;

/**
 * @author Louis DESPLANCHE
 */
public class Room
{
    private static HashMap<String, Room> aRoomList;
    private String aDescription;
    private HashMap<String, Room> aExits;
    
    /**
     * Constructeur de room
     */
    public Room(final String pDescription)
    {
        this.aDescription = pDescription;
        aExits = new HashMap<String, Room>();
    }
    
    /**
     * @return déscription de la salle
     */
    public String getDescription()
    {
        return this.aDescription;
    }
    
    /**
    * Paramètre toutes les salles voisines
    */
    public void setExits(final Room pNorthExit, final Room pEastExit, final Room pSouthExit,
        final Room pWestExit, final Room pTopExit, final Room pDownExit)
    {
        if(pNorthExit != null) aExits.put("north", pNorthExit);
        if(pEastExit != null) aExits.put("east", pEastExit);
        if(pSouthExit != null) aExits.put("south", pSouthExit);
        if(pWestExit != null) aExits.put("west", pWestExit);
        if(pTopExit != null) aExits.put("top", pTopExit);
        if(pDownExit != null) aExits.put("down", pDownExit);
    }
    
    /**
     * Parametre une salle avec ca direction
     */
    public void setExit(final String pDirection, final Room pNeighbor)
    {
        aExits.put(pDirection, pNeighbor);
    }
    
    /**
     * @return Recupere la salle spécifié
     */
    public Room getExit(final String pDirection)
    {
        return aExits.get(pDirection);
    }
    
    public String getLongDescription()
    {
        return "You are " + aDescription + ".\n" + getExitString();
    }
    
    /**
     * Récupere la liste des salles disponibles
     */
    public String getExitString()
    {
        StringBuilder vRes = new StringBuilder(40);
        vRes.append("Exits : ");
        Set<String> vKeys = aExits.keySet();
        for(String vExit : vKeys)
        {
            vRes.append(" " + vExit);
        }
        return vRes.toString();
    }
    
    public static void CreateRoom()
    {
        aRoomList = new HashMap<String, Room>();
        
        aRoomList.put("outside", new Room("outside the main entrance of the university"));
        aRoomList.put("theatre", new Room("in a lecture theatre"));
        aRoomList.put("pub", new Room("in the campus pub"));
        aRoomList.put("lab", new Room("in a computing lab"));
        aRoomList.put("office", new Room("in the computing admin office"));
        
        getRoom("outside").setExits(null, getRoom("theatre"), getRoom("lab"), getRoom("pub"), null, null);
        getRoom("theatre").setExits(null, null, null, getRoom("office"), null, null);
        getRoom("pub").setExits(null, getRoom("outside"), null, null, null, null);
        getRoom("lab").setExits(getRoom("outside"), getRoom("office"), null, null, null, null);
        getRoom("office").setExits(null, null, null, getRoom("lab"), null, null);
    }
    
    public static Room getRoom(final String pId)
    {
        return aRoomList.get(pId);
    }
} // Room
