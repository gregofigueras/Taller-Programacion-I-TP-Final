package test;

import controlador.Controlador;
import modeloDatos.Vehiculo;
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
    	JButton nuevochofer = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_CHOFER");
    	Assert.assertFalse("Nuevo chofer debe estar deshabilitado", nuevochofer.isEnabled());
 
    }
    // @Test
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
    	TestUtils.clickComponent(canthijos, robot);
    	TestUtils.tipeaTexto("2", robot);
    	TestUtils.clickComponent(aingreso, robot);
    	TestUtils.tipeaTexto("2012", robot);
    	
    	Assert.assertTrue("Nuevo chofer debe estar habilitado", nuevochofer.isEnabled());
    }
    @Test
    public void testNewVehiculoDisabled() {
    	JButton nuevovehiculo = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VEHICULO");
    	Assert.assertFalse("Nuevo chofer debe estar deshabilitado", nuevovehiculo.isEnabled());
    }
    @Test
    public void testNewVehiculoEnabledMoto() {
    	JButton nuevovehiculo = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VEHICULO");
    	JRadioButton moto=(JRadioButton) TestUtils.getComponentForName((Component) vista, "MOTO");
    	JTextField patente= (JTextField) TestUtils.getComponentForName((Component) vista, "PATENTE");
    	
    	TestUtils.clickComponent(moto, robot);
    	TestUtils.clickComponent(patente, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	Assert.assertTrue("Nuevo chofer debe estar habilitado para moto", nuevovehiculo.isEnabled());
    	
    }
    @Test
    public void testNewVehiculoEnabledAuto() {
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
    	 robot.delay(TestUtils.getDelay()); 
    	Assert.assertTrue("Nuevo chofer debe estar habilitado para auto", nuevovehiculo.isEnabled());
    }
    @Test
    public void testNewVehiculoEnabledCombi() {
    	JButton nuevovehiculo = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VEHICULO");
    	JRadioButton combi=(JRadioButton) TestUtils.getComponentForName((Component) vista, "COMBI");
    	JTextField patente= (JTextField) TestUtils.getComponentForName((Component) vista, "PATENTE");
    	JTextField plazas= (JTextField) TestUtils.getComponentForName((Component) vista, "CANTIDAD_PLAZAS");
    	JCheckBox mascotaCheckBox = (JCheckBox) TestUtils.getComponentForName((Component) vista, "CHECK_VEHICULO_ACEPTA_MASCOTA"); 

    	TestUtils.clickComponent(combi, robot);
    	TestUtils.clickComponent(mascotaCheckBox, robot);
    	TestUtils.clickComponent(patente, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(plazas, robot);
    	TestUtils.tipeaTexto("5", robot);
    	 robot.delay(TestUtils.getDelay()); 
    	Assert.assertTrue("Nuevo chofer debe estar habilitado para combi", nuevovehiculo.isEnabled());
    	
    }
    @Test
    public void testRegNewVehiculo() {
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
      	
      	JList<Vehiculo> vehiculos=(JList<Vehiculo>) TestUtils.getComponentForName((Component) vista, "LISTA_VEHICULOS_TOTALES");

        String pat = "12345";
        boolean encontrado = false;
        ListModel<Vehiculo> modelo = vehiculos.getModel();
        
    
        for (int i = 0; i < modelo.getSize(); i++) {
            Vehiculo vehiculo = modelo.getElementAt(i);
            if (vehiculo.getPatente().equals(pat)) {
                encontrado = true;
                break;
            }
        
        }
        Assert.assertTrue("El vehiculo deberia estar enlistado", encontrado);
    }
    @Test
    public void testVehiculoExistente() {
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
      	
      	TestUtils.clickComponent(auto, robot);
    	TestUtils.clickComponent(mascotaCheckBox, robot);
    	TestUtils.clickComponent(patente, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(plazas, robot);
    	TestUtils.tipeaTexto("4", robot);
    	TestUtils.clickComponent(nuevovehiculo, robot);
      	robot.delay(TestUtils.getDelay());
        
      	Assert.assertEquals("Deberia decir: "+Mensajes.VEHICULO_YA_REGISTRADO.getValor(),Mensajes.VEHICULO_YA_REGISTRADO.getValor(),op.getMensaje());
       
    }
}
