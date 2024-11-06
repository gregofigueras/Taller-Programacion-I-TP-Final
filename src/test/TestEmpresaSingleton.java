package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import modeloNegocio.Empresa;

public class TestEmpresaSingleton {
	private Empresa empresa1;
	private Empresa empresa2;

	@Before
	public void setUp() throws Exception {
		this.empresa1 = Empresa.getInstance();
		this.empresa2 = Empresa.getInstance();
	}

	@Test
	public void testEmpresaSingleton() {
		Assert.assertEquals("Singleton funciona mal", this.empresa2, this.empresa1);
	}

}
