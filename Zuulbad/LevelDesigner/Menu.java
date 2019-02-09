package LevelDesigner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Fenetre principale de l'éditeur de niveau
 *
 * @author Louis DESPLANCHE
 * @version 1
 */
public class Menu extends JFrame
{
    /**
     * Bouton de chargement du dossier
     */
    
    private String aDosRacine;
    private JButton aBtnItm;
    private JButton aBtnMnd;
    private JButton aBtnPers;
    private JLabel aLblRacine;
    private JLabel aRacine;
    private JLabel aTitre;
    
    

    public Menu(final String pDosRacine){
        super();
        aDosRacine = pDosRacine;
        AfficherFenetre();//On initialise notre fenêtre
    }

    /**
     * Methode d'affichage
     */
    private void AfficherFenetre()
    {
        this.setTitle("Editeur de jeu");
        this.setSize(214,340);
        
        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(214,340));
        contentPane.setBackground(new Color(192,192,192));

        aBtnItm = new JButton();
        aBtnItm.setBounds(5,142,194,38);
        aBtnItm.setBackground(new Color(214,217,223));
        aBtnItm.setForeground(new Color(0,0,0));
        aBtnItm.setEnabled(true);
        aBtnItm.setFont(new Font("sansserif",0,12));
        aBtnItm.setText("Editeur d'item");
        aBtnItm.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        aBtnItm.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    OuvrirEditItm(evt);
                }
            });

        aBtnMnd = new JButton();
        aBtnMnd.setBounds(5,94,194,38);
        aBtnMnd.setBackground(new Color(214,217,223));
        aBtnMnd.setForeground(new Color(0,0,0));
        aBtnMnd.setEnabled(true);
        aBtnMnd.setFont(new Font("sansserif",0,12));
        aBtnMnd.setText("Editeur de monde");
        aBtnMnd.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        aBtnMnd.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    OuvrirEditMnd(evt);
                }
            });

        aBtnPers = new JButton();
        aBtnPers.setBounds(5,194,194,38);
        aBtnPers.setBackground(new Color(214,217,223));
        aBtnPers.setForeground(new Color(0,0,0));
        aBtnPers.setEnabled(true);
        aBtnPers.setFont(new Font("sansserif",0,12));
        aBtnPers.setText("Editeur de personnage");
        aBtnPers.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        aBtnPers.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    OuvrirEditPers(evt);
                }
            });

        aLblRacine = new JLabel();
        aLblRacine.setBounds(52,238,90,35);
        aLblRacine.setBackground(new Color(214,217,223));
        aLblRacine.setForeground(new Color(0,0,0));
        aLblRacine.setEnabled(true);
        aLblRacine.setFont(new Font("sansserif",0,12));
        aLblRacine.setText("Racine du projet");
        aLblRacine.setVisible(true);

        aRacine = new JLabel();
        aRacine.setBounds(5,264,208,71);
        aRacine.setBackground(new Color(214,217,223));
        aRacine.setForeground(new Color(0,0,0));
        aRacine.setEnabled(true);
        aRacine.setFont(new Font("sansserif",0,12));
        aRacine.setText(aDosRacine);
        aRacine.setVisible(true);

        aTitre = new JLabel();
        aTitre.setBounds(5,5,207,84);
        aTitre.setBackground(new Color(214,217,223));
        aTitre.setForeground(new Color(0,0,0));
        aTitre.setEnabled(true);
        aTitre.setFont(new Font("SansSerif",0,60));
        aTitre.setText("Editeur");
        aTitre.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(aBtnItm);
        contentPane.add(aBtnMnd);
        contentPane.add(aBtnPers);
        contentPane.add(aLblRacine);
        contentPane.add(aRacine);
        contentPane.add(aTitre);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //Method mouseClicked for aBtnItm
    private void OuvrirEditItm (MouseEvent evt) {
        //TODO
    }

    //Method mouseClicked for aBtnMnd
    private void OuvrirEditMnd (MouseEvent evt) {
        //TODO
    }

    //Method mouseClicked for aBtnPers
    private void OuvrirEditPers (MouseEvent evt) {
        //TODO
    }
}