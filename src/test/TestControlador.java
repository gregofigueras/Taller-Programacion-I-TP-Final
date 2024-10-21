package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.Test;
import vista.IVista;
import vista.Ventana;
import vista.*;
import controlador.Controlador;
import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import junit.framework.Assert;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import modeloNegocio.Empresa;
import util.Constantes;

public class TestControlador {
	private Controlador controlador = null;
	private IVista vista = null;
	private Empresa empresa = null;
	@Before
	public void setUp() throws Exception {
		this.controlador = new Controlador();
		this.vista = new Ventana();
		this.empresa = Empresa.getInstance();
	}
/*
	@Test
	public void TestactionPerformed() {
		ActionEvent e = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,Constantes.CERRAR_SESION_ADMIN);
		controlador.actionPerformed(e);
		Assert.assertEquals("Hubo un error al cerrar sesion como admin",empresa.);

	}
*/	
	
	@Test
	public void TestVista() {
		controlador.setVista(vista);
		Assert.assertEquals("Hubo un fallo en la vista",vista,controlador.getVista());
	}

	@Test 
	public void TestPersistencia() {
		IPersistencia persistencia = new PersistenciaBIN();
		controlador.setPersistencia(persistencia);
		Assert.assertEquals("Hubo un fallo en la persistencia",persistencia,controlador.getPersistencia());
	}
	
	@Test 
	public void TestFileName() {
		String filename = "NombreArchivo";
		controlador.setFileName(filename);
		Assert.assertEquals("Hubo un error con el filename",filename,controlador.getFileName());
	}
	

	

}
