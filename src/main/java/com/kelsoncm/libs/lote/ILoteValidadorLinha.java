package com.kelsoncm.libs.lote;

import java.io.IOException;
import java.util.List;

import com.kelsoncm.libs.lote.campo.ILoteCampo;

public interface ILoteValidadorLinha {

	public ResultadoValidacao validarLinha( char[] linha, int numeroLinha, List<ILoteCampo> campos) throws IOException;
	
}
