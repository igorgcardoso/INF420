package othelo;

import jogo.Jogador;
import tabuleiro.Jogada;
import tabuleiro.Tabuleiro;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogadorMinMaxImp extends Jogador implements JogadorMinMax{

    public final static int MAXTEMPO = 10000;
    final static int MAXNIVEL = 6;
    private Jogada jogada;

    public JogadorMinMaxImp(String[] args) {
        super(args);
    }

    @Override
    public Jogada calculaJogada(Tabuleiro tab, byte jogador) {
        Date tempo1 = new Date();
        Date tempo2 = null;
        long usado =0;
        for (int prof = 1; prof <= MAXNIVEL; prof++) {
            MinMaxTempl minMax = new MinMaxTempl(tab, jogador,  prof);
            minMax.setPai(this);
            minMax.start();

            try {
                minMax.join(MAXTEMPO-usado);
            } catch (InterruptedException ex) {
                Logger.getLogger(JogadorMinMaxTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
            tempo2 = new Date();
            usado = tempo2.getTime() - tempo1.getTime();
            if (usado >= MAXTEMPO){
                break;
            }
        }
        return jogada;
    }

    @Override
    public Jogada getJogada() {
        return this.jogada;
    }

    @Override
    public void setJogada(Jogada jogada) {
        this.jogada = jogada;
    }
}
