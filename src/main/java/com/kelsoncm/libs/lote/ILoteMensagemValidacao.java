package com.kelsoncm.libs.lote;

import com.kelsoncm.libs.lote.campo.ILoteCampo;

public interface ILoteMensagemValidacao {
	 
		public TipoMensagemValidacao getTipoMensagemValidacao();

		public String getMensagem();

		public ILoteCampo getCampo();

}
