package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoRepetidoException;
import modeloNegocio.Empresa;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Viaje;
import modeloDatos.Auto;
import modeloDatos.Pedido;

public class TestEmpresa {
	private Empresa empresa = Empresa.getInstance();

	@Before
	public void setUp() throws Exception {
		Empresa.getInstance();
	}

	@Test
	public void testAgregarChofer() {
		ChoferTemporario chofer= new ChoferTemporario("43509237","Gregorio Figueras");
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarChofer(chofer);
			fail("No se lanzo la excepcion ChoferRepetidoException");
		} catch (ChoferRepetidoException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAgregarCliente() {	
		try {
			empresa.agregarCliente("Aguskpo","Aguskpo123","Agustin Gonzales");
			empresa.agregarCliente("Aguskpo","Aguskpo123","Agustin Gonzales");
			fail("No se lanzo la excepcion UsuarioYaExisteException");
		} catch (UsuarioYaExisteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAgregarPedidoClienteNoExisteException() {	
			Cliente cliente = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
			Pedido pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_PELIGROSA);
			Auto auto= new Auto("ABC123", 4, true);
			try {
				try {
					empresa.agregarVehiculo(auto);
				} catch (VehiculoRepetidoException e) {
					e.printStackTrace();
				}
				empresa.agregarPedido(pedido);
				fail("No se lanzo la excepcion ClienteNoExisteException");
			} catch (SinVehiculoParaPedidoException e) {
				e.printStackTrace();
			} catch (ClienteNoExisteException e) {
				e.printStackTrace();
			} catch (ClienteConViajePendienteException e) {
				e.printStackTrace();
			} catch (ClienteConPedidoPendienteException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void testAgregarPedidoSinVehiculoParaPedidoException() {	
			Cliente cliente = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
			Pedido pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_PELIGROSA);
			try {
				try {
					empresa.agregarCliente("Aguskpo","Aguskpo123","Agustin Gonzales");
				} catch (UsuarioYaExisteException e) {
					e.printStackTrace();
				}
				empresa.agregarPedido(pedido);
				fail("No se lanzo la excepcion SinVehiculoParaPedidoException");
				} catch (SinVehiculoParaPedidoException e) {
					e.printStackTrace();
				} catch (ClienteNoExisteException e) {
					e.printStackTrace();
				} catch (ClienteConViajePendienteException e) {
					e.printStackTrace();
				} catch (ClienteConPedidoPendienteException e) {
					e.printStackTrace();
				}		
	}
	
	@Test
	public void testAgregarClienteConViajePendienteException() {	
			Cliente cliente = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
			Pedido pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_PELIGROSA);
			try {
				try {
					empresa.agregarCliente("Aguskpo","Aguskpo123","Agustin Gonzales");
				} catch (UsuarioYaExisteException e) {
					e.printStackTrace();
				}
				empresa.agregarPedido(pedido);
				empresa.agregarPedido(pedido);
				fail("No se lanzo la excepcion ClienteConViajePendienteException");
				} catch (SinVehiculoParaPedidoException e) {
					e.printStackTrace();
				} catch (ClienteNoExisteException e) {
					e.printStackTrace();
				} catch (ClienteConViajePendienteException e) {
					e.printStackTrace();
				} catch (ClienteConPedidoPendienteException e) {
					e.printStackTrace();
				}		
	}




	@Test
	public void TestGetHisotrialViajeCliente(){
		
		Cliente cliente = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		try {
			empresa.agregarCliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		} catch (UsuarioYaExisteException e) {
			e.printStackTrace();
		}
		Pedido pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_PELIGROSA);
		try {
			empresa.agregarPedido(pedido);
		} catch (SinVehiculoParaPedidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteConViajePendienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteConPedidoPendienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Auto auto= new Auto("ABC123", 4, true);
		ChoferTemporario chofer= new ChoferTemporario("43509237","Gregorio Figueras");
		ArrayList<Viaje> viajes = new ArrayList<>();
	
		viajes.add(new Viaje(pedido,chofer,auto));
		pedido = new Pedido(cliente,5,false,false,5,util.Constantes.ZONA_SIN_ASFALTAR);
		
		auto= new Auto("XSC243", 4, true);
		chofer= new ChoferTemporario("44234729","Martin Patriarca");
	    viajes.add(new Viaje(pedido,chofer,auto));
	    Assert.assertEquals("No se puede obtener el historial de viaje de clientes",viajes,this.empresa.getHistorialViajeCliente(cliente)); 
	}
	
	@Test
	public void TestgetPedidoDeCliente(){
		Cliente cliente = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		try {
			empresa.agregarCliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		} catch (UsuarioYaExisteException e) {
			e.printStackTrace();
		}
		Pedido pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_PELIGROSA);
		Assert.assertEquals("No se puede obtener el pedido del cliente",null,this.empresa.getPedidoDeCliente(cliente));
		try {
			empresa.agregarPedido(pedido);
			Assert.assertEquals("No se puede obtener el pedido del cliente",pedido,this.empresa.getPedidoDeCliente(cliente));
		} catch (SinVehiculoParaPedidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteConViajePendienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteConPedidoPendienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
