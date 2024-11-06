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
import vista.IVista;

public class TestControladorNuevoVehiculo {

	private String tipo = null;
	private String patente = null;
	private boolean mascota = false;
	private int plazas = 0;
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
		this.patente = "ABC123";
		this.mascota = true;
		this.plazas = 4;
		when(this.vistamock.getTipoVehiculo()).thenReturn(Constantes.AUTO);
		when(this.vistamock.getPatente()).thenReturn(this.patente);
		when(this.vistamock.getPlazas()).thenReturn(this.plazas);
		when(this.vistamock.isVehiculoAptoMascota()).thenReturn(this.mascota);
		this.event = mock(ActionEvent.class);
		when(event.getActionCommand()).thenReturn(Constantes.NUEVO_VEHICULO);
	}



	@Test
	public void testNuevoVehiculoAuto() {
		this.controlador.actionPerformed(this.event);
		Assert.assertEquals("El chofer no fue agregado con exito",false,this.empresa.getVehiculos().isEmpty());
	}
	
	@Test
	public void testNuevoVehiculoMoto() {
		when(this.vistamock.getTipoVehiculo()).thenReturn(Constantes.MOTO);
		this.controlador.actionPerformed(this.event);
		Assert.assertEquals("El chofer no fue agregado con exito",false,this.empresa.getVehiculos().isEmpty());
	}

	@After
	public void tearDown() throws Exception {
		this.empresa.getVehiculos().clear();
	}
}
