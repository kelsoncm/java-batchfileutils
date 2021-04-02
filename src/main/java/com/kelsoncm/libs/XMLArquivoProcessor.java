package com.kelsoncm.libs;

import java.io.InputStream;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.io.ArquivoLayout;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.io.xml.XMLReader;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.io.xml.XMLWriter;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.validador.message.MensagemCollection;
import br.jus.tse.sepel2.arquitetura.api.lang.ArquivoException;

/**
 * Processador de arquivos XML
 * 
 * @author edle.silva
 * 
 */
public class XMLArquivoProcessor extends ArquivoProcessadorImpl {

	/**
     * 
     */
	private static final long serialVersionUID = -8342021681275705470L;

	/**
	 * @param nomeArquivoPara
	 * @param xsdArquivoPara
	 * @throws Exception
	 */
	private void escrever(final String nomeArquivoPara, final String xsdArquivoPara) throws ArquivoException {
		final ParamObjectImpl po = new ParamObjectImpl();
		po.setNomeArquivoPara(nomeArquivoPara);

		this.doBeforeWriter(po);

		final XMLWriter arquivoWriter = (XMLWriter) this.getArquivoWriter();
		arquivoWriter.escrever(this.getArquivoLayoutSaida(), po.getNomeArquivoPara(), xsdArquivoPara);
		this.doAfterWriter();
	}

	/**
	 * @param nomeArquivoDe
	 * @param xsdArquivoDe
	 * @throws Exception
	 */
	private void ler(final String nomeArquivoDe, final String xsdArquivoDe) throws ArquivoException {
		final Class<? extends ArquivoLayout> classLayout = this.getArquivoLayoutEntrada().getClass();
		final ParamObjectImpl po = new ParamObjectImpl();
		po.setNomeArquivoDe(nomeArquivoDe);

		this.doBeforeReader(po);
		final XMLReader xmlReader = (XMLReader) this.getArquivoReader();
		this.setArquivoLayoutEntrada(xmlReader.ler(classLayout, nomeArquivoDe, xsdArquivoDe));
		this.doAfterReader();
	}

	/**
	 * @param file
	 * @param xsdArquivoDe
	 * @throws Exception
	 */
	private void ler(final InputStream file, final String xsdArquivoDe) throws ArquivoException {
		final ParamObjectImpl po = new ParamObjectImpl();
		this.doBeforeReader(po);
		final XMLReader xmlReader = (XMLReader) this.getArquivoReader();
		this.setArquivoLayoutEntrada(xmlReader.ler(this.getArquivoLayoutEntrada().getClass(), file, xsdArquivoDe));
		this.doAfterReader();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private MensagemCollection validar() throws ArquivoException {
		this.getValidador().setArquivoLayoutSaida(this.getArquivoLayoutSaida());
		final MensagemCollection mensagemCollection = this.getValidador().validar(this.getArquivoLayoutEntrada());
		this.setArquivoLayoutSaida(getValidador().getArquivoLayoutSaida());
		return mensagemCollection;
	}

	/**
	 * @param nomeArquivoDe
	 * @param xsdArquivoDe
	 * @param nomeArquivoPara
	 * @param xsdArquivoPara
	 * @return
	 * @throws Exception
	 */
	public final MensagemCollection processar(final String nomeArquivoDe, final String xsdArquivoDe,
			final String nomeArquivoPara, final String xsdArquivoPara) throws ArquivoException {
		this.doBefore();

		this.validarPropriedadesObrigatorias();
		ler(nomeArquivoDe, xsdArquivoDe);
		final MensagemCollection mensagemCollection = validar();
		escrever(nomeArquivoPara, xsdArquivoPara);

		this.doAfter();
		return mensagemCollection;

	}

	/**
	 * @param file
	 * @param xsdArquivoDe
	 * @param nomeArquivoPara
	 * @param xsdArquivoPara
	 * @return
	 * @throws Exception
	 */
	public final MensagemCollection processar(final InputStream file, final String xsdArquivoDe,
			final String nomeArquivoPara, final String xsdArquivoPara) throws ArquivoException {
		this.doBefore();

		this.validarPropriedadesObrigatorias();
		try {
			ler(file, xsdArquivoDe);
			final MensagemCollection mensagemCollection = validar();
			escrever(nomeArquivoPara, xsdArquivoPara);

			this.doAfter();
			return mensagemCollection;
		} catch (final ArquivoException e) {
			throw new ArquivoException("Erro ao processar arquivo XML.", e);
		}
	}

}
