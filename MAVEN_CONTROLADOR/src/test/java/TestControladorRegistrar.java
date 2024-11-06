import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
import util.Mensajes;
import vista.IOptionPane;
import vista.IVista;
public class TestControladorRegistrar {
	private Controlador controlador = null;
	private String ussername = null;
	private String pass = null;
	private String confirm = null;
	private String nombrereal = null;
	private Empresa empresa = null;
	private ActionEvent event = null;
	private Usuario cliente=null;
	private IVista vistamock;
	private IOptionPane mockOptionPane = null;

	@Before
	public void setUp() throws Exception {
		this.ussername = "Cachito";
		this.pass = "Cachito123";
		this.confirm = "Cachito123";
		this.nombrereal = "Carlos";
		this.empresa = Empresa.getInstance();
		this.controlador = new Controlador();
		this.vistamock = mock(IVista.class);
		this.controlador.setVista(this.vistamock);
		this.mockOptionPane = mock(IOptionPane.class);
		this.vistamock.addActionListener(this.controlador);
		when(this.vistamock.getRegUsserName()).thenReturn(this.ussername);
		when(this.vistamock.getRegPassword()).thenReturn(this.pass);
		when(this.vistamock.getRegNombreReal()).thenReturn(this.nombrereal);
		when(this.vistamock.getRegConfirmPassword()).thenReturn(this.confirm);
		when(this.vistamock.getOptionPane()).thenReturn(this.mockOptionPane);
		this.event = mock(ActionEvent.class);
		when(event.getActionCommand()).thenReturn(Constantes.REG_BUTTON_REGISTRAR);
	}


	@Test
	public void testRegistrarCoincidenPass() {
		this.controlador.actionPerformed(this.event);
		Assert.assertEquals("No se pudo registrar correctamene", 1,this.empresa.getClientes().size());
	}
	
	@Test
	public void testRegistrarNOCoincidenPass() {
		when(this.vistamock.getRegConfirmPassword()).thenReturn("Otracosa");
		this.controlador.actionPerformed(this.event);
        verify(this.mockOptionPane).ShowMessage(Mensajes.PASS_NO_COINCIDE.getValor());  
        Assert.assertEquals("No debe registrar cliente cuando las contraseñas no coinciden", 0, this.empresa.getClientes().size());
	}
	
	@After
	public void tearDown() throws Exception {
		this.empresa.getClientes().clear();
	}
}
