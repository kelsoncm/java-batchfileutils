package br.jus.tse.sepel2.arquitetura.core.arquivo.spce.arquivosvalidos;

import com.kelsoncm.libs.lote.LoteDocumentoCabecalho;
import com.kelsoncm.libs.lote.LoteDocumentoDetalhe;
import com.kelsoncm.libs.lote.LoteDocumentoRodape;
import com.kelsoncm.libs.lote.LoteLeiaute;
import com.kelsoncm.libs.lote.campo.LoteCampoBranco;
import com.kelsoncm.libs.lote.campo.LoteCampoIdentificadorCabecalho;
import com.kelsoncm.libs.lote.campo.LoteCampoIdentificadorDetalhe2;
import com.kelsoncm.libs.lote.campo.LoteCampoIdentificadorDetalhe3;
import com.kelsoncm.libs.lote.campo.LoteCampoIdentificadorRodape;
import com.kelsoncm.libs.lote.campo.LoteCampoInteiro;


public class ApenasCampoRegistroTest extends AbstractTest {
	
	protected void initLeiaute() {
		super.initLeiaute();
		LoteLeiaute<LoteDocumentoCabecalho, LoteDocumentoDetalhe, LoteDocumentoRodape> l = getLeiaute();
		
		{
			l.setNomeClasseCabecalho(LoteDocumentoCabecalho.class.getName());
			l.adicionarCampoCabecalho(new LoteCampoIdentificadorCabecalho());
			l.adicionarCampoCabecalho(new LoteCampoBranco(1));
		}

		{
			l.setNomeClasseDetalhe2(LoteDocumentoDetalhe.class.getName());
			l.adicionarCampoDetalhe2(new LoteCampoIdentificadorDetalhe2());
			l.adicionarCampoDetalhe2(new LoteCampoBranco(1));
		}
		
		{
			l.setNomeClasseDetalhe3(LoteDocumentoDetalhe.class.getName());
			l.adicionarCampoDetalhe3(new LoteCampoIdentificadorDetalhe3());
			l.adicionarCampoDetalhe3(new LoteCampoBranco(1));
		}
		
		{
			l.setNomeClasseRodape(LoteDocumentoRodape.class.getName());
			l.adicionarCampoRodape(new LoteCampoIdentificadorRodape());
			l.adicionarCampoRodape(new LoteCampoInteiro(1, "totalDetalhes", "Total de linhas", "Total de linhas no arquivo"));
		}
		
		l.setTamanhoLinha(2);
	}
	
	public static void main(String[] args) {
		ApenasCampoRegistroTest test = new ApenasCampoRegistroTest();
		test.tentarLer("/SVN_SEPEL2/arquitetura/trunk/arquitetura.arquivo/src/test/java/br/jus/tse/sepel2/arquitetura/core/arquivo/spce/arquivosvalidos/ApenasCampoRegistro.bf");
		
	}
}
