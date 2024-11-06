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
import modeloNegocio.Empresa;
import util.Constantes;
import vista.IVista;

public class TestControladorLogout {
	private Controlador controlador = null;
	private String ussername = null;
	private String pass = null;
	private String nombrereal = null;
	private Empresa empresa = null;
	private ActionEvent event = null;
	private Usuario cliente=null;
	private IVista vistamock;
	
	
	@Before
	public void setUp() throws Exception {
		this.ussername = "Cachito";
		this.pass = "Cachito123";
		this.nombrereal = "Carlos";
		this.empresa = Empresa.getInstance();
		this.controlador = new Controlador();
		this.vistamock = mock(IVista.class);
		this.controlador.setVista(this.vistamock);
		
		this.vistamock.addActionListener(this.controlador);
		when(this.vistamock.getUsserName()).thenReturn(this.ussername);
		when(this.vistamock.getPassword()).thenReturn(this.pass);
		when(this.vistamock.getRegNombreReal()).thenReturn(this.nombrereal);
		this.cliente= new Cliente(this.ussername,this.pass,this.nombrereal);
		this.empresa.agregarCliente(this.ussername,this.pass, this.nombrereal);
		
		this.event = mock(ActionEvent.class);
		when(event.getActionCommand()).thenReturn(Constantes.CERRAR_SESION_CLIENTE);
	}
	




	@Test
	public void testLogout() {
		this.controlador.actionPerformed(this.event);
		Assert.assertEquals("Error al desloguearse",null,this.empresa.getUsuarioLogeado());
		}
	
	@After
	public void tearDown() {
		this.empresa.getClientes().clear();
	}
}