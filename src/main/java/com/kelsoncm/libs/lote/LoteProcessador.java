package com.kelsoncm.libs.lote;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.kelsoncm.libs.lote.campo.ILoteCampo;
import com.kelsoncm.libs.lote.campo.LoteCampo;

public class LoteProcessador
	<TCabecalho extends ILoteDocumentoCabecalho, 
	TDetalhe extends ILoteDocumentoDetalhe, 
	TRodape extends ILoteDocumentoRodape,
	TDocumento extends ILoteDocumento<TCabecalho, TDetalhe, TRodape>,
	TLeiaute extends ILoteLeiaute<TCabecalho, TDetalhe, TRodape>,
	TValidadorLeiaute extends ILoteValidadorArquivo,
	TValidador extends ILoteValidadorLinha> 
	implements ILoteProcessador<TCabecalho, TDetalhe, TRodape, TDocumento, TLeiaute, TValidadorLeiaute, TValidador> {
	
	// Leiaute do arquivo sendo processado
	private TLeiaute leiaute;
	
	// Validador do leiaute do arquivo e das linhas
	private TValidadorLeiaute validadorArquivo;
	
	//  Validador do conte�do das linhas
	private TValidador validadorLinha;
	
	// Refer�ncia para o arquivo informado pelo cliente no m�todo ler()
	private File arquivo;
	
	// Reader do arquivo informado pelo cliente no m�todo ler()
	private FileReader leitorArquivo;

	// Tamanho do arquivo
	private long tamanhoArquivo;

	// A �ltima linha n�o tem \r\n, portanto, faz de conta que tem
	private long tamanhoArquivoCorrigido;
	
	// Texto da linha atual
	private char[] linhaAtualTexto;
	
	// N�mero da Texto da linha atual
	private long linhaAtualNumero;

	private String nomeClasseDocumento;
	
	private TDocumento documento; 

	public TLeiaute getLeiaute() {
		return leiaute;
	}

	public void setLeiaute(TLeiaute leiaute) {
		this.leiaute = leiaute;
	}

	public TValidadorLeiaute getValidadorArquivo() {
		return validadorArquivo;
	}

	public void setValidadorArquivo(TValidadorLeiaute validadorArquivo) {
		this.validadorArquivo = validadorArquivo;
	}

	public TValidador getValidadorLinha() {
		return validadorLinha;
	}

	public void setValidadorLinha(TValidador validadorLinha) {
		this.validadorLinha = validadorLinha;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

	public FileReader getLeitorArquivo() {
		return leitorArquivo;
	}

	public void setLeitorArquivo(FileReader leitorArquivo) {
		this.leitorArquivo = leitorArquivo;
	}

	public long getTamanhoArquivo() {
		return tamanhoArquivo;
	}

	public void setTamanhoArquivo(long tamanhoArquivo) {
		this.tamanhoArquivo = tamanhoArquivo;
		this.tamanhoArquivoCorrigido = tamanhoArquivo + 2; 
	}

	public long getTamanhoArquivoCorrigido() {
		return tamanhoArquivoCorrigido;
	}

	public char[] getLinhaAtualTexto() {
		return linhaAtualTexto;
	}

	public long getLinhaAtualNumero() {
		return linhaAtualNumero;
	}

	private void initFileVars() {
		this.setTamanhoArquivo(getArquivo().length());
		this.linhaAtualTexto = new char[this.getLeiaute().getTamanhoLinhaCorrigido()];
		this.linhaAtualNumero = 0;
	}

	private void abrirArquivo() throws FileNotFoundException {
		setLeitorArquivo(new FileReader(getArquivo()));
	}

	private void fecharArquivo() throws IOException, IOException {
		if (getLeitorArquivo() != null)
			getLeitorArquivo().close();
	}

	private boolean proximaLinha() throws IOException {
		int bytesLidos = getLeitorArquivo().read(linhaAtualTexto);
		boolean linhaLida = bytesLidos >= 0;
		if (linhaLida) 
			this.linhaAtualNumero++;
		return linhaLida;
	}

	private long getNumeroLinhasPrevisto() {
		return this.getTamanhoArquivoCorrigido() / this.getLeiaute().getTamanhoLinhaCorrigido();
	}

	public void adicionarCampo(List<LoteCampo> lista, LoteCampo campo) {
		lista.add(campo);
		
		LoteCampo campoAnterior = lista.get(lista.size());
		
		campo.setOrdem(lista.size()+1);
		campo.setInicio(campoAnterior.getFim()+1);
		campo.setFim(campo.getInicio() +  + campo.getTamanho());
	}

	@SuppressWarnings("unchecked")
	@Override
	public TDocumento ler(File arquivo) throws IOException {

		if ( !getLeiaute().isValido() ) {
			throw new ArquivoLoteLeiauteException("Erro de leiaute. O leiaute definido para a valida��o e importa��o do arquivo est� inv�lido. Contacte o administrador do sistema.");
		}
		
		if (!arquivo.exists()) {
			throw new RuntimeException("O arquivo n�o existe.");
		}
		
		setArquivo(arquivo);

		initFileVars();
		
		getValidadorArquivo().validarArquivo(tamanhoArquivoCorrigido, leiaute.getTamanhoLinhaCorrigido());

		documento = ClasseUtils.instanciarPeloNome(nomeClasseDocumento); 
		IOException erroOriginal = null;
		try {
			abrirArquivo();
			int numeroLinha = 0;
			ILoteDocumentoLinha elemento = null;
			TDetalhe ultimoElementoTipo2 = null;
			while (proximaLinha()) {
				numeroLinha++;
				
				List<ILoteCampo> campos = null;
				boolean ehCabecalho = getLinhaAtualNumero() == 1;
				boolean ehDetalhe = getLinhaAtualNumero() < getNumeroLinhasPrevisto();
				boolean ehRodape = getLinhaAtualNumero() == getNumeroLinhasPrevisto();
				
				if (ehCabecalho) {
					elemento = ClasseUtils.instanciarPeloNome(leiaute.getNomeClasseCabecalho());
					campos = leiaute.getCamposCabecalho();
					documento.setCabecalho((TCabecalho)elemento);
				} else if (ehDetalhe) {
					if (linhaAtualTexto[0]=='2') {
						elemento = ClasseUtils.instanciarPeloNome(leiaute.getNomeClasseDetalhe2());
						campos = leiaute.getCamposDetalhe2();
						documento.adicionarDetalhe((TDetalhe)elemento);
						ultimoElementoTipo2 = (TDetalhe)elemento;
					} else if (linhaAtualTexto[0]=='3') {
						elemento = ClasseUtils.instanciarPeloNome(leiaute.getNomeClasseDetalhe3());
						campos = leiaute.getCamposDetalhe3();
						ultimoElementoTipo2.adicionarDetalhe((ILoteDocumentoDetalhe)elemento);
					}
				} else {
					elemento = ClasseUtils.instanciarPeloNome(leiaute.getNomeClasseRodape());
					campos = leiaute.getCamposRodape();
					documento.setRodape((TRodape)elemento);
				}
				
				elemento.setElementoPai(documento);
				elemento.setNumeroLinha( linhaAtualNumero );
				validarLinha(elemento, numeroLinha, campos);

				if (elemento.isValido()) {
					deserializarLinha(elemento, campos, numeroLinha);
					if (ehRodape) {
						if ( (numeroLinha-2) != ((TRodape)elemento).getTotalDetalhes()) {
							elemento.getResultadoValidacao().adicionarMensagemValidacao( new LoteMensagemValidacao("O n�mero de linhas informado � diferente do n�mero de linhas constante no arquivo.", null) );
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			erroOriginal = new IOException("Erro ao abrir o arquivo.", e);
			throw erroOriginal;
		} catch (IOException e) {
			erroOriginal = new IOException("Erro ao ler o arquivo.", e);
			throw erroOriginal;
		} finally {
			try {
				fecharArquivo();
			} catch (IOException e) {
				if (erroOriginal != null) {
					throw new IOException("Erro ao fechar o arquivo.", erroOriginal);
				} else {
					throw new IOException("Erro ao fechar o arquivo.", e);
				}
			}
		}
		return documento;
	}
	
	public void validarLinha(ILoteDocumentoLinha elemento, int numeroLinha, List<ILoteCampo> campos) throws IOException {
		ResultadoValidacao validacao = getValidadorLinha().validarLinha(linhaAtualTexto, numeroLinha, campos);
		elemento.setResultadoValidacao(validacao);
	}

	private void deserializarLinha(ILoteDocumentoLinha documentoElemento, List<ILoteCampo> leiauteLinha, int numeroLinha) throws IOException {
		documentoElemento.setNumeroLinha(linhaAtualNumero);
		documentoElemento.setTipoLinha(documentoElemento.getTipoLinha());
		for (ILoteCampo campo : leiauteLinha) {
			List<ILoteMensagemValidacao> validacoes = campo.validar(linhaAtualTexto, numeroLinha);
			if (validacoes != null) {
				for (ILoteMensagemValidacao validacao : validacoes) {
					if (validacao != null) {
						documentoElemento.adicionarMensagemValidacao(validacao);				
					}
				}
			}
			campo.deserializar(linhaAtualTexto, documentoElemento);
		}
	}

	@Override
	public String getNomeClasseDocumento() {
		return nomeClasseDocumento;
	}

	@Override
	public void setNomeClasseDocumento(String nomeClasseDocumento) {
		this.nomeClasseDocumento = nomeClasseDocumento;
	}
	
}