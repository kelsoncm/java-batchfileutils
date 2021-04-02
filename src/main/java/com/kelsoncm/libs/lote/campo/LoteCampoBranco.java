package com.kelsoncm.libs.lote.campo;

public class LoteCampoBranco extends LoteCampo {

	public LoteCampoBranco(int tamanho) {
		super( 
			LoteCampoTipoEnum.BRANCO, 
			tamanho,
			null, 
			"Preenchimento",
			"Preencher com espaços em branco");		
		StringBuilder sb = new StringBuilder(); 
		for (int i = 1; i<=tamanho; i++) {
			sb.append(' ');
		}
		this.setFixo(sb.toString());
	}
}