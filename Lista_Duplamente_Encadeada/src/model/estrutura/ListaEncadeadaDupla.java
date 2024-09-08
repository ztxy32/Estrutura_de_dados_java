package model.estrutura;

import java.lang.Exception;
import model.estrutura.No;

public class ListaEncadeadaDupla<T> {
    private No<T> inicio = null;
    private No<T> fim = null;

    //append    Adiciona o elemento no final
    //get       Retorna um dado no index
    //index     Retorna o indice de um elemento
    //insert    insere um elemento em uma determinada posiçãp
    //last      retorna o ultimo elemento
    //prepend   insere um elemento na cabeça da lista
    //remove	remove um elemento dado o index
    //total 	retorna o total de elementos

    public void append(T elemento){
        No<T> buffer = new No<>(elemento);
        if(this.fim == null){
            this.fim = buffer;
            this.inicio = buffer;
        }else{
            No<T> ex_ultimo = this.fim;
            ex_ultimo.setProximo(buffer);
            buffer.setAnterior(ex_ultimo);
            this.fim = buffer;
        }
    }

    public No<T> get(int index) throws IllegalArgumentException{
        int i = 0;
        if(this.inicio == null)
        throw new IllegalArgumentException("Não existe item na lista");

        No<T> buffer = this.inicio;
        for(i = 0; i < index; i++){
            if( buffer.getProximo() == null)
                break;
            buffer = buffer.getProximo();
        }
        if(i < index)
            throw new IllegalArgumentException("O indice informado não existe");

            return buffer;
    }

    public int index(T elemento) throws IllegalArgumentException {
        if(this.inicio == null)
        throw new IllegalArgumentException("Não existe item na lista");

        int index = 0;
        if(this.inicio.getValor() == elemento)
            return index;

        No<T> buffer = this.inicio;
        do{
            if(buffer.getValor() == elemento){
                return index;
            }
            buffer = buffer.getProximo();
            index++;

        }while(buffer != null);
        throw new IllegalStateException("item não encontrado");

    }

    public void insert(int index, T elemento) throws IllegalArgumentException{
        if(index == 0){
            No<T> novo = new No<>(elemento);
            if(this.inicio == null){
                this.inicio = novo;
                this.fim = novo;
            }else{
                No<T> ex_inicio = this.inicio;
                novo.setProximo(ex_inicio);
                this.inicio = novo;

                ex_inicio.setAnterior(novo);
            }
        }else{
            this.insert(this.get(--index), elemento);
        }
    }

    public void insert(No<T> item, T elemento) throws IllegalArgumentException{
        No<T> novo = new No<>(elemento);
        No<T> proximo = item.getProximo();
        item.setProximo(novo);
        novo.setProximo(proximo);
        proximo.setAnterior(novo);
        novo.setAnterior(item);

    }

    public void prepend(T elemento){
        No<T> buffer = new No<>(elemento);
        if(this.fim == null){
            this.fim = buffer;
            this.inicio = buffer;
        }else{
            No<T> ex_primeiro = this.inicio;
            this.inicio = buffer;
            buffer.setProximo(ex_primeiro);
            ex_primeiro.setAnterior(buffer);
        }
    }

    public void remove(int index){
        if(index == 0){
            this.inicio.setValor(null);
            if(this.inicio.getProximo() == null){
                this.inicio = null;
                this.fim = null;
            }else{
                No<T> buffer = this.inicio.getProximo();
                No<T> anterior = this.inicio.getAnterior();
                this.inicio.setProximo(null);
                this.inicio = buffer;

            }
            return;
        }
        No<T> anterior = this.get(index - 1);
        No<T> item = anterior.getProximo();
        No<T> proximo = item.getProximo();
        anterior.setProximo(proximo);
        item.setProximo(null);
        item.setValor(null);
    }

    public int total(){
        if(this.inicio == null)
            return 0;
        No<T> buffer = this.inicio;
        int total_elementos = 0;
        
        do{
            total_elementos++;
            buffer = buffer.getProximo();
        }while(buffer != null);
        return total_elementos;
    }

    @Override
    public String toString(){
        if(this.inicio == null){
            return "[]";
        }

        StringBuilder builder = new StringBuilder("[");
        No<T> buffer = this.inicio;
        builder.append(buffer.getValor());
        while(buffer.getProximo() != null){
            builder.append(",");
            buffer = buffer.getProximo();
            builder.append(buffer.getValor());
        }
        builder.append("]");
        return builder.toString();
    }
}