import java.util.*;

/**
 * @author Louis DESPLANCHE
 */
public class Room
{
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
        String vRes = "Exits : ";
        Set<String> vKeys = aExits.keySet();
        for(String vExit : vKeys)
        {
            vRes += " " + vExit;
        }
        return vRes;
    }
    
    
} // Room
