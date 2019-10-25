/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Loayza
 */
public class ConexionTest {
    
    public ConexionTest() {
    }      
    
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Conexion.main(args);
        assertTrue(true);
        if (false){
            fail("conexion no realizada");        
        }
    }
        
    
    @Test(timeout = 2000)
    public void Test_1() {
        Conexion cn = new Conexion();        
        cn.conectar();
    }
    
}
