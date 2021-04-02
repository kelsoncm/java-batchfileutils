package com.kelsoncm.libs;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.ParamObject;

/**
 * ParamObject utilizado no processador de arquivos
 * 
 * @author edle.silva
 * 
 */
public final class ParamObjectImpl implements ParamObject {

	/**
     * 
     */
	private static final long serialVersionUID = 6857654431499700493L;
	private String nomeArquivoDe;
	private String nomeArquivoPara;

	public void setNomeArquivoDe(final String nomeArquivoDe) {
		this.nomeArquivoDe = nomeArquivoDe;
	}

	public String getNomeArquivoDe() {
		return nomeArquivoDe;
	}

	public void setNomeArquivoPara(final String nomeArquivoPara) {
		this.nomeArquivoPara = nomeArquivoPara;
	}

	public String getNomeArquivoPara() {
		return nomeArquivoPara;
	}
}
