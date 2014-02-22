package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Usuario;

@Component("usuarioDAO")
public class HibernateUsuarioDAO implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Usuario buscaUsuarioPorId(Integer usuarioId) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		//Inicia transaccion
		Usuario usuario=(Usuario) session.get(Usuario.class, usuarioId);
		//Realiza los cambios
		session.getTransaction().commit();
		//Cerrar transaccion
		session.close();
		return usuario;
	}

	public void creaUsuario(Usuario usuario) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		//Inicia transaccion
		session.saveOrUpdate(usuario);
		//Realiza los cambios
		session.getTransaction().commit();
		//Cerrar transaccion
		session.close();
	}

	public Usuario buscaPorEmailYPassword(String email, String password) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria=session.createCriteria(Usuario.class);

		//EJEMPLO DE CRITERIO USANDO OR
		/*criteria.add(Restrictions.or(
				(Restrictions.eq("email", email)),
				(Restrictions.eq("password", password))
		));*/
		
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("password", password));
		
		//Si no obtiene nada regresa null
		Usuario usuario=(Usuario) criteria.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return usuario;
	}

	public List<Usuario> buscaPorNombre(String nombre) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria=session.createCriteria(Usuario.class);
		
		criteria.add(Restrictions.like("nombre", "%"+nombre+"%"));
		
		List <Usuario> usuarios=criteria.list();
		
		session.getTransaction().commit();
		session.close();
		return usuarios;
	}


	
	
}
