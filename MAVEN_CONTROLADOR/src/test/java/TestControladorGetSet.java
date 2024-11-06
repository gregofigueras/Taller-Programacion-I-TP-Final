import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;


import controlador.Controlador;
import excepciones.ClienteSinViajePendienteException;
import modeloNegocio.Empresa;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import vista.IVista;
import vista.Ventana;

public class TestControladorGetSet {
	private Controlador controlador = null;
	private IVista vistamock = null;
	private IPersistencia persistenciamock = null;
	private String filename = null;
	
	@Before
	public void setUp() throws Exception {
		this.controlador = new Controlador();
		this.vistamock = mock(IVista.class);
		this.persistenciamock = mock(IPersistencia.class);
		this.filename = "empresa.bin";
	}
	
	@Test
	public void TestVista() {
		this.controlador.setVista(this.vistamock);
		Assert.assertEquals("Hubo un fallo en la vista",this.vistamock,controlador.getVista());
	}

	@Test 
	public void TestPersistencia() {
		this.controlador.setPersistencia(this.persistenciamock);
		Assert.assertEquals("Hubo un fallo en la persistencia",this.persistenciamock,controlador.getPersistencia());
	}
	
	@Test 
	public void TestFileName() {
		this.controlador.setFileName(this.filename);
		Assert.assertEquals("Hubo un error con el filename",this.filename,controlador.getFileName());
	}
	

}
