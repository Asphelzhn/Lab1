package Lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class polyTestw1 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDerivation() {
		assertEquals("2*x+24*y+x+2*x*num",new poly().derivation("x"));
	}

}
