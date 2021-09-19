/*
 * AEstrela.java
 *
 * Criada em 21 de Abril de 2007, 09:33
 *
 */


import java.util.List;

/**
 *
 * @author Alcione
 * @version 1.0 
 */
public interface AEstrela {
    /**
     * Recebe um quebra-cabeca e retorna uma lista de posições que representa os movimentos necessarios para chegar a solucao.
     * @param qc - Quebra-cabeca com o estado inicial
     * @return lista com os movimentos a serem realizados 
     */
    List<Posicao> getSolucao(QuebraCabeca qc);
}
