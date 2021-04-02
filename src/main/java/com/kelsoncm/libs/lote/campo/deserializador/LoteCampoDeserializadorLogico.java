package com.kelsoncm.libs.lote.campo.deserializador;

import com.kelsoncm.libs.lote.campo.LoteCampoUtils;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;

public class LoteCampoDeserializadorLogico  extends LoteCampoDeserializador  {

	@Override
	protected Object doDeserializar(char[] linha, ILoteCampo campo) {
		return LoteCampoUtils.subCharArrayToBoolean(linha, campo.getInicio()-1);
	}

}
