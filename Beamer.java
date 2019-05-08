public class Beamer extends Item {
    private boolean aUsed;
    private Room aTargetRoom;

    public Beamer(final double pPoid, final String pDescription, final String pName) {
        super(pPoid, pDescription, pName);
        this.aUsed = false;
        this.aTargetRoom = null;
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
