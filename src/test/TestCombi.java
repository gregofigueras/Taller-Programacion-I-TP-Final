package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import util.Constantes;

public class TestCombi {
private Combi combi=null;
private Cliente cliente=null;

	@Before
	public void setUp() throws Exception {
		this.combi = new Combi("ABC123", 6, false);
		this.cliente = new Cliente("juanpepe", "AAA", "Juan Pepe");
	}

	@Test
	public void testPatente() {
		Assert.assertEquals("patente incorrecta", "ABC123", combi.getPatente());
	}

	@Test
	public void testPlazas() {
		Assert.assertEquals("plazas incorrecta", 6, combi.getCantidadPlazas());
	}

	@Test
	public void testMascota() {
		Assert.assertEquals("mascota incorrecta", false, combi.isMascota());
	}
	
	@Test
	public void testGetPuntajePedidoCombiMascota() {
		Pedido pedido = new Pedido(cliente, 6, true, true, 5, Constantes.ZONA_STANDARD);
		Assert.assertEquals("no acepta mascota", null, combi.getPuntajePedido(pedido));
	}
	
	@Test
	public void testGetPuntajePedidoCombiPasajeros() {
		Pedido pedido = new Pedido(cliente, 8, true, true, 5, Constantes.ZONA_STANDARD);
		Assert.assertEquals("no acepta mascota", null, combi.getPuntajePedido(pedido));
	}
	
	@Test
	public void testGetPuntajePedidoCombiPuntajeBaul() {
		Pedido pedido = new Pedido(cliente, 6, false, true, 5, Constantes.ZONA_STANDARD);
		Assert.assertTrue("puntaje incorrecto con bual", (160 == combi.getPuntajePedido(pedido)));
	}
	
	@Test
	public void testGetPuntajePedidoCombiPuntajeSinBaul() {
		Pedido pedido = new Pedido(cliente, 6, false, false, 5, Constantes.ZONA_STANDARD);
		Assert.assertTrue("puntaje incorrecto sin bual", (60 == combi.getPuntajePedido(pedido)));
	}


}
