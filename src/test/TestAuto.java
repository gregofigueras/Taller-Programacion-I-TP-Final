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
	private Cliente cliente =null;

	@Before
	public void setUp() throws Exception {
		this.auto = new Auto("ABC123", 3, false);
		this.cliente = new Cliente("juanpepe", "AAA", "Juan Pepe");
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

	@Test		//CHEQUEADO
	public void testGetPuntajePedidoAutoConMascota() {
		Pedido pedido = new Pedido(cliente, 3, true, true, 5, Constantes.ZONA_STANDARD);
		Assert.assertEquals("deberia devolver null, el auto no acepta mascota", null, auto.getPuntajePedido(pedido));
	}
	
	@Test		//CHEQUEADO
	public void testGetPuntajePedidoAutoPasajerosDeMas() {
		Pedido pedido = new Pedido(cliente, 8, false, true, 5, Constantes.ZONA_STANDARD);
		Assert.assertEquals("deberia devolver null, mas pasajeros de los permitidos para autos", null, auto.getPuntajePedido(pedido));
	}
	
	@Test		//CHEQUEADO
	public void testGetPuntajePedidoAutoConBaul() {
		Pedido pedido = new Pedido(cliente, 3, false, true, 5, Constantes.ZONA_STANDARD);
		Assert.assertTrue("puntaje incorrecto de auto con bual", (120 == auto.getPuntajePedido(pedido)));
	}
	
	@Test		//CHEQUEADO
	public void testGetPuntajePedidoAutoSinBaul() { 
		Pedido pedido = new Pedido(cliente, 3, false, false, 5, Constantes.ZONA_STANDARD);
		Assert.assertTrue("puntaje incorrecto de auto sin bual", (90 == auto.getPuntajePedido(pedido)));
	}

}
