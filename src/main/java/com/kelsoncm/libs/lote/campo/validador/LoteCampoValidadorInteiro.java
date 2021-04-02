package com.kelsoncm.libs.lote.campo.validador;

import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.isApenasNumeros;

import java.util.List;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteMensagemValidacao;
import br.jus.tse.sepel2.arquitetura.api.lang.ArquivoLoteConteudoException;

public class LoteCampoValidadorInteiro extends LoteCampoValidador {

	@Override
	public List<ILoteMensagemValidacao> doValidar(char[] linha, int numeroLinha, ILoteCampo campo) throws ArquivoLoteConteudoException {
		if (!isApenasNumeros(linha, campo))
			return mensagemToListMensage("O formato numérico deve conter apenas valores inteiros preenchidos com 0 (zeros) à esquerda.", numeroLinha, campo);
		return null;
	}

}
