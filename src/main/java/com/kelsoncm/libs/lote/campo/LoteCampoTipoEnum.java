package com.kelsoncm.libs.lote.campo;

import java.io.IOException;
import java.util.List;

import com.kelsoncm.libs.IOConteudoException;
import com.kelsoncm.libs.lote.ILoteDocumentoLinha;
import com.kelsoncm.libs.lote.ILoteMensagemValidacao;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializador;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializadorAlfa;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializadorBranco;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializadorData;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializadorDataHora;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializadorDataHoraSegundos;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializadorHora;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializadorID;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializadorInteiro;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializadorLogico;
import com.kelsoncm.libs.lote.campo.deserializador.LoteCampoDeserializadorValor;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidador;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidadorAlfa;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidadorBranco;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidadorData;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidadorDataHora;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidadorDataHoraSegundos;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidadorHora;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidadorID;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidadorInteiro;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidadorLogico;
import com.kelsoncm.libs.lote.campo.validador.LoteCampoValidadorValor;

public enum LoteCampoTipoEnum implements ILoteCampoTipo {
	
	ID				( "9", new LoteCampoDeserializadorID()       , new LoteCampoValidadorID()       ),
	INTEIRO			( "9", new LoteCampoDeserializadorInteiro()  , new LoteCampoValidadorInteiro()  ),
	ALFA			( "A", new LoteCampoDeserializadorAlfa()     , new LoteCampoValidadorAlfa()     ),
	DATA			( "9", new LoteCampoDeserializadorData()     , new LoteCampoValidadorData()     ),
	HORA			( "9", new LoteCampoDeserializadorHora()     , new LoteCampoValidadorHora()     ),
	DATAHORA		( "9", new LoteCampoDeserializadorDataHora() , new LoteCampoValidadorDataHora() ),
	DATAHORASEGUNDOS( "9", new LoteCampoDeserializadorDataHoraSegundos() , new LoteCampoValidadorDataHoraSegundos() ),
	VALOR			( "9", new LoteCampoDeserializadorValor()    , new LoteCampoValidadorValor()    ),
	LOGICO			( "S", new LoteCampoDeserializadorLogico()    , new LoteCampoValidadorLogico()    ),
	BRANCO			( " ", new LoteCampoDeserializadorBranco()   , new LoteCampoValidadorBranco()   );
	
	private LoteCampoDeserializador deserializador;
	private LoteCampoValidador validador;
	private String mascara;
	
	private LoteCampoTipoEnum(String mascara, LoteCampoDeserializador deserializador, LoteCampoValidador validador) {
		this.mascara = mascara;
		this.deserializador = deserializador;
		this.validador = validador;
	}

	@Override
	public void deserializar(char[] linha, ILoteDocumentoLinha documentoElemento, ILoteCampo campo) throws IOException {
		this.deserializador.deserializar(linha, documentoElemento, campo);
	}

	@Override
	public List<ILoteMensagemValidacao> validarConteudo(char[] linha, int numeroLinha, ILoteCampo campo) throws IOConteudoException {
		return this.validador.validar(linha, numeroLinha, campo);
	}

	@Override
	public String getMascara() {
		return mascara;
	}

}
