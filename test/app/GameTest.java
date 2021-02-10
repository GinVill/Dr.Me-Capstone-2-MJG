package app;

import entities.Pathogen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    Game game = new Game();

    @Test
    public void testQuestionsInCurrentOrganShouldReturnThree(){
        // there are only three questions related to mouth should return 3.
        assertEquals(3,game.questionsInOrgan(XMLController.readXML(),"mouth"));
    }

}