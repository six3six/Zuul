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
    private JButton aBtnChrg;

    public Home(){
        super();

        aBtnChrg = new JButton("Charger le projet");
        aBtnChrg.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                ChoisirDossier();
            }
        });
        AfficherFenetre();//On initialise notre fenêtre
    }

    /**
     * Methode d'affichage
     */
    private void AfficherFenetre()
    {
        setSize(300, 200);//On lui donne une taille pour qu'on puisse la voir
        setTitle("Editeur de niveau");

        add(aBtnChrg);
        setVisible(true);//On la rend visible

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
    }

    private String ChoisirDossier()
    {
        JFileChooser vSltDos = new JFileChooser();
        vSltDos.setCurrentDirectory(new java.io.File("."));
        vSltDos.setDialogTitle("choosertitle");
        vSltDos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        vSltDos.setAcceptAllFileFilterUsed(false);

        if (vSltDos.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + vSltDos.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + vSltDos.getSelectedFile());
            return  vSltDos.getSelectedFile().toString();
        } else {
            JOptionPane.showMessageDialog(null, "Vous n'avez rien sélectioné...");
            return "";
        }
    }
}
