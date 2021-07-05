package test;

import java.time.LocalDate;

import datos.Cliente;
import negocio.ClienteABM;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// AGREGAR CLIENTE		
		try {
			long id=0;
			id = ClienteABM.getInstance().agregar("Maria", "Martinez", 45698236, LocalDate.of(2004, 5, 6));
			System.out.println(ClienteABM.getInstance().traer(id) +" Agregado con Exito! ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// ACTUALIZAR
		try {
			long id = 5;
			Cliente c = ClienteABM.getInstance().traer(id);
			c.setDni(40008847);
			ClienteABM.getInstance().modificar(c);
			System.out.println(ClienteABM.getInstance().traer(id)+ "Modificado con Exito!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ELIMINAR
		try {
			long id = 5;
			ClienteABM.getInstance().eliminarLogica(id);
			System.out.println("Cliente " + ClienteABM.getInstance().traer(id) +" Dado de baja con Exito!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TRAER TODOS LOS CLIENTES
		System.out.println(ClienteABM.getInstance().traer());

	}

}
