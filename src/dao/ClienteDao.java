package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;

public class ClienteDao {
	private static Session session;
	private Transaction tx;
	private static ClienteDao instancia=null;
	
	protected ClienteDao() {}
	
	public static ClienteDao getInstance() {
		if(instancia==null) {
			instancia=new ClienteDao();
		}
		return instancia;
	}
	
	protected void iniciaOperacion() throws HibernateException { 
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	protected void manejaExcepcion(HibernateException he) throws HibernateException { 
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	
	
	public int agregar (Cliente objeto) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		}catch(HibernateException he) {
			throw he;
		}finally {
			session.close();
		}
		return id;
	}
	
	public void actualizar(Cliente objeto) throws HibernateException{
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		}catch(HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
	}
	
	public void eliminar(Cliente objeto) throws HibernateException{
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		}catch(HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
	}
	
	public Cliente traer(long idCliente) throws HibernateException{
		Cliente objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cliente) session.get(Cliente.class, idCliente);
		}finally {
			session.close();
		}
		return objeto;
	}
	
	public Cliente traer(int dni) throws HibernateException{
		Cliente objeto = null;
		try {
			iniciaOperacion();
			String hql = "from Cliente c where c.dni=" + dni;
			objeto = (Cliente) session.createQuery(hql).uniqueResult();
		}finally {
			session.close();
		}
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> traer() throws HibernateException{
		List<Cliente> lista = null;
		try {
			iniciaOperacion();
			String hql = "from Cliente c";
			lista = session.createQuery(hql).list();
		}finally {
			session.close();
		}
		return lista;
	}


}
