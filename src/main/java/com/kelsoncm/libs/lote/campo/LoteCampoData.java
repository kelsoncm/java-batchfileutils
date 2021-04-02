package com.kelsoncm.libs.lote.campo;

public class LoteCampoData extends LoteCampo {

	private static final int TAMANHO = 8;
	public LoteCampoData(String propriedade, String nome, String descricao, String fixo) {
		super(LoteCampoTipoEnum.DATA, TAMANHO, propriedade,  nome, descricao, fixo);		
	}
	public LoteCampoData(String propriedade, String nome, String descricao) {
		super(LoteCampoTipoEnum.DATA, TAMANHO, propriedade,  nome, descricao);		
	}
}