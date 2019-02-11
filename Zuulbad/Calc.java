

/**
 * Décrivez votre classe Calc ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Calc
{
    public static double racNeg(final double pVal)
    {
        if(pVal > 0) return Math.sqrt(pVal);
        else return Math.sqrt(-pVal);
    }
    
    public static boolean sontProches(final double pVal1, final double pVal2)
    {
        return (pVal2 - 1E-9) < pVal1 && pVal1 < (pVal2 + 1E-9);
    }
    
    public static void moities(final int pVal)
    {
        System.out.println(pVal);
        if(pVal == 1) return;
        moities(pVal/2);
    }
        
}
