package v1;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CommandTest.
 *
 * @author  DB
 * @version 2015.02.01
 */
public class CommandTest
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

    /**
     * Default constructor for test class SRoomTest
     */
    public CommandTest()
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
    public void testClasse_1_1()
    {
        sPkg       = "v1";
        sClassName = "Command";
        veref.ClassContent.setRefPkg( sPkg );
        sFil = sPkg + "/" + sClassName + ".java";
//         veref.V.verifClaY( vClassName );
//         veref.ClassContent vCla = veref.V.getClaFName( vClassName );
        sCla = veref.V.getVClaFName( sClassName );
    } // testClasse_1()

    @Test
    public void testAttributs_2()
    {
        testClasse_1_1();
        veref.V.verifNbAttOp( sCla, "==", 2 );
        auxTest2( "Command" );
        auxTest2( "Second" );
    } // testAttributs_5()
    
    private void auxTest2( final String pN )
    {
        sAttName = "a" + pN + "Word";
        sAttType = "String";
        veref.FieldContent vAtt = veref.V.getAttFTN( sCla, sAttType, sAttName );
        veref.V.failIfNot();
        veref.V.verifModAttribut( vAtt, "private", "static final" );
//         sAtt = veref.V.getV1AttFTN( sCla, sAttType, sAttName );
        veref.V.verifAttThis( sFil, sAttType, sAttName );
    } // auxTest2(.)
    
    @Test
    public void testConstructeur_3()
    {
        testAttributs_2();
        sProtoC = "( String p1, String p2 )";
//         veref.V.verifDefCon( vCla, "F" );
//         veref.V.verifConNbP( vCla, 1, "T" );
//         veref.ConstructorContent vCon = veref.V.getConFProto( vCla, vProtoC, "T" );
//         veref.V.verifModCon( vCon, "public", "static final" );
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        for ( int vI=1; vI<=2; vI++ ) {
          String vRet = veref.V.verifFinalN( sFil, sClassName, vI );
          veref.V.vrai( vRet.equals( "OK" ), vRet );
          veref.V.failIfNot();        
          veref.V.verifParamPNType( sFil, sClassName, "String", vI );
        } // for
        veref.V.vrai( veref.V.nbCon( sCla ) <= 1 , "Il y a au moins un constructeur de trop ..." );
        veref.V.mesIfNot(); 
        String vValue1 = "le Command Word";
        veref.FieldContent vAtt1 = veref.V.selAttFTN( sCla, sAttType, "aCommandWord" );
        String vValue2 = "le Second Word";
        veref.FieldContent vAtt2 = veref.V.selAttFTN( sCla, sAttType, "aSecondWord" );
        veref.V.getAndVerifIns2( sCon, vAtt1, vValue1, vAtt2, vValue2 ); //object discarded
    } // testConstructeur_3()
    
    @Test
    public void testAccesseurs_4()
    {
        testConstructeur_3();
        auxTest4( "CommandWord" );
        auxTest4( "SecondWord" );
    } // testAccesseurs_4()
    
    private void auxTest4( final String pName )
    {
        testConstructeur_3();
        sMetName = "get" + pName;
        sMetRT   = "String";
        sProtoM  = "()";
//         veref.V.verifMet( vCla, vMetName, "T" );
//         veref.V.verifMetRT( vCla, vMetName, vMetRT, "T" );
//         veref.V.verifMetRTNP( vCla, vMetName, vMetRT, 0, "T" );
//         veref.MethodContent vMet = veref.V.getMetFProto( vCla, vMetName, vMetRT, vProtoM, "T" );
//         veref.V.verifModMet( vMet, "public", "static final" );
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "public" );
//         veref.V.verifFinal( sFil, vMetName, vProtoM ); // inutile pour 0 param
//         veref.V.vrai( veref.V.nbMet( sCla ) <= 2 , "Il y a au moins une methode de trop ..." );
//         veref.V.mesIfNot();        
//         String vValue2 = pName + " de l'attribut";
//         Object vObj2 = veref.V.getAndVerifIns1( sCon, sAtt, vValue2 );
//         veref.V.verifGetter( vObj2, sMet, vValue2 );
        
        veref.V.verifAttThis( sFil, sAttType, sAttName );
    } // auxTest4()
    
    @Test
    public void testHSW_5()
    {
        testAccesseurs_4();
        sMetName = "hasSecondWord";
        sMetRT   = "boolean";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "public" );
//         veref.V.vrai( veref.V.nbMet( sCla ) <= 2 , "Il y a au moins une methode de trop ..." );
//         veref.V.mesIfNot();        
    } // testsetExits_6()
    
    @Test
    public void testIU_6()
    {
        testHSW_5();
        sMetName = "isUnknown";
        sMetRT   = "boolean";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "public" );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 4 , "Il y a au moins une methode de trop ..." );
        veref.V.mesIfNot(); 
        // A decommenter une fois que testIU_6 succeeded :
//         Command vC12 = new Command( "un", "deux" );
//         veref.V.vrai( !vC12.isUnknown() && vC12.hasSecondWord(), "pb pour un,deux" ); veref.V.failIfNot();
//         Command vC10 = new Command( "un", null );
//         veref.V.vrai( !vC10.isUnknown() && !vC10.hasSecondWord(), "pb pour un,null" ); veref.V.failIfNot();
//         Command vC02 = new Command( null, "deux" );
//         veref.V.vrai( vC02.isUnknown() && vC02.hasSecondWord(), "pb pour null,deux" ); veref.V.failIfNot();
//         Command vC00 = new Command( null, null );
//         veref.V.vrai( vC00.isUnknown() && !vC00.hasSecondWord(), "pb pour null,null" ); veref.V.failIfNot();
    } // testsetExits_6()
}
