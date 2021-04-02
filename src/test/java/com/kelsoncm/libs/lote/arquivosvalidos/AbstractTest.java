package br.jus.tse.sepel2.arquitetura.core.arquivo.spce.arquivosvalidos;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.kelsoncm.libs.lote.LoteDocumento;
import com.kelsoncm.libs.lote.LoteDocumentoCabecalho;
import com.kelsoncm.libs.lote.LoteDocumentoDetalhe;
import com.kelsoncm.libs.lote.LoteDocumentoRodape;
import com.kelsoncm.libs.lote.LoteLeiaute;
import com.kelsoncm.libs.lote.LoteLeiauteFormatadorArquivoExemplo;
import com.kelsoncm.libs.lote.LoteLeiauteFormatadorEscritor;
import com.kelsoncm.libs.lote.LoteProcessador;
import com.kelsoncm.libs.lote.LoteValidadorArquivo;
import com.kelsoncm.libs.lote.LoteValidadorLinha;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteDocumentoDetalhe;
import br.jus.tse.sepel2.arquitetura.api.infra.validador.MensagemValidacao;
import br.jus.tse.sepel2.arquitetura.api.lang.ArquivoLoteException;

public class AbstractTest extends LoteProcessador
		<
		LoteDocumentoCabecalho, 
		LoteDocumentoDetalhe, 
		LoteDocumentoRodape, 
		LoteDocumento
			<
				LoteDocumentoCabecalho, 
				LoteDocumentoDetalhe, 
				LoteDocumentoRodape
			>, 
			LoteLeiaute
			<
				LoteDocumentoCabecalho, 
				LoteDocumentoDetalhe, 
				LoteDocumentoRodape
			>, 
		LoteValidadorArquivo, 
		LoteValidadorLinha> {
	
	public AbstractTest() {
		initLeiaute();
		initDocumento();
		initValidadores();
	}
	
	protected void initLeiaute() {
		LoteLeiaute<LoteDocumentoCabecalho, LoteDocumentoDetalhe, LoteDocumentoRodape> leiaute =
				new LoteLeiaute<LoteDocumentoCabecalho, LoteDocumentoDetalhe, LoteDocumentoRodape>();
		leiaute.setTamanhoLinha(2);
		this.setLeiaute(leiaute);
	}

	protected void initDocumento() {
		this.setNomeClasseDocumento(LoteDocumento.class.getName());
	}

	protected void initValidadores() {
		this.setValidadorArquivo(new LoteValidadorArquivo());
		this.setValidadorLinha(new LoteValidadorLinha());
	}

	@SuppressWarnings("rawtypes") 
	protected void tentarLer(String nomeArquivo) {
		try {
			File arquivo = new File(nomeArquivo);
			LoteDocumento doc = this.ler(arquivo);
			if (!doc.isValido()) {
				System.out.println("Erros:");
				for (MensagemValidacao m : doc.getResultadoValidacao()) {
					System.out.printf("\t%s - %s\n", m.getTipoMensagemValidacao(), m.getMensagem());
				}
			} else {
				System.out.println("Tudo válido.");
				for (Iterator iterator = doc.getDetalhes().iterator(); iterator.hasNext();) {
					LoteDocumentoDetalhe detalhe2 = (LoteDocumentoDetalhe)iterator.next();
					System.out.printf("\t%s - %d\n", detalhe2.getTipoLinha(), detalhe2.getNumeroLinha());
					if (detalhe2.getDetalhes() != null)
						for (ILoteDocumentoDetalhe detalhe3 : detalhe2.getDetalhes()) {
							System.out.printf("\t%s - %d\n", detalhe3.getTipoLinha(), detalhe3.getNumeroLinha());
						}
				}
			}
		} catch (ArquivoLoteException e) {
			e.printStackTrace();
		}
	}

	protected void imprimirArquivoExemplo() throws IOException {
		LoteLeiauteFormatadorArquivoExemplo.imprimir(getLeiaute(), System.out);
	}
	
	protected void imprimirLeiaute() throws IOException {
		LoteLeiauteFormatadorEscritor.imprimir(getLeiaute(), System.out);
	}
}
