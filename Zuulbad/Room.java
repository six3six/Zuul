import java.util.*;

/**
 * @author Louis DESPLANCHE
 */
public class Room
{
    private static HashMap<String, Room> aRoomList;
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName;
    
    /**
     * Constructeur de room
     */
    public Room(final String pDescription, String pImage)
    {
        this.aDescription = pDescription;
        this.aImageName = pImage;
        aExits = new HashMap<String, Room>();
    }
    
    /**
     * @return description de la salle
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
        if(pNorthExit != null) aExits.put("nord", pNorthExit);
        if(pEastExit != null) aExits.put("est", pEastExit);
        if(pSouthExit != null) aExits.put("sud", pSouthExit);
        if(pWestExit != null) aExits.put("ouest", pWestExit);
        if(pTopExit != null) aExits.put("haut", pTopExit);
        if(pDownExit != null) aExits.put("bas", pDownExit);
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
   
    /**
     * Description compléte
     */
    public String getLongDescription()
    {
        return "Vous êtes " + aDescription + ".\n" + getExitString();
    }
    
    /**
     * Récupere la liste des salles disponibles
     */
    public String getExitString()
    {
        StringBuilder vRes = new StringBuilder(10 + aExits.size()*10);
        vRes.append("Sorties : ");
        Set<String> vKeys = aExits.keySet();
        for(String vExit : vKeys)
        {
            vRes.append(" " + vExit);
        }
        return vRes.toString();
    }
    
    /**
     * Créé les salles et les place en mémoires
     */
    public static void CreateRoom()
    {
        aRoomList = new HashMap<String, Room>();
        
        //Piece maison
        aRoomList.put("jardin", new Room("devant chez vous, dans le jardin", "Assets/Map/outside.jpg"));
        aRoomList.put("salon", new Room("dans votre lieu de vie, très agréable pour écouter les nouvelles à la radio", ""));
        aRoomList.put("cuisine", new Room("dans votre cuisine très bien équipé", ""));
        aRoomList.put("chambre", new Room("dans votre chambre : une pièce austère mais confortable", ""));
        
        //Extérieur
        aRoomList.put("rue", new Room("dans une grande rue très large mais personne ne s'y promène...", ""));
        aRoomList.put("egouts", new Room("dans les égouts, un endroit sal et mal odorant", ""));
        aRoomList.put("tour", new Room("dans la tour : certainement l'endroit le plus haut de la ville", ""));
        aRoomList.put("bibliotheque", new Room("dans la Grande Bibliothèque : pièce refermant tous le savoir d'une civilisation", ""));
        
        //Ministère
        aRoomList.put("ministere", new Room("dans le hall du ministère : salle grise avec un bureau et un standardiste", ""));
        aRoomList.put("bureau", new Room("dans le bureau du ministre orné de souvenir du coup d'état", ""));
        
        
        getRoom("jardin").setExits(null, getRoom("rue"), null, getRoom("salon"), null, null);
        getRoom("salon").setExits(null, getRoom("jardin"), getRoom("cuisine"), null, getRoom("chambre"), null);
        getRoom("cuisine").setExits(getRoom("salon"), null, null, null, null, null);
        getRoom("chambre").setExits(null, null, null, null, null, getRoom("salon"));
        
        getRoom("rue").setExits(null, getRoom("ministere"), null, getRoom("jardin"), getRoom("tour"), getRoom("egouts"));
        getRoom("egouts").setExits(getRoom("bibliotheque"), null, null, null, getRoom("rue"), null);
        getRoom("tour").setExits(null, null, null, null, null,  getRoom("rue"));
        getRoom("bibliotheque").setExits(null, null, getRoom("egouts"), null, null,  null);
        
        getRoom("ministere").setExits(null, null, null, getRoom("rue"), getRoom("bureau"),  null);
        getRoom("bureau").setExits(null, null, null, null, null, getRoom("ministere"));
    }
    
    /**
     * Récupere la salle à la direction donnée
     */
    public static Room getRoom(final String pId)
    {
        return aRoomList.get(pId);
    }
    
    /**
     * Return a string describing the room's image name
     */
    public String getImageName()
    {
        return aImageName;
    }
} // Room
