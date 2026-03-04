package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.model.Alumno;
import init.repository.AlumnosRepository;

@Service
public class AlumnosServiceImpl implements AlumnosService {

	@Autowired
	AlumnosRepository alumnosRepository;
	
	@Override
	public List<String> getCursos() {
		return alumnosRepository.findAllByCurso();
	}

	@Override
	public List<Alumno> alumnosCurso( String curso) {
		return alumnosRepository.findByCurso(curso);
	}

	@Override
	public boolean nuevoAlumno(Alumno alumno) {
		if (alumnosRepository.findFirstByNombreAndCurso(alumno.getNombre(), alumno.getCurso())==null) {
			alumnosRepository.save(alumno);
			return true;
		}
		return false;
	}

	@Override
	public boolean eliminarAlumno(String nombre, String curso) {
		if (alumnosRepository.findFirstByNombreAndCurso(nombre, curso)!=null) {
			return alumnosRepository.deleteByNombreAndCurso(nombre, curso);
		}
		return false;
	}
	
	@Override
	public Alumno findById(int idAlumno) {
		return alumnosRepository.findByIdAlumno(idAlumno);
	}
	
	@Override
	public void removeById(int idAlumno) {
		alumnosRepository.deleteById(idAlumno);
	}
	
	@Override
	public void update(Alumno alumno) {
		alumnosRepository.save(alumno);
	}
	

}
