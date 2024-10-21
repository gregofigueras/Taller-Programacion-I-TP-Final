package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ChoferRepetidoException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoRepetidoException;
import modeloNegocio.Empresa;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Vehiculo;

public class TestEmpresaAgregar {
	private Empresa empresa=null;
	private Chofer chofer=null;
	private Auto auto=null;
	private HashMap<String,Chofer> choferes=null;
	private HashMap<String,Cliente> clientes = null;
	private HashMap<String,Vehiculo> vehiculos = null;

	@Before
	public void setUp() throws Exception {
		this.empresa=Empresa.getInstance();
		this.chofer= new ChoferTemporario("43509237","Gregorio Figueras");
		this.auto= new Auto("ABC123",4,true);
		this.choferes=this.empresa.getChoferes();
		this.clientes=this.empresa.getClientes();
		this.vehiculos=this.empresa.getVehiculos();
	}

	@Test
	public void testAgregarChofer() {
		try {
			empresa.agregarChofer(this.chofer);
			Assert.assertEquals("Agregar Chofer no funciona correctamente", this.choferes, this.empresa.getChoferes());
		} catch (ChoferRepetidoException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAgregarCliente() {
		try {
			empresa.agregarCliente("grego","1234","Gregorio Figueras");
		} catch (UsuarioYaExisteException e) {
			e.printStackTrace();
		}
		Assert.assertEquals("Agregar Cliente no funciona correctamente", this.clientes, this.empresa.getClientes());
	}
	
	@Test
	public void testAgregarVehiculo() {
		try {
			empresa.agregarVehiculo(this.auto);
		} catch (VehiculoRepetidoException e) {
			e.printStackTrace();
		}
		Assert.assertEquals("Agregar Vehiculo no funciona correctamente", this.vehiculos,
				this.empresa.getVehiculos());
	}
	

}
