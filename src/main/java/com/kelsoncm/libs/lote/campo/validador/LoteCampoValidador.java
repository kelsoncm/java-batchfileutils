package com.kelsoncm.libs.lote.campo.validador;

import java.util.ArrayList;
import java.util.List;

import com.kelsoncm.libs.IOConteudoException;
import com.kelsoncm.libs.lote.ILoteMensagemValidacao;
import com.kelsoncm.libs.lote.LoteMensagemCampoValidacao;
import com.kelsoncm.libs.lote.campo.ILoteCampo;

public abstract class LoteCampoValidador {

	protected List<ILoteMensagemValidacao> mensagemToListMensage(ILoteMensagemValidacao mensagem) {
		if (mensagem != null) {
			List<ILoteMensagemValidacao> result = new ArrayList<ILoteMensagemValidacao>();
			result.add(mensagem);
			return result;
		}
		return null;
	}

	protected List<ILoteMensagemValidacao> mensagemToListMensage(String mensagem, int numeroLinha, ILoteCampo campo) {
		return mensagemToListMensage(new LoteMensagemCampoValidacao(mensagem, numeroLinha, campo));
	}

	protected ILoteMensagemValidacao validarValorFixo(char[] linha, int numeroLinha, ILoteCampo campo) {
		if (campo.getFixo() == null)
			return null;

		int j = 0;
		char[] fixo = campo.getFixoChars();
		for (int i = campo.getInicio() - 1; i <= campo.getFim() - 1; i++) {
			if (linha[i] != fixo[j]) {
				return new LoteMensagemCampoValidacao("O valor deste campo � fixo, portanto, informe '" + campo.getFixo() + "'.", numeroLinha, campo);
			}
			j++;
		}

		return null;
	}

	public List<ILoteMensagemValidacao> validar(char[] linha, int numeroLinha, ILoteCampo campo) throws IOConteudoException {

		List<ILoteMensagemValidacao> result = doValidar(linha, numeroLinha, campo);
		if (result != null)
			return result;

		return mensagemToListMensage(validarValorFixo(linha, numeroLinha, campo));
	}

	public abstract List<ILoteMensagemValidacao> doValidar(char[] linha, int numeroLinha, ILoteCampo campo) throws IOConteudoException;

}