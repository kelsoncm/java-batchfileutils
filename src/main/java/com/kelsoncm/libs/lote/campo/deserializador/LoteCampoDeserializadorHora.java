package com.kelsoncm.libs.lote.campo.deserializador;

import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.subCharArrayToHora;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;

public class LoteCampoDeserializadorHora  extends LoteCampoDeserializador  {

	@Override
	protected Object doDeserializar(char[] linha, ILoteCampo campo) {
		return subCharArrayToHora(linha, campo.getInicio()-1);
	}

}
