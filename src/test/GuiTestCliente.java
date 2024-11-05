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
    	JButton botonRegistrar = (JButton) TestUtils.getComponentForName((Component) vista, "REGISTRAR");
    	Assert.assertTrue("Deberia volver a la pantalla de inicio", botonRegistrar.isEnabled());
    	
    }
    
 
}
