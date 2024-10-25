package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import modeloDatos.Cliente;
import modeloDatos.Usuario;
import modeloNegocio.Empresa;

public class TestEmpresaLogin {
	private Empresa empresa=null;
	private Usuario usuario=null;

	@Before
	public void setUp() throws Exception {
		this.empresa=Empresa.getInstance();
		this.empresa.agregarCliente("grego","1234","Gregorio Figueras");
		this.usuario= new Cliente("grego","1234","Gregorio Figueras");
	}

	@After
	public void tearDown() throws Exception {
		this.empresa.getClientes().clear();
	}

	@Test
	public void testLogin() {
		try {
			Assert.assertEquals("Error en login",this.empresa.getClientes().get("grego"),this.empresa.login("grego", "1234"));
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
		} catch (PasswordErroneaException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLoginUsuarioNoExisteException() {
		try {
			Assert.assertEquals("Error en login",this.usuario,this.empresa.login("fern", "1234"));
			fail("Deberia haber lanzado UsuarioNoExisteException");
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
		} catch (PasswordErroneaException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLoginPasswordErroneaException() {
		try {
			Assert.assertEquals("Error en login",this.usuario,this.empresa.login("grego", "12345"));
			fail("Deberia haber lanzado UsuarioNoExisteException");
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
		} catch (PasswordErroneaException e) {
			e.printStackTrace();
		}
	}

}
