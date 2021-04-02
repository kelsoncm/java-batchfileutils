package com.kelsoncm.libs.lote.campo.deserializador;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.kelsoncm.libs.lote.ILoteDocumentoLinha;
import com.kelsoncm.libs.lote.campo.ILoteCampo;

public abstract class LoteCampoDeserializador {

	public void deserializar(char[] linha, ILoteDocumentoLinha objeto, ILoteCampo campo) throws IOException {
		try {
			Object valor = doDeserializar(linha, campo);
			BeanUtils.setProperty(objeto, campo.getPropriedade(), valor);
		} catch (NumberFormatException e) {
			throw new IOException(e );
		} catch (IllegalAccessException e) {
			throw new IOException(e);
		} catch (InvocationTargetException e) {
			throw new IOException(e);
		} catch (Exception e) {
			System.out.println("erro na propriedade " + campo.getPropriedade());
			throw new IOException(e);
		}
	}
	
	protected abstract Object doDeserializar(char[] linha, ILoteCampo campo);

}