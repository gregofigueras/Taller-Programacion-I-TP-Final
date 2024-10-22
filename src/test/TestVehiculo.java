package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import util.Constantes;

public class TestVehiculo {
	public Vehiculo vehiculo=null;

	@Before
	public void setUp() throws Exception {
		this.vehiculo = new Auto("AAA123", 3, false);
		
	}

	@Test
	public void testPatente() {
		Assert.assertEquals("patente incorrecta", "AAA123", vehiculo.getPatente());
	}

	@Test
	public void testPlazas() {
		Assert.assertEquals("plazas incorrecta", 3, vehiculo.getCantidadPlazas());
	}

	@Test
	public void testMascota() {
		Assert.assertEquals("mascota incorrecta", false, vehiculo.isMascota());
	}

	
}
