package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;

public class TestEmpresaVehiculosOrdenadosPorPedido {
	private Empresa empresa=null;
	private Chofer chofer=null;
	private Auto auto=null;
	private Auto auto2=null;
	private Auto auto3=null;
	private Combi combi=null;
	private Pedido pedido=null;

	@Before
	public void setUp() throws Exception {
		this.empresa=Empresa.getInstance();
		this.chofer= new ChoferTemporario("43509237","Gregorio Figueras");
		this.auto= new Auto("ABC123",4,true);
		this.auto2= new Auto("ABC124",2,true);
		this.auto3= new Auto("ABC125",4,false);
		this.combi= new Combi("ABC126",7,true);
		this.empresa.agregarCliente("grego","1234","Gregorio Figueras");
		this.empresa.login("grego", "1234");
		this.pedido= new Pedido(this.empresa.getClientes().get("grego"),2,true,true,10,Constantes.ZONA_STANDARD);
		this.empresa.agregarChofer(this.chofer);
		this.empresa.agregarVehiculo(this.auto);
		this.empresa.agregarVehiculo(this.auto2);
		this.empresa.agregarVehiculo(this.auto3);
		this.empresa.agregarVehiculo(this.combi);
	}
	
	@After
	public void tearDown() throws Exception {
		this.empresa.getChoferes().clear();
		this.empresa.getVehiculos().clear();
		this.empresa.getPedidos().clear();
	}

	@Test
	public void testVehiculosOrdenadosPorPedido() {
		Assert.assertNotNull("VehiculosOrdenadosPorPedido no funciona correctamente", this.empresa.vehiculosOrdenadosPorPedido(this.pedido));
	}

}
