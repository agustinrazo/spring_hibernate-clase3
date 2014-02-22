package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.UsuarioDAO;
import org.upiita.spring.jdbc.entidades.Usuario;

public class TestSpringHibernate {

	public static void main(String[] args) {
		//creamos el contexto de Spring
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/contexto.xml");

		//mismo nombre @Component en HibernateUsuarioDAO
		UsuarioDAO usuarioDAO=(UsuarioDAO) contexto.getBean("usuarioDAO");
		
		Usuario usuario=new Usuario();
		usuario.setUsuarioId(3);
		usuario.setNombre("Agustin");
		usuario.setEmail("asd@asd.asd");
		usuario.setPassword("123");
		
		usuarioDAO.creaUsuario(usuario);
		
		usuario.setPassword("1234");
		
		usuarioDAO.creaUsuario(usuario);
		
		System.out.println("guardar datos");
		
		Usuario usuarioBD=usuarioDAO.buscaUsuarioPorId(3);
		System.out.println(usuarioBD);
		
		Usuario usuarioCriterio=usuarioDAO.buscaPorEmailYPassword("asd@asd.asd", "1234");
		System.out.println("Usuario por email y password:"+usuarioCriterio);
		
		List<Usuario> usuarios=usuarioDAO.buscaPorNombre("a");
		System.out.println("Usuarios por nombre:"+usuarios);
	}

}
