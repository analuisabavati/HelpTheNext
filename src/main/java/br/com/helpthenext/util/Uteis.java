package br.com.helpthenext.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
 
public class Uteis {
 
	public static EntityManager JpaEntityManager(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request  = (HttpServletRequest)externalContext.getRequest();
		return (EntityManager)request.getAttribute("entityManager");
	}
 
	public static void Mensagem(String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Alerta",mensagem));
	}
 
	public static void MensagemAtencao(String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten��o:", mensagem));
	}
 
	public static void MensagemInfo(String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}
 
	public static Date asDate(LocalDateTime localDateTime) {
		if (localDateTime != null) {
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}
	
	
}