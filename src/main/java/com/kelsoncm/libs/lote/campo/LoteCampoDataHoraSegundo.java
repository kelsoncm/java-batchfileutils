package com.kelsoncm.libs.lote.campo;

public class LoteCampoDataHoraSegundo extends LoteCampo {

	private static final int TAMANHO = 14;
	
	public LoteCampoDataHoraSegundo(String propriedade, String nome, String descricao, String fixo) {
		super(LoteCampoTipoEnum.DATAHORASEGUNDOS, TAMANHO, propriedade,  nome, descricao, fixo);		
	}
	public LoteCampoDataHoraSegundo(String propriedade, String nome, String descricao) {
		super(LoteCampoTipoEnum.DATAHORASEGUNDOS, TAMANHO, propriedade,  nome, descricao);		
	}
}