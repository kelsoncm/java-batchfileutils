package com.kelsoncm.libs.lote;

import java.util.ArrayList;
import java.util.List;

public class LoteDocumentoDetalhe extends LoteDocumentoLinha implements ILoteDocumentoDetalhe {

	private List<ILoteDocumentoDetalhe> detalhes;

	@Override
	public List<ILoteDocumentoDetalhe> getDetalhes() {
		return detalhes;
	}

	@Override
	public void adicionarDetalhe(ILoteDocumentoDetalhe detalhe) {
		if (detalhes==null) detalhes = new ArrayList<ILoteDocumentoDetalhe>(); 
		detalhes.add(detalhe);
	}

}
