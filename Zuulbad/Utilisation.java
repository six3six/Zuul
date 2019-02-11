

/**
 * Décrivez votre classe Utilisation ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Utilisation
{
    public static void essai()
    {
        
        System.out.println("racNeg(40) = " + Calc.racNeg(40));
        System.out.println("sontProches(4, 4+1E-10) = " + Calc.sontProches(4, 4+1E-10));
        System.out.println("sontProches(4, 8) = " + Calc.sontProches(4, 8));
        System.out.println("moities(45) = "); Calc.moities(45);
        System.out.println("affTab(...) = "); affTab(new int[]{ 10, 20, 30, 40 });
        System.out.println("affTab(...) = "); affTabInv(new int[]{ 10, 20, 30, 40 });
        System.out.println("initTab(...) = "); affTab(initTab(new int[10]));
        System.out.println("valeurMinimale(...) = " +  valeurMinimale(new double[]{ 33, 32, 37, 35, 38, 34, 29, 36, 37, 28, 29, 30, 36, 37, 31, 29, 28, 30, 28, 29, 28, 37, 31, 30, 33}));
        System.out.println("indiceDuMinimum(...) = " +  indiceDuMinimum(new double[]{ 33, 32, 37, 35, 38, 34, 29, 36, 37, 28, 29, 30, 36, 37, 31, 29, 28, 30, 28, 29, 28, 37, 31, 30, 33}));
    }
    
    public static void affTab(final int[] pTab)
    {
        for(int i = 0; i<pTab.length; i++)
        {
            System.out.println("tab[" + i + "] = " + pTab[i]);
        }
    }
    
    public static void affTabInv(final int[] pTab)
    {
        for(int i = pTab.length-1; i>0; i--)
        {
            System.out.println("tab[" + i + "] = " + pTab[i]);
        }
    }
    
    public static int[] initTab(int[] pTab)
    {
        for(int i = 0; i<pTab.length; i++)
        {
            pTab[i] = i*2;
        }
        
        return pTab;
    }
    
    public static int somme(int[] pTab)
    {
        int vRes = 0;
        for(int i = 0; i<pTab.length; i++)
        {
            vRes += pTab[i];
        }
        return vRes;
    }
    
    public static int valeurMinimale (int[] pTab)
    {
        int vMin = pTab[0];
        for(int i = 1; i<pTab.length; i++)
        {
            if(vMin > pTab[i])vMin = pTab[i];
        }
        return vMin;
    }
    
    public static double valeurMinimale(double[] pTab)
    {
        double vMin = pTab[0];
        for(int i = 1; i<pTab.length; i++)
        {
            if(vMin > pTab[i])vMin = pTab[i];
        }
        return vMin;
    }
    
    public static int indiceDuMinimum (double[] pTab)
    {
        double vMin = pTab[0];
        int vInd = 0;
        for(int i = 1; i<pTab.length; i++)
        {
            if(vMin > pTab[i])
            {
                vMin = pTab[i];
                vInd = i;
            }
        }
        return vInd;
    }
}
