
/**
 * Structure d'un item
 *
 * @author Louis DESPLANCHE
 * @version 1
 */
public class Item
{
    private double aPoid;
    private String aDescription;
    private String aName;

    /**
     * Constructeur naturel
     * @param pPoid
     * @param pDescription
     * @param pName
     */
    public Item(final double pPoid, final String pDescription, final String pName)
    {
        this.aPoid = pPoid;
        this.aDescription = pDescription;
        this.aName = pName;
    }

    /**
     * @return Poid de l'item
     */
    public double getPoid()
    {
        return this.aPoid;
    }

    /**
     * @return Description de l'item
     */
    public String getDescription()
    {
        return this.aDescription;
    }

    /**
     * @return Nom de l'item
     */
    public String getName()
    {
        return this.aName;
    }
}
