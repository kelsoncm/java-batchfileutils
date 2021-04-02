package com.kelsoncm.libs.lote;

import com.kelsoncm.libs.lote.campo.ILoteCampo;

public class LoteMensagemValidacao implements ILoteMensagemValidacao {
	
	private String mensagem;
	private ILoteCampo campo;

	public LoteMensagemValidacao(String mensagem, ILoteCampo campo) {
		super();
		this.mensagem = mensagem;
		this.campo = campo;
	}
 
	@Override
	public TipoMensagemValidacao getTipoMensagemValidacao() {
		return TipoMensagemValidacao.ERROR;
	}

	@Override
	public String getMensagem() {
		return mensagem;
	}
	
	@Override
	public ILoteCampo getCampo() {
		return campo;
	}

	@Override
	public String toString() {
		return mensagem;
	}

}