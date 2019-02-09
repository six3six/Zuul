package v1;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GameTest.
 *
 * @author  DB
 * @version 2016.02.09
 */
public class GameTest
{
    private static String                   sClassName;
    private static String                   sPkg;
    private static String                   sFil;
    private static veref.ClassContent       sCla;
    private static String                   sAttName;
    private static String                   sAttType;
    private static veref.FieldContent       sAtt;
    private static String                   sProtoC;
    private static veref.ConstructorContent sCon;
    private static String                   sMetName;
    private static String                   sMetRT;
    private static String                   sProtoM;
    private static veref.MethodContent      sMet;
    private static int                      sPhase=3;

    /**
     * Default constructor for test class SRoomTest
     */
    public GameTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        sPkg       = "v1";
        sClassName = "Game";
        veref.ClassContent.setRefPkg( sPkg );
        sFil = sPkg + "/" + sClassName + ".java";
        
        sAttName = "aCurrentRoom";
        sAttType = "Room";
        
        sProtoC = "()";
        veref.V.error( "===== ===== ===== ===== =====" );
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testClasse_1()
    {
        sPhase = 1;
        sCla = veref.V.getVClaFName( sClassName );
    } // testClasse_1()

    @Test
    public void testAttribut_2()
    {
        testClasse_1();
        sPhase++;
//         veref.V.verifNbAttOp( vCla, "==", 1 );
//         veref.FieldContent vAtt = veref.V.getAttFType( vCla, "String" );
//         veref.V.failIfNot();
//         veref.V.verifModAttribut( vAtt, "private", "static final" );
//         veref.V.verifNomAttribut( vAtt );
//         veref.V.vrai( vAttName.equals( vAtt.fieldName() ), "$n n'est pas le nom attendu pour l'attribut" );
//         veref.V.mesIfNot();
        sAtt = veref.V.getV1AttFTN( sCla, sAttType, sAttName );
    } // testAttribut_2()
    
    @Test
    public void testcreateRooms_3()
    {
        testAttribut_2();
        sPhase++;
        sMetName = "createRooms";
        sMetRT   = "void";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );
//         veref.V.vrai( veref.V.nbMet( sCla ) <= 1 ,
//                       "Il y a au moins une methode de trop a la phase " + sPhase );
        veref.V.mesIfNot();      
        veref.V.verifAttThisDebug(sFil,"Room","aCurrentRoom",false);      
    } // testcreateRooms_3()
        
    @Test
    public void testConstructeur_4()
    {
        testcreateRooms_3();
        sPhase++;
        veref.V.sDefCo = veref.V.hasCoDebug( sFil, sClassName, "public", false );
        veref.V.verifDefCon( sCla, "T" );
        sCon = veref.V.premierConstructeur( sCla );
        veref.V.vrai( sCon.getNbParameters()==0, "Le constructeur n'a pas le bon nombre de parametres !" );
        veref.V.failIfNot();
        veref.V.verifModCon( sCon, "public", "static final" );
        veref.V.vrai( veref.V.nbCon( sCla ) <= 1 , "Il y a au moins un constructeur de trop ..." );
        veref.V.mesIfNot();
        veref.V.error( sPhase+": Signature du constructeur verifiee." );
        if ( sPhase <= 4 )
        try {
            Game vObj = (Game)sCon.newInstance( new Object[]{} );
            java.lang.reflect.Field vF = sAtt.getField( sCla.getClassName(), sAttName );
            Object vRes = sAtt.fieldValue( vObj );
            veref.V.vrai( vRes.equals("null"), "Le lieu de depart n'est pas initialise !?" );
            veref.V.failIf();
        }
        catch ( final Exception pE ) {
            veref.V.vrai( false, pE+" + On ne peut pas appeler le constructeur !?" );
            veref.V.failIfNot();
        }
    } // testConstructeur_4()

    @Test
    public void testgoRoom_5()
    {
        testConstructeur_4();
        sPhase++;
        sMetName = "goRoom";
        sMetRT   = "void";
        sProtoM  = "( " + sPkg + ".Command p1 )";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.verifFinal1Type( sFil, sMetName, "Command" );
        veref.V.verifParamP1Type( sFil, sMetName, "Command" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 2 ,
                      "Il y a au moins une methode de trop a la phase " + sPhase );
        veref.V.mesIfNot();        
        veref.V.verifAttThisDebug(sFil,"Room","aCurrentRoom",false);      
    } // testgoRoom_5()
        
    @Test
    public void testprint_6()
    {
        testConstructeur_4();
        sPhase++;
        sMetName = "printWelcome";
        sMetRT   = "void";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );

        sMetName = "printHelp";
        sMetRT   = "void";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 4 ,
                      "Il y a au moins une methode de trop a la phase " + sPhase );
        veref.V.mesIfNot();        
    } // testprint_6()
    
    @Test
    public void testquit_7()
    {
        testConstructeur_4();
        sPhase++;
        sMetName = "quit";
        sMetRT   = "boolean";
        sProtoM  = "( " + sPkg + ".Command p1 )";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.verifFinal1Type( sFil, sMetName, "Command" );
        veref.V.verifParamP1Type( sFil, sMetName, "Command" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 5 ,
                      "Il y a au moins une methode de trop a la phase " + sPhase );
        veref.V.mesIfNot();        
    } // testquit_7()
    
    @Test
    public void testprocessCommand_8()
    {
        testConstructeur_4();
        sPhase++;
        sMetName = "processCommand";
        sMetRT   = "boolean";
        sProtoM  = "( " + sPkg + ".Command p1 )";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.verifFinal1Type( sFil, sMetName, "Command" );
        veref.V.verifParamP1Type( sFil, sMetName, "Command" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 6 ,
                      "Il y a au moins une methode de trop a la phase " + sPhase );
        veref.V.mesIfNot();        
    } // testprocessCommand_8()

} // GameTest
