/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gps.nmea;

import org.junit.*;

/**
 *
 * @author Parham
 */
public class ChecksumTest {

    public ChecksumTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isValid method, of class Checksum.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid Checksum Test");
        Checksum instance = new Checksum("$GPVTG,139.4,T,139.4,M,0.14,N,0.26,K*4F");
        boolean expResult = true;
        boolean result = instance.isValid();
        Assert.assertEquals(expResult, result);
        instance = new Checksum("$GPVTG,139.4,T,139.4,M,0.14,N,0.26,K*5F");
        expResult = false;
        result = instance.isValid();
        Assert.assertEquals(expResult, result);
    }
}
