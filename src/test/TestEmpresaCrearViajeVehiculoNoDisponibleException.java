package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ChoferNoDisponibleException;
import excepciones.ClienteConViajePendienteException;
import excepciones.PedidoInexistenteException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;

public class TestEmpresaCrearViajeVehiculoNoDisponibleException {
	private Empresa empresa=null;
	private Chofer chofer=null;
	private Auto auto=null;
	private Auto auto2=null;
	private Pedido pedido=null;

	@Before
	public void setUp() throws Exception {
		this.empresa=Empresa.getInstance();
		this.chofer= new ChoferTemporario("43509237","Gregorio Figueras");
		this.auto= new Auto("ABC123",4,true);
		this.auto2= new Auto("ABC124",4,true);
		this.empresa.agregarCliente("grego","1234","Gregorio Figueras");
		this.empresa.login("grego", "1234");
		this.pedido= new Pedido(this.empresa.getClientes().get("grego"),4,true,true,10,Constantes.ZONA_STANDARD);
		this.empresa.agregarVehiculo(this.auto2);
		this.empresa.agregarChofer(this.chofer);
		this.empresa.agregarPedido(this.pedido);
	}

	@After
	public void tearDown() throws Exception {
		this.empresa.getChoferes().clear();
		this.empresa.getVehiculos().clear();
		this.empresa.getPedidos().clear();
	}

	@Test
	public void testVehiculoNoDisponibleException() {
		try {
			this.empresa.crearViaje(this.pedido, this.chofer, this.auto);
			fail("No se lanzo VehiculoNoDisponibleException");
		} catch (PedidoInexistenteException | ChoferNoDisponibleException | VehiculoNoDisponibleException
				| VehiculoNoValidoException | ClienteConViajePendienteException e) {
			e.printStackTrace();
		}
	}

}
