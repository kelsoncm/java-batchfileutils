package com.kelsoncm.libs.lote.campo.deserializador;

import com.kelsoncm.libs.lote.campo.LoteCampoUtils;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;

public class LoteCampoDeserializadorID  extends LoteCampoDeserializador  {

	@Override
	protected Object doDeserializar(char[] linha, ILoteCampo campo) {
		return LoteCampoUtils.subCharArrayToByte(linha, campo.getInicio()-1, campo.getFim()-1);
	}

}
