package br.com.helpthenext.view;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.helpthenext.repository.VagaRepository;
import br.com.helpthenext.repository.entity.VagaEntity;

@Named(value = "vagaImagem")
@SessionScoped
public class VagaImagem {

	@Inject
	VagaRepository vagaRepository;

	public StreamedContent getImagem() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String vagaId = context.getExternalContext().getRequestParameterMap().get("vagaId");

			if (vagaId != null) {
				VagaEntity vaga = vagaRepository.findVagaById(new Long(vagaId));

				if (vaga != null && vaga.getBanner() != null && vaga.getBanner().length != 0) {
					return new DefaultStreamedContent(new ByteArrayInputStream(vaga.getBanner()));
				} 
			}
		}

		return null;
	}

	public byte[] getImagemDefault() throws IOException {
		File imgPath = new File("/HelpTheNext/src/main/resources/default.png");
		BufferedImage bufferedImage = ImageIO.read(imgPath);

		WritableRaster raster = bufferedImage.getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

		return data.getData();
	}
}
