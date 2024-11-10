package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GuiTestAdmin.class, GuiTestCliente.class, GuiTestLogin.class, GuiTestPanelRegistro.class,
		TestAuto.class, TestChoferPermanente.class, TestChoferTemporario.class, TestCliente.class, TestCombi.class,
		TestControlador.class, TestEmpresaAgregar.class, TestEmpresaAgregarPedido.class,
		TestEmpresaAgregarPedidoClienteConPedidoPendienteException.class,
		TestEmpresaAgregarPedidoClienteConViajePendienteExceptionTest.class,
		TestEmpresaAgregarPedidoClienteNoExisteException.class,
		TestEmpresaAgregarPedidoSinVehiculoParaPedidoException.class, TestEmpresaCalificacionDeChofer.class,
		TestEmpresaCrearViaje.class, TestEmpresaCrearViajeChoferNoDisponibleException.class,
		TestEmpresaCrearViajeClienteConViajePendienteException.class,
		TestEmpresaCrearViajePedidoInexistenteException.class, TestEmpresaCrearViajeVehiculoNoDisponibleException.class,
		TestEmpresaCrearViajeVehiculoNoValidoException.class, TestEmpresaDTO.class, TestEmpresaGetterYSetters.class,
		TestEmpresaIsAdmin.class, TestEmpresaLogin.class, TestEmpresaLogut.class, TestEmpresaPagarYFinalizarViaje.class,
		TestEmpresaSingleton.class, TestEmpresaValidarPedidoFalse.class, TestEmpresaValidarPedidoTrue.class,
		TestEmpresaVehiculosOrdenadosPorPedido.class, TestMoto.class, TestPedido.class, TestPersistenciaBIN.class,
		TestUtilPersistenciaDTOFromEmpresa.class, TestUtilPersistenciaEmpresaFromDTO.class, TestVehiculo.class,
		TestViaje.class })

public class AllTests {

}
