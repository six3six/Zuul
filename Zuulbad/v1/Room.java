package v1;

public class Room
{
    private String aDescription;
    public Room aNorthExit;
    public Room aEastExit;
    public Room aSouthExit;
    public Room aWestExit;
    
    public Room(final String pDescription)
    {
        this.aDescription = pDescription;
    }
    
    public String getDescription()
    {
        return this.aDescription;
    }
    
    public void setExits(final Room pNorthExit, final Room pEastExit, final Room pSouthExit,
        final Room aWestExit)
    {
        this.aNorthExit = pNorthExit;
        this.aEastExit = pEastExit;
        this.aSouthExit = pSouthExit;
        this.aWestExit = aWestExit;
    }
} // Room
