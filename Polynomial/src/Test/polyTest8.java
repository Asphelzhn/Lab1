package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Lab1.Poly;

public class polyTest8 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExpression() {
		assertEquals("∑«∑® ‰»Î",new Poly().expression("!hahah x=2"));
	}

}
