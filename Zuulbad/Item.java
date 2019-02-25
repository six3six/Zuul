
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    private int aPoid;
    private String aDescription;
    private String aLongDescription;
    
    public Item(final int pPoid, final String pDescription, final String pLongDescription)
    {
        this.aPoid = pPoid;
        this.aDescription = pDescription;
        this.aLongDescription = pLongDescription;
    }
    
    public int getPoid()
    {
        return this.aPoid;
    }
    
    public String getDescription()
    {
        return this.aDescription;
    }
    
    public String getLongDescription()
    {
        return this.aLongDescription;
    }
}
