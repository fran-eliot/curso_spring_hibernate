package init.service;

import java.util.List;

import init.dtos.CursoDto;
import init.dtos.MatriculaInfoDto;

public interface EscuelaService {
	
	List<CursoDto> getCursos();
	
	List<MatriculaInfoDto> getMatriculasByCurso(int codCurso);
	
	boolean matricular(int codCurso, String dni);

}
