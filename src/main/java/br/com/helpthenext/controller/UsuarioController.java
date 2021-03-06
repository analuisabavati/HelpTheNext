package br.com.helpthenext.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.helpthenext.enums.TipoUsuario;
import br.com.helpthenext.model.UsuarioModel;
import br.com.helpthenext.repository.UsuarioRepository;
import br.com.helpthenext.repository.entity.UsuarioEntity;
import br.com.helpthenext.util.Uteis;
 
@Named(value="usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Inject
	private UsuarioModel usuarioModel;
 
	@Inject
	private UsuarioRepository usuarioRepository;
 
	@Inject
	private UsuarioEntity usuarioEntity;
		 
	public UsuarioModel GetUsuarioSession(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}
 
	public String Logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}
	
	public String EfetuarLogin(){
		if(StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())){
			Uteis.Mensagem("Favor informar o login!");
			return null;
		}
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){
			Uteis.MensagemAtencao("Favor informar a senha!");
			return null;
		}
		else{	
			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);
			if(usuarioEntity != null){

				usuarioModel.setId(usuarioEntity.getId());
				usuarioModel.setTipoUsuario(usuarioEntity.getTipoUsuario());
 
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
 
				if (TipoUsuario.ONG.equals(usuarioModel.getTipoUsuario())) {
					return "sistema/ONG/home?faces-redirect=true";
				} else {
					return "sistema/Voluntario/home?faces-redirect=true";
				}
			}
			else{
				Uteis.Mensagem("N�o foi poss�vel efetuar o login com esse usu�rio e senha!");
				return null;
			}
		}
	}
	
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
 
 
}