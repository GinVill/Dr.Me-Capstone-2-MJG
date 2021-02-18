package app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XMLGUIMainControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parseXML() {
        System.out.println(XMLController.readPathogenXML().size());
    }
}