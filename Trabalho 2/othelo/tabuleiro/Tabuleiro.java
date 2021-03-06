package tabuleiro;
/**
 * Tabuleiro.java
 * Esta &eacute; a interface do tabuleiro
 * N&Atilde;O ALTERE ESSA CLASSE.
 * <p>Se precisar alter&aacute;-la fa&ccedil;a outra classe.
 *
 * Criado em  5 de Abril de 2007, 06:49
 *
 * @author  Alcione
 * @version 1.0
 */
public interface Tabuleiro {

    public final byte AZUL = 0;
    public final byte LIVRE = -1;
    public final byte VERM = 1;
    public final byte EMPATE = -2;
    public String[] cor={"Azul","Vermelho"};

    /**
     * Copia as posicoes de um array
     *
     * @param aTab Array contendo os valores para a c&oacute;pia.
     */
    void copiaTab(byte[][] aTab);

    /**
     * Verifica se o jogo terminou
     *
     * @return false = nao terminou; true = terminou
     */
    boolean fimJogo();

    /**
     * Verifica quem  &eacute; o vencedor
     * @return 0 = nao terminou; AZUL = venceu o jogador 1; VERM = venceu o jogador 2
     */
    public String vencedorCor();

    /**
     * Verifica quem &eacute; o vencedor
     * @return 0 = nao terminou; 1 = venceu o jogador 1; 2 = venceu o jogador 2
     */
    public int vencedorNum();

    /**
     * Executa um movimento. Retonar true se o movimento foi bem sucedido
     * 
     * @param jogador jogador que irá jogar
     * @param j jogada que será realizada
     * @return true se o movimento eh valido.
       */
    public boolean move(byte jogador, Jogada j);

    /**
     * Retorna o numero de pecas de um jogador.
     *
     * @param aiJogador jogador
     * @return n&uacute;mero de pe&ccedil;as
     */
    int numPecas(byte aiJogador);

    /**
     * Retorna a melhor jogada
     *
     * @param aiJogador jogador
     * @return melhor jogada
     */
    Jogada obtemJogadaBoa(byte aiJogador);

    
    /**
     * Retorna a melhor jogada
     *
     * @param jogador numero do jogador
     * @return melhor jogada
     */
    public Jogada obtemJogadaHeuristica(byte jogador);
    
    /**
     * Retorna um vetor contendo as jogadas possiveis de um jogador
     *
     * @param aiJogador jogador
     * @return jogadas possiveis
     */
    java.util.List<Jogada> obtemJogadasPossiveis(byte aiJogador);

    /**
     * Retorna uma copia do tabuleiro na forma de array.
     *
     * @return array com os valores
     */
    byte[][] getTab();

    /**
     *        Retorna o tabuleiro na forma de String
     */
    @Override
    String toString();

    /**
     *   Verifica se um movimento eh valido.
     *
     * @param aiJogador jogador
     * @param j Jogada
     * @return 0 se o movimento é inválido e maior que 0 movimento eh valido.
     */
    byte verifica(byte aiJogador, Jogada j);

    /**
     * Inicia o tabuleiro com a configuracao padrao
     */
    public void inicia();

    /**
     * Inicia o tabuleiro com a configuracao passada na forma de array
     * @param tab tabela com a configuracao
     */
    public void inicia(byte tab[][]);

    /**
     *
     * @param jogador jogador
     * @return oponente 
     */
    public byte oponente(byte jogador);

}
