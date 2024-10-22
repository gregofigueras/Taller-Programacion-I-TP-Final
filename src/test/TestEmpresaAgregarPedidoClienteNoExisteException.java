package test;

import static org.junit.Assert.*;

import org.junit.After;
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
import modeloNegocio.Empresa;
import util.Constantes;

public class TestEmpresaAgregarPedidoClienteNoExisteException {
	private Cliente cliente=null;
	private Empresa empresa=null;
	private Chofer chofer=null;
	private Auto auto=null;
	private Pedido pedido=null;

	@Before
	public void setUp() throws Exception {
		this.empresa=Empresa.getInstance();
		this.chofer= new ChoferTemporario("43509237","Gregorio Figueras");
		this.auto= new Auto("ABC123",2,false);
		this.empresa.agregarCliente("FerGarcia","1234","Fernando Garcia");
		this.cliente= new Cliente("grego","1234","Gregorio Figueras");
		this.pedido= new Pedido(this.cliente,4,true,true,10,Constantes.ZONA_STANDARD);
		this.empresa.agregarChofer(this.chofer);
		this.empresa.agregarVehiculo(this.auto);
	}

	@After
	public void tearDown() throws Exception {
		this.empresa.getChoferes().clear();
		this.empresa.getClientes().clear();
		this.empresa.getVehiculos().clear();
		this.empresa.getPedidos().clear();
	}

	@Test
	public void testAgregarPedidoClienteNoExisteException() {
		try {
			empresa.agregarPedido(this.pedido);
			fail("Deberia haber lanzado ClienteNoExisteException");
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
