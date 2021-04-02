package com.kelsoncm.libs.lote.campo;

public class LoteCampoHora extends LoteCampo {

	private static final int TAMANHO = 4;
	public LoteCampoHora(String propriedade, String nome, String descricao, String fixo) {
		super(LoteCampoTipoEnum.HORA, TAMANHO, propriedade,  nome, descricao, fixo);		
	}
	public LoteCampoHora(String propriedade, String nome, String descricao) {
		super(LoteCampoTipoEnum.HORA, TAMANHO, propriedade,  nome, descricao);		
	}
}