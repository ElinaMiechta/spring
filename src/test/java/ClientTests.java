import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class ClientTests {

    @Test
    public void shouldValidateAdress() {

        Pattern pattern = Pattern.compile("^[#.0-9a-zA-Z\\s,-]+$");
        Matcher matcher = pattern.matcher("Greenhill str  165B");
        assertTrue(matcher.find());


    }
}
