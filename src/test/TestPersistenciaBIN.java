package test;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import persistencia.PersistenciaBIN;


public class TestPersistenciaBIN {

	private PersistenciaBIN archivo = null;

	@Before
	public void setUp() throws Exception {
		this.archivo = new PersistenciaBIN();
	}
	
	@Test
	public void testAbrirInputLanzaIOException() {
        try {
            archivo.abrirInput("ArchivoInexistente.txt");
            Assert.fail("Se esperaba una IOException al intentar abrir un archivo inexistente");
        } catch (IOException e) {
            Assert.assertTrue("La excepción lanzada debe ser IOException", e instanceof IOException);
        }
    }
	
	@Test
	public void testAbrirOutputLanzaIOException() {
        try {
            archivo.abrirInput("ArchivoInexistente.txt");
            Assert.fail("Se esperaba una IOException al intentar abrir un archivo inexistente");
        } catch (IOException e) {
            Assert.assertTrue("La excepción lanzada debe ser IOException", e instanceof IOException);
        }
    }
	
}