package com.kelsoncm.libs.lote.campo.validador;

import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.isApenasNumeros;
import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.subCharArrayToHora;
import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.subCharArrayToString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteMensagemValidacao;

public class LoteCampoValidadorHora extends LoteCampoValidador {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("HHmm");	

	@Override
	public List<ILoteMensagemValidacao> doValidar(char[] linha, int numeroLinha, ILoteCampo campo) {
		if (isApenasNumeros(linha, campo)) {
			Date date = subCharArrayToHora(linha, campo.getInicio());
			String reformatado = sdf.format(date);
			String noArquivo = subCharArrayToString(linha, campo.getInicio()-1, campo.getFim()-1);
			if (!reformatado.equals(noArquivo)) {
				return mensagemToListMensage("O formato hora para ser válido dever ser HHMM.",  numeroLinha, campo);
			}
		} else {
			return mensagemToListMensage("O formato hora deve conter apenas numeros.", numeroLinha, campo);
		}
		return null;
	}

}
