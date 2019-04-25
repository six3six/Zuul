import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * View
 *
 * @author Louis DESPLANCHE
 */
public class UserInterface implements ActionListener
{
    private GameModel  aModel;
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JButton    aHelpButton;
    private JButton    aEatButton;
    private HashMap<String, JButton> aCardinalBtn;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     * @param pGameModel   The GameModem object implementing game messages
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

        //Mise en place des boutons cardinaux
        this.aCardinalBtn = new HashMap<>();
        //Création du panel
        JPanel vCardPanel = new JPanel();

        vCardPanel.setLayout(new GridBagLayout());
        //vControlPanel.add(vCardPanel, BorderLayout.EAST);

        //Création des boutons
        this.aCardinalBtn.put("nord", new JButton("Nord"));
        this.aCardinalBtn.put("sud", new JButton("Sud"));
        this.aCardinalBtn.put("est", new JButton("Est"));
        this.aCardinalBtn.put("ouest", new JButton("Ouest"));
        this.aCardinalBtn.put("haut", new JButton("Haut"));
        this.aCardinalBtn.put("bas", new JButton("Bas"));

        vCardPanel.add(this.aCardinalBtn.get("nord"), gbConstraintsGen(1,0));
        vCardPanel.add(this.aCardinalBtn.get("sud"), gbConstraintsGen(1,2));
        vCardPanel.add(this.aCardinalBtn.get("est"), gbConstraintsGen(2,1));
        vCardPanel.add(this.aCardinalBtn.get("ouest"), gbConstraintsGen(0,1));
        vCardPanel.add(this.aCardinalBtn.get("haut"), gbConstraintsGen(3,0));
        vCardPanel.add(this.aCardinalBtn.get("bas"), gbConstraintsGen(3,2));
        //Placement dans le panel
        for (Map.Entry<String, JButton> vBtn : this.aCardinalBtn.entrySet()) {
            vBtn.getValue().addActionListener(this);
        }


        this.aHelpButton = new JButton("Aide");

        this.aEatButton = new JButton("Manger");

        JPanel vContextPanel = new JPanel();

        vPanel.setLayout( new BorderLayout() );
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( vContextPanel, BorderLayout.SOUTH );

        vContextPanel.add( this.aEntryField, BorderLayout.NORTH );
        vContextPanel.add( this.aHelpButton);
        vContextPanel.add( this.aEatButton);
        vContextPanel.add(vCardPanel, BorderLayout.EAST);

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aMyFrame.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) { System.exit(0); }
            } );

        this.aEntryField.addActionListener( this );
        this.aEatButton.addActionListener( this );
        this.aHelpButton.addActionListener( this );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
        updateBtn();
    } // createGUI()

    /**
     * Met a jour l'interface utilisateur
     */
    public void update()
    {
        printLocationInfo();
        showImage(aModel.getImageName());
        updateBtn();
    }

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry

        if(pE.getSource() == this.aEntryField) this.processCommand();
        else if(pE.getSource() == this.aEatButton) aEngine.interpretCommand( CommandWord.EAT.toString()  );
        else if(pE.getSource() == this.aHelpButton) aEngine.interpretCommand( CommandWord.HELP.toString() );
        else {
            for (Map.Entry<String, JButton> vBtn : this.aCardinalBtn.entrySet()) {
                if(pE.getSource() == vBtn.getValue()) {
                    aEngine.interpretCommand(CommandWord.GO.toString() + " " + vBtn.getValue().getText().toLowerCase());
                    break;
                }
            }
        }
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

    /**
     * Génére une variable de contrainte permetant de placé des composants dans une grille
     * @param x
     * @param y
     * @return
     */
    private GridBagConstraints gbConstraintsGen(final int x, final int y)
    {
        GridBagConstraints vCon = new GridBagConstraints();
        vCon.gridx = x;
        vCon.gridy = y;
        return vCon;
    }

    /**
     * Met à jour les boutons de directions
     */
    private void updateBtn() {
        for (Map.Entry<String, JButton> vBtn: aCardinalBtn.entrySet()) {
            vBtn.getValue().setEnabled(aModel.isExit(vBtn.getKey()));
        }
    }


} // UserInterface 
