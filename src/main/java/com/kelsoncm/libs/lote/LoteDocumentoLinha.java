package com.kelsoncm.libs.lote;


/**
 * Implementação padrão para a interface IArquivoLoteLinha. 
 * @author kelson.medeiros
 *
 */
public class LoteDocumentoLinha
	extends LoteDocumentoElemento
	implements ILoteDocumentoLinha {

	/**
	 * Número da linha no arquivo
	 */
	private long numeroLinha;
	
	/**
	 * Tipo de registro. O registro 1 é cabecalho, o 9 é rodapé, de 2 a 8 é detalhe.
	 */
	private int tipoLinha;
	
	@Override
	public long getNumeroLinha() {
		return this.numeroLinha;
	}

	@Override
	public void setNumeroLinha(long numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	@Override
	public int getTipoLinha() {
		return this.tipoLinha;
	}

	@Override
	public void setTipoLinha(int tipoLinha) {
		this.tipoLinha = tipoLinha;
	}
	
}