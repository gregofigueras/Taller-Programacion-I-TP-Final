package test;

import controlador.Controlador;
import modeloDatos.Chofer;
import modeloDatos.Cliente;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;
import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;
import java.awt.event.ComponentEvent;

import vista.Ventana;
import vista.IVista;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuiTestCliente {
    Robot robot;
    Controlador controlador;
    IVista vista = new Ventana();
    FalsoOptionPane op= new FalsoOptionPane();  
    
    public GuiTestCliente()
    {
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
        }
    }
    @Before
    public void setUp() throws Exception
    {   
        controlador = new Controlador();
        vista=controlador.getVista();

        JButton botonRegistrar = (JButton) TestUtils.getComponentForName((Component) vista, "REGISTRAR");
        TestUtils.clickComponent(botonRegistrar, robot);  
        robot.delay(TestUtils.getDelay());
        
    	JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_REGISTRAR");
        JTextField regnombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_USSER_NAME");
        JTextField regpassword = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_PASSWORD");
        JTextField repetirPassword = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_CONFIRM_PASSWORD");
        JTextField nombreReal = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_REAL_NAME");
        
        
        TestUtils.clickComponent(regnombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(regpassword, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(repetirPassword, robot);
        TestUtils.tipeaTexto("password1", robot); 
        TestUtils.clickComponent(nombreReal, robot);
        TestUtils.tipeaTexto("Nombre Real", robot);

        TestUtils.clickComponent(registrarButton, robot);
        
        robot.delay(TestUtils.getDelay());
        
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");

        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(loginButton, robot);
        robot.delay(TestUtils.getDelay());
        vista.setOptionPane(op);
    }
    
    @After
    public void tearDown() throws Exception {
        controlador = null;
        robot = null;
    }
    @Test
    public void testTituloNombre()
    {   
    	JPanel panelCliente = (JPanel) TestUtils.getComponentForName((Component) vista, "PANEL_CLIENTE");
    	Border border = panelCliente.getBorder();
    	Assert.assertTrue("El JPanel debería tener un TitledBorder", border instanceof TitledBorder);
        TitledBorder titledBorder = (TitledBorder) border;
    	String titulo = titledBorder.getTitle();
    	String nombre="Nombre Real";
    	Assert.assertEquals("El título del panel cliente debería ser el nombre real del cliente", nombre, titulo);
    	
    }
    @Test
    public void testCerrarSesion() {
    	JButton cerrarsesion=(JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_CLIENTE");
    	//chequea que este habilitado
    	Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarsesion.isEnabled());
    	//chequeo que vuelva a la pantalla de inicio
    	TestUtils.clickComponent(cerrarsesion, robot);
    	JPanel panellogin = (JPanel) TestUtils.getComponentForName((Component) vista, Constantes.PANEL_LOGIN);
        Assert.assertTrue("El panel de logueo debería estar visible", panellogin.isVisible());
    	
    }
    @Test
    public void testPanelCliente() {
    	JTextArea pedidoactual=(JTextArea) TestUtils.getComponentForName((Component) vista, "PEDIDO_O_VIAJE_ACTUAL");
    	String contenido = pedidoactual.getText();
    	Assert.assertTrue("El JTextArea debería estar vacío", contenido.isEmpty());
    	JTextField calificacion=(JTextField) TestUtils.getComponentForName((Component) vista, Constantes.CALIFICACION_DE_VIAJE);
    	JTextField valor=(JTextField) TestUtils.getComponentForName((Component) vista, "VALOR_VIAJE");
    	JTextField km=(JTextField) TestUtils.getComponentForName((Component) vista, "CANT_KM");
    	JTextField cantpas=(JTextField) TestUtils.getComponentForName((Component) vista, "CANT_PAX");
    	JRadioButton zstandar=(JRadioButton) TestUtils.getComponentForName((Component) vista, Constantes.ZONA_STANDARD);
    	JRadioButton zsnasfaltar=(JRadioButton) TestUtils.getComponentForName((Component) vista, Constantes.ZONA_SIN_ASFALTAR);
    	JRadioButton zpeligrosa=(JRadioButton) TestUtils.getComponentForName((Component) vista, Constantes.ZONA_PELIGROSA);
    	JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) vista, "CHECK_MASCOTA");
    	JCheckBox baul = (JCheckBox) TestUtils.getComponentForName((Component) vista, "CHECK_BAUL");
    	//No hay pedido
    	Assert.assertFalse("Calificacion deberia estar disabled", calificacion.isEnabled());
    	contenido = valor.getText();
        Assert.assertTrue("El valor debería estar vacío", contenido.isEmpty());
        Assert.assertTrue("El textfield km deberia estar habilitado", km.isEnabled());
        Assert.assertTrue("El textfield cant pasajeros deberia estar habilitado", cantpas.isEnabled());
        Assert.assertTrue("El boton zona estandar deberia estar habilitado", zstandar.isEnabled());
        Assert.assertTrue("El boton zona sin asfaltar deberia estar habilitado", zsnasfaltar.isEnabled());
        Assert.assertTrue("El boton zona peligrosa deberia estar habilitado", zpeligrosa.isEnabled());
        Assert.assertTrue("El check box mascota deberia estar habilitado", mascota.isEnabled());
        Assert.assertTrue("El check box baul deberia estar habilitado", baul.isEnabled());
        JButton nuevopedido=(JButton)TestUtils.getComponentForName((Component) vista, Constantes.NUEVO_PEDIDO);
    	JButton cerrarsesioncliente = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_CLIENTE");
    	TestUtils.clickComponent(cerrarsesioncliente, robot);
    	
    	JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");
    	TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
    	
        JButton nuevovehiculo = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VEHICULO");
    	JRadioButton auto=(JRadioButton) TestUtils.getComponentForName((Component) vista, "AUTO");
    	JTextField patente= (JTextField) TestUtils.getComponentForName((Component) vista, "PATENTE");
    	JTextField plazas= (JTextField) TestUtils.getComponentForName((Component) vista, "CANTIDAD_PLAZAS");
    	JCheckBox mascotaCheckBox = (JCheckBox) TestUtils.getComponentForName((Component) vista, "CHECK_VEHICULO_ACEPTA_MASCOTA"); 
    	
    	TestUtils.clickComponent(auto, robot);
    	TestUtils.clickComponent(mascotaCheckBox, robot);
    	TestUtils.clickComponent(patente, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(plazas, robot);
    	TestUtils.tipeaTexto("4", robot);
    	TestUtils.clickComponent(nuevovehiculo, robot);
      	robot.delay(TestUtils.getDelay());
      	
        JButton nuevochofer = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_CHOFER");
    	JRadioButton permanente= (JRadioButton) TestUtils.getComponentForName((Component) vista, "PERMANENTE");
    	JTextField dnichofer=(JTextField) TestUtils.getComponentForName((Component) vista, "DNI_CHOFER");
    	JTextField nombrechofer=(JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_CHOFER");
    	JTextField canthijos=(JTextField) TestUtils.getComponentForName((Component) vista, "CH_CANT_HIJOS");
    	JTextField aingreso=(JTextField) TestUtils.getComponentForName((Component) vista, Constantes.CH_ANIO);
    	
      	TestUtils.clickComponent(permanente, robot);  	
    	TestUtils.clickComponent(dnichofer, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(nombrechofer, robot);
    	TestUtils.tipeaTexto("Juan", robot);
    	TestUtils.clickComponent(canthijos, robot);
    	TestUtils.tipeaTexto("2", robot);
    	TestUtils.clickComponent(aingreso, robot);
    	TestUtils.tipeaTexto("2012", robot);
    	TestUtils.clickComponent(nuevochofer, robot);
    	robot.delay(TestUtils.getDelay());
      	
      	JButton cerrarsesionadmin = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_ADMIN");
      	TestUtils.clickComponent(cerrarsesionadmin, robot);
      	
      	TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(loginButton, robot);
        robot.delay(TestUtils.getDelay());
        
    	TestUtils.clickComponent(cantpas, robot);
    	TestUtils.tipeaTexto("3", robot);
    	TestUtils.clickComponent(km, robot);
    	TestUtils.tipeaTexto("12", robot);
    	TestUtils.clickComponent(nuevopedido, robot);
    	//Hay pedido
    	contenido = pedidoactual.getText();
    	Assert.assertTrue("Deberia haber un pedido", contenido.startsWith("Pedido"));
    	Assert.assertFalse("Calificacion deberia estar disabled", calificacion.isEnabled());
    	contenido = valor.getText();
        Assert.assertTrue("El valor debería estar vacío", contenido.isEmpty());
        Assert.assertFalse("El textfield km deberia estar inhabilitado", km.isEnabled());
        Assert.assertFalse("El textfield cant pasajeros deberia estar inhabilitado", cantpas.isEnabled());
        Assert.assertFalse("El boton zona estandar deberia estar inhabilitado", zstandar.isEnabled());
        Assert.assertFalse("El boton zona sin asfaltar deberia estar inhabilitado", zsnasfaltar.isEnabled());
        Assert.assertFalse("El boton zona peligrosa deberia estar inhabilitado", zpeligrosa.isEnabled());
        Assert.assertFalse("El check box mascota deberia estar inhabilitado", mascota.isEnabled());
        Assert.assertFalse("El check box baul deberia estar inhabilitado", baul.isEnabled());
        
        TestUtils.clickComponent(cerrarsesioncliente, robot);
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
        JList<Viaje> pedidosdisp=(JList<Viaje>) TestUtils.getComponentForName((Component) vista, "LISTA_PEDIDOS_PENDIENTES");
    	JList<Chofer> chofereslib=(JList<Chofer>) TestUtils.getComponentForName((Component) vista, "LISTA_CHOFERES_LIBRES");
    	JList<Vehiculo> vehiculosdisp=(JList<Vehiculo>) TestUtils.getComponentForName((Component) vista, "LISTA_VEHICULOS_DISPONIBLES");
    	JButton nuevoviaje = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VIAJE");
    	pedidosdisp.setSelectedIndex(0);
    	chofereslib.setSelectedIndex(0);
    	vehiculosdisp.setSelectedIndex(0);
    	TestUtils.clickComponent(pedidosdisp, robot);
    	TestUtils.clickComponent(chofereslib, robot);
    	TestUtils.clickComponent(vehiculosdisp, robot);
        TestUtils.clickComponent(nuevoviaje, robot);
        TestUtils.clickComponent(cerrarsesionadmin, robot);
      	
      	TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(loginButton, robot);
        robot.delay(TestUtils.getDelay());
        
        //hay viaje
        contenido = pedidoactual.getText();
    	Assert.assertTrue("Deberia haber un viaje", contenido.startsWith("Viaje"));
    	Assert.assertTrue("Calificacion deberia estar habilitada", calificacion.isEnabled());
    	contenido = valor.getText();
        Assert.assertFalse("El valor no debería estar vacío", contenido.isEmpty());
        Assert.assertFalse("El textfield km deberia estar inhabilitado", km.isEnabled());
        Assert.assertFalse("El textfield cant pasajeros deberia estar inhabilitado", cantpas.isEnabled());
        Assert.assertFalse("El boton zona estandar deberia estar inhabilitado", zstandar.isEnabled());
        Assert.assertFalse("El boton zona sin asfaltar deberia estar inhabilitado", zsnasfaltar.isEnabled());
        Assert.assertFalse("El boton zona peligrosa deberia estar inhabilitado", zpeligrosa.isEnabled());
        Assert.assertFalse("El check box mascota deberia estar inhabilitado", mascota.isEnabled());
        Assert.assertFalse("El check box baul deberia estar inhabilitado", baul.isEnabled());
        
    }
    @Test
    public void testPedido() {
    	JTextArea pedidoactual=(JTextArea) TestUtils.getComponentForName((Component) vista, "PEDIDO_O_VIAJE_ACTUAL");
    	JTextField km=(JTextField) TestUtils.getComponentForName((Component) vista, "CANT_KM");
    	JTextField cantpas=(JTextField) TestUtils.getComponentForName((Component) vista, "CANT_PAX");
        JButton nuevopedido=(JButton)TestUtils.getComponentForName((Component) vista, Constantes.NUEVO_PEDIDO);
    	JButton cerrarsesioncliente = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_CLIENTE");
    	
    	Assert.assertFalse("El boton nuevo pedido deberia estar deshabilitado", nuevopedido.isEnabled());
    	
    	TestUtils.clickComponent(cantpas, robot);
    	TestUtils.tipeaTexto("3", robot);
    	Assert.assertFalse("El boton nuevo pedido deberia estar deshabilitado", nuevopedido.isEnabled());
    	TestUtils.clickComponent(km, robot);
    	TestUtils.tipeaTexto("12", robot);
    	Assert.assertTrue("El boton nuevo pedido deberia estar habilitado", nuevopedido.isEnabled());
    	TestUtils.clickComponent(nuevopedido, robot);
    	//pide y no hay vehiculo
    	Assert.assertEquals("Deberia mostrar mensaje de que no hay vehiculo", Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), op.getMensaje());
    	
    	TestUtils.clickComponent(cerrarsesioncliente, robot);
    	JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");
    	TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
    	
        JButton nuevovehiculo = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VEHICULO");
    	JRadioButton auto=(JRadioButton) TestUtils.getComponentForName((Component) vista, "AUTO");
    	JTextField patente= (JTextField) TestUtils.getComponentForName((Component) vista, "PATENTE");
    	JTextField plazas= (JTextField) TestUtils.getComponentForName((Component) vista, "CANTIDAD_PLAZAS");
    	JCheckBox mascotaCheckBox = (JCheckBox) TestUtils.getComponentForName((Component) vista, "CHECK_VEHICULO_ACEPTA_MASCOTA"); 
    	
    	TestUtils.clickComponent(auto, robot);
    	TestUtils.clickComponent(mascotaCheckBox, robot);
    	TestUtils.clickComponent(patente, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(plazas, robot);
    	TestUtils.tipeaTexto("4", robot);
    	TestUtils.clickComponent(nuevovehiculo, robot);
      	robot.delay(TestUtils.getDelay());
      	
        JButton nuevochofer = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_CHOFER");
    	JRadioButton permanente= (JRadioButton) TestUtils.getComponentForName((Component) vista, "PERMANENTE");
    	JTextField dnichofer=(JTextField) TestUtils.getComponentForName((Component) vista, "DNI_CHOFER");
    	JTextField nombrechofer=(JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_CHOFER");
    	JTextField canthijos=(JTextField) TestUtils.getComponentForName((Component) vista, "CH_CANT_HIJOS");
    	JTextField aingreso=(JTextField) TestUtils.getComponentForName((Component) vista, Constantes.CH_ANIO);
    	
      	TestUtils.clickComponent(permanente, robot);  	
    	TestUtils.clickComponent(dnichofer, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(nombrechofer, robot);
    	TestUtils.tipeaTexto("Juan", robot);
    	TestUtils.clickComponent(canthijos, robot);
    	TestUtils.tipeaTexto("2", robot);
    	TestUtils.clickComponent(aingreso, robot);
    	TestUtils.tipeaTexto("2012", robot);
    	TestUtils.clickComponent(nuevochofer, robot);
    	robot.delay(TestUtils.getDelay());
      	
      	JButton cerrarsesionadmin = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_ADMIN");
      	TestUtils.clickComponent(cerrarsesionadmin, robot);
      	
      	TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(loginButton, robot);
        robot.delay(TestUtils.getDelay());
        
    	TestUtils.clickComponent(cantpas, robot);
    	TestUtils.tipeaTexto("3", robot);
    	TestUtils.clickComponent(km, robot);
    	TestUtils.tipeaTexto("12", robot);
    	TestUtils.clickComponent(nuevopedido, robot);
    	
    	String contenido = pedidoactual.getText();
    	Assert.assertTrue("Deberia haber un pedido", contenido.startsWith("Pedido"));
    }
    @Test
    public void testCalificar() {
    	JTextArea pedidoactual=(JTextArea) TestUtils.getComponentForName((Component) vista, "PEDIDO_O_VIAJE_ACTUAL");
    	JTextField calificacion=(JTextField) TestUtils.getComponentForName((Component) vista, Constantes.CALIFICACION_DE_VIAJE);
    	JTextField km=(JTextField) TestUtils.getComponentForName((Component) vista, "CANT_KM");
    	JTextField cantpas=(JTextField) TestUtils.getComponentForName((Component) vista, "CANT_PAX");
        JButton nuevopedido=(JButton)TestUtils.getComponentForName((Component) vista, Constantes.NUEVO_PEDIDO);
        JButton calificarPagar=(JButton) TestUtils.getComponentForName((Component) vista, Constantes.CALIFICAR_PAGAR);
    	JButton cerrarsesioncliente = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_CLIENTE");
    	
    	TestUtils.clickComponent(cerrarsesioncliente, robot);
    	JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");
    	TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
    	
        JButton nuevovehiculo = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VEHICULO");
    	JRadioButton auto=(JRadioButton) TestUtils.getComponentForName((Component) vista, "AUTO");
    	JTextField patente= (JTextField) TestUtils.getComponentForName((Component) vista, "PATENTE");
    	JTextField plazas= (JTextField) TestUtils.getComponentForName((Component) vista, "CANTIDAD_PLAZAS");
    	JCheckBox mascotaCheckBox = (JCheckBox) TestUtils.getComponentForName((Component) vista, "CHECK_VEHICULO_ACEPTA_MASCOTA"); 
    	
    	TestUtils.clickComponent(auto, robot);
    	TestUtils.clickComponent(mascotaCheckBox, robot);
    	TestUtils.clickComponent(patente, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(plazas, robot);
    	TestUtils.tipeaTexto("4", robot);
    	TestUtils.clickComponent(nuevovehiculo, robot);
      	robot.delay(TestUtils.getDelay());
      	
        JButton nuevochofer = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_CHOFER");
    	JRadioButton permanente= (JRadioButton) TestUtils.getComponentForName((Component) vista, "PERMANENTE");
    	JTextField dnichofer=(JTextField) TestUtils.getComponentForName((Component) vista, "DNI_CHOFER");
    	JTextField nombrechofer=(JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_CHOFER");
    	JTextField canthijos=(JTextField) TestUtils.getComponentForName((Component) vista, "CH_CANT_HIJOS");
    	JTextField aingreso=(JTextField) TestUtils.getComponentForName((Component) vista, Constantes.CH_ANIO);
    	
      	TestUtils.clickComponent(permanente, robot);  	
    	TestUtils.clickComponent(dnichofer, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(nombrechofer, robot);
    	TestUtils.tipeaTexto("Juan", robot);
    	TestUtils.clickComponent(canthijos, robot);
    	TestUtils.tipeaTexto("2", robot);
    	TestUtils.clickComponent(aingreso, robot);
    	TestUtils.tipeaTexto("2012", robot);
    	TestUtils.clickComponent(nuevochofer, robot);
    	robot.delay(TestUtils.getDelay());
      	
      	JButton cerrarsesionadmin = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_ADMIN");
      	TestUtils.clickComponent(cerrarsesionadmin, robot);
      	
      	TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(loginButton, robot);
        robot.delay(TestUtils.getDelay());
        
    	TestUtils.clickComponent(cantpas, robot);
    	TestUtils.tipeaTexto("3", robot);
    	TestUtils.clickComponent(km, robot);
    	TestUtils.tipeaTexto("12", robot);
    	TestUtils.clickComponent(nuevopedido, robot);
    	
    	 TestUtils.clickComponent(cerrarsesioncliente, robot);
         TestUtils.clickComponent(nombreUsuario, robot);
         TestUtils.tipeaTexto("admin", robot);
         TestUtils.clickComponent(password, robot);
         TestUtils.tipeaTexto("admin", robot);
         TestUtils.clickComponent(loginButton, robot);
         JList<Viaje> pedidosdisp=(JList<Viaje>) TestUtils.getComponentForName((Component) vista, "LISTA_PEDIDOS_PENDIENTES");
     	JList<Chofer> chofereslib=(JList<Chofer>) TestUtils.getComponentForName((Component) vista, "LISTA_CHOFERES_LIBRES");
     	JList<Vehiculo> vehiculosdisp=(JList<Vehiculo>) TestUtils.getComponentForName((Component) vista, "LISTA_VEHICULOS_DISPONIBLES");
     	JButton nuevoviaje = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VIAJE");
     	pedidosdisp.setSelectedIndex(0);
     	chofereslib.setSelectedIndex(0);
     	vehiculosdisp.setSelectedIndex(0);
     	TestUtils.clickComponent(pedidosdisp, robot);
     	TestUtils.clickComponent(chofereslib, robot);
     	TestUtils.clickComponent(vehiculosdisp, robot);
         TestUtils.clickComponent(nuevoviaje, robot);
         TestUtils.clickComponent(cerrarsesionadmin, robot);
       	
       	TestUtils.clickComponent(nombreUsuario, robot);
         TestUtils.tipeaTexto("nuevoUsuario", robot);
         TestUtils.clickComponent(password, robot);
         TestUtils.tipeaTexto("password1", robot);
         TestUtils.clickComponent(loginButton, robot);
         robot.delay(TestUtils.getDelay());
    	
    	
    	Assert.assertFalse("El boton pagar deberia estar deshabilitado",calificarPagar.isEnabled());
    	TestUtils.clickComponent(calificacion, robot);
    	TestUtils.tipeaTexto("4", robot);
    	robot.delay(TestUtils.getDelay());
    	Assert.assertTrue("El boton pagar deberia estar habilitado",calificarPagar.isEnabled());
    	TestUtils.clickComponent(calificarPagar, robot);
    	String contenido=pedidoactual.getText();
    	Assert.assertTrue("La lista de pendiente actual debería estar vacía", contenido.isEmpty());
    	contenido=calificacion.getText();
    	Assert.assertTrue("La calificacion debería estar vacía", contenido.isEmpty());
    	
    	JList viajesCliente=(JList) TestUtils.getComponentForName((Component) vista, "LISTA_VIAJES_CLIENTE");
    	Assert.assertEquals("El viaje deberia aparecer en la lista de viajes del cliente", 1, viajesCliente.getModel().getSize());
    	
    }
}
