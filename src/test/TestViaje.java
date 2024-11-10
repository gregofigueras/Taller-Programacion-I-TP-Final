package test;


import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import modeloDatos.Pedido;
import modeloDatos.Cliente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Auto;
import util.Constantes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestViaje {
private Viaje viaje = null;
private Pedido pedido = null;
private Cliente cliente = null;
private ChoferTemporario chofer = null;
private Auto auto=null;
private Empresa empresa = null;
private Object incremento;

	@Before
	public void setUp() throws Exception {
		this.empresa = Empresa.getInstance();
		this.cliente = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		this.empresa.agregarCliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		this.pedido = new Pedido(this.empresa.getClientes().get("Aguskpo"),2,true,true,10,util.Constantes.ZONA_PELIGROSA);
		this.chofer = new ChoferTemporario("43509237","Gregorio");
		this.auto= new Auto("ABC123", 4, true);
		this.viaje = new Viaje(this.pedido,this.chofer,this.auto);
		this.viaje.setValorBase(500);

		this.empresa.agregarChofer(this.chofer);
		this.empresa.agregarVehiculo(this.auto);
		this.empresa.agregarPedido(this.pedido);
		
	}

	@Test
	public void testFinalizarViaje() {
		this.viaje.finalizarViaje(3);
		Assert.assertEquals("No finalizo el viaje", true,this.viaje.isFinalizado());
	}
	
	@Test
	public void testgetCalificacion() {
		this.viaje.finalizarViaje(3);
		Assert.assertEquals("califacion incorrecta",3,this.viaje.getCalificacion());
	}
	
	@Test
	public void testgetChofer() {
		Assert.assertEquals("El chofer no fue mal asignado", this.chofer,this.viaje.getChofer());
	}
	
	@Test
	public void testgetPedido() {
		Assert.assertEquals("Pedido mal hecho",this.pedido,this.viaje.getPedido());
	}
	
	@Test
	public void testgetValorZonaPeligrosaConMascotaConBaul() {
		
		Assert.assertEquals("Valor de Zona peligrosa con mascota y sin baul mal calculado",18850.0,this.viaje.getValor(), 0.01);
	}
	
	@Test
	public void testgetValorZonaStandardSinMascotaConBaul() {		
		this.pedido = new Pedido(this.cliente,2,false,true,10,util.Constantes.ZONA_STANDARD);
		Assert.assertEquals("Valor de Zona standard sin mascota y con baul mal calculado",13800.0,this.viaje.getValor());
	}
	
	@Test
	public void testgetValorZonaSinAsfaltarSinMascotaSinBaul() {
		this.pedido = new Pedido(this.cliente,2,false,false,10,util.Constantes.ZONA_SIN_ASFALTAR);
		Assert.assertEquals("Valor de Zona sin asfaltar sin mascota y sin baul mal calculado",13800.0,this.viaje.getValor());
	}
		
	
	@Test
	public void testValorBase() {
		Assert.assertEquals("Valor base incoherente",500.0,this.viaje.getValorBase());
	}

	@Test
	public void testgetVehiculo() {
		Assert.assertEquals("Vehiculo mal asignado",this.auto,this.viaje.getVehiculo());
	}
	
	@Test
	public void testisFinalizado() {
		this.viaje.finalizarViaje(3);
		Assert.assertEquals("Viaje no finalizado",true,this.viaje.isFinalizado());
	}
	

}
