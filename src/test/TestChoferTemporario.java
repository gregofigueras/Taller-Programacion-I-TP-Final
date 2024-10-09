package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import modeloDatos.ChoferTemporario;

public class TestChoferTemporario {
private ChoferTemporario chofer=null;

	@Before
	public void setUp() throws Exception {
		this.chofer = new ChoferTemporario("43509237","Gregorio");
	}

	@Test
	public void testDNI() {
		Assert.assertEquals("DNI incorrecto", "43509237", chofer.getDni());
	}

	@Test
	public void testNombre() {
		Assert.assertEquals("Nombre incorrecto", "Gregorio", chofer.getNombre());
	}

	@Test
	public void testSueldo() {
		chofer.setSueldoBasico(100);
		Assert.assertEquals("Sueldo Bruto incorrecto", 100,0, chofer.getSueldoBruto());
		Assert.assertEquals("Sueldo Neto incorrecto", 87,0, chofer.getSueldoNeto());
	}
	
}
