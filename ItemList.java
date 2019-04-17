import java.util.HashSet;

public class ItemList {
    private HashSet<Item> aItems;

    public ItemList() {
        this.aItems = new HashSet<>();
    }

    public void add(final Item pItem) {
        aItems.add(pItem);
    }

    public void remove(final Item pItem) {
        aItems.remove(pItem);
    }

    public boolean isEmpty() {
        return aItems.isEmpty();
    }

    /**
     * Cherche l'item par son nom
     * Si aucun item ne correspond alors la fonction retourne null
     * @param pName nom de l'item recherché
     * @return
     */
    public Item getByName(final String pName) {
        for ( Item item: this.aItems) {
            if(item.getName().toLowerCase().equals(pName.toLowerCase())) return item;
        }
        return null;
    }

    public boolean removeByName(final String pName) {
        try {
            Item vItm = getByName(pName);
            remove(vItm);
            if(vItm == null) throw new Exception("L'item supprimé est nul");
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean isExist(final String pName) {
        return getByName(pName) != null;
    }

    public double getWeight() {
        double ret = 0.0;
        for (Item vItm : this.aItems) {
            ret += vItm.getPoid();
        }
        return ret;
    }

    public String toString()
    {
        String ret = "";
        if(isEmpty()) return "Pas d'item";

        for (Item vItm : this.aItems) {
            ret += vItm.getName() + "";
        }
        return ret;
    }


}
