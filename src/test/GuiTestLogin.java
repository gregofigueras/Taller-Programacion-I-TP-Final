package test;

import controlador.Controlador;
import util.Constantes;
import util.Mensajes;
import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;
import vista.Ventana;
import vista.IVista;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuiTestLogin {
    Robot robot;
    Controlador controlador;
    IVista vista = new Ventana();
    FalsoOptionPane op= new FalsoOptionPane();  
    public GuiTestLogin()
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
        vista.setOptionPane(op);
    }
    
    @After
    public void tearDown() throws Exception {
        controlador = null;
        robot = null;
    }
    @Test
    public void testVacios()
    {
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");
        JButton register = (JButton) TestUtils.getComponentForName((Component) vista, "REGISTRAR");
        Assert.assertFalse("El boton de login deberia estar deshablitado", loginButton.isEnabled());
        Assert.assertTrue("El boton de registro deberia estar habilitado",register.isEnabled());
    }
    @Test
    public void testRegisterEnabled() {
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), "REGISTRAR");
        Assert.assertTrue("El boton de registro deberia estar habilitado",registrarButton.isEnabled());

    }
    
    @Test 
    public void testLogSoloNombre() {
    	robot.delay(TestUtils.getDelay());
    	 JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
         JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");
         //lleno los JTextField
         TestUtils.clickComponent(nombreUsuario, robot);
         TestUtils.tipeaTexto("hola", robot);
         //verifico los resultados
         Assert.assertFalse("El boton de login deberia estar deshablitado", loginButton.isEnabled());
    }
    @Test 
    public void testLogSoloPassword() {
    	robot.delay(TestUtils.getDelay());
    	 JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
         JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");
         //lleno los JTextField
         TestUtils.clickComponent(password, robot);
         TestUtils.tipeaTexto("hola", robot);
         //verifico los resultados
         Assert.assertFalse("El boton de login deberia estar deshablitado", loginButton.isEnabled());
    }
    
    @Test
    public void testLogEnabled()
    {
        robot.delay(TestUtils.getDelay());

        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");

        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("h", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("kjiykjhih", robot);

        Assert.assertTrue("El boton de login deberia estar hablitado", loginButton.isEnabled());
    }
    @Test
    public void testLogIncorrecto()
    {
        robot.delay(TestUtils.getDelay());

        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");

        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("hfknfcrub", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("kjiykjhih", robot);
        TestUtils.clickComponent(loginButton, robot);
        
        robot.delay(TestUtils.getDelay());
        String msjerror= op.getMensaje();
        
        Assert.assertEquals("Deberia decir: "+Mensajes.USUARIO_DESCONOCIDO.getValor(),Mensajes.USUARIO_DESCONOCIDO.getValor(), msjerror);  
    }
    @Test
    public void testPassMal() {
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
        TestUtils.tipeaTexto("passjyf1", robot);
        TestUtils.clickComponent(loginButton, robot);
        
        Assert.assertEquals("Deberia decir: "+Mensajes.PASS_ERRONEO.getValor(),Mensajes.PASS_ERRONEO.getValor(),op.getMensaje());
    }
    @Test
    public void testLoginAdmin() {
    	robot.delay(TestUtils.getDelay());

        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");

        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
        
        robot.delay(TestUtils.getDelay());
        vista=controlador.getVista();
        JPanel paneladmin = (JPanel) TestUtils.getComponentForName((Component) vista, Constantes.PANEL_ADMINISTRADOR);
        Assert.assertTrue("El panel de administrador debería estar visible", paneladmin.isVisible());
        
    }
    @Test
    public void testLoginCliente(){
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
        vista=controlador.getVista();
        JPanel panelcliente = (JPanel) TestUtils.getComponentForName((Component) vista, Constantes.PANEL_CLIENTE);
        Assert.assertTrue("El panel de cliente debería estar visible", panelcliente.isVisible());
    }
}
