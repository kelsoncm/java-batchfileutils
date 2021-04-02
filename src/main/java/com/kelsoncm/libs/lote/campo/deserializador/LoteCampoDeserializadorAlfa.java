package com.kelsoncm.libs.lote.campo.deserializador;

import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.subCharArrayToString;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;

public class LoteCampoDeserializadorAlfa extends LoteCampoDeserializador {

	@Override
	public Object doDeserializar(char[] linha, ILoteCampo campo) {
		return subCharArrayToString(linha, campo.getInicio()-1, campo.getFim()-1);
	}

}
