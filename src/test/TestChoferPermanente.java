package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import modeloDatos.ChoferPermanente;

public class TestChoferPermanente {
private ChoferPermanente chofer=null;

	@Before
	public void setUp() throws Exception {
		this.chofer = new ChoferPermanente("43509237","Gregorio",2003,0);
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
		Assert.assertEquals("Sueldo Neto incorrecto", 174,0, chofer.getSueldoNeto());
		Assert.assertEquals("Sueldo Bruto incorrecto", 200,0, chofer.getSueldoBruto());
		chofer.setCantidadHijos(1);
		Assert.assertEquals("Sueldo Bruto incorrecto con hijo", 214,0, chofer.getSueldoBruto());
	}
	
	@Test
	public void testCantidadHijos() {
		Assert.assertEquals("Hijos incorrectos", 0, chofer.getCantidadHijos());
		chofer.setCantidadHijos(1);
		Assert.assertEquals("Hijos incorrectos en seter", 1, chofer.getCantidadHijos());
	}
	
	@Test
	public void testAnioIngreso() {
		Assert.assertEquals("Anio ingreso incorreto", 2003, chofer.getAnioIngreso());
	}
	
	@Test
	public void testAntiguedad() {
		Assert.assertEquals("Antiguedad incorrecta", 21, chofer.getAntiguedad());
	}

}
