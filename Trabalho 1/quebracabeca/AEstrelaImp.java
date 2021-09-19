import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AEstrelaImp implements AEstrela {

    public AEstrelaImp() {
    }

    @Override
    public List<Posicao> getSolucao(QuebraCabeca qc) {
        QuebraCabeca initial = qc;
        List<Posicao> movimentos = new ArrayList<>();
        List<QuebraCabeca> previous_states = new ArrayList<>();
        while (!qc.isOrdenado()) {
            List<Posicao> poss_moves = qc.getMovePossiveis();
            List<QuebraCabeca> arvore = new ArrayList<>();
            for (Posicao pos:poss_moves) {
                boolean add = true;
                QuebraCabeca nqc = new QuebraCabecaImp();
                try {
                    nqc.setTab(qc.getTab());
                    Posicao vazio = nqc.getPosVazio();
                    nqc.move(vazio.getLinha(), vazio.getColuna(), pos.getLinha(), pos.getColuna());
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
                for (QuebraCabeca q:previous_states) {
                    if (initial.equals(nqc)) {
                        break;
                    }
                    if (q.equals(nqc)) {
                        add = false;
                        break;
                    }
                }
                if (add) {
                    arvore.add(nqc);
                }
            }
            if (arvore.size() == 0) {
                previous_states.clear();
                continue;
            }
            arvore.sort(Comparator.comparingInt(q -> q.getValor() + movimentos.size()));
            qc = arvore.get(0);
            Posicao vazio = qc.getPosVazio();
            for (Posicao pos:poss_moves) {
                if (vazio.getLinha() == pos.getLinha() && vazio.getColuna() == pos.getColuna()) {
                    movimentos.add(pos);
                    break;
                }
            }
            previous_states.add(qc);
        }
        return movimentos;
    }

}