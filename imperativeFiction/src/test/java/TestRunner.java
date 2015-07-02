/**
 * Created by developer on 7/1/15.
 */
import org.junit.Test;
import org.tads.gameRunner.GameRunner;
//import org.testng.Assert;
//import org.testng.annotations.Test;

import java.io.IOException;

public class TestRunner {

	@Test
	public void testGameRunner() throws IOException {
		GameRunner gameRunner = new GameRunner();
		gameRunner.runGame("/home/developer/caf/projects/tads/tadsForJava/src/main/resources/games/above.gam");
		//gameRunner.runGame("games/Dead_Mans_Party.t3");
	}
}
