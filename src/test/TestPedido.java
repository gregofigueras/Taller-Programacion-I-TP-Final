package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;

public class TestPedido {
	private Pedido pedido = null;
	private Cliente cliente = null;
	
	@Before
	public void setUp() throws Exception {
		this.cliente = new Cliente("juanpepe", "AAA", "Juan Pepe");
		this.pedido = new Pedido(cliente, 4, false, true, 5, Constantes.ZONA_STANDARD);
	}
	
	@Test
	public void testCliente() {
		Assert.assertEquals("cliente incorrecto", "juanpepe", this.cliente.getNombreUsuario());
	}
	
	@Test
	public void testCantPasajeros() {
		Assert.assertEquals("cantidad pasajeros incorrecta", 4, pedido.getCantidadPasajeros());
	}
	
	public void testMascota() {
		Assert.assertEquals("mascota incorrecta", false, pedido.isMascota());
	}
	
	public void testBaul() {
		Assert.assertEquals("baul incorrecta", true, pedido.isBaul());
	}
	
	public void testKilometros() {
		Assert.assertEquals("kilometros incorrecto", 5, pedido.getKm());
	}
	
	public void testZona() {
		Assert.assertEquals("zona incorrecta", Constantes.ZONA_STANDARD, pedido.getZona());
	}
}
