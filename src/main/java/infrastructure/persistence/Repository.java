package infrastructure.persistence;

import java.util.List;

public interface Repository<T> {
    void adiciona(T objeto);
    void atualiza(T cidade);
    void remove(T cidade);
    List<T> listaTodos();
    List<T> pesquisar(String textoDePesquisa);
}
