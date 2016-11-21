package Lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class polyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExpression() {
		assertEquals("a*b*c+4*25*z+2*num*num+a*a*a",new poly().expression("a*b*c+4*25	*z+2*num*num+a ^3"));
	}

}
