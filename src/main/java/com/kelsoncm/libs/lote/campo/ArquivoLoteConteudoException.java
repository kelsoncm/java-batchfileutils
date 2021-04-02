package com.kelsoncm.libs.lote.campo;

import java.io.IOException;

public class ArquivoLoteConteudoException extends IOException {

	private static final long serialVersionUID = -2669900337535613656L;

	public ArquivoLoteConteudoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArquivoLoteConteudoException(String message) {
		super(message);
	}

	public ArquivoLoteConteudoException(Throwable cause) {
		super(cause);
	}

}
