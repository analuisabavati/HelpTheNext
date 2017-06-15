package br.com.helpthenext.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.helpthenext.model.UsuarioModel;
import br.com.helpthenext.repository.entity.UsuarioEntity;
import br.com.helpthenext.uteis.Uteis;
 
public class UsuarioRepository implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){
		try {
			
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");
 
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());
 
			return (UsuarioEntity)query.getSingleResult();
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public UsuarioEntity validaUsuarioCadastrado(String usuario){
		try {
			
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUserCadastro");
 
			query.setParameter("usuario", usuario);
			
			return (UsuarioEntity)query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}