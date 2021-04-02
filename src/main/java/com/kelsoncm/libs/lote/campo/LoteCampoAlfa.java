package com.kelsoncm.libs.lote.campo;

public class LoteCampoAlfa extends LoteCampo {

	public LoteCampoAlfa(int tamanho, String propriedade, String nome, String descricao) {
		super(LoteCampoTipoEnum.ALFA, tamanho, propriedade, nome, descricao);
	}

	public LoteCampoAlfa(int tamanho, String propriedade, String nome, String descricao, String fixo) {
		super(LoteCampoTipoEnum.ALFA, tamanho, propriedade, nome, descricao, fixo);
	}
}