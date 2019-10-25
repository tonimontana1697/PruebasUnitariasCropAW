
package controlador;

import java.util.List;
import modelo.Sector;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Loayza
 */
public class SectorControllerTest {
    
    public SectorControllerTest() {
    }

    @Test
    public void testPrepareCreate() {
        System.out.println("prepareCreate");
        SectorController instance = new SectorController();
        Sector expResult = null;
        Sector result = instance.prepareCreate();
        assertNotEquals(expResult, result);
        if (expResult == result){
            fail("La respuestra no debe ser igual");
        }
        
    }    
    
}
