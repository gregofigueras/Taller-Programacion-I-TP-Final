package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import persistencia.EmpresaDTO;
import util.Constantes;

public class TestEmpresaDTO {
	private EmpresaDTO empresaDTO = null;

	@Before
	public void setUp() throws Exception {
		this.empresaDTO = new EmpresaDTO();
	}

	@Test
	public void testSetGetClientes() {	//CONSULTAR SI ES CORRECTA LA FORMA DE BUSQUEDA
		Cliente clienteEsperado = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		HashMap<String, Cliente> mapa = new HashMap<>();
		mapa.put(clienteEsperado.getNombreUsuario(),clienteEsperado);	//asumo que el indice del hashmap es el nombre de usuario
		this.empresaDTO.setClientes(mapa);
		Cliente clienteObtenido = mapa.get("Aguskpo");
		Assert.assertEquals("El cliente obtenido no coincide con el esperado", clienteEsperado, clienteObtenido);
	}
	
	@Test
	public void testSetGetChoferesDesocupados() {
		ChoferPermanente choferEsperado= new ChoferPermanente("43509237","Gregorio",2003,0);
		ArrayList<Chofer> lista = new ArrayList<>();
		lista.add(choferEsperado);
		this.empresaDTO.setChoferesDesocupados(lista);
		ArrayList<Chofer> listaObtenida = new ArrayList<>();
		listaObtenida = this.empresaDTO.getChoferesDesocupados();
		
		Assert.assertEquals("El chofer obtenido no coincide con el esperado", choferEsperado.getDni(), listaObtenida.get(0).getDni());
	}

	@Test
	public void testSetGetVehiculosDesocupados() {
		Auto autoEsperado = new Auto("ABC123", 3, false);
		ArrayList<Vehiculo> lista = new ArrayList<>();
		lista.add(autoEsperado);
		this.empresaDTO.setVehiculosDesocupados(lista);
		ArrayList<Vehiculo> listaObtenida = new ArrayList<>();
		listaObtenida = this.empresaDTO.getVehiculosDesocupados();
		
		Assert.assertEquals("El vehiculo desocupado obtenido no coincide con el esperado", autoEsperado.getPatente(), listaObtenida.get(0).getPatente());
	}
	
	@Test
	public void testSetGetPedidos() {
		Cliente clienteEsperado = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		Pedido pedidoEsperado = new Pedido (clienteEsperado, 4, false, true, 5, Constantes.ZONA_STANDARD);
		HashMap<Cliente, Pedido> mapa = new HashMap<>();
		mapa.put(clienteEsperado,pedidoEsperado);	
		this.empresaDTO.setPedidos(mapa);
		Pedido pedidoObtenido = this.empresaDTO.getPedidos().get(clienteEsperado);
		Assert.assertEquals("El pedido obtenido no coincide con el esperado", pedidoEsperado, pedidoObtenido);
	}
	
	@Test
	public void testSetGetViajesIniciados() { //CHEQUEAR
		Cliente cliente = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		Pedido pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_PELIGROSA);
		Chofer chofer = new ChoferTemporario("43509237","Gregorio");
		Auto auto= new Auto("ABC123", 4, true);
		Viaje viajeEsperado = new Viaje(pedido,chofer,auto);
		HashMap<Cliente, Viaje> mapa = new HashMap<>();
		mapa.put(cliente,viajeEsperado);	
		this.empresaDTO.setViajesIniciados(mapa);;
		Viaje viajeObtenido = this.empresaDTO.getViajesIniciados().get(cliente);
		Assert.assertEquals("El viaje iniciado obtenido no coincide con el esperado", viajeEsperado, viajeObtenido);
	}
	
	
	@Test
	public void testSetGetViajesTerminados() { //CONSULTAR
		Cliente cliente = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
		Pedido pedido = new Pedido(cliente,2,true,false,10,util.Constantes.ZONA_PELIGROSA);
		Chofer chofer = new ChoferTemporario("43509237","Gregorio");
		Auto auto= new Auto("ABC123", 4, true);
		Viaje viajeEsperado = new Viaje(pedido,chofer,auto);
		ArrayList<Viaje> lista = new ArrayList<>();
		lista.add(viajeEsperado);
		this.empresaDTO.setViajesTerminados(lista);
		ArrayList<Viaje> listaObtenida = new ArrayList<>();
		listaObtenida = this.empresaDTO.getViajesTerminados();
		
		Assert.assertEquals("El viaje terminado obtenido no coincide con el esperado", viajeEsperado.getVehiculo().getPatente(), listaObtenida.get(0).getVehiculo().getPatente());
	}
	
	@Test
	public void testSetGetChoferes() {	
		Chofer choferEsperado = new ChoferPermanente("43509237","Gregorio",2003,0);
		HashMap<String, Chofer> mapa = new HashMap<>();
		mapa.put(choferEsperado.getDni(),choferEsperado);
		this.empresaDTO.setChoferes(mapa);
		Chofer choferObtenido = mapa.get("43509237");
		Assert.assertEquals("El Chofer obtenido no coincide con el esperado", choferEsperado.getDni(), choferObtenido.getDni());
	}
	
	@Test
	public void testSetGetVehiculos() {	
		Vehiculo autoEsperado = new Auto("ABC123", 3, false);
		HashMap<String, Vehiculo> mapa = new HashMap<>();
		mapa.put(autoEsperado.getPatente(),autoEsperado);
		this.empresaDTO.setVehiculos(mapa);
		Vehiculo autoObtenido = mapa.get("ABC123");
		Assert.assertEquals("El vehiculo obtenido no coincide con el esperado", autoEsperado.getPatente(), autoObtenido.getPatente());
	}
	
	@Test
	public void testSetGetUsuarioLogeado() {	
		Usuario usuarioEsperado = new Cliente("valensen","123456","Valentin");
		this.empresaDTO.setUsuarioLogeado(usuarioEsperado);
		Usuario usuarioObtenido = this.empresaDTO.getUsuarioLogeado();
		Assert.assertEquals("El usuario logeado obtenido no coincide con el esperado", usuarioEsperado.getNombreUsuario(), usuarioObtenido.getNombreUsuario());
	}
	
}
