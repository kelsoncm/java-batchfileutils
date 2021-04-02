package com.kelsoncm.libs.lote;

public class LoteDocumentoCabecalho extends LoteDocumentoLinha implements ILoteDocumentoCabecalho {

	/**
	 * N�mero da vers�o do arquivo. �til para verifica��o.
	 */
	private int versao;

	/**
	 * Nome do leiaute do arquivo. �til para verifica��o.
	 */
	private String nomeLeiaute;

	/**
	 * N�mero da vers�o do arquivo. �til para verifica��o.
	 */
	public int getVersao() {
		return versao;
	}

	/**
	 * N�mero da vers�o do arquivo. �til para verifica��o.
	 */
	public void setVersao(int versao) {
		this.versao = versao;
	}

	/**
	 * Nome do leiaute do arquivo. �til para verifica��o.
	 */
	public String getNomeLeiaute() {
		return nomeLeiaute;
	}

	/**
	 * Nome do leiaute do arquivo. �til para verifica��o.
	 */
	public void setNomeLeiaute(String nomeLeiaute) {
		this.nomeLeiaute = nomeLeiaute;
	}

}
