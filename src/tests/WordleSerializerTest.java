package tests;

import model.WordleSerializer;
import org.junit.Test;

public class WordleSerializerTest {

	@Test
	public void test() {
		WordleSerializer ws = new WordleSerializer();
		System.out.println(ws.getMap().toString());
	}


}
