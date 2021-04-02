package br.jus.tse.sepel2.arquitetura.core.arquivo.spce.arquivosvalidos;


import com.kelsoncm.libs.lote.LoteDocumentoCabecalho;
import com.kelsoncm.libs.lote.LoteDocumentoDetalhe;
import com.kelsoncm.libs.lote.LoteDocumentoRodape;
import com.kelsoncm.libs.lote.LoteLeiaute;
import com.kelsoncm.libs.lote.campo.LoteCampoAlfa;
import com.kelsoncm.libs.lote.campo.LoteCampoBranco;
import com.kelsoncm.libs.lote.campo.LoteCampoData;
import com.kelsoncm.libs.lote.campo.LoteCampoDataHora;
import com.kelsoncm.libs.lote.campo.LoteCampoDataHoraSegundo;
import com.kelsoncm.libs.lote.campo.LoteCampoHora;
import com.kelsoncm.libs.lote.campo.LoteCampoIdentificadorCabecalho;
import com.kelsoncm.libs.lote.campo.LoteCampoIdentificadorDetalhe2;
import com.kelsoncm.libs.lote.campo.LoteCampoIdentificadorRodape;
import com.kelsoncm.libs.lote.campo.LoteCampoInteiro;
import com.kelsoncm.libs.lote.campo.LoteCampoValor;


public class ApenasDetalhe2ComTodosOsTiposTest extends AbstractTest {

	protected void initLeiaute() {
		super.initLeiaute();
		LoteLeiaute<LoteDocumentoCabecalho, LoteDocumentoDetalhe, LoteDocumentoRodape> leiaute = this.getLeiaute();
		{
			leiaute.setNomeClasseCabecalho(LoteDocumentoCabecalho.class.getName());
			leiaute.adicionarCampoCabecalho(new LoteCampoIdentificadorCabecalho());
			leiaute.adicionarCampoCabecalho(new LoteCampoBranco(58));
		}

		{
			leiaute.setNomeClasseDetalhe2(LoteDocumentoDetalhe.class.getName());
			leiaute.adicionarCampoDetalhe2(new LoteCampoIdentificadorDetalhe2());
			leiaute.adicionarCampoDetalhe2(new LoteCampoAlfa(5, "propriedade", "nome", "descricao"));
			leiaute.adicionarCampoDetalhe2(new LoteCampoAlfa(5, "propriedadeFixo", "nomeFixo", "descricao", "12345"));
			leiaute.adicionarCampoDetalhe2(new LoteCampoData("propriedade", "nome", "descricao"));
			leiaute.adicionarCampoDetalhe2(new LoteCampoDataHora("propriedade", "nome", "descricao"));
			leiaute.adicionarCampoDetalhe2(new LoteCampoDataHoraSegundo("propriedade", "nome", "descricao"));
			leiaute.adicionarCampoDetalhe2(new LoteCampoHora("propriedade", "nome", "descricao"));
			leiaute.adicionarCampoDetalhe2(new LoteCampoInteiro(5, "propriedade", "nome", "descricao"));
			leiaute.adicionarCampoDetalhe2(new LoteCampoValor(5, "propriedade", "nome", "descricao"));
		}
	
		{
			leiaute.setNomeClasseRodape(LoteDocumentoRodape.class.getName());
			leiaute.adicionarCampoRodape(new LoteCampoIdentificadorRodape());
			leiaute.adicionarCampoRodape(new LoteCampoInteiro(1, "totalDetalhes", "Total de linhas", "Total de linhas no arquivo"));
			leiaute.adicionarCampoRodape(new LoteCampoBranco(57));
		}
		leiaute.setTamanhoLinha(59);
	}
	
	public static void main(String[] args) throws Exception {
		ApenasDetalhe2ComTodosOsTiposTest test = new ApenasDetalhe2ComTodosOsTiposTest();
		//test.tentarLer("/SVN_SEPEL2/arquitetura/trunk/arquitetura.arquivo/src/test/java/br/jus/tse/sepel2/arquitetura/core/arquivo/spce/arquivosvalidos/ApenasCampoRegistro.bf");
		System.out.println("É válido? " + test.getLeiaute().isValido());
		test.imprimirLeiaute();
		System.out.println("\n\n");
		test.imprimirArquivoExemplo();
	}
}
