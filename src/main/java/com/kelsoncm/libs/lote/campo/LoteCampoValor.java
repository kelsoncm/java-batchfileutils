package com.kelsoncm.libs.lote.campo;

public class LoteCampoValor extends LoteCampo {

	public LoteCampoValor(int tamanho, String propriedade, String nome, String descricao) {
		super(LoteCampoTipoEnum.VALOR, tamanho, propriedade, nome, descricao);
	}

	public LoteCampoValor(int tamanho, String propriedade, String nome, String descricao, String fixo ) {
		super(LoteCampoTipoEnum.VALOR, tamanho, propriedade, nome, descricao, fixo);
	}
}