package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.ClienteDao;
import datos.Cliente;

public class ClienteABM {
	private static ClienteABM instancia = null;
	
	protected ClienteABM () {}
	
	public static ClienteABM getInstance() {
		if(instancia==null) {
			instancia=new ClienteABM();
		}
		return instancia;
	}
	
	public Cliente traer(long idCliente) {
		return ClienteDao.getInstance().traer(idCliente);
	}
	
	public Cliente traer(int dni) {
		return ClienteDao.getInstance().traer(dni);
	}
	
	public int agregar(String nombre, String apellido, int dni, LocalDate fechaDeNacimiento) throws Exception{
		if (traer(dni) != null) throw new Exception("Ya existe el cliente con DNI: " +dni );
		Cliente c = new Cliente(apellido, nombre, dni, fechaDeNacimiento);
		return ClienteDao.getInstance().agregar(c);
	}
	
	public void modificar(Cliente c) throws Exception{
		 //NO FUNCIONA CORRECTAMENTE LA VALIDACION
		if (traer(c.getDni()) != null && traer(c.getIdCliente()) != c) throw new Exception("Ya existe un cliente con DNI: "+c.getDni());
		ClienteDao.getInstance().actualizar(c);
	}
	
	public void eliminarFisica(long idCliente) throws Exception{
		if (traer(idCliente) == null) throw new Exception("El Cliente a Eliminar No Existe");
		ClienteDao.getInstance().eliminar(traer(idCliente));
	}
	
	public void eliminarLogica(long idCliente) throws Exception{
		Cliente c = traer(idCliente);
		if (c == null) throw new Exception("El Cliente a Eliminar No Existe");
		c.setBaja(true);
		ClienteDao.getInstance().actualizar(c);
	}
	
	public List<Cliente> traer(){
		return ClienteDao.getInstance().traer();
	}

}
