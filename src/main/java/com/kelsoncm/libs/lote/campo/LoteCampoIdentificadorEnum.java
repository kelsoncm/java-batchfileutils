package com.kelsoncm.libs.lote.campo;

public enum LoteCampoIdentificadorEnum {
	
	CABECALHO('1'),
	DETALHE2 ('2'),
	DETALHE3 ('3'),
	RODAPE   ('9');
	
	private char identificador;

	LoteCampoIdentificadorEnum(char identificador) {
		this.identificador = identificador;
	}

	public char getIdentificador() {
		return identificador;
	}
	
}