package test;

import static org.junit.Assert.*;
import modeloDatos.Viaje;
import modeloDatos.Pedido;
import modeloDatos.Cliente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Auto;
import util.Constantes; 

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class TestViaje {
private Viaje viaje = null;
private Pedido pedido = null;
private Cliente cliente = null;
private ChoferTemporario chofer = null;
private Auto auto=null;

	@Before
	public void setUp() throws Exception {
		this.cliente = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		this.pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_PELIGROSA);
		this.chofer = new ChoferTemporario("43509237","Gregorio");
		this.auto= new Auto("ABC123", 4, true);
		this.viaje = new Viaje(this.pedido,this.chofer,this.auto);
		this.viaje.setValorBase(500);
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
	public void testgetValor() {
		Assert.assertEquals("Valor incoherente en zona peligrosa",16800.0,this.viaje.getValor());
		this.pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_STANDARD);
		Assert.assertEquals("Valor incoherente en zona estandar",9360.0,this.viaje.getValor());
		this.pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_SIN_ASFALTAR);
		Assert.assertEquals("Valor incoherente en zona sin asfaltar",10530.0,this.viaje.getValor());
		this.pedido = new Pedido(cliente,2,false,false,10,util.Constantes.ZONA_STANDARD);
		Assert.assertEquals("Valor incoherente sin mascota",7260.0,this.viaje.getValor());
		this.pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_STANDARD);
		Assert.assertEquals("Valor incoherente con mascota",9360.0,this.viaje.getValor());
		this.pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_STANDARD);
		Assert.assertEquals("Valor incoherente sin baul",9360.0,this.viaje.getValor());
		this.pedido = new Pedido(cliente,2,true,true,10,util.Constantes.ZONA_STANDARD);
		Assert.assertEquals("Valor incoherente con baul",17550.0,this.viaje.getValor());
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
