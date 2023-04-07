package tests;

import model.WordleAccount;
import model.WordleSerializer;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordleSerializerTest {

	@Test
	public void getAccounts() {
		WordleSerializer ws = new WordleSerializer();
		System.out.println(ws.getAccounts());
	}


	/**
	 * can only run this test if the accounts.ser file is deleted
	 */
//	@Test
//	public void testSave(){
//		WordleSerializer ws = new WordleSerializer();
//		ws.createNewUser("jackson","burns");
//		ws.saveAccounts();
//	}

	@Test
	public void update() {
		WordleSerializer ws = new WordleSerializer();
		WordleAccount jackson = ws.getAccounts().get(0);
		jackson.updateScore(5);
		ws.update(jackson);
		assertEquals(ws.getAccounts().get(0).getScore()[4],1);
		assertEquals(ws.getAccounts().get(0).getScore()[3],0);
		jackson.updateScore(5);
		assertEquals(ws.getAccounts().get(0).getScore()[4],2);


	}

	@Test
	public void testUser() {
		WordleSerializer ws = new WordleSerializer();
		assertNotNull(ws.verifyLogin("jackson","burns"));
		assertNull(ws.verifyLogin("jackson","burns2"));
	}

	@Test
	public void getMap() {
		WordleSerializer ws = new WordleSerializer();
		System.out.println(ws.getMap());
	}
}
