
package controlador;

import java.util.List;
import modelo.Cultivo;
import org.junit.Test;
import static org.junit.Assert.*;


public class CultivoControllerTest {
    
    public CultivoControllerTest() {
    }

   
    @Test
    public void testComplete() throws Exception {
        System.out.println("complete");
        String nomcul = "Limon";
        CultivoController instance = new CultivoController();
        List<Cultivo> expResult = instance.complete(nomcul);
        List<Cultivo> result = instance.complete(nomcul);
        assertEquals(expResult, result);
        if (result != result){
            fail("Error el resultado es distinto.");
        }        
    }    
    
}
