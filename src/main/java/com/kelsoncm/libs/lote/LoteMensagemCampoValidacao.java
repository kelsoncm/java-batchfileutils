package com.kelsoncm.libs.lote;

import com.kelsoncm.libs.lote.campo.ILoteCampo;

public class LoteMensagemCampoValidacao extends LoteMensagemValidacao {

	private int numeroLinha;

	public LoteMensagemCampoValidacao(String mensagem, int numeroLinha, ILoteCampo campo) {
		super(mensagem, campo);
		this.numeroLinha = numeroLinha;
	}

	@Override
	public String getMensagem() {
		return "Erro de conte�do. Linha N� " + this.numeroLinha + ". Campo " + this.getCampo().getNome() + ". " + super.getMensagem();
	}
	
}
