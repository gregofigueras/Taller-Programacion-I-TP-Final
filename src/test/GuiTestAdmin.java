package test;

import controlador.Controlador;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
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
    @Test
    public void testVehiculosListaContent() {
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
      	//No se borran los datos dsp de registrar un vehiculo
      	
    	TestUtils.clickComponent(patente, robot);
    	TestUtils.tipeaTexto("1", robot);
    	TestUtils.clickComponent(nuevovehiculo, robot);
      	robot.delay(TestUtils.getDelay());

      	JList<Vehiculo> vehiculos=(JList<Vehiculo>) TestUtils.getComponentForName((Component) vista, "LISTA_VEHICULOS_TOTALES");

        Assert.assertNotNull("La lista de clientes debería existir en la vista.", vehiculos);
        

        String[] patEsperadas = { "123451", "12345" };
        

        ListModel<Vehiculo> model = vehiculos.getModel();
        
        Assert.assertEquals("El tamaño de la lista de vehículos no coincide con el esperado.", patEsperadas.length, model.getSize());
        

        for (int i = 0; i < patEsperadas.length; i++) {
            Assert.assertEquals("El elemento en la posición " + i + " no coincide con el esperado.", patEsperadas[i], model.getElementAt(i).getPatente());
        }
      	
    }
    @Test
    public void testSueldosTextField() {
    	//creo un chofer, para que haya sueldos que mostrar
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
    	
    	JTextField sueldos=(JTextField) TestUtils.getComponentForName((Component) vista, "TOTAL_SUELDOS_A_PAGAR");
    	String sueldos_mostrados=sueldos.getText();
    	Assert.assertNotNull("Deberia mostrar el sueldo del chofer registrado", sueldos_mostrados);
    }
    @Test
    public void testViajesHistoricosLista() {
    	JButton nuevovehiculo = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VEHICULO");
    	JRadioButton auto=(JRadioButton) TestUtils.getComponentForName((Component) vista, "AUTO");
    	JTextField patente= (JTextField) TestUtils.getComponentForName((Component) vista, "PATENTE");
    	JTextField plazas= (JTextField) TestUtils.getComponentForName((Component) vista, "CANTIDAD_PLAZAS");
    	JCheckBox mascotaCheckBox = (JCheckBox) TestUtils.getComponentForName((Component) vista, "CHECK_VEHICULO_ACEPTA_MASCOTA"); 
    	JList<Viaje> viajes=(JList<Viaje>) TestUtils.getComponentForName((Component) vista, "LISTA_VIAJES_HISTORICOS");
    	
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
        
        JTextField cant_pas = (JTextField) TestUtils.getComponentForName((Component) vista, "CANT_PAX");
        JTextField km = (JTextField) TestUtils.getComponentForName((Component) vista, "CANT_KM");
        JButton nuevopedido = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_PEDIDO");
        JButton cerrarsesioncliente = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_CLIENTE");
        
        TestUtils.clickComponent(cant_pas, robot);
        TestUtils.tipeaTexto("3", robot);
        TestUtils.clickComponent(km, robot);
        TestUtils.tipeaTexto("14", robot);
        TestUtils.clickComponent(nuevopedido, robot);
        TestUtils.clickComponent(cerrarsesioncliente, robot);
        robot.delay(TestUtils.getDelay());
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
        
    	
    	JList<Viaje> pedidosdisp=(JList<Viaje>) TestUtils.getComponentForName((Component) vista, "LISTA_PEDIDOS_PENDIENTES");
    	JList<Viaje> chofereslib=(JList<Viaje>) TestUtils.getComponentForName((Component) vista, "LISTA_CHOFERES_LIBRES");
    	JList<Viaje> vehiculosdisp=(JList<Viaje>) TestUtils.getComponentForName((Component) vista, "LISTA_VEHICULOS_DISPONIBLES");
 
    	pedidosdisp.setSelectedIndex(0);
    	chofereslib.setSelectedIndex(0);
    	vehiculosdisp.setSelectedIndex(0);
    	TestUtils.clickComponent(pedidosdisp, robot);
    	TestUtils.clickComponent(chofereslib, robot);
    	TestUtils.clickComponent(vehiculosdisp, robot);
        JButton nuevoviaje = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VIAJE");
        TestUtils.clickComponent(nuevoviaje, robot);
    	
      	TestUtils.clickComponent(cerrarsesionadmin, robot);
        robot.delay(TestUtils.getDelay());    
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(loginButton, robot);
        robot.delay(TestUtils.getDelay());
        
        JTextField calificacion=(JTextField) TestUtils.getComponentForName((Component) vista, Constantes.CALIFICACION_DE_VIAJE);
        JButton pagar = (JButton) TestUtils.getComponentForName((Component) vista, "CALIFICAR_PAGAR");
        Assert.assertNotNull("Se deberia poder pagar", pagar);
        Assert.assertNotNull("Se deberia poder calificar", calificacion);
        TestUtils.clickComponent(calificacion, robot);
        TestUtils.tipeaTexto("5", robot);
        TestUtils.clickComponent(pagar, robot);
        TestUtils.clickComponent(cerrarsesioncliente, robot);
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
        
        
        ListModel<Viaje> model = viajes.getModel();
        
        Assert.assertEquals("El tamaño de la lista de viajes no coincide con el esperado.", 1, model.getSize());    
    }
    @Test
    public void testListasInicialmenteVacias() {
        JList<?> listaPedidosPend = (JList<?>) TestUtils.getComponentForName((Component) vista, "LISTA_PEDIDOS_PENDIENTES");
        JList<?> listaChoferesLibres = (JList<?>) TestUtils.getComponentForName((Component) vista, "LISTA_CHOFERES_LIBRES");
        JList<?> listaVehiculosDisp = (JList<?>) TestUtils.getComponentForName((Component) vista, "LISTA_VEHICULOS_DISPONIBLES");
        JList<?> listaChoferesTot = (JList<?>) TestUtils.getComponentForName((Component) vista, "LISTA_CHOFERES_TOTALES");
        JList<?> listaVehiculos = (JList<?>) TestUtils.getComponentForName((Component) vista, "LISTA_VEHICULOS_TOTALES");
        JList<?> listaViajesChofer = (JList<?>) TestUtils.getComponentForName((Component) vista, "LISTA_VIAJES_DE_CHOFER");
        JList<?> listaClientes = (JList<?>) TestUtils.getComponentForName((Component) vista, "LISTADO_DE_CLIENTES");
        JList<?> listaViajesHist = (JList<?>) TestUtils.getComponentForName((Component) vista, "LISTA_VIAJES_HISTORICOS");

        Assert.assertTrue("La lista de pedidos pendientes debería estar vacía al inicio", listaPedidosPend.getModel().getSize() == 0);
        Assert.assertTrue("La lista de Choferes Libres debería estar vacía al inicio", listaChoferesLibres.getModel().getSize() == 0);
        Assert.assertTrue("La lista de vehiculos disponibles debería estar vacía al inicio", listaVehiculosDisp.getModel().getSize() == 0);
        Assert.assertTrue("La lista de choferes totales debería estar vacía al inicio", listaChoferesTot.getModel().getSize() == 0);
        Assert.assertTrue("La lista de vehículos debería estar vacía al inicio", listaVehiculos.getModel().getSize() == 0);
        Assert.assertTrue("La lista de viajes del chofer debería estar vacía al inicio", listaViajesChofer.getModel().getSize() == 0);
        Assert.assertTrue("La lista de Clientes debería estar vacía al inicio", listaClientes.getModel().getSize() == 0);
        Assert.assertTrue("La lista de viajes historicos debería estar vacía al inicio", listaViajesHist.getModel().getSize() == 0);
    }
    @Test
    public void testMuestraStatsChofer() {
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
        
        JTextField cant_pas = (JTextField) TestUtils.getComponentForName((Component) vista, "CANT_PAX");
        JTextField km = (JTextField) TestUtils.getComponentForName((Component) vista, "CANT_KM");
        JButton nuevopedido = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_PEDIDO");
        JButton cerrarsesioncliente = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_CLIENTE");
        
        TestUtils.clickComponent(cant_pas, robot);
        TestUtils.tipeaTexto("3", robot);
        TestUtils.clickComponent(km, robot);
        TestUtils.tipeaTexto("14", robot);
        TestUtils.clickComponent(nuevopedido, robot);
        TestUtils.clickComponent(cerrarsesioncliente, robot);
        robot.delay(TestUtils.getDelay());
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
        
    	
    	JList<Viaje> pedidosdisp=(JList<Viaje>) TestUtils.getComponentForName((Component) vista, "LISTA_PEDIDOS_PENDIENTES");
    	JList<Chofer> chofereslib=(JList<Chofer>) TestUtils.getComponentForName((Component) vista, "LISTA_CHOFERES_LIBRES");
    	JList<Vehiculo> vehiculosdisp=(JList<Vehiculo>) TestUtils.getComponentForName((Component) vista, "LISTA_VEHICULOS_DISPONIBLES");
 
    	pedidosdisp.setSelectedIndex(0);
    	chofereslib.setSelectedIndex(0);
    	vehiculosdisp.setSelectedIndex(0);
    	TestUtils.clickComponent(pedidosdisp, robot);
    	TestUtils.clickComponent(chofereslib, robot);
    	TestUtils.clickComponent(vehiculosdisp, robot);
        JButton nuevoviaje = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VIAJE");
        TestUtils.clickComponent(nuevoviaje, robot);
    	
      	TestUtils.clickComponent(cerrarsesionadmin, robot);
        robot.delay(TestUtils.getDelay());    
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("nuevoUsuario", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("password1", robot);
        TestUtils.clickComponent(loginButton, robot);
        robot.delay(TestUtils.getDelay());
        
        JTextField calificacion=(JTextField) TestUtils.getComponentForName((Component) vista, Constantes.CALIFICACION_DE_VIAJE);
        JButton pagar = (JButton) TestUtils.getComponentForName((Component) vista, "CALIFICAR_PAGAR");
        Assert.assertNotNull("Se deberia poder pagar", pagar);
        Assert.assertNotNull("Se deberia poder calificar", calificacion);
        TestUtils.clickComponent(calificacion, robot);
        TestUtils.tipeaTexto("5", robot);
        TestUtils.clickComponent(pagar, robot);
        TestUtils.clickComponent(cerrarsesioncliente, robot);
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);	
        
        JList<Viaje> choferestot=(JList<Viaje>) TestUtils.getComponentForName((Component) vista, "LISTA_CHOFERES_TOTALES");
        JList<Viaje> viajeschof=(JList<Viaje>) TestUtils.getComponentForName((Component) vista, "LISTA_VIAJES_DE_CHOFER");
        JTextField calificacionchof=(JTextField) TestUtils.getComponentForName((Component) vista, "CALIFICACION_CHOFER");
        JTextField sueldochof=(JTextField) TestUtils.getComponentForName((Component) vista, "SUELDO_DE_CHOFER");
        choferestot.setSelectedIndex(0);
    	TestUtils.clickComponent(choferestot, robot);
    	Assert.assertEquals("El tamaño de la lista de viajes no coincide con el esperado", 1, viajeschof.getModel().getSize());
    	Assert.assertNotNull("La calificacion deberia mostrarse", calificacionchof);
    	Assert.assertNotNull("El sueldo deberia mostrarse", sueldochof);
    	
    }
    @Test
    public void testPedidos() {
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
        
        JTextField cant_pas = (JTextField) TestUtils.getComponentForName((Component) vista, "CANT_PAX");
        JTextField km = (JTextField) TestUtils.getComponentForName((Component) vista, "CANT_KM");
        JButton nuevopedido = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_PEDIDO");
        JButton cerrarsesioncliente = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_CLIENTE");
        
        TestUtils.clickComponent(cant_pas, robot);
        TestUtils.tipeaTexto("3", robot);
        TestUtils.clickComponent(km, robot);
        TestUtils.tipeaTexto("14", robot);
        TestUtils.clickComponent(nuevopedido, robot);
        TestUtils.clickComponent(cerrarsesioncliente, robot);
        robot.delay(TestUtils.getDelay());
        
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(loginButton, robot);
        
    	
    	JList<Viaje> pedidosdisp=(JList<Viaje>) TestUtils.getComponentForName((Component) vista, "LISTA_PEDIDOS_PENDIENTES");
    	Assert.assertEquals("El tamaño de la lista de viajes no coincide con el esperado", 1, pedidosdisp.getModel().getSize());
    	JList<Chofer> chofereslib=(JList<Chofer>) TestUtils.getComponentForName((Component) vista, "LISTA_CHOFERES_LIBRES");
    	String dni = "12345";
        boolean encontrado = false;
        ListModel<Chofer> modelo = chofereslib.getModel();
        
    
        for (int i = 0; i < modelo.getSize(); i++) {
            Chofer chofer = modelo.getElementAt(i);
            if (chofer.getDni().equals(dni)) {
                encontrado = true;
                break;
            }
    	
        }
        Assert.assertTrue("El chofer deberia estar enlistado", encontrado);
    	JList<Vehiculo> vehiculosdisp=(JList<Vehiculo>) TestUtils.getComponentForName((Component) vista, "LISTA_VEHICULOS_DISPONIBLES");
    	Assert.assertEquals("La lista de vehiculos deberia estar vacia", 0, vehiculosdisp.getModel().getSize());
        JButton nuevoviaje = (JButton) TestUtils.getComponentForName((Component) vista, "NUEVO_VIAJE");  
        Assert.assertFalse("El boton nuevo viaje deberia estar deshabilitado", nuevoviaje.isEnabled());
    	pedidosdisp.setSelectedIndex(0);
    	chofereslib.setSelectedIndex(0);
    	vehiculosdisp.setSelectedIndex(0);
    	TestUtils.clickComponent(pedidosdisp, robot);
    	Assert.assertEquals("La lista de vehiculos no deberia estar vacia", 1, vehiculosdisp.getModel().getSize());
    	TestUtils.clickComponent(chofereslib, robot);
    	TestUtils.clickComponent(vehiculosdisp, robot);
        Assert.assertTrue("El boton nuevo viaje deberia estar habilitado", nuevoviaje.isEnabled());
        TestUtils.clickComponent(nuevoviaje, robot);

    	Assert.assertEquals("La lista de pedidos deberia estar vacia", 0, pedidosdisp.getModel().getSize());
    	Assert.assertEquals("La lista de choferes deberia estar vacia", 0, chofereslib.getModel().getSize());
    	
        
    	
    }
}
