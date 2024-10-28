package test;

import controlador.Controlador;
import modeloDatos.Cliente;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Mensajes;
import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;
import vista.Ventana;
import vista.IVista;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;

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
        vista.setOptionPane(op);
        
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
    	JButton nuevochofer = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_CHOFER");
    	Assert.assertFalse("Nuevo chofer debe estar deshabilitado", nuevochofer.isEnabled());
    	
    }
 
}
