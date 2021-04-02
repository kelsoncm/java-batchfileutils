package com.kelsoncm.libs.lote;

import java.util.List;

public interface ILoteDocumentoDetalhe extends ILoteDocumentoElemento {

	public List<ILoteDocumentoDetalhe> getDetalhes();
	public void adicionarDetalhe(ILoteDocumentoDetalhe detalhe);
}
