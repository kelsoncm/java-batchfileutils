package com.kelsoncm.libs.lote.campo;

public class LoteCampoInteiro extends LoteCampo {

	public LoteCampoInteiro(int tamanho, String propriedade, String nome, String descricao) {
		super(LoteCampoTipoEnum.INTEIRO, tamanho, propriedade, nome, descricao);
	}

	public LoteCampoInteiro(int tamanho, String propriedade, String nome, String descricao, String fixo) {
		super(LoteCampoTipoEnum.INTEIRO, tamanho, propriedade, nome, descricao, fixo);
	}
}