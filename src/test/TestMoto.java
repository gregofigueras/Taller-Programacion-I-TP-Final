package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import util.Constantes;

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
	@Test
	public void testGetPuntajePedidoMoto() {
		Cliente cliente = new Cliente("juanpepe", "AAA", "Juan Pepe");
		Pedido pedido = new Pedido(cliente, 1, false, false, 5, Constantes.ZONA_STANDARD);
		Assert.assertEquals("no acepta mascota", false, pedido.isMascota());
		Assert.assertEquals("no usa baul", false, pedido.isBaul());
	}
}
