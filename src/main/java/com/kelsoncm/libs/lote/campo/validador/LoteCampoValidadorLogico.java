package com.kelsoncm.libs.lote.campo.validador;

import java.util.List;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteMensagemValidacao;
import br.jus.tse.sepel2.arquitetura.api.lang.ArquivoLoteConteudoException;

public class LoteCampoValidadorLogico extends LoteCampoValidador {

	@Override
	public List<ILoteMensagemValidacao> doValidar(char[] linha, int numeroLinha, ILoteCampo campo) throws ArquivoLoteConteudoException {
		if (linha[campo.getInicio()-1] != 'S' && linha[campo.getInicio()-1] != 'N')
			return mensagemToListMensage("O formato lógico deve conter apenas valores preenchidos com 'S' ou 'N'.", numeroLinha, campo);
		return null;
	}

}
