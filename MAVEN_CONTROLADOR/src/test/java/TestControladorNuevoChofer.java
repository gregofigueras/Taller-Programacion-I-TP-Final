import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.event.ActionEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.IOptionPane;
import vista.IVista;

public class TestControladorNuevoChofer {
	private String tipo = null;
	private String nombre = null;
	private String dni = null;
	private int anio = 0;
	private int hijos = 0;
	private Controlador controlador = null;
	private Empresa empresa = null;
	private ActionEvent event = null;
	private IVista vistamock;
	
	@Before
	public void setUp() throws Exception {
		this.empresa = Empresa.getInstance();
		this.controlador = new Controlador();
		this.vistamock = mock(IVista.class);
		this.controlador.setVista(this.vistamock);
		this.nombre = "Alberto";
		this.dni = "42541028";
		this.anio = 2018;
		this.hijos = 4;
		this.vistamock.addActionListener(this.controlador);
		when(this.vistamock.getTipoChofer()).thenReturn(Constantes.PERMANENTE);
		when(this.vistamock.getNombreChofer()).thenReturn(this.nombre);
		when(this.vistamock.getDNIChofer()).thenReturn(this.dni);
		when(this.vistamock.getAnioChofer()).thenReturn(this.anio);
		when(this.vistamock.getHijosChofer()).thenReturn(this.hijos);
		
		this.event = mock(ActionEvent.class);
		when(event.getActionCommand()).thenReturn(Constantes.NUEVO_CHOFER);
	}

	@Test
	public void testNuevoChoferPermanente() {
		this.controlador.actionPerformed(this.event);
		Assert.assertEquals("El chofer no fue agregado con exito",false,this.empresa.getChoferes().isEmpty());
	}
	
	  public void testNuevoChoferTemporario() {
	        // Cambiar el tipo a temporario
	        when(this.vistamock.getTipoChofer()).thenReturn(Constantes.TEMPORARIO);
	        
	        this.controlador.actionPerformed(this.event);
	        
	        assertFalse("El chofer temporario no fue agregado con éxito", this.empresa.getChoferes().isEmpty());
	    }
	
	@After
	public void tearDown() throws Exception {
		this.empresa.getChoferes().clear();

	}
	

	

}
