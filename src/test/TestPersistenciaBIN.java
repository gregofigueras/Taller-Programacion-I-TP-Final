package test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Moto;
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
            Assert.fail("Se esperaba una IOException al intentar abrir un archivo inexistente en abrir input");
        } catch (IOException e) {
            Assert.assertTrue("Lanzo excepcion  incorrecta al abrir input por un archivo inexistente", e instanceof IOException);
        }
    }
	
	@Test
	public void testAbrirOutputLanzaIOException() {
        try {
            archivo.abrirInput("ArchivoInexistente.txt");
            Assert.fail("Se esperaba una IOException al intentar abrir un archivo inexistente en Abrir Output");
        } catch (IOException e) {
            Assert.assertTrue("Lanzo excepción  incorrecta al  abrir output por un archivo inexistente", e instanceof IOException);
        }
    }
	
	@Test
	public void testCerrarOutputLanzaIOExcepction() {
		try {
			archivo.cerrarOutput();
			 Assert.fail("Se esperaba una IOException al intentar cerrar un archivo que nunca se abrio en Cerrar Output");
		}catch (IOException e){
			Assert.assertTrue("Lanzo excepción incorrecta al cerrar output con un archivo que no fue abierto", e instanceof IOException);
		}
	}
	
	@Test
	public void testCerrarInputLanzaIOExcepction() {
		try {
			archivo.cerrarInput();
			Assert.fail("Se esperaba una IOException al intentar cerrar un archivo que nunca se abrio en cerrar Input");
		}catch (IOException e){
			Assert.assertTrue("Lanzo excepción incorrecta al cerrar Input con un archivo que no fue abierto", e instanceof IOException);
		}
	}
	
	@Test
	public void testEscribirLanzaException() {
		try {
			Moto moto = new Moto("ABC123");
			archivo.escribir(moto);
			Assert.fail("Se esperaba una IOException al intentar escribir un archivo que nunca se abrio");
		}catch(IOException e) {
			Assert.assertTrue("Lanzo excepción incorrecta al escribir con un archivo que no fue abierto", e instanceof IOException);
		}
	}
	
	@Test
	public void testLeerLanzaIOException() {
		try {
			Serializable objeto = archivo.leer();
			Assert.fail("Se esperaba una IOException al intentar leer un archivo que nunca se abrio");
		}catch (ClassNotFoundException e) {
            Assert.fail("No se esperaba ClassNotFoundException en este caso");
        }catch(IOException e) {
			Assert.assertTrue("Lanzo excepción incorrecta al leer con un archivo que no fue abierto", e instanceof IOException);
		}
	}
	
	public void testLeerLanzaClassNotFoundException() {
		try {
			archivo.abrirInput("archivoExistente.txt");
			Serializable objeto = archivo.leer();
			Assert.fail("Se esperaba una ClassNotFoundException al intentar leer una clase desconocida");
		} catch (ClassNotFoundException e) {
            Assert.assertTrue("Se esperaba una ClassNotFoundException", e instanceof ClassNotFoundException);
        } catch (IOException e) {
            Assert.fail("No se esperaba IOException en este caso");
        }
	}
	
	 @Test
	    public void testEscribirYLeerObjeto() {
	        try {
	        	Moto motoEsperada = new Moto("ABC123");
	            
	        	archivo.abrirOutput("archivoExistente.txt");
	            archivo.escribir(motoEsperada);
	            archivo.cerrarOutput();
	            
	            archivo.abrirInput("archivoExistente.txt");
	            Moto objetoLeido = (Moto) archivo.leer();
	            archivo.cerrarInput();

	            Assert.assertEquals("El objeto leído no coincide con el objeto escrito", motoEsperada.getPatente() , objetoLeido.getPatente());
	        } catch (IOException e) {
	            Assert.fail("No se esperaba una IOException: " + e.getMessage());
	        } catch (ClassNotFoundException e) {
	            Assert.fail("No se esperaba una ClassNotFoundException: " + e.getMessage());
	        }
	    }
	
}