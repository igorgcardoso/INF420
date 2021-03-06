/*
 * QuebraCabeca.java
 *
 * Created on 19 de Abril de 2007, 15:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


import java.util.List;

/**
 *
 * @author alcione
 */
public interface QuebraCabeca {
    int VAZIO = -1;
    
    /**
     * Mï¿½todo que retorna uma copia do array interno de posicoes (-1 = vazio)
     * @return int[][] - um array 3x3 de inteiros
     */
    int[][] getTab();
    
    /**
     * Metodo define o arranjo de pecas segundo o array passado por parametro
     * @param  aux - um array 3x3 de inteiros (-1 = vazio)
     * @throws Exception se o array nao e compativel
     */
    void setTab(int[][] aux) throws Exception;
    
    /**
     * Move o vazio da posicao linha1, col1 para linha2,  col2
     * @param  linha1 - linha do vazio
     * @param  col1 - coluna do vazio
     * @param  linha2 - linha do destino
     * @param  col2 - coluna do destino
     * @throws Exception se a posição é invalida
     */
    void move(int linha1, int col1, int linha2, int col2) throws Exception;
    
    /**
     * Verifica se Quebra-Cabeca esta' ordenado
     * @return true se esta' ordenado
     */
    boolean isOrdenado();
    
    /**
     * retorna a posicao do vazio
     * @return objeto Posicao com as coordenadas da posicao vazia
     */
    Posicao getPosVazio();

    /**
     * retorna o valor do quebra-cabeca segundo uma heuristica (implementada a distancia quarterao)
     * @return valor do quebra-cabeca
     */
    int getValor();

    /**
     * retorna uma lista de posições de movimentos possiveis
     * @return Lista de posicoes
     */
    List<Posicao> getMovePossiveis();
    
    /**
     * retorna o Quebra-cabeca na forma de String para a impressao
     * @return quebra-cabeca na forma de String
     */
    String toString();
    
    /**
     * Verifica se dois quebra-cabecas são iguais
     * @param qc - quebra-cabeça
     * @return true se são iguas
     */
    boolean equals(QuebraCabeca qc);
    
    /**
     * Retorna um codigo hash para este quebra-cabeca
     * @return codigo hash
     */
    int hashCode();
    
}
