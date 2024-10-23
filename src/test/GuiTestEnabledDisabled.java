package test;

import controlador.Controlador;

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

public class GuiTestEnabledDisabled {
    Robot robot;
    Controlador controlador;
    IVista vista = new Ventana();
    
    public GuiTestEnabledDisabled()
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
    }
    @After
    public void tearDown() throws Exception {
        controlador = null;
        robot = null;
    }
    @Test
    public void testVacios()
    {
        //obtengo las referencias a los componentes necesarios
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");
        JButton register = (JButton) TestUtils.getComponentForName((Component) vista, "REGISTRAR");
        //verifico los resultados
        Assert.assertFalse("El boton de login deberia estar deshablitado", loginButton.isEnabled());
        Assert.assertTrue("El boton de registro deberia estar habilitado",register.isEnabled());
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
    public void testLogLleno()
    {
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");
        //lleno los JTextField
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("h", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("kjiykjhih", robot);
        //verifico los resultados
        Assert.assertTrue("El boton de login deberia estar hablitado", loginButton.isEnabled());
    }

}
