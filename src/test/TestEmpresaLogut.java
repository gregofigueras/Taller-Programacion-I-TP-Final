package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Usuario;
import modeloNegocio.Empresa;

public class TestEmpresaLogut {
	private Empresa empresa=null;
	private Usuario usuario=null;

	@Before
	public void setUp() throws Exception {
		this.empresa=Empresa.getInstance();
		this.empresa.agregarCliente("grego","1234","Gregorio Figueras");
		this.empresa.login("grego", "1234");
	}
	
	@After
	public void tearDown() throws Exception {
		this.empresa.getChoferes().clear();
		this.empresa.getVehiculos().clear();
		this.empresa.getViajesIniciados().clear();
		this.empresa.getViajesTerminados().clear();
		this.empresa.getPedidos().clear();
		this.empresa.getClientes().clear();
	}

	@Test
	public void testLogout() {
		this.empresa.logout();
        assertNull("Error en logout",this.empresa.getUsuarioLogeado());
	}

}
