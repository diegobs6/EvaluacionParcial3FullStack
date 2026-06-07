package cl.EvaluacionParcial3.MicroService_Usuario.Service;

import cl.EvaluacionParcial3.MicroService_Usuario.Dto.UsuarioRequest;
import cl.EvaluacionParcial3.MicroService_Usuario.Dto.UsuarioResponse;
import cl.EvaluacionParcial3.MicroService_Usuario.Dto.UsuarioUpdateRequest;
import cl.EvaluacionParcial3.MicroService_Usuario.Exception.ConflictException;
import cl.EvaluacionParcial3.MicroService_Usuario.Mapper.UsuarioMapper;
import cl.EvaluacionParcial3.MicroService_Usuario.Model.Usuario;
import cl.EvaluacionParcial3.MicroService_Usuario.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    // Listar todos los usuarios
    public List<Usuario> buscarUsuarios() {
        log.info("Buscando a todos los usuarios...");
        return usuarioRepository.findAll();
    }

    // Buscar Por ID
    public UsuarioResponse buscarPorId(Long id) {
        log.info("Buscando usuario con el ID: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        return usuarioMapper.toResponse(usuario);
    }

    // Buscar por RUN
    public UsuarioResponse buscarPorRun(String run) {
        log.info("Buscando usuario con el RUN: {}", run);
        Usuario usuario = usuarioRepository.findByRun(run)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        return usuarioMapper.toResponse(usuario);
    }

    // Crear USUARIO
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        log.info("Creando usuario con el RUN: {}", request.getRun());
        if (usuarioRepository.existsByRun(request.getRun())) {
            throw new ConflictException("El usuario ya existe");
        }
        Usuario usuario = usuarioRepository.save(usuarioMapper.fromRequest(request));
        return usuarioMapper.toResponse(usuario);
    }

    // Actualixar informacion del USUARIO
    public UsuarioResponse actualizarUsuario(Long id, UsuarioUpdateRequest request) {
        log.info("Actualizando el usuario con el ID: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        if (request.getNombre() != null) {
            usuario.setNombre(request.getNombre());
        }
        if (request.getApellido() != null) {
            usuario.setApellido(request.getApellido());
        }
        if (request.getFechaNac() != null) {
            usuario.setFechaNac(request.getFechaNac());
        }
        if (request.getCorreo() != null) {
            usuario.setCorreo(request.getCorreo());
        }
        if (request.getDireccion() != null) {
            usuario.setDireccion(request.getDireccion());
        }
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuario);
    }

    // Eliminar USUARIO
    public void eliminarUsuario(Long id) {
        log.info("Eliminando el usuario con el ID: {}", id);
        if (!usuarioRepository.existsById(id)) {
            throw new NoSuchElementException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
