package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Usuario;
import modeloNegocio.Empresa;

public class TestEmpresaIsAdmin {
	private Empresa empresa=null;
	private Usuario usuario=null;

	@Before
	public void setUp() throws Exception {
		this.empresa=Empresa.getInstance();
		this.empresa.login("admin", "admin");
	}

	@After
	public void tearDown() throws Exception {
		this.empresa.getClientes().clear();
	}

	@Test
	public void test() {
		Assert.assertTrue("Is admin funciona erroneamente", this.empresa.isAdmin());
	}

}
