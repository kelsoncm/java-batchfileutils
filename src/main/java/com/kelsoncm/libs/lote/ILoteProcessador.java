package com.kelsoncm.libs.lote;

public interface ILoteProcessador
	<TCabecalho extends ILoteDocumentoCabecalho, 
	TDetalhe extends ILoteDocumentoDetalhe, 
	TRodape extends ILoteDocumentoRodape,
	TDocumento extends ILoteDocumento<TCabecalho, TDetalhe, TRodape>,
	TLeiaute extends ILoteLeiaute<TCabecalho, TDetalhe, TRodape>,
	TValidadorLeiaute extends ILoteValidadorArquivo,
	TValidador extends ILoteValidadorLinha> {

}
