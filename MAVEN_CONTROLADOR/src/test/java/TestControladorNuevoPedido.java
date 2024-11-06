// Para métodos como mock() y when()
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.event.ActionEvent;

// Para las anotaciones de JUnit
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import controlador.Controlador;
import modeloDatos.Cliente;
import modeloDatos.Usuario;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.IVista;
import vista.IOptionPane;

public class TestControladorNuevoPedido {
	private Controlador controlador = null;
	private String ussername = null;
	private String pass = null;
	private String nombrereal = null;
	private Empresa empresa = null;
	private ActionEvent event = null;
	private Cliente cliente=null;
	private IVista vistamock;
	private int cantidadPasajeros = 0;
	private boolean mascota = false;
	private boolean baul = false;
	private int km = 0;
	private Pedido pedido = null;
	private String zona = null;
	private IOptionPane mockOptionPane = null;
	
	@Before
	public void setUp() throws Exception {
		this.ussername = "Cachito";
		this.pass = "Cachito123";
		this.nombrereal = "Carlos";
		this.empresa = Empresa.getInstance();
		this.controlador = new Controlador();
		this.vistamock = mock(IVista.class);
		this.controlador.setVista(this.vistamock);
		this.cantidadPasajeros = 3;
		this.mascota = true;
		this.km = 100; 
		this.zona = Constantes.ZONA_SIN_ASFALTAR;
		this.vistamock.addActionListener(this.controlador);
		this.mockOptionPane = mock(IOptionPane.class);
		when(this.vistamock.getUsserName()).thenReturn(this.ussername);
		when(this.vistamock.getPassword()).thenReturn(this.pass);
		when(this.vistamock.getRegNombreReal()).thenReturn(this.nombrereal);
		when(this.vistamock.getCantidadPax()).thenReturn(this.cantidadPasajeros);
		when(this.vistamock.isPedidoConMascota()).thenReturn(this.mascota);
		when(this.vistamock.isPedidoConBaul()).thenReturn(this.baul);
		when(this.vistamock.getTipoZona()).thenReturn(this.zona);
		when(this.vistamock.getOptionPane()).thenReturn(this.mockOptionPane);
		this.cliente= new Cliente(this.ussername,this.pass,this.nombrereal);
		
		this.empresa.agregarCliente(this.ussername,this.pass, this.nombrereal);
		this.empresa.login(this.ussername, this.pass);
		this.pedido = new Pedido(this.cliente, this.cantidadPasajeros, this.mascota, this.baul, this.km,this.zona);
		this.event = mock(ActionEvent.class);
		when(event.getActionCommand()).thenReturn(Constantes.NUEVO_PEDIDO);
	}

	@Test
	public void testNuevoPedido() {
		this.controlador.actionPerformed(this.event);
		Assert.assertNotNull("No se pudo crear el nuevo pedido", this.empresa.getPedidos());
		}
	
	@After
	public void tearDown() {
		this.empresa.getClientes().clear();
		this.empresa.getPedidos().clear();
	}

}
