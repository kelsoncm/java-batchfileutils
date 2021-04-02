package com.kelsoncm.libs.lote.campo;



public class LoteCampoIdentificador extends LoteCampo {
	
	protected LoteCampoIdentificador(String descricao, LoteCampoIdentificadorEnum tipoLinha) {
		super( 
			LoteCampoTipoEnum.ID, 
			1,
			"tipoLinha", 
			"Registro",
			descricao, 
			tipoLinha.getIdentificador()+"");
	}

}
