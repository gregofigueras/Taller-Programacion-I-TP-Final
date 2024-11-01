package test;

import controlador.Controlador;
import modeloDatos.Vehiculo;
import modeloDatos.Chofer;
import modeloDatos.Cliente;
import util.Constantes;
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

    @Test
    public void testNewChoferEnabledTemp()
    {   
    	JButton nuevochofer = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_CHOFER");
    	JRadioButton temporal= (JRadioButton) TestUtils.getComponentForName((Component) vista, "TEMPORARIO");
    	JTextField dnichofer=(JTextField) TestUtils.getComponentForName((Component) vista, "DNI_CHOFER");
    	JTextField nombrechofer=(JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_CHOFER");
    	
    	TestUtils.clickComponent(temporal, robot);
    	TestUtils.clickComponent(dnichofer, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(nombrechofer, robot);
    	TestUtils.tipeaTexto("Juan", robot);

    	Assert.assertTrue("Nuevo chofer debe estar habilitado", nuevochofer.isEnabled());

    }
    @Test
    public void testNewChoferEnabledPerm()
    {   
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
    	
    	 robot.delay(TestUtils.getDelay());
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
    public void testNewAuto() {
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
      	//ERROR ENCONTRADO, NO SE BORRAN LOS JTEXFIELDS AL REGISTRAR UN VEHICULO

    	TestUtils.clickComponent(nuevovehiculo, robot);
      	robot.delay(TestUtils.getDelay());
        
      	Assert.assertEquals("Deberia decir: "+Mensajes.VEHICULO_YA_REGISTRADO.getValor(),Mensajes.VEHICULO_YA_REGISTRADO.getValor(),op.getMensaje());
       
    }
    public void testNewMoto() {
    	JButton nuevovehiculo = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VEHICULO");
    	JRadioButton moto=(JRadioButton) TestUtils.getComponentForName((Component) vista, "MOTO");
    	JTextField patente= (JTextField) TestUtils.getComponentForName((Component) vista, "PATENTE");
    	
    	TestUtils.clickComponent(moto, robot);
    	TestUtils.clickComponent(patente, robot);
    	TestUtils.tipeaTexto("11111", robot);
    	TestUtils.clickComponent(nuevovehiculo, robot);
    	robot.delay(TestUtils.getDelay());
    	
      	JList<Vehiculo> vehiculos=(JList<Vehiculo>) TestUtils.getComponentForName((Component) vista, "LISTA_VEHICULOS_TOTALES");

        String pat = "11111";
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
    public void testNewCombi() {
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
    public void testNewChoferTemp() {
    	JButton nuevochofer = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_CHOFER");
    	JRadioButton temporal= (JRadioButton) TestUtils.getComponentForName((Component) vista, "TEMPORARIO");
    	JTextField dnichofer=(JTextField) TestUtils.getComponentForName((Component) vista, "DNI_CHOFER");
    	JTextField nombrechofer=(JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_CHOFER");
    	
    	TestUtils.clickComponent(temporal, robot);
    	TestUtils.clickComponent(dnichofer, robot);
    	TestUtils.tipeaTexto("12345", robot);
    	TestUtils.clickComponent(nombrechofer, robot);
    	TestUtils.tipeaTexto("Juan", robot);
    	TestUtils.clickComponent(nuevochofer, robot);
    	robot.delay(TestUtils.getDelay());
    	
    	JList<Chofer> choferes=(JList<Chofer>) TestUtils.getComponentForName((Component) vista, "LISTA_CHOFERES_LIBRES");

        String dni = "12345";
        boolean encontrado = false;
        ListModel<Chofer> modelo = choferes.getModel();
        
    
        for (int i = 0; i < modelo.getSize(); i++) {
            Chofer chofer = modelo.getElementAt(i);
            if (chofer.getDni().equals(dni)) {
                encontrado = true;
                break;
            }
    	
        }
        Assert.assertTrue("El chofer deberia estar enlistado", encontrado);
    }
    @Test
    public void testNewChoferPerma() {
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
    	
    	JList<Chofer> choferes=(JList<Chofer>) TestUtils.getComponentForName((Component) vista, "LISTA_CHOFERES_TOTALES");

        String dni = "12345";
        boolean encontrado = false;
        ListModel<Chofer> modelo = choferes.getModel();
        
    
        for (int i = 0; i < modelo.getSize(); i++) {
            Chofer chofer = modelo.getElementAt(i);
            if (chofer.getDni().equals(dni)) {
                encontrado = true;
                break;
            }
    	
        }
        Assert.assertTrue("El chofer deberia estar enlistado", encontrado);
    }
    @Test
    public void testChoferYaReg() {
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
      	//ERROR ENCONTRADO, NO SE BORRAN LOS JTEXFIELDS AL REGISTRAR UN CHOFER

    	TestUtils.clickComponent(nuevochofer, robot);
    	robot.delay(TestUtils.getDelay());
    	
    	Assert.assertEquals("Deberia decir: "+Mensajes.CHOFER_YA_REGISTRADO.getValor(),Mensajes.CHOFER_YA_REGISTRADO.getValor(),op.getMensaje());
    }
    @Test
    public void testClientesListaContent() {
    	JButton cerrarsesion = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_ADMIN");
    	TestUtils.clickComponent(cerrarsesion, robot);
     	robot.delay(TestUtils.getDelay());
        vista = controlador.getVista();
        JButton registrar = (JButton) TestUtils.getComponentForName((Component) vista, "REGISTRAR");
        TestUtils.clickComponent(registrar, robot);
     	robot.delay(TestUtils.getDelay());
        vista.setOptionPane(op);
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
        vista.setOptionPane(op);
        TestUtils.clickComponent(registrar, robot);
        vista.setOptionPane(op);
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario2", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(repetirPassword, robot);
        TestUtils.tipeaTexto("password1", robot); 
        TestUtils.clickComponent(nombreReal, robot);
        TestUtils.tipeaTexto("Nombre Real2", robot);
        TestUtils.clickComponent(registrarButton, robot);
        
     	robot.delay(TestUtils.getDelay());
        vista.setOptionPane(op);
        JTextField contra = (JTextField) TestUtils.getComponentForName((Component) vista, "PASSWORD");
        JTextField usuario = (JTextField) TestUtils.getComponentForName((Component) vista, "NOMBRE_USUARIO");
        JButton loginButton = (JButton) TestUtils.getComponentForName((Component) vista, "LOGIN");

        TestUtils.clickComponent(usuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(contra, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
        
        robot.delay(TestUtils.getDelay());  
        vista.setOptionPane(op);
        
        JList<Cliente> clientesLista = (JList<Cliente>) TestUtils.getComponentForName((Component) vista, "LISTADO_DE_CLIENTES");

        Assert.assertNotNull("La lista de clientes debería existir en la vista.", clientesLista);
        

        String[] clientesEsperados = { "nuevoUsuario", "nuevoUsuario2" };
        

        ListModel<Cliente> model = clientesLista.getModel();
        
        Assert.assertEquals("El tamaño de la lista de vehículos no coincide con el esperado.", clientesEsperados.length, model.getSize());
        

        for (int i = 0; i < clientesEsperados.length; i++) {
            Assert.assertEquals("El elemento en la posición " + i + " no coincide con el esperado.", clientesEsperados[i], model.getElementAt(i).getNombreUsuario());
        }
    }

}
