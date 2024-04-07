package vdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import vdg.model.domain.EstadoUsuario;
import vdg.model.domain.RolDeUsuario;
import vdg.model.domain.Usuario;

public interface UsuarioRepository extends Repository<Usuario, Integer>{
	
	public List<Usuario> findAll();
	public Usuario save(Usuario usuario);
	public void delete(Usuario usuario);
	public List<Usuario> findByEmail(String email);
	public List<Usuario> findByIdUsuario(int idUsuario);
	@Query(value = "SELECT * FROM Usuario u WHERE u.rolDeUsuario='ADMINISTRATIVO' OR u.rolDeUsuario='SUPERVISOR'", nativeQuery = true)
	public List<Usuario> findEmpleados();
	public List<Usuario> findAllByestadoUsuario(EstadoUsuario estadoUsuario);
	public List<Usuario> findAllByrolDeUsuario(RolDeUsuario rolDeUsuario);
	public List<Usuario> findAllByIdGrupo(Integer idGrupo);
	

}
