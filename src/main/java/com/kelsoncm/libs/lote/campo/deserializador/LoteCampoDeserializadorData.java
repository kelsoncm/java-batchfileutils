package com.kelsoncm.libs.lote.campo.deserializador;

import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.subCharArrayToData;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;

public class LoteCampoDeserializadorData  extends LoteCampoDeserializador  {

	@Override
	protected Object doDeserializar(char[] linha, ILoteCampo campo) {
		return subCharArrayToData(linha, campo.getInicio());
	}

}
