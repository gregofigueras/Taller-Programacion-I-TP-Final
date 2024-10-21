package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import modeloNegocio.Empresa;
import modeloDatos.Chofer;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Cliente;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;


public class TestEmpresaGetterYSetters {
	private Empresa empresa=null;
	private HashMap<String,Chofer> choferes =null;
	private ArrayList<Chofer> choferesDesocupados = null;
	private HashMap<String,Cliente> clientes = null;
	private HashMap<Cliente,Pedido> pedidos = null;
	private Usuario usuarioLogeado = null;
	private HashMap<String,Vehiculo> vehiculos = null;
	private ArrayList<Vehiculo> vehiculosDesocupados = null;
	private HashMap<Cliente,Viaje> viajesIniciados = null;
	private ArrayList<Viaje> viajesTerminados = null;

	@Before
	public void setUp() throws Exception {
		this.empresa = Empresa.getInstance();
		this.choferes = new HashMap<String,Chofer>();
		this.choferesDesocupados = new ArrayList<Chofer>();
		this.clientes = new HashMap<String,Cliente>();
		this.pedidos = new HashMap<Cliente,Pedido>();
		this.usuarioLogeado = new Cliente("grego","1234","Gregorio Figueras");
		this.vehiculos = new HashMap<String,Vehiculo>();
		this.vehiculosDesocupados = new ArrayList<Vehiculo>();
		this.viajesIniciados = new HashMap<Cliente,Viaje>();
		this.viajesTerminados = new ArrayList<Viaje>();
	}

	@Test
	public void testSetChoferes() {
		empresa.setChoferes(this.choferes);
		Assert.assertEquals("Error en setChoferes", this.choferes, this.empresa.getChoferes());
	}
	
	@Test
	public void testSetChoferesDesocupados() {
		empresa.setChoferesDesocupados(this.choferesDesocupados);
		Assert.assertEquals("Error en setChoferesDesocupados", this.choferesDesocupados, this.empresa.getChoferesDesocupados());
	}
	
	@Test
	public void testSetClientes() {
		empresa.setClientes(this.clientes);
		Assert.assertEquals("Error en setClientes", this.clientes, this.empresa.getClientes());
	}
	
	@Test
	public void testSetPedidos() {
		empresa.setPedidos(this.pedidos);
		Assert.assertEquals("Error en setPedidos", this.pedidos, this.empresa.getPedidos());
	}
	
	@Test
	public void testSetUsuarioLogeado() {
		empresa.setUsuarioLogeado(this.usuarioLogeado);
		Assert.assertEquals("Error en setUsuarioLogeado", this.usuarioLogeado, this.empresa.getUsuarioLogeado());
	}
	
	@Test
	public void testSetVehiculos() {
		empresa.setVehiculos(this.vehiculos);
		Assert.assertEquals("Error en setVehiculos", this.vehiculos, this.empresa.getVehiculos());
	}
	
	@Test
	public void testSetVehiculosDesocupados() {
		empresa.setVehiculosDesocupados(this.vehiculosDesocupados);
		Assert.assertEquals("Error en setVehiculosDesocupados", this.vehiculosDesocupados,
				this.empresa.getVehiculosDesocupados());
	}
	
	@Test
	public void testSetViajesIniciados() {
		empresa.setViajesIniciados(this.viajesIniciados);
		Assert.assertEquals("Error en setViajesIniciados", this.viajesIniciados, this.empresa.getViajesIniciados());
	}
	
	@Test
	public void testSetViajesTerminados() {
		empresa.setViajesTerminados(this.viajesTerminados);
		Assert.assertEquals("Error en setViajesTerminados", this.viajesTerminados, this.empresa.getViajesTerminados());
	}

}
