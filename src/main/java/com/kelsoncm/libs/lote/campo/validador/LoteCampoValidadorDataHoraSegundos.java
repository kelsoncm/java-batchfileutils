package com.kelsoncm.libs.lote.campo.validador;

import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.isApenasNumeros;
import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.subCharArrayToDataHoraSegundos;
import static com.kelsoncm.libs.lote.campo.LoteCampoUtils.subCharArrayToString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteCampo;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.lote.ILoteMensagemValidacao;

public class LoteCampoValidadorDataHoraSegundos extends LoteCampoValidador {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");	

	@Override
	public List<ILoteMensagemValidacao> doValidar(char[] linha, int numeroLinha,  ILoteCampo campo) {
		if (isApenasNumeros(linha, campo)) {
			Date date = subCharArrayToDataHoraSegundos(linha, campo.getInicio());
			String reformatado = sdf.format(date);
			String noArquivo = subCharArrayToString(linha, campo.getInicio()-1, campo.getFim()-1);
			if (!reformatado.equals(noArquivo)) {
				return mensagemToListMensage("O formato data,hora e segundos para ser válido dever ser DDMMYYYHHMMSS.", numeroLinha, campo);
			}
		} else {
			return mensagemToListMensage("O formato data,hora e segundos deve conter apenas numeros.", numeroLinha, campo);
		}
		return null;
	}

}