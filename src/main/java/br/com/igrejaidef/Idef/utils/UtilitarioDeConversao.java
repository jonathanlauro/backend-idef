package br.com.igrejaidef.Idef.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class UtilitarioDeConversao {
    public <F, T> T converter(F item, FuncaoDeConversao<F, T> funcaoDeConversao) throws Exception {
        return isNull(item) ? null : funcaoDeConversao.apply(item);
    }
    public <F, T> List<T> converterColecao(Iterable<F> lista, FuncaoDeConversao<F, T> funcaoDeConversao) throws Exception {
        if (nonNull(lista)) {
            List<T> listaDeRetorno = new ArrayList<>();
            for (F item : lista) {
                if (nonNull(item)) {
                    listaDeRetorno.add(funcaoDeConversao.apply(item));
                }
            }
            return listaDeRetorno;
        }
        return emptyList();
    }
}
