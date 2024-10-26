package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.SinViajesException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;

public class TestEmpresaCalificacionDeChofer {
	private Empresa empresa=null;
	private Chofer chofer=null;
	private Auto auto=null;
	private Pedido pedido=null;
	private double calificacion;

	@Before
	public void setUp() throws Exception {
		this.empresa=Empresa.getInstance();
		this.chofer= new ChoferTemporario("43509237","Gregorio Figueras");
		this.auto= new Auto("ABC123",4,true);
		this.empresa.agregarCliente("grego","1234","Gregorio Figueras");
		this.empresa.login("grego", "1234");
		this.pedido= new Pedido(this.empresa.getClientes().get("grego"),4,true,true,10,Constantes.ZONA_STANDARD);
		this.empresa.agregarChofer(this.chofer);
		this.empresa.agregarVehiculo(this.auto);
		this.empresa.agregarPedido(this.pedido);
		this.empresa.crearViaje(this.pedido, this.chofer, this.auto);
		this.calificacion=5;
		this.empresa.pagarYFinalizarViaje(5);
	}

	@After
	public void tearDown() throws Exception {
		this.empresa.getChoferes().clear();
		this.empresa.getVehiculos().clear();
		this.empresa.getViajesIniciados().clear();
		this.empresa.getViajesTerminados().clear();
		this.empresa.getPedidos().clear();
	}

	@Test
	public void testCalificacionDeChofer() {
		try {
			Assert.assertEquals(this.calificacion,this.empresa.calificacionDeChofer(this.chofer),0);
		} catch (SinViajesException e) {
			e.printStackTrace();
		}
	}

}
