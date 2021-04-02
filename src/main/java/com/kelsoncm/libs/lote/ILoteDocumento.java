package com.kelsoncm.libs.lote;

import java.util.List;

public interface ILoteDocumento
	<Cabecalho extends ILoteDocumentoCabecalho, Detalhe extends ILoteDocumentoDetalhe, Rodape extends ILoteDocumentoRodape> {

	public Cabecalho getCabecalho();

	public void setCabecalho(Cabecalho cabecalho);

	public List<Detalhe> getDetalhes();

	public void adicionarDetalhe(Detalhe detalhe);

	public Rodape getRodape();

	public void setRodape(Rodape rodape);
	
	public boolean isValid();
}
