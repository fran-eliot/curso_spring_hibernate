package init.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import init.dtos.AlumnoDto;
import init.mappers.Mapeador;
import init.model.Alumno;
import init.repository.AlumnosRepository;

@Service
public class AlumnosServiceImpl implements AlumnosService {

    @Autowired
    AlumnosRepository alumnosRepository;
    
    @Autowired
    Mapeador mapeador;
    
    @Override
    public List<String> getCursos() {
        return alumnosRepository.findAllCursos();
    }
    
    @Override
    public List<AlumnoDto> todosAlumnos() {
        return alumnosRepository.findAll() // Recupera todas las entidades de la DB
                .stream()
                .map(mapeador::alumnoEntityToDto) // Transforma cada Alumno en AlumnoDto
                .toList();
    }

    @Override
    public List<AlumnoDto> alumnosCurso(String curso) {
        return alumnosRepository.findByCurso(curso)
                .stream()
                .map(mapeador::alumnoEntityToDto)
                .toList();
    }

    @Override
    public boolean nuevoAlumno(AlumnoDto alumnoDto) {
        // Comprobamos si ya existe para no duplicar
        if (alumnosRepository.findByNombreAndCurso(alumnoDto.getNombre(), alumnoDto.getCurso()).isEmpty()) {
            Alumno nuevo = mapeador.alumnoDtoToEntity(alumnoDto);
            alumnosRepository.save(nuevo);
            return true;
        }
        return false;
    }

    @Override
    public AlumnoDto eliminarAlumno(String nombre, String curso) {
        Optional<Alumno> alumno = alumnosRepository.findByNombreAndCurso(nombre, curso);
        if (alumno.isPresent()) {
            AlumnoDto dto = mapeador.alumnoEntityToDto(alumno.get());
            alumnosRepository.deleteByNombreAndCurso(nombre, curso);
            return dto;
        }
        return null;
    }
    
    @Override
    public AlumnoDto findById(int idAlumno) {
        // JpaRepository devuelve Optional por defecto
        return alumnosRepository.findById(idAlumno)
                .map(mapeador::alumnoEntityToDto)
                .orElse(null);
    }
    
    @Override
    public void removeById(int idAlumno) {
        if(alumnosRepository.existsById(idAlumno)) {
            alumnosRepository.deleteById(idAlumno);
        }
    }
    
    @Override
    public void update(AlumnoDto alumnoDto) {
        // En JPA, el update se hace con save() si el objeto ya tiene un ID que existe
        // Aquí necesitaríamos buscarlo primero o que el DTO incluya el ID
        Optional<Alumno> existente = alumnosRepository.findByNombreAndCurso(alumnoDto.getNombre(), alumnoDto.getCurso());
        if(existente.isPresent()) {
            Alumno entity = existente.get();
            entity.setNota(alumnoDto.getNota());
            entity.setEmail(alumnoDto.getEmail());
            alumnosRepository.save(entity);
        }
    }

    @Override
    public AlumnoDto eliminarPorEmail(String email) {
        // 1. Buscamos el primer alumno con ese email
        Optional<Alumno> alumnoOpt = alumnosRepository.findFirstByEmail(email);
        
        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();
            
            // 2. Mapeamos a DTO antes de borrarlo para no perder los datos
            AlumnoDto dto = mapeador.alumnoEntityToDto(alumno);
            
            // 3. Borramos por ID (es la forma más segura)
            alumnosRepository.deleteById(alumno.getIdAlumno());
            
            return dto;
        }
        
        return null; 
    }
}
