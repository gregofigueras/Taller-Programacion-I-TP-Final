package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAuto.class, TestCombi.class, TestChoferPermanente.class, TestChoferTemporario.class, TestCombi.class,TestMoto.class,TestPedido.class, FalsoOptionPane.class, GuiTestAdmin.class, GuiTestCliente.class, GuiTestLogin.class, GuiTestPanelRegistro.class, })
public class AllTests {

}
