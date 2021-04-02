package com.kelsoncm.libs;

import java.io.InputStream;
import java.io.OutputStream;

import com.kelsoncm.libs.util.ObjetoUtils;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.ArquivoProcessador;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.ArquivoProcessadorCallback;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.ParamObject;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.io.ArquivoLayout;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.io.ArquivoReader;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.io.ArquivoWriter;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.validador.Validador;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.validador.message.MensagemCollection;
import br.jus.tse.sepel2.arquitetura.api.lang.ArquivoException;
import br.jus.tse.sepel2.arquitetura.api.lang.NaoImplementadoException;

/**
 * @author edle.silva
 * 
 */
public class ArquivoProcessadorImpl implements ArquivoProcessador {

	/**
     * 
     */
	private static final long serialVersionUID = 1190399240094269411L;
	private ArquivoLayout arquivoLayoutEntrada;
	private ArquivoLayout arquivoLayoutSaida;
	private ArquivoReader arquivoReader;
	private ArquivoWriter arquivoWriter;
	private Validador validador;
	private ArquivoProcessadorCallback callBack;

	@Override
	public final ArquivoLayout getArquivoLayoutEntrada() {
		return arquivoLayoutEntrada;
	}

	@Override
	public final ArquivoReader getArquivoReader() {
		return arquivoReader;
	}

	@Override
	public final ArquivoWriter getArquivoWriter() {
		return arquivoWriter;
	}

	@Override
	public final void setArquivoLayoutEntrada(final ArquivoLayout arquivoLayoutEntrada) {
		this.arquivoLayoutEntrada = arquivoLayoutEntrada;
	}

	@Override
	public final void setArquivoReader(final ArquivoReader arquivoReader) {
		this.arquivoReader = arquivoReader;
	}

	@Override
	public final void setArquivoWriter(final ArquivoWriter arquivoWriter) {
		this.arquivoWriter = arquivoWriter;
	}

	@Override
	public final Validador getValidador() {
		return validador;
	}

	@Override
	public final void setValidador(final Validador validador) {
		this.validador = validador;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.jus.tse.sepel2.arquitetura.api.infra.arquivo.ArquivoProcessador#processar
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public final MensagemCollection processar(final String nomeArquivoDe, final String nomeArquivoPara)
			throws ArquivoException {
		doBefore();

		validarPropriedadesObrigatorias();

		final ParamObjectImpl po = new ParamObjectImpl();
		po.setNomeArquivoDe(nomeArquivoDe);
		po.setNomeArquivoPara(nomeArquivoPara);

		doBeforeReader(po);
		final Class<? extends ArquivoLayout> classLayout = this.getArquivoLayoutEntrada().getClass();
		this.setArquivoLayoutEntrada(getArquivoReader().ler(classLayout, po.getNomeArquivoDe()));
		doAfterReader();

		getValidador().setArquivoLayoutSaida(this.getArquivoLayoutSaida());
		final MensagemCollection mensagemCollection = getValidador().validar(this.getArquivoLayoutEntrada());
		this.setArquivoLayoutSaida(getValidador().getArquivoLayoutSaida());
		doBeforeWriter(po);
		arquivoWriter.escrever(this.getArquivoLayoutSaida(), po.getNomeArquivoPara());
		doAfterWriter();

		doAfter();
		return mensagemCollection;
	}

	protected final void doBefore() {
		if (hasCallBack()) {
			callBack.doBefore(this);
		}
	}

	protected final void doAfter() {
		if (hasCallBack()) {
			callBack.doAfter(this);
		}
	}

	protected final void doBeforeWriter(final ParamObject po) {
		if (hasCallBack()) {
			callBack.doBeforeWriter(this, po);
		}
	}

	protected final void doAfterWriter() {
		if (hasCallBack()) {
			callBack.doAfterWriter(this);
		}
	}

	protected final void doBeforeReader(final ParamObject po) {
		if (hasCallBack()) {
			callBack.doBeforeReader(this, po);
		}
	}

	protected final void doAfterReader() {
		if (hasCallBack()) {
			callBack.doAfterReader(this);
		}
	}

	protected final boolean hasCallBack() {
		return callBack != null;
	}

	protected final void validarPropriedadesObrigatorias() {
		ObjetoUtils.isNotNull(arquivoLayoutEntrada, "A classe ArquivoLayout de entrada é obrigatório!");
		ObjetoUtils.isNotNull(arquivoLayoutSaida, "A classe ArquivoLayout de saÃ­da é obrigatório!");
		ObjetoUtils.isNotNull(arquivoReader, "O ArquivoReader é obrigatório!");
		ObjetoUtils.isNotNull(arquivoWriter, "O ArquivoWriter é obrigatório!");
		ObjetoUtils.isNotNull(validador, "O Validador é obrigatório!");
	}

	public ArquivoProcessadorImpl() {
	}

	public ArquivoProcessadorImpl(final ArquivoLayout arquivoLayoutEntrada, final ArquivoLayout arquivoLayoutSaida,
			final ArquivoReader arquivoReader, final ArquivoWriter arquivoWriter, final Validador validador) {
		this.arquivoLayoutEntrada = arquivoLayoutEntrada;
		this.arquivoLayoutSaida = arquivoLayoutSaida;
		this.arquivoReader = arquivoReader;
		this.arquivoWriter = arquivoWriter;
		this.validador = validador;
	}

	@Override
	public final MensagemCollection processar() throws ArquivoException {
		throw new NaoImplementadoException();
	}

	public final MensagemCollection processar(final InputStream arquivoEntrada, final OutputStream arquivoSaida)
			throws ArquivoException {
		throw new NaoImplementadoException();
	}

	@Override
	public final ArquivoProcessadorCallback getArquivoProcessadorCallBack() {
		return callBack;
	}

	@Override
	public final void setArquivoProcessadorCallBack(final ArquivoProcessadorCallback callBack) {
		this.callBack = callBack;
	}

	@Override
	public final void setArquivoLayoutSaida(final ArquivoLayout arquivoLayoutSaida) {
		this.arquivoLayoutSaida = arquivoLayoutSaida;
	}

	@Override
	public final ArquivoLayout getArquivoLayoutSaida() {
		return arquivoLayoutSaida;
	}
}
