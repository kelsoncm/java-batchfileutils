package com.kelsoncm.libs.lote.campo.validador;

import java.util.List;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteMensagemValidacao;


public class LoteCampoValidadorBranco extends LoteCampoValidador {

	@Override
	public List<ILoteMensagemValidacao> doValidar(char[] linha, int numeroLinha, ILoteCampo campo) {
		for (int i = campo.getInicio()-1; i <= campo.getFim()-1; i++) {
			if (linha[i]!=' ') {
				return mensagemToListMensage("Campos do tipo BRANCO devem ser integralmente formados por espaços em branco.", numeroLinha,  campo);
			}
		}
		return null;
	}

}
