package com.kelsoncm.libs.lote.campo.validador;

import java.util.List;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteMensagemValidacao;

public class LoteCampoValidadorID extends LoteCampoValidador {

	@Override
	public List<ILoteMensagemValidacao> doValidar(char[] linha, int numeroLinha, ILoteCampo campo) {
		if (linha[0] != campo.getFixoChars()[0] ) {
			return mensagemToListMensage("Esta linha deve ser do tipo " + campo.getFixo() + ".", numeroLinha, campo);
		}
		return null;
	}

}
