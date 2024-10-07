package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;

public class TestAuto {
private Auto auto=null;

	@Before
	public void setUp() throws Exception {
		this.auto = new Auto("ABC123", 3, true);
	}

	@Test
	public void testPatente() {
		Assert.assertEquals("patente incorrecta", "ABC123", auto.getPatente());
	}

	@Test
	public void testPlazas() {
		Assert.assertEquals("plazas incorrecta", 3, auto.getCantidadPlazas());
	}

	@Test
	public void testMascota() {
		Assert.assertEquals("mascota incorrecta", true, auto.isMascota());
	}

}
