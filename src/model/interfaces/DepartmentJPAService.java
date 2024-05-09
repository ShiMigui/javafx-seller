package model.interfaces;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;
import model.services.JPAServiceInterface;

public class DepartmentJPAService implements JPAServiceInterface<Department> {

	@Override
	public List<Department> findAll() {
		List<Department> deps = new ArrayList<>();
		
		deps.add(new Department(1, "Livros"));
        deps.add(new Department(2, "Computadores"));
        deps.add(new Department(3, "Videogames"));
        deps.add(new Department(4, "Eletrônicos"));
        deps.add(new Department(5, "Roupas"));
        deps.add(new Department(6, "Alimentos"));
        deps.add(new Department(7, "Decoração"));
        deps.add(new Department(8, "Brinquedos"));
        deps.add(new Department(9, "Esportes"));
        deps.add(new Department(10, "Ferramentas"));
        deps.add(new Department(11, "Saúde e Beleza"));
        deps.add(new Department(12, "Automotivo"));
        deps.add(new Department(13, "Móveis"));
        deps.add(new Department(14, "Jardinagem"));
        deps.add(new Department(15, "Instrumentos Musicais"));
		
		return deps;
	}

}
