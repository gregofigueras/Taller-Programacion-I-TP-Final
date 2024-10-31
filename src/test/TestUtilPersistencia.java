package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import persistencia.EmpresaDTO;
import persistencia.UtilPersistencia;
import util.Constantes;

public class TestUtilPersistencia {
	
	private Empresa empresa;
	private Cliente clienteEsperado = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
	private Chofer choferEsperado = new ChoferPermanente("43509237","Gregorio",2003,0);
	private Pedido pedidoEsperado = new Pedido (clienteEsperado, 4, false, true, 5, Constantes.ZONA_STANDARD);
	

	@Before
	public void setUp() throws Exception {		
		empresa = Empresa.getInstance();

		HashMap<String, Cliente> clientes = new HashMap<>();
		clientes.put(clienteEsperado.getNombreUsuario(),clienteEsperado);

		HashMap<String, Chofer> choferes = new HashMap<>();
		choferes.put(choferEsperado.getDni(),choferEsperado);
		 
		HashMap<Cliente, Pedido> pedidos = new HashMap<>();
		pedidos.put(clienteEsperado,pedidoEsperado);	
	
        empresa.setChoferes(choferes);
        empresa.setClientes(clientes);
        empresa.setPedidos(pedidos);
        
        EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
	}

	@Test
    public void testEmpresaDtoFromEmpresaCheckClientes() {
		HashMap<String, Cliente> clientesObtenidos =  Empresa.getInstance().getClientes();
		Cliente clienteObtenido = clientesObtenidos.get("Aguskpo");
        Assert.assertEquals("Los clientes no se copiaron correctamente", this.clienteEsperado.getNombreUsuario(), clienteObtenido.getNombreUsuario());
    }

	@Test
    public void testEmpresaDtoFromEmpresaCheckChoferes() {
		HashMap<String, Chofer> choferesObtenidos = Empresa.getInstance().getChoferes();
		Chofer choferObtenido = choferesObtenidos.get("43509237");
		Assert.assertEquals("Los choferes no se copiaron correctamente", this.choferEsperado.getDni(), choferObtenido.getDni());
    }
	
	@Test
    public void testEmpresaDtoFromEmpresaCheckPedidos() {
		HashMap<Cliente, Pedido> pedidosObtenidos = Empresa.getInstance().getPedidos();
		Pedido pedidoObtenido = pedidosObtenidos.get(clienteEsperado);
		Assert.assertEquals("Los pedidos no se copiaron correctamente", this.pedidoEsperado.getCantidadPasajeros(), pedidoObtenido.getCantidadPasajeros());
    }
	
	//FALTA CHOFERES DESOCUPADOS, USUARIO LOGEADO, VEHICULOS,VEHICULOS DESOCUPADOS, VIAJES INICIADOS Y TERMINADOS
}
