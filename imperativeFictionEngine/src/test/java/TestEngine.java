import junit.framework.Assert;
import org.imperativeFiction.engine.GameEngine;
import org.imperativeFiction.engine.GameException;
import org.junit.Test;

/**
 * Created by developer on 7/21/15.
 */
public class TestEngine {

	@Test
	public void testEngine() {
		String gameFileName = "/home/developer/caf/projects/tads/imperativeFictionEngine/src/test/resources/testGame.xml";
		GameEngine engine = new GameEngine();
		try {
			engine.runGame(gameFileName);
		} catch (GameException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
