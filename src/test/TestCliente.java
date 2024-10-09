package test;

import static org.junit.Assert.*;

import org.junit.Assert;

import modeloDatos.Cliente;

import org.junit.Before;
import org.junit.Test;

public class TestCliente {
private Cliente cliente = null;
	@Before
	public void setUp() throws Exception {
		this.cliente = new Cliente("Aguskpo","Aguskpo123","Agustin Gonzales");
	}

	@Test
	public void testNombreUsuario() {
		Assert.assertEquals("Usuario incorrecto", "Aguskpo", cliente.getNombreUsuario());
	}
    
	@Test
	public void testPass() {
		Assert.assertEquals("Contrasenia incorrecta", "Aguskpo123", cliente.getPass());
	}
	
	@Test
	public void testNombreReal() {
		Assert.assertEquals("Nombre incorrecto", "Agustin Gonzales", cliente.getNombreReal());
	}
}
