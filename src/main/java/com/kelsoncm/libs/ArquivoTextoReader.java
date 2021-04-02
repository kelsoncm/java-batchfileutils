package com.kelsoncm.libs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.kelsoncm.libs.texto.ArquivoTextoLayout;

import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.io.ArquivoLayout;
import br.jus.tse.sepel2.arquitetura.api.infra.arquivo.io.ArquivoReader;
import br.jus.tse.sepel2.arquitetura.api.lang.ArquivoException;

/**
 * 
 * Leitor de arquivo textual
 * 
 * @author edle.silva
 * 
 */
public class ArquivoTextoReader implements ArquivoReader {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.jus.tse.corporativo.sepel2.infra.api.arquivo.oi.ArquivoReader#ler(
	 * java.lang.Class, java.lang.String)
	 */
	/**
	 * Ler uma arquivo texto e devolve um ArquivoTextoLayout
	 */
	@Override
	public final ArquivoLayout ler(final Class<? extends ArquivoLayout> arquivoLayoutClass, final String fileName)
			throws ArquivoException {
		InputStream arquivo;
		try {
			arquivo = new FileInputStream(new File(fileName));
			return new ArquivoTextoLayout(arquivo);
		} catch (final FileNotFoundException e) {
			throw new ArquivoException(e);
		}
	}
}
