package com.kelsoncm.libs.lote;

public class LoteDocumentoRodape extends LoteDocumentoLinha implements ILoteDocumentoRodape {

	/**
	 * Total de linhas detalhe
	 */
	private long totalDetalhes;

	/**
	 * Total de linhas detalhe somadas do cabeçalho e do rodapé
	 */
	private long totalLinhas;
	
	/**
	 * Total de linhas detalhe
	 */
	public long getTotalDetalhes() {
		return totalDetalhes;
	}
	
	/**
	 * Total de linhas detalhe
	 */
	public void setTotalDetalhes(long totalDetalhes) {
		this.totalDetalhes = totalDetalhes;
		this.totalLinhas = totalDetalhes + 2;
	}

	/**
	 * Total de linhas detalhe somadas do cabeçalho e do rodapé
	 */
	public long getTotalLinhas() {
		return totalLinhas;
	}
}
