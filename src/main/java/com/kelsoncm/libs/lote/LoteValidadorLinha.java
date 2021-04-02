package com.kelsoncm.libs.lote;

import java.io.IOException;
import java.util.List;

import com.kelsoncm.libs.lote.campo.ArquivoLoteConteudoException;
import com.kelsoncm.libs.lote.campo.ILoteCampo;

public class LoteValidadorLinha implements ILoteValidadorLinha {

	@Override
	public ResultadoValidacao validarLinha( char[] linha, int numeroLinha, List<ILoteCampo> campos) throws IOException {
		validarFinalLinha(linha, campos);
		ResultadoValidacaoImpl resultado = new ResultadoValidacaoImpl();
		for (ILoteCampo campo : campos) {
			List<ILoteMensagemValidacao> validacoes = campo.validar(linha, numeroLinha);
			if (validacoes != null) {
				for (ILoteMensagemValidacao validacao : validacoes) {
					if (validacao != null) {
						resultado.adicionarMensagemValidacao(validacao);					
					}
				}
			}
		}
		return resultado; 
	}

	private void validarFinalLinha(char[] linha, List<ILoteCampo> campos) throws ArquivoLoteConteudoException {
		// Ser n�o for rodap�
		if (campos.get(0).getFixoChars()[0]!='9')
			// Se n�o terminar com \\r\\n
			if (linha[linha.length-2] != '\r' || linha[linha.length-1] != '\n')
				throw new ArquivoLoteConteudoException("A linha tem que terminar com \\n \\r.");
	}

}