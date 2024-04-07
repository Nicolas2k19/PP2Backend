package vdg.model.logica;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import vdg.model.domain.Grupo;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.RolDeUsuario;
import vdg.model.domain.Usuario;
import vdg.repository.GrupoRepository;
import vdg.repository.UsuarioRepository;
import vdg.model.domain.EstadoUsuario;


@Component
public class SelectorAdministrativo {
	
	
	
	
	public RestriccionPerimetral seleccionarAdminstrativo(RestriccionPerimetral res ,GrupoRepository repoGrupo, UsuarioRepository repoUsuario) throws Exception{
		ArrayList<Usuario> usuariosDelGrupo = (ArrayList<Usuario>) repoUsuario.findAllByIdGrupo(res.getIdGrupo());
		
		if(usuariosDelGrupo.size()==0) throw new Exception("Este grupo no tiene administrativos asignados");
		
		for(Usuario usuario : usuariosDelGrupo  ){
			if(usuario.getEstadoUsuario()==EstadoUsuario.CONECTADO && usuario.getRolDeUsuario() == RolDeUsuario.ADMINISTRATIVO) {
				res.setIdUsuario(usuario.getIdUsuario());
				return res;
			}	
		}
		
		for(Usuario usuario : usuariosDelGrupo  ){
			if(usuario.getEstadoUsuario()==EstadoUsuario.AUSENTE && usuario.getRolDeUsuario() == RolDeUsuario.ADMINISTRATIVO) {
				res.setIdUsuario(usuario.getIdUsuario());
				return res;
			}	
		}
		
		throw new Exception("Este grupo no tiene administrativos disponibles");
		
	}
	
	

}
