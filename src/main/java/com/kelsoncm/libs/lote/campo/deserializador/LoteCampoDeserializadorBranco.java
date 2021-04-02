package com.kelsoncm.libs.lote.campo.deserializador;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteDocumentoLinha;
import br.jus.tse.sepel2.arquitetura.api.lang.ArquivoLoteException;

public class LoteCampoDeserializadorBranco  extends LoteCampoDeserializador  {
	
	public void deserializar(char[] linha, ILoteDocumentoLinha objeto, ILoteCampo campo) throws ArquivoLoteException {
	}
	@Override
	protected Object doDeserializar(char[] linha, ILoteCampo campo) {
		return null;
	}

}
