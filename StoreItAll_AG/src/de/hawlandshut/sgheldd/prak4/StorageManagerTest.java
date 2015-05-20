package de.hawlandshut.sgheldd.prak4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by s-gheldd on 5/17/15.
 */
public class StorageManagerTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testStorePallet() throws Exception {
        StorageManager warehouse = new StorageManager(2,2,2);
        warehouse.storePallet(new Pallet(),0,0,0);
        warehouse.storePallet(new Pallet(), 0, 0, 1);

        try {
            exception.expect(STORAGE_OCCUPIED_EXCEPTION.class);
            warehouse.storePallet(new Pallet(), 0, 0, 0);
        } catch (STORAGE_OCCUPIED_EXCEPTION message){
            //message.printStackTrace();
        }
        exception.expect(STORAGE_OCCUPIED_EXCEPTION.class);
        warehouse.storePallet(new Pallet(), 0, 0, 5);
    }

    @Test
    public void testGetPalletById() throws Exception {
        StorageManager warehouse = new StorageManager(4,4,4);
        warehouse.storePallet(new Pallet(4).store("Bread", "Bacon", "Beans"),0,0,0);
        Pallet pallet = new Pallet().store("Tomatoes", "Brocoli");
        warehouse.storePallet(pallet,0,0,1);
        String id = pallet.getId();
        assertEquals(pallet, warehouse.getPalletById(id));
        assertNull(warehouse.getPalletById("BlaBla"));

    }

    @Test
    public void testSearchArticle() throws Exception {
        StorageManager warehouse = new StorageManager(4,4,4);
        warehouse.storePallet(new Pallet(4).store("Bread", "Bacon", "Beans"),0,0,0);
        assertEquals("Bread", warehouse.searchArticle("Bread"));
        assertNull(warehouse.searchArticle("Tofu"));
    }

    @Test
    public void testContainsPallet() throws Exception {
        StorageManager warehouse = new StorageManager(4,4,4);
        Pallet pallet;
        warehouse.storePallet(pallet = new Pallet(4).store("Bread", "Bacon", "Beans"),0,0,0);
        assertTrue(warehouse.containsPallet(pallet));
        assertFalse(warehouse.containsPallet(new Pallet(1)));
    }

    @Test
    public void testPrintStorage() throws Exception {
        StorageManager warehouse = new StorageManager(4,4,4);
        warehouse.storePallet(new Pallet(4).store("Bread", "Bacon", "Beans"),0,0,0);
        warehouse.storePallet(new Pallet(2).store("Tofu","Wine"),0,1,1);
        warehouse.printStorage();
    }
}