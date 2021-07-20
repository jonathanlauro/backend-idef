package br.com.igrejaidef.Idef.utils;

public interface FuncaoDeConversao <F,T>{
    T apply(F from) throws Exception;
}

