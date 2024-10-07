package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Combi;

public class TestCombi {
private Combi combi=null;

	@Before
	public void setUp() throws Exception {
		this.combi = new Combi("ABC123", 3, true);
	}

	@Test
	public void testPatente() {
		Assert.assertEquals("patente incorrecta", "ABC123", combi.getPatente());
	}

	@Test
	public void testPlazas() {
		Assert.assertEquals("plazas incorrecta", 3, combi.getCantidadPlazas());
	}

	@Test
	public void testMascota() {
		Assert.assertEquals("mascota incorrecta", true, combi.isMascota());
	}

}
