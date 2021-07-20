package br.com.igrejaidef.Idef.dto.mapper;

import java.util.List;

public interface Mapper <E,M>{
    E toEntidade(M modelo) throws Exception;

    M toModelo(E entidade) throws Exception;

    List<E> toListaDeEntidades(Iterable<M> modelos) throws Exception;

    List<M> toListaDeModelos(Iterable<E> entidades) throws Exception;
}
