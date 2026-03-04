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
	public boolean guardar(Alumno alumno) {
		//si no hay ningún alumno con esa combinación nombre/curso
		//entonces lo añadimos
		if(alumnosRepository.findByNombreAndCurso(alumno.getNombre(), alumno.getCurso())==null) {
			alumnosRepository.save(alumno);
			return true;
		}
		return false;
	}

	@Override
	public List<Alumno> alumnosCurso(String curso) {
		return alumnosRepository.findByCurso(curso);
	}

	@Override
	public List<String> cursos() {
		return alumnosRepository.findAllCursos();
	}

	@Override
	public void eliminar(int idAlumno) {
		alumnosRepository.removeById(idAlumno);
		
	}

}
