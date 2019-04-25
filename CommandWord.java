public enum CommandWord {
    UNKNOWN("?"),
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    LOOK("look"),
    EAT("eat"),
    BACK("back"),
    TEST("test"),
    TAKE("take"),
    DROP("drop"),
    INVENTORY("inventory");

    private String aName;

    CommandWord(final String pName) {
        this.aName = pName;
    }

    @Override
    public String toString() {
        return this.aName;
    }
}
