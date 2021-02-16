package app;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game game = new Game();

    @Test
    public void testQuestionsInCurrentOrganShouldReturnThree(){
        // there are only three questions related to mouth should return 3.
        assertEquals(3,game.questionsInOrgan(XMLController.readPathogenXML(),"mouth"));
    }

}