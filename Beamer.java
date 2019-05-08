public class Beamer extends Item {
    private boolean aUsed;
    private Room aTargetRoom;

    public Beamer(final double pPoid, final String pDescription, final String pName, final Room pRoom){
        super(pPoid, pDescription, pName);
        this.aUsed = false;
        this.aTargetRoom = pRoom;
    }
    public Beamer(final double pPoid, final String pDescription, final String pName) {
        this(pPoid, pDescription, pName, null);
    }

    public void attachRoom(final Room pRoom) {
        this.aTargetRoom = pRoom;
    }

    public Room use() {
        this.aUsed = true;
        return this.aTargetRoom;
    }

    public boolean isAttached() {
        return aTargetRoom != null;
    }

    public boolean isUsed() {
        return this.aUsed;
    }
}
