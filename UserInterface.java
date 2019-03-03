import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.util.Observable;
import java.util.Observer;

public class UserInterface implements Observer, ActionListener
{
    private GameModel  aModel;
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JButton    aHelpButton;
    private JButton    aEatButton;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameModel pGameModel, final GameEngine pGameEngine )
    {
        this.aModel = pGameModel;
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        URL vImageURL = this.getClass().getClassLoader().getResource( pImageName );
        if ( vImageURL == null )
            println( "image not found" );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff );
        if ( ! pOnOff )
            this.aEntryField.getCaret().setBlinkRate( 0 );
    } // enable(.)

    /**
     * Affiche le message de bienvenue
     */
    public void printWelcome()
    {
        println("\n" + aModel.getWelcomeString() + "\n");
    }

    /**
     * Affiche le nom de la piece
     */
    public void printLocationInfo()
    {
        println(aModel.getLocationInfo());
    }

    /**
     * Affiche le message de fin
     */
    public void printGoodBye() 
    {
        println(aModel.getGoodByeString());
    }

    /**
     * Affiche le message d'aide
     */
    public void printHelp()
    {
        println(aModel.getHelpString());
    }

    /**
     * Met en place les éléments graphique
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Zork" );
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();

        this.aHelpButton = new JButton("Aide");
        this.aHelpButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    aEngine.interpretCommand( "aide" );
                }
            });

        this.aEatButton = new JButton("Manger");
        this.aEatButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    aEngine.interpretCommand( "manger" );
                }
            });

        JPanel vContextPanel = new JPanel();

        vPanel.setLayout( new BorderLayout() );
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( vContextPanel, BorderLayout.SOUTH );

        vContextPanel.add( this.aEntryField, BorderLayout.NORTH );
        vContextPanel.add( this.aHelpButton, BorderLayout.SOUTH );

        vContextPanel.add( this.aEatButton, BorderLayout.EAST );

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aMyFrame.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) { System.exit(0); }
            } );

        this.aEntryField.addActionListener( this );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    public void update(Observable o, Object arg)
    {
        printLocationInfo();
        showImage(aModel.getImageName());
    }

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
        this.processCommand();
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
