package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.SinVehiculoParaPedidoException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Constantes;

public class TestEmpresaAgregarPedido {
	private Cliente cliente=null;
	private Empresa empresa=null;
	private Chofer chofer=null;
	private Auto auto=null;
	private Pedido pedido=null;
	private HashMap<Cliente, Pedido> pedidos=null;

	/*Pedido(Cliente cliente, int cantidadPasajeros, boolean mascota, boolean baul, int km, String zona)*/
	
	@Before
	public void setUp() throws Exception {
		this.empresa=Empresa.getInstance();
		this.pedidos=this.empresa.getPedidos();
		this.chofer= new ChoferTemporario("43509237","Gregorio Figueras");
		this.auto= new Auto("ABC123",4,true);
		this.empresa.agregarCliente("grego","1234","Gregorio Figueras");
		this.cliente= new Cliente("grego","1234","Gregorio Figueras");
		this.pedido= new Pedido(this.cliente,4,true,true,10,Constantes.ZONA_STANDARD);
		this.empresa.agregarChofer(this.chofer);
		this.empresa.agregarVehiculo(this.auto);
	}

	@Test
	public void testAgregarPedido() {
		try {
			empresa.agregarPedido(this.pedido);
			Assert.assertEquals("Agregar Pedido no funciona correctamente", this.pedidos, empresa.getPedidos());
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
	
	@After
	public void tearDown() {
		this.empresa.getClientes().clear();
		this.empresa.getVehiculos().clear();
		this.empresa.getChoferes().clear();
		this.empresa.getPedidos().clear();
	}

}
