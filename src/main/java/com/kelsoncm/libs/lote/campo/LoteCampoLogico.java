package com.kelsoncm.libs.lote.campo;

public class LoteCampoLogico extends LoteCampo {

	public LoteCampoLogico(String propriedade, String nome, String descricao) {
		super(LoteCampoTipoEnum.LOGICO, 1, propriedade, nome, descricao);
	}

	public LoteCampoLogico(String propriedade, String nome, String descricao, String fixo) {
		super(LoteCampoTipoEnum.LOGICO, 1, propriedade, nome, descricao, fixo);
	}
}