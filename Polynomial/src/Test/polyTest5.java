package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Lab1.Poly;

public class polyTest5 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExpression() {
		assertEquals("�Ƿ�����",new Poly().expression("x*y            )*zz+2*3*z"));
	}

}
