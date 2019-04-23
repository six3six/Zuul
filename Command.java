public class Command
{
    private CommandWord aCommandWord;
    private String aSecondWord;
    
    public Command(final CommandWord pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }
    
    public CommandWord getCommandWord()
    {
        return this.aCommandWord;
    }
    
    public String getSecondWord()
    {
        return this.aSecondWord;
    }
    
    public boolean hasSecondWord()
    {
        return this.aSecondWord!=null;
    }
    
    public boolean isUnknown()
    {
        return this.aCommandWord==CommandWord.UNKNOWN;
    }
    
    
} // Command
