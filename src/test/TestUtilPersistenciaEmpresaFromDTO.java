package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import persistencia.EmpresaDTO;
import persistencia.UtilPersistencia;
import util.Constantes;

public class TestUtilPersistenciaEmpresaFromDTO {
	
	private Empresa empresa;
	private EmpresaDTO empresaDTO = new EmpresaDTO();
	private Cliente clienteEsperado = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
	private Chofer choferEsperado = new ChoferPermanente("43509237","Gregorio",2003,0);
	private Pedido pedidoEsperado = new Pedido (clienteEsperado, 4, false, true, 5, Constantes.ZONA_STANDARD);
	private Vehiculo vehiculoEsperado = new Auto("AAA123", 3, false);
	private Viaje viajeEsperado = new Viaje(this.pedidoEsperado,this.choferEsperado,this.vehiculoEsperado);
	
	@Before
	public void setUp() throws Exception {		
	
		HashMap<String, Cliente> clientes = new HashMap<>();
		clientes.put(clienteEsperado.getNombreUsuario(),clienteEsperado);

		HashMap<String, Chofer> choferes = new HashMap<>();
		choferes.put(choferEsperado.getDni(),choferEsperado);
		 
		HashMap<Cliente, Pedido> pedidos = new HashMap<>();
		pedidos.put(clienteEsperado,pedidoEsperado);	
		
		ArrayList<Chofer> choferesDesocupados = new ArrayList<Chofer>();
		choferesDesocupados.add(choferEsperado);
		
		HashMap<String, Vehiculo> vehiculos = new HashMap<>();
		vehiculos.put(vehiculoEsperado.getPatente(),vehiculoEsperado);
		
		ArrayList<Vehiculo> vehiculosDesocupados = new ArrayList<Vehiculo>();
		vehiculosDesocupados.add(vehiculoEsperado);
		
		HashMap<Cliente, Viaje> viajesIniciados = new HashMap<>();
		viajesIniciados.put(clienteEsperado,viajeEsperado);	

		ArrayList<Viaje> viajesTerminados = new ArrayList<Viaje>();
		viajesTerminados.add(viajeEsperado);
		
        empresaDTO.setChoferes(choferes);
        empresaDTO.setClientes(clientes);
        empresaDTO.setPedidos(pedidos);
        empresaDTO.setChoferesDesocupados(choferesDesocupados);
        empresaDTO.setVehiculos(vehiculos);
        empresaDTO.setVehiculosDesocupados(vehiculosDesocupados);
        empresaDTO.setViajesIniciados(viajesIniciados);
        empresaDTO.setViajesTerminados(viajesTerminados);
        
        UtilPersistencia.empresaFromEmpresaDTO(empresaDTO);
        empresa = Empresa.getInstance();
	}
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckUsuarioClientes() {
		HashMap<String, Cliente> clientesObtenidos =  empresa.getClientes();
		Cliente clienteObtenido = clientesObtenidos.get("Aguskpo");
        Assert.assertEquals("Los usuarios de los clientes no se copiaron correctamente", this.clienteEsperado.getNombreUsuario(), clienteObtenido.getNombreUsuario());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckNombreClientes() {
		HashMap<String, Cliente> clientesObtenidos =  empresa.getClientes();
		Cliente clienteObtenido = clientesObtenidos.get("Aguskpo");
        Assert.assertEquals("Los nombres de los clientes no se copiaron correctamente", this.clienteEsperado.getNombreReal(), clienteObtenido.getNombreReal());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckPasswordClientes() {
		HashMap<String, Cliente> clientesObtenidos =  empresa.getClientes();
		Cliente clienteObtenido = clientesObtenidos.get("Aguskpo");
        Assert.assertEquals("Las contraseñas de los clientes no se copiaron correctamente", this.clienteEsperado.getPass(), clienteObtenido.getPass());
    }

	@Test
    public void testEmpresaDtoFromEmpresaCheckDniChoferes() {
		HashMap<String, Chofer> choferesObtenidos = empresa.getChoferes();
		Chofer choferObtenido = choferesObtenidos.get("43509237");
		Assert.assertEquals("Los DNI de los choferes no se copiaron correctamente", this.choferEsperado.getDni(), choferObtenido.getDni());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckNombreChoferes() {
		HashMap<String, Chofer> choferesObtenidos = empresa.getChoferes();
		Chofer choferObtenido = choferesObtenidos.get("43509237");
		Assert.assertEquals("Los nombres de los choferes no se copiaron correctamente", this.choferEsperado.getNombre(), choferObtenido.getNombre());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckSueldoBrutoChoferes() {
		HashMap<String, Chofer> choferesObtenidos = empresa.getChoferes();
		Chofer choferObtenido = choferesObtenidos.get("43509237");
		Assert.assertEquals("Los sueldos brutos de los choferes no se copiaron correctamente", this.choferEsperado.getSueldoBruto(), choferObtenido.getSueldoBruto());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckSueldoNetoChoferes() {
		HashMap<String, Chofer> choferesObtenidos = empresa.getChoferes();
		Chofer choferObtenido = choferesObtenidos.get("43509237");
		Assert.assertEquals("Los sueldos netos de los choferes no se copiaron correctamente", this.choferEsperado.getSueldoNeto(), choferObtenido.getSueldoNeto());
    }
		
	@Test
    public void testEmpresaDtoFromEmpresaCheckCantPasPedidos() {
		HashMap<Cliente, Pedido> pedidosObtenidos = empresa.getPedidos();
		Pedido pedidoObtenido = pedidosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Las cantidades de pasajeros de los pedidos no se copiaron correctamente", this.pedidoEsperado.getCantidadPasajeros(), pedidoObtenido.getCantidadPasajeros());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckKmPedidos() {
		HashMap<Cliente, Pedido> pedidosObtenidos = empresa.getPedidos();
		Pedido pedidoObtenido = pedidosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Los kilometos de los pedidos no se copiaron correctamente", this.pedidoEsperado.getKm(), pedidoObtenido.getKm());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckClientePedidos() {
		HashMap<Cliente, Pedido> pedidosObtenidos = empresa.getPedidos();
		Pedido pedidoObtenido = pedidosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Los clientes de los pedidos no se copiaron correctamente", this.pedidoEsperado.getCliente().getNombreUsuario(), pedidoObtenido.getCliente().getNombreUsuario());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckZonaPedidos() {
		HashMap<Cliente, Pedido> pedidosObtenidos = empresa.getPedidos();
		Pedido pedidoObtenido = pedidosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Las zonas de los pedidos no se copiaron correctamente", this.pedidoEsperado.getZona(), pedidoObtenido.getZona());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckBaulPedidos() {
		HashMap<Cliente, Pedido> pedidosObtenidos = empresa.getPedidos();
		Pedido pedidoObtenido = pedidosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Los baules de los pedidos no se copiaron correctamente", this.pedidoEsperado.isBaul(), pedidoObtenido.isBaul());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckMascotaPedidos() {
		HashMap<Cliente, Pedido> pedidosObtenidos = empresa.getPedidos();
		Pedido pedidoObtenido = pedidosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Las mascotas de los pedidos no se copiaron correctamente", this.pedidoEsperado.isMascota(), pedidoObtenido.isMascota());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckNombreChoferesDesoc() {
		ArrayList<Chofer> choferesDesocupadosObtenidos = empresa.getChoferesDesocupados();
		Chofer choferObtenido = choferesDesocupadosObtenidos.get(0);
		Assert.assertEquals("Los nombres de los choferes desocupados no se copiaron correctamente", this.choferEsperado.getNombre(), choferObtenido.getNombre());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckDNIChoferesDesoc() {
		ArrayList<Chofer> choferesDesocupadosObtenidos = empresa.getChoferesDesocupados();
		Chofer choferObtenido = choferesDesocupadosObtenidos.get(0);
		Assert.assertEquals("Los DNI de los choferes desocupados no se copiaron correctamente", this.choferEsperado.getDni(), choferObtenido.getDni());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckPatenteVehiculos() {
		HashMap<String, Vehiculo> vehiculosObtenidos = empresa.getVehiculos();
		Vehiculo vehiculoObtenido = vehiculosObtenidos.get("AAA123");
		Assert.assertEquals("Las patentes de los vehiculos no se copiaron correctamente", this.vehiculoEsperado.getPatente(), vehiculoObtenido.getPatente());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckPlazasVehiculos() {
		HashMap<String, Vehiculo> vehiculosObtenidos = empresa.getVehiculos();
		Vehiculo vehiculoObtenido = vehiculosObtenidos.get("AAA123");
		Assert.assertEquals("La cantidad de plazas de los vehiculos no se copiaron correctamente", this.vehiculoEsperado.getCantidadPlazas(), vehiculoObtenido.getCantidadPlazas());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckMascotaVehiculos() {
		HashMap<String, Vehiculo> vehiculosObtenidos = empresa.getVehiculos();
		Vehiculo vehiculoObtenido = vehiculosObtenidos.get("AAA123");
		Assert.assertEquals("Las mascotas de los vehiculos no se copiaron correctamente", this.vehiculoEsperado.isMascota(), vehiculoObtenido.isMascota());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckPatenteVehiculosDesoc() {
		ArrayList<Vehiculo> vehiculosDesocupadosObtenidos = empresa.getVehiculosDesocupados();
		Vehiculo vehiculoObtenido = vehiculosDesocupadosObtenidos.get(0);
		Assert.assertEquals("Las patentes de los vehiculos desocupados no se copiaron correctamente", this.vehiculoEsperado.getPatente(), vehiculoObtenido.getPatente());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckPlazasVehiculosDesoc() {
		ArrayList<Vehiculo> vehiculosDesocupadosObtenidos = empresa.getVehiculosDesocupados();
		Vehiculo vehiculoObtenido = vehiculosDesocupadosObtenidos.get(0);
		Assert.assertEquals("La cantidad de plazas de los vehiculos desocupados no se copiaron correctamente", this.vehiculoEsperado.getCantidadPlazas(), vehiculoObtenido.getCantidadPlazas());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckMascotaVehiculosDesoc() {
		ArrayList<Vehiculo> vehiculosDesocupadosObtenidos = empresa.getVehiculosDesocupados();
		Vehiculo vehiculoObtenido = vehiculosDesocupadosObtenidos.get(0);
		Assert.assertEquals("Las mascotas de los vehiculos desocupados no se copiaron correctamente", this.vehiculoEsperado.isMascota(), vehiculoObtenido.isMascota());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckCantPasViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Las cantidades de los pasajeros de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getPedido().getCantidadPasajeros(), viajeIniciadoObtenido.getPedido().getCantidadPasajeros());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckKmPasViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Los kilometros de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getPedido().getKm(), viajeIniciadoObtenido.getPedido().getKm());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckClienteViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Los clientes de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getPedido().getCliente().getNombreUsuario(), viajeIniciadoObtenido.getPedido().getCliente().getNombreUsuario());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckZonaViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Las zonas de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getPedido().getZona(), viajeIniciadoObtenido.getPedido().getZona());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckBaulViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Los baules de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getPedido().isBaul(), viajeIniciadoObtenido.getPedido().isBaul());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckMascotaViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Las mascotas de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getPedido().isMascota(), viajeIniciadoObtenido.getPedido().isMascota());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckDniChoferViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Los DNI de los choferes de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getChofer().getDni(), viajeIniciadoObtenido.getChofer().getDni());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckNombreChoferViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Los nombres de los choferes de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getChofer().getNombre(), viajeIniciadoObtenido.getChofer().getNombre());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckPatenteViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Las patentes de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getVehiculo().getPatente(), viajeIniciadoObtenido.getVehiculo().getPatente());
		
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckPasajVehiculoViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Las cantidades de pasajeros permitidas en el vehiculo de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getVehiculo().getCantidadPlazas(), viajeIniciadoObtenido.getVehiculo().getCantidadPlazas());	
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckMascotaVehiculoViajesIni() {
		HashMap<Cliente, Viaje> viajesIniciadosObtenidos = empresa.getViajesIniciados();
		Viaje viajeIniciadoObtenido = viajesIniciadosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Los permisos de las mascotas en los vehiculos de los viajes iniciados no se copiaron correctamente", this.viajeEsperado.getVehiculo().isMascota(), viajeIniciadoObtenido.getVehiculo().isMascota());
		
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckCantPasViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Las cantidades de pasajeros de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getPedido().getCantidadPasajeros(), viajeObtenido.getPedido().getCantidadPasajeros());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckKmViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Los Km de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getPedido().getKm(), viajeObtenido.getPedido().getKm());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckClienteViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Los clientes de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getPedido().getCliente().getNombreUsuario(), viajeObtenido.getPedido().getCliente().getNombreUsuario());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckZonaViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Las zonas de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getPedido().getZona(), viajeObtenido.getPedido().getZona());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckBaulViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Los baules de los pedidos de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getPedido().isBaul(), viajeObtenido.getPedido().isBaul());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckMascotaPedViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Las mascotas de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getPedido().isMascota(), viajeObtenido.getPedido().isMascota());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckDniChoferViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Los DNI de los choferes de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getChofer().getDni(), viajeObtenido.getChofer().getDni());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckNombresChoferViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Los nombres de los choferes de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getChofer().getNombre(), viajeObtenido.getChofer().getNombre());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckPatentesViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Las patentes de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getVehiculo().getPatente(), viajeObtenido.getVehiculo().getPatente());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckPasVehiculoViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Los pasajeros permitidos de los vehiculos de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getVehiculo().getCantidadPlazas(), viajeObtenido.getVehiculo().getCantidadPlazas());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckMascotaViajesTerm() {
		ArrayList<Viaje> viajesTerminados = empresa.getViajesTerminados();
		Viaje viajeObtenido = viajesTerminados.get(0);
		Assert.assertEquals("Las mascotas permitidos de los vehiculos de los viajes terminados no se copiaron correctamente", this.viajeEsperado.getVehiculo().isMascota(), viajeObtenido.getVehiculo().isMascota());
    }
	
	
	@After
	public void tearDown() {
		this.empresaDTO.getChoferes().clear();
		this.empresaDTO.getClientes().clear();
		this.empresaDTO.getPedidos().clear();
		this.empresaDTO.getVehiculos().clear();
		this.empresaDTO.getChoferesDesocupados().clear();
		this.empresaDTO.getVehiculosDesocupados().clear();
		this.empresaDTO.getViajesIniciados().clear();
		this.empresaDTO.getViajesTerminados().clear();
	}


}
