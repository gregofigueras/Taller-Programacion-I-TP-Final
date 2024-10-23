package test;

import controlador.Controlador;
import vista.Ventana;
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

public class GuiTestPanelCliente {
    Robot robot;
    Controlador controlador;
    IVista vista = new Ventana();
    
    public GuiTestPanelCliente() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {
        controlador = new Controlador();
    }

    @After
    public void tearDown() throws Exception {
        controlador = null;
        robot = null;
    }

    @Test
    public void testAbrirPanelCliente() {
        // Simular el clic en el botón "Registrar" para abrir el "Panel Cliente"
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REGISTRAR");
        Assert.assertNotNull("El botón de registro debería estar disponible", registrarButton);
        TestUtils.clickComponent(registrarButton, robot);

        // Esperar que el panel se cargue
        robot.delay(TestUtils.getDelay());

        // Verificar que el panel de cliente se abrió
        JPanel panelCliente = (JPanel) TestUtils.getComponentForName((Component) vista, "PANEL_CLIENTE");
        Assert.assertNotNull("El panel cliente debería estar visible", panelCliente);

        // Verificar la presencia de los componentes en el panel de cliente
        JTextField pedidoActual = (JTextField) TestUtils.getComponentForName(panelCliente, "PEDIDO_O_VIAJE_ACTUAL");
        Assert.assertNotNull("El campo de Pedido o Viaje Actual debería estar presente", pedidoActual);

        JTextField cantPax = (JTextField) TestUtils.getComponentForName(panelCliente, "CANT_PAX");
        Assert.assertNotNull("El campo de Cantidad de Pasajeros debería estar presente", cantPax);

        JTextField cantKm = (JTextField) TestUtils.getComponentForName(panelCliente, "CANT_KM");
        Assert.assertNotNull("El campo de Cantidad de Kilómetros debería estar presente", cantKm);

        JButton cerrarSesionButton = (JButton) TestUtils.getComponentForName(panelCliente, "CERRAR_SESION_CLIENTE");
        Assert.assertNotNull("El botón de Cerrar Sesión debería estar presente", cerrarSesionButton);
    }

    @Test
    public void testInteraccionPanelCliente() {
        // Simular el clic en el botón "Registrar" para abrir el "Panel Cliente"
        JButton registrarButton = (JButton) TestUtils.getComponentForName((Component) vista, "REGISTRAR");
        Assert.assertNotNull("El botón de registro debería estar disponible", registrarButton);
        TestUtils.clickComponent(registrarButton, robot);

        // Esperar que el panel se cargue
        robot.delay(TestUtils.getDelay());

        // Interactuar con los componentes del "Panel Cliente"
        JTextField cantPax = (JTextField) TestUtils.getComponentForName((Component) vista, "CANT_PAX");
        Assert.assertNotNull("El campo de Cantidad de Pasajeros debería estar presente", cantPax);
        TestUtils.clickComponent(cantPax, robot);
        TestUtils.tipeaTexto("3", robot); // Simula la entrada de texto '3'

        JTextField cantKm = (JTextField) TestUtils.getComponentForName((Component) vista, "CANT_KM");
        Assert.assertNotNull("El campo de Cantidad de Kilómetros debería estar presente", cantKm);
        TestUtils.clickComponent(cantKm, robot);
        TestUtils.tipeaTexto("20", robot); // Simula la entrada de texto '20'

        // Verificar si los valores fueron correctamente ingresados
        Assert.assertEquals("El campo de Cantidad de Pasajeros debería contener '3'", "3", cantPax.getText());
        Assert.assertEquals("El campo de Cantidad de Kilómetros debería contener '20'", "20", cantKm.getText());

        // Simular clic en el botón de cerrar sesión
        JButton cerrarSesionButton = (JButton) TestUtils.getComponentForName((Component) vista, "CERRAR_SESION_CLIENTE");
        Assert.assertNotNull("El botón de Cerrar Sesión debería estar presente", cerrarSesionButton);
        TestUtils.clickComponent(cerrarSesionButton, robot);
    }
}
