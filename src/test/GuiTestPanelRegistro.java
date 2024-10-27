package test;

import controlador.Controlador;
import gui.MiOptionPane;
import util.Mensajes;
import vista.Ventana;
import vista.IOptionPane;
import vista.IVista;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuiTestPanelRegistro {
    Robot robot;
    Controlador controlador;
    IVista vista = new Ventana();
    FalsoOptionPane op= new FalsoOptionPane(); 

    
    public GuiTestPanelRegistro() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {
        controlador = new Controlador();        
        vista=controlador.getVista();
        vista.setOptionPane(op); 
        // Transición al panel de registro desde la vista de login
        JButton botonRegistrar = (JButton) TestUtils.getComponentForName((Component) vista, "REGISTRAR");
        TestUtils.clickComponent(botonRegistrar, robot);  
        robot.delay(TestUtils.getDelay());


    }

    @After
    public void tearDown() throws Exception {
        controlador = null;
        robot = null;
    }
    @Test
    public void testRegisterEnabled() {
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_REGISTRAR");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_USSER_NAME");
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_PASSWORD");
        JTextField repetirPassword = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_CONFIRM_PASSWORD");
        JTextField nombreReal = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_REAL_NAME");
        Assert.assertFalse("El boton de registro deberia estar deshabilitado",registrarButton.isEnabled());
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(repetirPassword, robot);
        TestUtils.tipeaTexto("password1", robot); 
        TestUtils.clickComponent(nombreReal, robot);
        TestUtils.tipeaTexto("Nombre Real", robot);
        
        Assert.assertTrue("El boton de registro deberia estar habilitado",registrarButton.isEnabled());
    }
    @Test
    public void testRegisterDisabled() {
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_REGISTRAR");
        Assert.assertFalse("El boton de registro deberia estar deshabilitado",registrarButton.isEnabled());

    }
    @Test
    public void testCancelarEnabled() {
        JButton cancelarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_CANCELAR");
        Assert.assertTrue("El boton de registro cancelar deberia estar habilitado",cancelarButton.isEnabled());
    }
    @Test
    public void testRegSoloNombre() {
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_REGISTRAR");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_USSER_NAME");
        
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);

        Assert.assertFalse("El boton de registro deberia estar deshabilitado",registrarButton.isEnabled());
    }
    @Test
    public void testRegSolPassword() {
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_REGISTRAR");
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_PASSWORD");
        
        
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password", robot);

        Assert.assertFalse("El boton de registro deberia estar deshabilitado",registrarButton.isEnabled());
    }
    @Test
    public void testRegSolRepPassword() {
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_REGISTRAR");
        JTextField repetirPassword = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_CONFIRM_PASSWORD");
        
        
        TestUtils.clickComponent(repetirPassword, robot);
        TestUtils.tipeaTexto("password", robot);

        Assert.assertFalse("El boton de registro deberia estar deshabilitado",registrarButton.isEnabled());
    }
    @Test
    public void testRegSolNombreReal() {
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_REGISTRAR");
        JTextField nombreReal = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_REAL_NAME");
        
        
        TestUtils.clickComponent(nombreReal, robot);
        TestUtils.tipeaTexto("nombre", robot);

        Assert.assertFalse("El boton de registro deberia estar deshabilitado",registrarButton.isEnabled());}
    @Test
    public void testRgExitoso() {
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_REGISTRAR");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_USSER_NAME");
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_PASSWORD");
        JTextField repetirPassword = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_CONFIRM_PASSWORD");
        JTextField nombreReal = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_REAL_NAME");
        
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(repetirPassword, robot);
        TestUtils.tipeaTexto("password1", robot); 
        TestUtils.clickComponent(nombreReal, robot);
        TestUtils.tipeaTexto("Nombre Real", robot);

        TestUtils.clickComponent(registrarButton, robot);
        
        robot.delay(TestUtils.getDelay());
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), "LOGIN");
        Assert.assertFalse("Deberíamos regresar al panel de Login después del registro exitoso", loginButton.isEnabled());
        }
    @Test
    public void testRgNomreYaExiste() {
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_REGISTRAR");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_USSER_NAME");
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_PASSWORD");
        JTextField repetirPassword = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_CONFIRM_PASSWORD");
        JTextField nombreReal = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_REAL_NAME");
        
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario2", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(repetirPassword, robot);
        TestUtils.tipeaTexto("password1", robot); 
        TestUtils.clickComponent(nombreReal, robot);
        TestUtils.tipeaTexto("Nombre Real", robot);
        
        TestUtils.clickComponent(registrarButton, robot);
        
        robot.delay(TestUtils.getDelay());
        

        
        JButton register= (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), "REGISTRAR");
        TestUtils.clickComponent(register, robot);  
        robot.delay(TestUtils.getDelay());
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario2", robot); 
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(repetirPassword, robot);
        TestUtils.tipeaTexto("password1", robot); 
        TestUtils.clickComponent(nombreReal, robot);
        TestUtils.tipeaTexto("Nombre Real", robot);

        TestUtils.clickComponent(registrarButton, robot);
        robot.delay(TestUtils.getDelay());
        Assert.assertEquals("Deberia decir: "+Mensajes.USUARIO_REPETIDO.getValor(),Mensajes.USUARIO_REPETIDO.getValor(), op.getMensaje());
        }
    @Test
    public void testContrasenaNoCoincide() {
    	JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_REGISTRAR");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_USSER_NAME");
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_PASSWORD");
        JTextField repetirPassword = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_CONFIRM_PASSWORD");
        JTextField nombreReal = (JTextField) TestUtils.getComponentForName((Component) vista, "REG_REAL_NAME");
        
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(repetirPassword, robot);
        TestUtils.tipeaTexto("password2", robot); 
        TestUtils.clickComponent(nombreReal, robot);
        TestUtils.tipeaTexto("Nombre Real", robot);
        
        TestUtils.clickComponent(registrarButton, robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertEquals("El mensaje deberia decir: "+Mensajes.PASS_NO_COINCIDE.getValor(),Mensajes.PASS_NO_COINCIDE.getValor(),op.getMensaje());
    	
    }
    @Test
    public void testCancelarFunciona() {
    	JButton cancelarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REG_BUTTON_CANCELAR");
    	TestUtils.clickComponent(cancelarButton, robot);
    	
    	robot.delay(TestUtils.getDelay());
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), "LOGIN");
        Assert.assertFalse("Deberíamos regresar al panel de Login después de cancelar", loginButton.isEnabled());
    	
    }
}
