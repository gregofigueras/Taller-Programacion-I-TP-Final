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
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.IOptionPane;
import vista.IVista;

public class TestControladorNuevoViaje {
	private Controlador controlador = null;
	private Empresa empresa = null;
	private ActionEvent event = null;
	private IVista vistamock;
	private int cantidadPasajeros = 0;
	private boolean mascota = false;
	private boolean baul = false;
	private int km = 0;
	private Pedido pedido = null;
	private String zona = null;
	private IOptionPane mockOptionPane = null;
	private String tipo = null;
	private String nombre = null;
	private String dni = null;
	private int anio = 0;
	private int hijos = 0;
	private Chofer chofer = null;
	private Vehiculo vehiculo = null;
	private Cliente cliente = null;
	private String ussername = null;
	private String pass= null;
	private String nombrereal = null;
	
	@Before
	public void setUp() throws Exception {
		
			this.empresa = Empresa.getInstance();
			this.controlador = new Controlador();
			this.vistamock = mock(IVista.class);
			this.controlador.setVista(this.vistamock);
			
			//nuevo cliente
			this.ussername = "Cachito";
			this.pass = "Cachito123";
			this.nombrereal = "Carlos";
			
			//nuevo pedido
			this.cantidadPasajeros = 3;
			this.mascota = true;
			this.km = 100; 
			//nuevo vehiculo
			this.vehiculo = new Auto("AAA123", 4, true);
			
			//nuevo chofer
			this.nombre = "Alberto";
			this.dni = "42541028";
			this.anio = 2018;
			this.hijos = 4;
			this.zona = Constantes.ZONA_SIN_ASFALTAR;
		
			
			this.vistamock.addActionListener(this.controlador);
			this.mockOptionPane = mock(IOptionPane.class);
			
			this.empresa.agregarVehiculo(this.vehiculo);	
			
			this.empresa.agregarCliente(this.ussername,this.pass, this.nombrereal);
			this.empresa.login(this.ussername, this.pass);
			this.cliente= new Cliente(this.ussername,this.pass,this.nombrereal);
			
			this.pedido = new Pedido(this.empresa.getClientes().get("Cachito"),this.cantidadPasajeros, this.mascota, this.baul, this.km,this.zona);
			this.empresa.agregarPedido(this.pedido);
			
			when(this.vistamock.getPedidoSeleccionado()).thenReturn(this.pedido);
			when(this.vistamock.getChoferDisponibleSeleccionado()).thenReturn(this.chofer);
			when(this.vistamock.getVehiculoDisponibleSeleccionado()).thenReturn(this.vehiculo);
			when(this.vistamock.getOptionPane()).thenReturn(this.mockOptionPane);
			
			this.event = mock(ActionEvent.class);
			when(event.getActionCommand()).thenReturn(Constantes.NUEVO_VIAJE);
		}
	
	
	@Test
	public void test() {
		this.controlador.actionPerformed(this.event);
		Assert.assertTrue("No se pudo iniciar el viaje nuevo",this.empresa.getViajesIniciados().isEmpty());
	}

	@After
	public void tearDown() throws Exception {
		this.empresa.getClientes().clear();
		this.empresa.getChoferes().clear();
		this.empresa.getVehiculos().clear();
		this.empresa.getPedidos().clear();
		this.empresa.getViajesIniciados().clear();
	}

	
}
