package com.kelsoncm.libs.lote.campo;

public class LoteCampoDataHora extends LoteCampo {

	private static final int TAMANHO = 12;
	
	public LoteCampoDataHora(String propriedade, String nome, String descricao, String fixo) {
		super(LoteCampoTipoEnum.DATAHORA, TAMANHO, propriedade,  nome, descricao, fixo);		
	}
	public LoteCampoDataHora(String propriedade, String nome, String descricao) {
		super(LoteCampoTipoEnum.DATAHORA, TAMANHO, propriedade,  nome, descricao);		
	}
}