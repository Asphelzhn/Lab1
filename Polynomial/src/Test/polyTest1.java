package Test;

import static org.junit.Assert.*;
import Lab1.Poly;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class polyTest1 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("��",new Poly().expression("!simplify x=2"));
	}

}
