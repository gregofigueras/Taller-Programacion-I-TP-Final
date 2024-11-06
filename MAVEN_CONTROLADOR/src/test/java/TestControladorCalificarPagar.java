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
import modeloDatos.Cliente;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.IVista;
import vista.IOptionPane;

public class TestControladorCalificarPagar {
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
	private int calificacion = 0;
	private Vehiculo vehiculo = null;
	
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
		this.vehiculo = new Auto("AAA123", 4, true);
		this.zona = Constantes.ZONA_SIN_ASFALTAR;
		this.calificacion = 4;
		this.vistamock.addActionListener(this.controlador);
		this.mockOptionPane = mock(IOptionPane.class);
		
		this.empresa.agregarVehiculo(this.vehiculo);		
		this.empresa.agregarCliente(this.ussername,this.pass, this.nombrereal);
		this.empresa.login(this.ussername, this.pass);
		
		this.cliente= new Cliente(this.ussername,this.pass,this.nombrereal);
		this.pedido = new Pedido(this.empresa.getClientes().get("Cachito"),this.cantidadPasajeros, this.mascota, this.baul, this.km,this.zona);
		this.empresa.agregarPedido(this.pedido);
		
		when(this.vistamock.getCalificacion()).thenReturn(this.calificacion);
		when(this.vistamock.getOptionPane()).thenReturn(this.mockOptionPane);
		this.event = mock(ActionEvent.class);
		when(event.getActionCommand()).thenReturn(Constantes.CALIFICAR_PAGAR);
	}

	@Test
	public void testCalificarPagar() {
		this.controlador.actionPerformed(this.event);
		Assert.assertTrue("El pedido no fue finalizado correctamente", this.empresa.getViajesTerminados().isEmpty());
	}

	@After
	public void tearDown() throws Exception {
		this.empresa.getClientes().clear();
		this.empresa.getPedidos().clear();
		this.empresa.getViajesTerminados().clear();
		this.empresa.getVehiculos().clear();	
		}

}
