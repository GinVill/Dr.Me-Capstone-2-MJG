package app;

import entities.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game testGame;
    Player testPlayer;


    @Before
    public void setUp() throws Exception {
        testGame = new Game();
        testPlayer = new Player();
    }

    @Test
    public void testQuestionsInCurrentOrganShouldReturnThree() {
        // there are only three questions related to mouth should return 3.
        assertEquals(3, testGame.questionsInOrgan(XMLController.readPathogenXML(), "mouth"));
    }

    @Test
    public void testQuestionsInCurrentOrganShouldReturnZero() {
        assertEquals(0, testGame.questionsInOrgan(XMLController.readPathogenXML(), "notOrgan"));
    }

    @Test
    public void testISWinWithRequiredPointsToWin() {
        //player points set to required win threshold
        testPlayer.setPlayerPoints(100);
        //required points for win set to 100
        int testRequiredPoints = 100;

        assertTrue(testGame.checkForWin(testPlayer, testRequiredPoints));
    }

    @Test
    public void testISWinFalseWithBelowRequiredPoints() {
        //player points set to below required win threshold
        testPlayer.setPlayerPoints(99);
        //required points for win set to 100
        int testRequiredPoints = 100;

        assertFalse(testGame.checkForWin(testPlayer, testRequiredPoints));
    }

    @Test
    public void testISWinWithPlayerPointsOverRequiredPoints() {
        //player points set to above required win threshold
        testPlayer.setPlayerPoints(101);
        //required points for win set to 100
        int testRequiredPoints = 100;

        assertTrue(testGame.checkForWin(testPlayer, testRequiredPoints));
    }

    @Test
    public void testisDeadWithPlayerHealthAtZero() {
        //player health is set to death threshold
        testPlayer.setHealth(0);

        assertTrue(testGame.checkForDeath(testPlayer));
    }

    @Test
    public void testisDeadWithPlayerHealthAboveZero() {
        //player health is set to death threshold
        testPlayer.setHealth(1);
        assertFalse(testGame.checkForDeath(testPlayer));
    }

    @Test
    public void testisDeadWithPlayerHealthBelowZero() {
        //player health is set to death threshold
        testPlayer.setHealth(-1);
        assertTrue(testGame.checkForDeath(testPlayer));
    }

}