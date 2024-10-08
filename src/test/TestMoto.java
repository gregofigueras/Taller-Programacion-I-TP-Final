package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Moto;

public class TestMoto {
private Moto moto=null;

	@Before
	public void setUp() throws Exception {
		this.moto = new Moto("ABC123");
	}

	@Test
	public void testPatente() {
		Assert.assertEquals("patente incorrecta", "ABC123", moto.getPatente());
	}

	@Test
	public void testPlazas() {
		Assert.assertEquals("plazas incorrecta", 1, moto.getCantidadPlazas());
	}

	@Test
	public void testMascota() {
		Assert.assertEquals("mascota incorrecta", false, moto.isMascota());
	}
	

}
