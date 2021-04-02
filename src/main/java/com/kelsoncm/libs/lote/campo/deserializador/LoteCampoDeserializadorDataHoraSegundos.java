package com.kelsoncm.libs.lote.campo.deserializador;

import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.subCharArrayToDataHoraSegundos;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;

public class LoteCampoDeserializadorDataHoraSegundos extends LoteCampoDeserializador {

	@Override
	protected Object doDeserializar(char[] linha, ILoteCampo campo) {
		return subCharArrayToDataHoraSegundos(linha, campo.getInicio());
	}

}
