package othelo;

import java.util.ArrayList;
import java.util.List;
import jogo.Configuracao;
import tabuleiro.Jogada;
import tabuleiro.Tabuleiro;

/**
 * Othelo &eacute; jogado em um tabuleiro n&atilde;o quadriculado com 64 quadrados dispostos
 * em 8 filas x 8 colunas. O jogo come&ccedil;a com quatro pe&ccedil;as j&aacute; colocadas no
 * tabuleiro como pode ser visto.
 * <p><img SRC="file:othelo.png" height=393 width=400 alt="Tabuleiro">
 * <br>&nbsp;
 * O objetivo do Reversi &eacute; virar a maioria das pe&ccedil;as para sua cor. Se ambos os
 * jogadores tiverem o mesmo n&uacute;mero de pe&ccedil;as de sua cor, o jogo empata.
 * <p><b>O Jogo</b>
 * <p>Os jogadores s&atilde;o designados como Preto e Branco. Cada jogador na sua vez joga uma pedra em um
 * quadrado livre no tabuleiro.&nbsp; Uma vez colocadas, uma pedra nunca se move, embora ela possa ser
 * "virada" para que mude de cor.
 * <p>O posicionamento de uma pe&ccedil;a, geralmente denominado " movimento," pode
 * apenas ser feito se ela " capturar" uma ou mais pedras do oponente.&nbsp;
 * Uma pedra, ou uma linha&nbsp; de pedras da mesma cor, &eacute; capturada quando ela
 * fica cercada nas pontas opostas por duas pedras advers&aacute;rias, uma das quais deve
 * estar a pedra que acabou de ser colocada no tabuleiro. As capturas podem ser
 * feitas nas fileiras (fileiras horizontais de quadrados), colunas (fileiras verticais de quadrados)
 * ou diagonais. As pedras capturadas s&atilde;o viradas para que a cor
 * corresponda &agrave; cor das pedras do jogador que a capturou.
 * <p>Um jogador que n&atilde;o pode fazer um movimento legal perde sua vez.
 * O jogo continua at&eacute; que nenhum lado possa se mover; geralmente, isso n&atilde;o
 * acontece at&eacute; que o
 * tabuleiro esteja completamente preenchido. O jogador cuja cor estiver
 * na maioria
 * das pe&ccedil;as no final &eacute; o vencedor. Se ambos tiverem o mesmo n&uacute;mero de pe&ccedil;as de sua cor, o jogo empata.
 *
 * <br>&nbsp;
 * Esta Classe implementa o servidor Tabuleiro. <br>
 * N&Atilde;O ALTERE ESSA CLASSE.<br>
 *
 *<b>Execu&ccedil;&atilde;o</b><br>
 * Configure o local de execucao do  projeto (othelo2018) em:<br>
 *  diretorio othelo2018\build\classes<br><br>
 * Primeiro &eacute; preciso iniciar o sevidor (ServidorImp)<br>
 * Depois execute os jogadores<br>
 * <br><br>
 *<b>Protocolo</b><br>
 * A cada rodada o servidor recebe uma jogada e envia para o oponente. <br>
 * A jogada recebida possui o seguinte formato:<br>
 *<center><i> linha n coluna \n-1\n-1\n</i></center> <br>
 *<br>
 *Caso o servidor receba o caracter # de um jogador significa que ocorreu algum problema e o jogador esta desistindo
 *<br>
 * A jogada enviada possui o seguinte format:<br>
 *<center><i> jogador\n linha\n coluna\n</i></center> <br>
 *Onde:
 *<ul>
 *<li> jogador= indica qual &eacute; a cor do jogador (Tabuleiro.AZUL ou Tabuleiro.VERM) ou
 * '#' indicando fim do jogo.
 *<li>  x y = sao as coordenadas da jogada (0 a 7).
 *</ul> <br><p>
 *<br>



 * Esta Classe implementa o tabuleiro e as funcoes necessarias
 * para a manipulacao. <br>
 * NAO ALTERE ESSA CLASSE.
 * <p>Se precisar altera-la faca outra classe.
 * @author  Alcione
 * @version 2.0
 */
public final class TabuleiroOthelo implements Tabuleiro {

    private final int DIM = Configuracao.getInstance().getDim();
    private byte tab[][];
    public int win_r1, win_c1, win_r2, win_c2;

    /**
     * Inicia o tabuleiro com a configura&ccedil;&atilde;o padr&atilde;o
     */
    public void inicia() {
        tab = new byte[DIM][DIM];

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                tab[i][j] = LIVRE;
            }
        }
        tab[(DIM / 2) - 1][(DIM / 2) - 1] = VERM;
        tab[DIM / 2][DIM / 2] = VERM;
        tab[(DIM / 2) - 1][DIM / 2] = AZUL;
        tab[DIM / 2][(DIM / 2) - 1] = AZUL;


    }

    /**
     * Inicia o tabuleiro com a configura&ccedil;&atilde;o passada na forma de
     * array
     *
     * @param aTab tabela com a configura&ccedil;&atilde;o
     */
    public void inicia(byte aTab[][]) {
        tab = new byte[DIM][DIM];
        if (aTab.length == DIM && aTab[0].length == DIM) {
            for (int i = 0; i < DIM; i++) {
                System.arraycopy(aTab[i], 0, tab[i], 0, DIM);
            }
        }
    }

    /**
     * Cria um tabuleiro com os campos inicializados
     */
    public TabuleiroOthelo() {
        inicia();
    }

    /**
     * Cria um tabuleiro com os campos inicializados segundo um array.
     *
     * @param aabTab Array contendo os valores para a
     * inicializa&ccedil;&atilde;o.
     */
    public TabuleiroOthelo(byte aabTab[][]) {
        tab = new byte[DIM][DIM];
        if (aabTab.length == DIM && aabTab[0].length == DIM) {
            for (int i = 0; i < DIM; i++) {
                System.arraycopy(aabTab[i], 0, tab[i], 0, DIM);
            }
        }
    }

    /**
     * Copia as posicoes de um array
     *
     * @param aTab Array contendo os valores para a col&oacute;pia.
     */
    public void copiaTab(byte aTab[][]) {
        for (int i = 0; i < DIM; i++) {
            System.arraycopy(aTab[i], 0, tab[i], 0, DIM);
        }
    }

    /**
     * Copia as posicoes para um array
     *
     * @param aTab Array contendo os valores para a col&oacute;pia.
     */
    public void copiaToTab(byte aTab[][]) {
        for (int i = 0; i < DIM; i++) {
            System.arraycopy(tab[i], 0, aTab[i], 0, DIM);
        }
    }

    /**
     * Retorna uma col&oacute;pia do tabuleiro na forma de array.
     *
     * @return array com os valores
     */
    public byte[][] getTab() {
        byte labTab[][] = new byte[DIM][DIM];
        for (int i = 0; i < DIM; i++) {
            System.arraycopy(tab[i], 0, labTab[i], 0, DIM);
        }
        return labTab;
    }

    /**
     * Retorna o n&uacute;mero de pe&ccedil;as de um jogador.
     *
     * @param jogador byte numero do jogador
     * @return n&uacute;mero de pe&ccedil;as
     */
    public int numPecas(byte jogador) {
        int liTot = 0;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (tab[i][j] == jogador) {
                    liTot++;
                }
            }
        }
        return liTot;
    }

    /**
     * Retorna um vetor contendo as jogadas possiveis de um jogador
     *
     * @param jogador jogador
     * @return jogadas possiveis
     */
    public List<Jogada> obtemJogadasPossiveis(byte jogador) {
        List<Jogada> lista = null;
        Jogada aux;

        for (int linha = 0; linha < DIM; linha++) {
            for (int coluna = 0; coluna < DIM; coluna++) {
                aux = new Jogada(-1, -1, linha, coluna);
                if (verifica(jogador, aux) > 0) {
                    if (lista == null) {
                        lista = new ArrayList<Jogada>(20);
                    }
                    lista.add(aux);
                }
            }
        }
        return lista;
    }

    /**
     * Retorna a melhor jogada
     *
     * @param jogador numero do jogador
     * @return melhor jogada
     */
    public Jogada obtemJogadaBoa(byte jogador) {
        List<Jogada> lista = null;

        if (lista == null) {
            lista = obtemJogadasPossiveis(jogador);
        }
        if (lista == null) {
            return null;
        }

        int tot = lista.size() - 1;
        TabuleiroOthelo auxTab = new TabuleiroOthelo();
        int maxval = Integer.MIN_VALUE;
        int ind = 0;
        for (int i = tot; i > -1; i--) {
            auxTab.copiaTab(tab);
            Jogada jogAux = lista.get(i);
            auxTab.move(jogador, jogAux);
            int val = auxTab.numPecas(jogador) - auxTab.numPecas(jogador == AZUL?VERM:AZUL);
            if (val > maxval) {
                maxval = val;
                ind = i;
            }
        }
        return lista.get(ind);
    }

    /**
     * Retorna a melhor jogada
     *
     * @param jogador numero do jogador
     * @return melhor jogada
     */
    public Jogada obtemJogadaHeuristica(byte jogador) {
        Jogada maxj = new Jogada(-1, -1, -1, -1);
        Jogada auxj = new Jogada(-1, -1, -1, -1);
        int valorMax = Integer.MIN_VALUE;
        int valor;
        byte tabAux[][] = new byte[DIM][DIM];
        for (int k = 5; k > -1; k--) {
            for (int linha = 0; linha < DIM; linha++) {
                for (int coluna = 0; coluna < DIM; coluna++) {
                    auxj.setJogada(-1, -1, linha, coluna);
                    if (verifica(jogador, auxj) > 0) {
                        this.copiaToTab(tabAux);
                        tabAux[linha][coluna] = jogador;
                        valor = heuristicaBasica(jogador, tabAux);
                        if (valor > valorMax) {
                            valorMax = valor;
                            maxj.setJogada(-1, -1, linha, coluna);
                        }
                    }
                }
            }
        }
        return maxj;
    }

    /**
     * Retorna um valor heuristico para o tabuleiro dado um jogador
     *
     * @param jogador numero do jogador
     * @param tab tabuleiro
     * @return valor do tabuleiro
     */
    public int heuristicaBasica(byte jogador, byte tab[][]) {
        int valor = 0;
 
        TabuleiroOthelo auxTab = new TabuleiroOthelo();

        auxTab.copiaTab(tab);
        valor = auxTab.numPecas(jogador) - auxTab.numPecas(jogador == AZUL?VERM:AZUL);
 
        
      //  imprimeTab(tab);
      //  System.out.println("valor do tabuleiro:" + valor + " -- para jogador:" + jogador);
        return valor;
    }

    /**
     * Executa um movimento. Retonar true se o movimento foi bem sucedido
     *
     * @param jogador jogador
     * @param jog jogada
     * @return true se o movimento eh valido.
     */
     public boolean move(byte jogador, Jogada jog) {
        byte lbTot = verifica(jogador, jog);
        if (lbTot > 0) {
            tab[jog.getLinha()][jog.getColuna()] = jogador;
            mudaPecas(jogador, jog.getLinha(), jog.getColuna());
            return true;
        }
        return false;
    }

    /**
     * Limpa o valor da jogada
     * @param jog jogada
     */
    public void limpa(Jogada jog) {
        tab[jog.getLinha()][jog.getColuna()] = LIVRE;
    }

    /**
     * Verifica se o jogo terminou
     * @return false = nao terminou; true = terminou
     */
    public boolean fimJogo() {

        if (obtemJogadasPossiveis(AZUL) != null ||
                obtemJogadasPossiveis(VERM) != null) {
            return false;
        }

        return true;
    }

    public String vencedorCor() {
        return cor[vencedorNum()];
    }

    /**
     * Verifica quem e o vencedor
     *
     * @return 0 = nao terminou; AZUL = venceu o jogador Azul; VERM = venceu o
     * jogador vermelho; -1 empate
     */
    public int vencedorNum() {
        byte numPecasAzul = 0, numPecasVerm = 0;

        if (obtemJogadasPossiveis(AZUL) != null ||
                obtemJogadasPossiveis(VERM) != null) {
            return 0;
        }

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (tab[i][j] == VERM) {
                    numPecasVerm++;
                }
                if (tab[i][j] == AZUL) {
                    numPecasAzul++;
                }
            }
        }
        if (numPecasAzul == numPecasVerm) {
            return EMPATE;
        }
        return numPecasAzul > numPecasVerm ? AZUL : VERM;

    }

    /**
     * Verifica se um movimento eh valido.
     *
     * @param jogador jogador
     * @param j Jogada
     * @return 0 se o movimento é inválido e maior que 0 movimento eh valido.
     */
    public byte verifica(byte jogador, Jogada j) {
        byte valid = 0;
        byte outro = 0;
        int i;
        int x = j.getLinha();
        int y = j.getColuna();
        if (x < 0 || y < 0 || x >= DIM || y >= DIM) {
            return 0;
        }
        if (tab[x][y] != LIVRE) {
            return 0;
        }

        outro = 0;	/* checa direcao negativa de coluna */
        for (i = x - 1; i >= 0; i--) {
            if (tab[i][y] == LIVRE) {
                break;
            }
            if (tab[i][y] != jogador) {
                outro++;
            }
            if ((tab[i][y] == jogador) && outro > 0) {
                valid += outro;
                break;
            }
            if (tab[i][y] == jogador) {
                break;
            }
        }

        outro = 0;	/* checa direcao negativa linha */
        for (i = y - 1; i >= 0; i--) {
            if (tab[x][i] == LIVRE) {
                break;
            }
            if (tab[x][i] != jogador) {
                outro++;
            }
            if (tab[x][i] == jogador && outro > 0) {
                valid += outro;
                break;
            }
            if (tab[x][i] == jogador) {
                break;
            }
        }

        outro = 0;	/* checa direcao positiva de X */
        for (i = x + 1; i < DIM; i++) {
            if (tab[i][y] == LIVRE) {
                break;
            }
            if (tab[i][y] != jogador) {
                outro++;
            }
            if (tab[i][y] == jogador && outro > 0) {
                valid += outro;
                break;
            }
            if (tab[i][y] == jogador) {
                break;
            }
        }

        outro = 0;	/* check direcao positiva aiY */
        for (i = y + 1; i < DIM; i++) {
            if (tab[x][i] == LIVRE) {
                break;
            }
            if (tab[x][i] != jogador) {
                outro++;
            }
            if (tab[x][i] == jogador && outro > 0) {
                valid += outro;
                break;
            }
            if (tab[x][i] == jogador) {
                break;
            }
        }
        outro = 0;	/* checa diagonal superior esquerda */
        for (i = 1; i < DIM; i++) {
            if (x - i < 0 || y - i < 0) {
                break;
            }
            if (tab[x - i][y - i] == LIVRE) {
                break;
            }
            if (tab[x - i][y - i] != jogador) {
                outro++;
            }
            if (tab[x - i][y - i] == jogador && outro > 0) {
                valid += outro;
                break;
            }
            if (tab[x - i][y - i] == jogador) {
                break;
            }
        }
        outro = 0;	/* checa diagonal superior direita */
        for (i = 1; i < DIM; i++) {
            if (x + i >= DIM || y - i < 0) {
                break;
            }
            if (tab[x + i][y - i] == LIVRE) {
                break;
            }
            if (tab[x + i][y - i] != jogador) {
                outro++;
            }
            if (tab[x + i][y - i] == jogador && outro > 0) {
                valid += outro;
                break;
            }
            if (tab[x + i][y - i] == jogador) {
                break;
            }
        }
        outro = 0;	/* checa diagonal inferior direita */
        for (i = 1; i < DIM; i++) {
            if (x + i >= DIM || y + i >= DIM) {
                break;
            }
            if (tab[x + i][y + i] == LIVRE) {
                break;
            }
            if (tab[x + i][y + i] != jogador) {
                outro++;
            }
            if (tab[x + i][y + i] == jogador && outro > 0) {
                valid += outro;
                break;
            }
            if (tab[x + i][y + i] == jogador) {
                break;
            }
        }
        outro = 0;	/* checa diagonal inferior esquerda */
        for (i = 1; i < DIM; i++) {
            if (x - i < 0 || y + i >= DIM) {
                break;
            }
            if (tab[x - i][y + i] == LIVRE) {
                break;
            }
            if (tab[x - i][y + i] != jogador) {
                outro++;
            }
            if (tab[x - i][y + i] == jogador && outro > 0) {
                valid += outro;
                break;
            }
            if (tab[x - i][y + i] == jogador) {
                break;
            }
        }
        return valid;
    }

    /**
     * Verifica se uma posicao esta fora do tabuleiro.
     *
     * @param linha linha
     * @param coluna coluna
     * @return true se saiu.
     */
    public boolean saiuTabuleiro(int linha, int coluna) {

        if (linha < 0 || coluna < 0 || linha > DIM - 1 || coluna > DIM - 1) {
            return true;
        }
        return false;
    }

   /**
     * Reverte as pecas capturadas
     * @param jogador jogador
     * @param coluna coordenada coluna
     * @param linha coordenada linha
     */
    public void mudaPecas(byte jogador, int coluna, int linha) {
        int i;

        /* reverte na direcao negativa de coluna  */
        for (i = coluna - 1; i >= 0; i--) {
            if (tab[i][linha] == LIVRE) {
                break;
            }
            if (tab[i][linha] == jogador) {
                for (; i < coluna; i++) {
                    tab[i][linha] = jogador;
                }
                break;
            }
        }

        /* reverte na direcao negativa linha  */
        for (i = linha - 1; i >= 0; i--) {
            if (tab[coluna][i] == LIVRE) {
                break;
            }
            if (tab[coluna][i] == jogador) {
                for (; i < linha; i++) {
                    tab[coluna][i] = jogador;
                }
                break;
            }
        }

        /* reverte na direcao positiva coluna  */
        for (i = coluna + 1; i < DIM; i++) {
            if (tab[i][linha] == LIVRE) {
                break;
            }
            if (tab[i][linha] == jogador) {
                for (; i > coluna; i--) {
                    tab[i][linha] = jogador;
                }
                break;
            }
        }

        /* reverte na direcao positive linha  */
        for (i = linha + 1; i < DIM; i++) {
            if (tab[coluna][i] == LIVRE) {
                break;
            }
            if (tab[coluna][i] == jogador) {
                for (; i > linha; i--) {
                    tab[coluna][i] = jogador;
                }
                break;
            }
        }

        /* reverte diagonal superior esquerda  */
        for (i = 1; i < DIM; i++) {
            if (coluna - i < 0 || linha - i < 0) {
                break;
            }
            if (tab[coluna - i][linha - i] == LIVRE) {
                break;
            }
            if (tab[coluna - i][linha - i] == jogador) {
                for (; i > 0; i--) {
                    tab[coluna - i][linha - i] = jogador;
                }
                break;
            }
        }

        /* reverte diagonal superior direita  */
        for (i = 1; i < DIM; i++) {
            if (coluna + i >= DIM || linha - i < 0) {
                break;
            }
            if (tab[coluna + i][linha - i] == LIVRE) {
                break;
            }
            if (tab[coluna + i][linha - i] == jogador) {
                for (; i > 0; i--) {
                    tab[coluna + i][linha - i] = jogador;
                }
                break;
            }
        }

        /* reverte diagonal inferior direita  */
        for (i = 1; i < DIM; i++) {
            if (coluna + i >= DIM || linha + i >= DIM) {
                break;
            }
            if (tab[coluna + i][linha + i] == LIVRE) {
                break;
            }
            if (tab[coluna + i][linha + i] == jogador) {
                for (; i > 0; i--) {
                    tab[coluna + i][linha + i] = jogador;
                }
                break;
            }
        }

        /* reverte inferior esquerda diagonal */
        for (i = 1; i < DIM; i++) {
            if (coluna - i < 0 || linha + i >= DIM) {
                break;
            }
            if (tab[coluna - i][linha + i] == LIVRE) {
                break;
            }
            if (tab[coluna - i][linha + i] == jogador) {
                for (; i > 0; i--) {
                    tab[coluna - i][linha + i] = jogador;
                }
                break;
            }
        }
    }

    /**
     * Retorna o tabuleiro na forma de String
     */
    public String toString() {
        StringBuffer loBuff = new StringBuffer("    ");
        for (int i = 0; i < DIM; i++) {
            loBuff.append(i).append("  ");
        }
        loBuff.append("\n");
        for (int linha = 0; linha < DIM; linha++) {
            loBuff.append(" " + linha);
            for (int coluna = 0; coluna < DIM; coluna++) {
                if (tab[linha][coluna] == VERM) {
                    loBuff.append("  V");
                } else if (tab[linha][coluna] == AZUL) {
                    loBuff.append("  A");
                } else {
                    loBuff.append(" " + tab[linha][coluna]);
                }
            }
            loBuff.append('\n');
        }
        return loBuff.toString();
    }

    public byte oponente(byte jogador) {
      
        if (jogador == Tabuleiro.AZUL) {
            return Tabuleiro.VERM;
        } else {
            return Tabuleiro.AZUL;
        }

    }
} // Fim da Classe Tabuleiro
