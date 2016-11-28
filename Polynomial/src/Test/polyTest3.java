package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Lab1.Poly;

public class polyTest3 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExpression() {
		assertEquals("∑«∑® ‰»Î",new Poly().expression("x*y*z-z+2*3*z"));
	}

}
