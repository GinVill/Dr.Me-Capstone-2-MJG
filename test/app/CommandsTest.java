package app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class CommandsTest {


    @Before
    public void setUp() throws Exception {
        Commands.loadWordXMLfile();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void handleCommandWithEmptyString() {
        String cmd = "";
        String pathname = "";


        assertFalse(Commands.handleCommand(cmd,pathname));
        System.out.println("empty");
    }

    @Test
    public void handleCommandWithNullString(){
        String cmd = null;
        String pathname = null;


        assertFalse(Commands.handleCommand(cmd, pathname));
        System.out.println("null");
    }


    @Test
    public void hintTest(){
        String name = "Salmonella";
        XMLController.readXML();
        // TODO change the following to check for false && verify the below prints out in terminal
        //  previously it test correct until we changed return type.
       // assertEquals("It looks like this pathogen is able to produce energy regardless of its surroundings. Perhaps a general attack can do the trick.", Commands.hint(name));
    }

    @Test
    public void wordMatchTest() {
        Commands.loadWordXMLfile();
        List<String> test1 = Arrays.asList(new String[]{"locate", "ramdom", "word"});
        List<String> test2 = Arrays.asList(new String[]{"test", "ramdom", "word"});

        assertEquals("find", Commands.wordMatch(test1));
        assertEquals("no match", Commands.wordMatch((test2)));
    }

    @Test
    public void loadWordXMLfileTest() {
        assertNotNull(Commands.loadWordXMLfile());
        assertEquals(4,Commands.loadWordXMLfile().keySet().size());
    }
}