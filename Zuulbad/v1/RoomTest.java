package v1;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RoomTest.
 *
 * @author  DB
 * @version 2015.02.01
 */
public class RoomTest
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
    private static int                      sStep=1;

    /**
     * Default constructor for test class SRoomTest
     */
    public RoomTest()
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
        sClassName = "Room";
        veref.ClassContent.setRefPkg( sPkg );
        sFil = sPkg + "/" + sClassName + ".java";
        
        sAttName = "aDescription";
        sAttType = "String";
        
        sProtoC = "( String p1 )";
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
        sCla = veref.V.getVClaFName( sClassName );
    } // testClasse_1()

    @Test
    public void testAttribut_2()
    {
        testClasse_1_1();
//         veref.V.verifNbAttOp( vCla, "==", 1 );
//         veref.FieldContent vAtt = veref.V.getAttFType( vCla, "String" );
//         veref.V.failIfNot();
//         veref.V.verifModAttribut( vAtt, "private", "static final" );
//         veref.V.verifNomAttribut( vAtt );
//         veref.V.vrai( vAttName.equals( vAtt.fieldName() ), "$n n'est pas le nom attendu pour l'attribut" );
//         veref.V.mesIfNot();
           sAtt = veref.V.getV1AttFTN( sCla, sAttType, sAttName, sStep );
    } // testAttribut_2()
    
    @Test
    public void testConstructeur_3()
    {
        testAttribut_2();
//         veref.V.verifDefCon( vCla, "F" );
//         veref.V.verifConNbP( vCla, 1, "T" );
//         veref.ConstructorContent vCon = veref.V.getConFProto( vCla, vProtoC, "T" );
//         veref.V.verifModCon( vCon, "public", "static final" );
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        veref.V.verifFinal1Type( sFil, sClassName, "String" );
        veref.V.verifParamP1Type( sFil, sClassName, "String" );
        veref.V.vrai( veref.V.nbCon( sCla ) <= 1 , "Il y a au moins un constructeur de trop ..." );
        veref.V.mesIfNot();        
        String vValue1 = "Room du constructeur";
        veref.V.getAndVerifIns1( sCon, sAtt, vValue1 ); //object discarded
        
        veref.V.verifAttThis( sFil, sAttType, sAttName );
    } // testConstructeur_3()
    
    @Test
    public void testAccesseur_4()
    {
        testConstructeur_3();
        sMetName = "getDescription";
        sMetRT   = "String";
        sProtoM  = "()";
//         veref.V.verifMet( vCla, vMetName, "T" );
//         veref.V.verifMetRT( vCla, vMetName, vMetRT, "T" );
//         veref.V.verifMetRTNP( vCla, vMetName, vMetRT, 0, "T" );
//         veref.MethodContent vMet = veref.V.getMetFProto( vCla, vMetName, vMetRT, vProtoM, "T" );
//         veref.V.verifModMet( vMet, "public", "static final" );
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "public" );
//         veref.V.verifFinal( sFil, vMetName, vProtoM ); // inutile pour 0 param
        veref.V.vrai( veref.V.nbMet( sCla ) <= 1 , "Il y a au moins une methode de trop ..." );
        veref.V.mesIfNot();        
        String vValue2 = "Room de l'attribut";
        Object vObj2 = veref.V.getAndVerifIns1( sCon, sAtt, vValue2 );
        veref.V.verifGetter( vObj2, sMet, vValue2 );
        
        veref.V.verifAttThis( sFil, sAttType, sAttName );
    } // testAccesseur_4()

    @Test
    public void testAttributs_5()
    {
        sStep=5; // nbs attribut
        testConstructeur_3();
        auxTest5( "North" );
        auxTest5( "East" );
        auxTest5( "South" );
        auxTest5( "West" );
        veref.V.verifNbAttOp( sCla, "==", 5 );

        veref.ConstructorsContent vLCo;
        for ( int vNP=5; vNP>=2; vNP-- )
            vLCo = veref.V.verifConstructorNP( sClassName, vNP, "F" );
    } // testAttributs_5()
    
    private void auxTest5( final String pDir )
    {
        sAttName = "a" + pDir + "Exit";
        sAttType = "Room";
        veref.FieldContent vAtt = veref.V.getAttFTN( sCla, sAttType, sAttName );
        veref.V.failIfNot();
        veref.V.verifModAttribut( vAtt, "public", "static final" );
        veref.V.verifAttThis( sFil, sAttType, sAttName );
//         sAtt = veref.V.getV1AttFTN( sCla, sAttType, sAttName );
    } // auxTest5(.)
    
    @Test
    public void testsetExits_6()
    {
        testAttributs_5();
        sMetName = "setExits";
        sMetRT   = "void";
        sProtoM  = "( v1.Room p1, v1.Room p2, v1.Room p3, v1.Room p4 )";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "public" );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 2 , "Il y a au moins une methode de trop ..." );
        veref.V.mesIfNot();        
//         veref.V.verifFinal( sFil, vMetName, vProtoM ); // inutile pour 0 param
        for ( int vI=1; vI<=4; vI++ ) {
          String vRet = veref.V.verifFinalN( sFil, sMetName, vI );
          veref.V.vrai( vRet.equals( "OK" ), vRet );
          veref.V.failIfNot();        
        } // for
        // TODO: manque verif apres invoke : 4 attributs = 4 valeurs
        Object vIns = null;
        Room vRoom = null;
        try {
            Room vRN = (Room)(sCon.newInstance( new Object[]{ "au Nord" } ));
            Room vRE = (Room)(sCon.newInstance( new Object[]{ "a l'Est" } ));
            Room vRS = (Room)(sCon.newInstance( new Object[]{ "au Sud" } ));
            Room vRO = (Room)(sCon.newInstance( new Object[]{ "a l'Ouest" } ));
            vIns = sCon.newInstance( new Object[]{ "room description" } );
            vRoom = (Room)vIns;
            sMet.invoke( vRoom, new Object[]{ vRN, vRE, vRS, vRO } );
            veref.FieldContent sAttN = veref.V.getAttFTN( sCla, "Room", "aNorthExit" );
            veref.FieldContent sAttE = veref.V.getAttFTN( sCla, "Room", "aEastExit" );
            veref.FieldContent sAttS = veref.V.getAttFTN( sCla, "Room", "aSouthExit" );
            veref.FieldContent sAttO = veref.V.getAttFTN( sCla, "Room", "aWestExit" );
            boolean vCond = sAttN.fieldValue( vIns ).equals(vRN.toString())           
                         && sAttE.fieldValue( vIns ).equals(vRE.toString())           
                         && sAttS.fieldValue( vIns ).equals(vRS.toString())            
                         && sAttO.fieldValue( vIns ).equals(vRO.toString());            
            veref.V.vrai( vCond, "Mauvaises valeurs dans les sorties !" );
            veref.V.failIfNot();        
        } catch( final Exception pE ) {
            fail( "la methode " + sMet.getName() /*+ sMet.getParameterString()*/
                + " genere l'exception " + pE );
        } // t/c

    } // testsetExits_6()
} // RoomTest
