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
public class Home extends JFrame
{
    /**
     * Bouton de chargement du dossier
     */
    private JButton aBtnCht;
    private JLabel aTitre;

    public Home(){
        super();
        AfficherFenetre();//On initialise notre fenêtre
    }

    /**
     * Methode d'affichage
     */
    private void AfficherFenetre()
    {
        
        this.setTitle("Editeur de jeu");
        this.setSize(488,242);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(488,242));
        contentPane.setBackground(new Color(192,192,192));


        aBtnCht = new JButton();
        aBtnCht.setBounds(151,165,183,47);
        aBtnCht.setBackground(new Color(214,217,223));
        aBtnCht.setForeground(new Color(0,0,0));
        aBtnCht.setEnabled(true);
        aBtnCht.setFont(new Font("sansserif",0,12));
        aBtnCht.setText("Charger le projet");
        aBtnCht.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        aBtnCht.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                ChoisirDossier(evt);
            }
        });


        aTitre = new JLabel();
        aTitre.setBounds(5,5,494,147);
        aTitre.setBackground(new Color(214,217,223));
        aTitre.setForeground(new Color(0,0,0));
        aTitre.setEnabled(true);
        aTitre.setFont(new Font("SansSerif",0,60));
        aTitre.setText("Editeur de niveau");
        aTitre.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(aBtnCht);
        contentPane.add(aTitre);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private void ChoisirDossier (MouseEvent evt)
    {
        JFileChooser vSltDos = new JFileChooser();
        vSltDos.setCurrentDirectory(new java.io.File("."));
        vSltDos.setDialogTitle("Chosir le dossier");
        vSltDos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        vSltDos.setAcceptAllFileFilterUsed(false);

        if (vSltDos.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            this.hide();
            Menu vMenu = new Menu(vSltDos.getSelectedFile().toString());
        } else {
            JOptionPane.showMessageDialog(null, "Vous n'avez rien sélectioné...");
            //return "";
        }
    }
}
