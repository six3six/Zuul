
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
    private String aName;
    
    public Item(final int pPoid, final String pDescription, final String pName)
    {
        this.aPoid = pPoid;
        this.aDescription = pDescription;
        this.aName = pName;
    }
    
    public int getPoid()
    {
        return this.aPoid;
    }
    
    public String getDescription()
    {
        return this.aDescription;
    }
    
    public String getName()
    {
        return this.aName;
    }
}
