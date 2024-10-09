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

	@Before
	public void setUp() throws Exception {
		this.combi = new Combi("ABC123", 6, false);
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
	
	public void testGetPuntajePedidoCombi() {
		Cliente cliente = new Cliente("juanpepe", "AAA", "Juan Pepe");
		Pedido pedido = new Pedido(cliente, 6, false, true, 5, Constantes.ZONA_STANDARD);
		Assert.assertEquals("no acepta mascota", false, pedido.isMascota());
		Assert.assertEquals("puntaje incorrecto", 160, ((pedido.getCantidadPasajeros()*10)+100));
		Pedido pedido1 = new Pedido(cliente, 6, false, false, 5, Constantes.ZONA_STANDARD);
		Assert.assertEquals("puntaje incorrecto", 60, (pedido1.getCantidadPasajeros()*10));
	}

}
