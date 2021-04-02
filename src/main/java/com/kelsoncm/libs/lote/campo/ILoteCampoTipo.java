package com.kelsoncm.libs.lote.campo;

import java.io.IOException;
import java.util.List;

import com.kelsoncm.libs.IOConteudoException;
import com.kelsoncm.libs.lote.ILoteDocumentoLinha;
import com.kelsoncm.libs.lote.ILoteMensagemValidacao;

public interface ILoteCampoTipo {

	String name();
	
	Object getMascara();

	void deserializar(char[] linha, ILoteDocumentoLinha documentoElemento,
			ILoteCampo campo) throws IOException;

	List<ILoteMensagemValidacao> validarConteudo(char[] linha, int numeroLinha,
			ILoteCampo campo) throws IOConteudoException;

}
