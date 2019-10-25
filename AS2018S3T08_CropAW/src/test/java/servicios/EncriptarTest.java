package servicios;

import org.junit.Test;
import static org.junit.Assert.*;

public class EncriptarTest {
    
    public EncriptarTest() {
    }

    @Test
    public void testEncriptar() {
        System.out.println("encriptar");
        String input = "toni";
        String expResult = "aefe34008e63f1eb205dc4c4b8322253";
        String result = Encriptar.encriptar(input);
        assertEquals(expResult, result);
//        if (expResult != result) {
//            fail("La encriptacion no es igual");
//        }        
    }
    
}
