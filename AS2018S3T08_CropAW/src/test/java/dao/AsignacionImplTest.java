package dao;

import java.util.List;
import modelo.AsignacionPersona;
import org.junit.Test;
import static org.junit.Assert.*;

public class AsignacionImplTest {
    
    public AsignacionImplTest() {
    }

    
    @Test
    public void testListaEstado() throws Exception {
        System.out.println("listaEstado");
        String estado = "A";
        AsignacionImpl instance = new AsignacionImpl();
        //List<AsignacionPersona> expResult = null;
        List<AsignacionPersona> result = instance.listaEstado(estado);
        assertEquals(result.size(), 5);
        if (result.size() != 5){
            fail("La cantidad de datos de la lista no es lo esperado");
        } 
        
    } 
    
}
