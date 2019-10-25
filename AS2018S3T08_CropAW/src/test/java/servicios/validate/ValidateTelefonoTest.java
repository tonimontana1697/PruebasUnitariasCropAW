package servicios.validate;

import dao.PersonalImpl;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateTelefonoTest {
    
    public ValidateTelefonoTest() {
    }
    
    @Test
    public void testGetFacade() {
        System.out.println("getFacade");
        ValidateTelefono instance = new ValidateTelefono();
        PersonalImpl expResult = instance.getFacade();
        PersonalImpl result = instance.getFacade();
        assertEquals(expResult, result);
        if (expResult != result){
            fail("El resultado debe de se igual en ambas partes");
        }
        
    }
    
}
