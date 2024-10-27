package test;

import controlador.Controlador;
import util.Mensajes;
import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;
import vista.Ventana;
import vista.IVista;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuiTestAdmin {
    Robot robot;
    Controlador controlador;
    IVista vista = new Ventana();
    FalsoOptionPane op= new FalsoOptionPane();  
    public GuiTestAdmin()
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
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
        JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");

        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
        
        robot.delay(TestUtils.getDelay());        
        
    }
    
    @After
    public void tearDown() throws Exception {
        controlador = null;
        robot = null;
    }
    @Test
    public void testNewChoferDisabled()
    {   
    	vista=controlador.getVista();
    	JButton nuevochofer = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_CHOFER");
    	Assert.assertFalse("Nuevo chofer debe estar deshabilitado", nuevochofer.isEnabled());
 
    }
    @Test
    public void testNewChoferEnabled()
    {   
    	vista=controlador.getVista();
    	JButton nuevochofer = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_CHOFER");
    	JRadioButton permanente= (JRadioButton) TestUtils.getComponentForName((Component) vista, "PERMANENTE");
    	JRadioButton temporal= (JRadioButton) TestUtils.getComponentForName((Component) vista, "TEMPORARIO");
    	JTextField dnichofer=(JTextField) TestUtils.getComponentForName((Component) vista, "DNI_CHOFER");
    	JTextField nombrechofer=(JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_CHOFER");
    	JTextField canthijos=(JTextField) TestUtils.getComponentForName((Component) vista, "CH_CANT_HIJOS");
    	JTextField aingreso=(JTextField) TestUtils.getComponentForName((Component) vista, "CH_ANIO");
    	
    	TestUtils.clickComponent(temporal, robot);
    	TestUtils.clickComponent(dnichofer, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(nombrechofer, robot);
    	TestUtils.tipeaTexto("Juan", robot);
    	
    	Assert.assertTrue("Nuevo chofer debe estar habilitado", nuevochofer.isEnabled());
 
    	TestUtils.clickComponent(permanente, robot);    	
    	TestUtils.clickComponent(aingreso, robot);
    	TestUtils.tipeaTexto("2012", robot);
    	TestUtils.clickComponent(canthijos, robot);
    	TestUtils.tipeaTexto("2", robot);

    	
    	Assert.assertTrue("Nuevo chofer debe estar habilitado", nuevochofer.isEnabled());
    }
    
}
