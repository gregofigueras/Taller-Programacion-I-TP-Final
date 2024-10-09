package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;

public class TestAuto {
	private Auto auto=null;

	@Before
	public void setUp() throws Exception {
		this.auto = new Auto("ABC123", 3, false);
		
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
		Assert.assertEquals("mascota incorrecta", false, auto.isMascota());
	}

	public void testGetPuntajePedidoAuto() {
		Cliente cliente = new Cliente("juanpepe", "AAA", "Juan Pepe");
		Pedido pedido = new Pedido(cliente, 3, false, true, 5, Constantes.ZONA_STANDARD);
		Assert.assertEquals("no acepta mascota", false, pedido.isMascota());
		Assert.assertEquals("valor incorrecto", 120, (pedido.getCantidadPasajeros()*40));
		Pedido pedido1 = new Pedido(cliente, 3, false, false, 5, Constantes.ZONA_STANDARD);
		Assert.assertEquals("valor incorrecto", 90, (pedido1.getCantidadPasajeros()*30));
	}
}
