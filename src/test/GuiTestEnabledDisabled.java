package test;

import controlador.Controlador;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuiTestEnabledDisabled {
    Robot robot;
    Controlador controlador;
    IVista vista=new Ventana(); 
    
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

    @Test 
    public void testLogSoloNombre() {
    	robot.delay(TestUtils.getDelay());
    	JTextField nombre = (JTextField) TestUtils.getComponentForName(controlador.getVentana(), "jtNombreRegistro");
    }
    
}
