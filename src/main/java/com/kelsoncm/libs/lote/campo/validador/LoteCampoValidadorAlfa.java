package com.kelsoncm.libs.lote.campo.validador;

import java.util.List;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteMensagemValidacao;

public class LoteCampoValidadorAlfa extends LoteCampoValidador {

	@Override
	public List<ILoteMensagemValidacao> doValidar(char[] linha, int numeroLinha, ILoteCampo campo) {
		for (int i = campo.getInicio()-1; i <= campo.getFim()-1; i++) {
			if (linha[i] == '\n' || linha[i] == '\r' || linha[i] == '\0')
				return mensagemToListMensage("Campos do tipo ALFA não permitem \\0, \\r ou \\n.", numeroLinha, campo);
		}
		return null;
	}

}