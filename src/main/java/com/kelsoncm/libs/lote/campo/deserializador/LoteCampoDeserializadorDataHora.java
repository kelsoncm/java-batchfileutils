package com.kelsoncm.libs.lote.campo.deserializador;

import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.subCharArrayToDataHora;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;

public class LoteCampoDeserializadorDataHora  extends LoteCampoDeserializador  {

	@Override
	protected Object doDeserializar(char[] linha, ILoteCampo campo) {
		return subCharArrayToDataHora(linha, campo.getInicio());
	}

}
