package de.hawlandshut.sgheldd.prak4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by s-gheldd on 5/13/15.
 */
public class PalletTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testStore() throws Exception {
        Pallet pallet = new Pallet(2);
        pallet.store("Beans");
        pallet.store("Bread");
        exception.expect(PALLET_FULL_EXCEPTION.class);
        pallet.store("Bacon");
    }

    @Test
    public void testSearchArticle() throws Exception {
        Pallet pallet = new Pallet(3);
        pallet.store("Beans");
        pallet.store("Bread");
        assertEquals("Beans", pallet.searchArticle("Beans"));
        assertEquals("Bread", pallet.searchArticle("Bread"));
        assertNull(pallet.searchArticle("Bacon"));
    }

    @Test
    public void testCopyConstructor() throws Exception{
        Pallet palletA = new Pallet();
        palletA.store("Bread");
        Pallet palletB = new Pallet(palletA);
        assertEquals(palletA.searchArticle("Bread"),palletB.searchArticle("Bread"));
    }


    @Test
    public void testEquals() throws Exception {
        Pallet palletA = new Pallet();
        palletA.store("Bread", "Beans" , "Bacon");
        Pallet palletB = new Pallet(palletA);
        assertTrue(palletA.equals(palletB));
    }
}