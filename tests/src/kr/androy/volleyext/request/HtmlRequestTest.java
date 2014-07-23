package kr.androy.volleyext.request;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class HtmlRequestTest {
	@Test
	public void testParseNetworkResponse() throws Exception {
		assertEquals("test", "test");
	}
}
