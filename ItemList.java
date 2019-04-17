import java.util.HashSet;

public class ItemList {
    private HashSet<Item> aItems;

    public ItemList() {
        this.aItems = new HashSet<>();
    }

    /**
     * Ajoute un item
     * @param pItem
     */
    public void add(final Item pItem) {
        aItems.add(pItem);
    }

    /**
     * Supprime un item
     * @param pItem
     */
    public void remove(final Item pItem) {
        aItems.remove(pItem);
    }

    /**
     * Vérifie si la liste est vide
     * @return
     */
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

    /**
     * Supprime un objet désigné par son nom
     * @param pName nom de l'item à supprimer
     * @return
     */
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

    /**
     * Vérifie si un item existe
     * @param pName nom de l'item
     * @return
     */
    public boolean isExist(final String pName) {
        return getByName(pName) != null;
    }

    /**
     * Retourne le poid total
     */
    public double getWeight() {
        double ret = 0.0;
        for (Item vItm : this.aItems) {
            ret += vItm.getPoid();
        }
        return ret;
    }

    /**
     * Retourne la liste des items
     */
    public String toString()
    {
        String ret = "";
        if(isEmpty()) return "Pas d'item";

        for (Item vItm : this.aItems) {
            ret += vItm.getName() + " ";
        }
        return ret;
    }


}
