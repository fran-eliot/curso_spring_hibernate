package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.CursoDto;
import init.dtos.MatriculaInfoDto;
import init.mapper.Mapeador;
import init.model.Alumno;
import init.model.Curso;
import init.repositoy.AlumnosRepository;
import init.repositoy.CursosRepository;

@Service
public class EscuelaServiceImpl implements EscuelaService {
	
	@Autowired
	AlumnosRepository alumnosRepository;
	
	@Autowired
	CursosRepository cursosRepository;
	
	@Autowired
	Mapeador mapeador;

	@Override
	public List<CursoDto> getCursos() {
		return cursosRepository.findAll()
				.stream()
				.map(curso -> mapeador.cursoEntityToDto(curso))
				.toList();
	}

	@Override
	public List<MatriculaInfoDto> getMatriculasByCurso(int codCurso) {
		CursoDto curso = cursosRepository.findById(codCurso)
				.map(c->mapeador.cursoEntityToDto(c)).orElse(null);
		
		if (curso==null) {
			return List.of();
		}
		
		List<Alumno> alumnos = alumnosRepository.findByCodigoCurso(codCurso);
		return alumnos.stream().map(a -> new MatriculaInfoDto(mapeador.alumnoEntitiyToDto(a),curso)).toList();
	}

	@Override
	public boolean matricular(int codCurso, String dni) {
		//Matricular un alumno en un curso es salvar un curso con su lista de alumnos en la que se incluye el nuevo alumno
		if (cursosRepository.findById(codCurso).isPresent()&& alumnosRepository.findById(dni).isPresent()) {
			cursosRepository.matricular(dni,codCurso);
			return true;
		}
		return false;
	}

}
