package Lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class polyTest3 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExpression() {
		assertEquals("�Ƿ�����",new poly().expression("x*y*z-z+2*3*z"));
	}

}