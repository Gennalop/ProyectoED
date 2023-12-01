/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class LinkedList<E> implements List<E>, Serializable{
    
    private Node<E> last;

    @Override
    public int size() {
        int contador = 1;
        for(Node<E> n = last.getNext(); n != last; n = n.getNext()){
            contador++;
        }
        return contador;
    }

    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> n = new Node<>(element);
        if (element == null)
            return false;
        if(this.isEmpty()){
            last = n;
            return true;
        } else{
            last.getNext().setPrevious(n);
            n.setNext(last.getNext());
            n.setPrevious(last);
            last.setNext(n);
            return true;
        }
    }

    @Override
    public boolean addLast(E element) {
        Node<E> n = new Node<>(element);
        if (element == null)
            return false;
        if(this.isEmpty()){
            last = n;
            return true;
        } else{
            last.getNext().setPrevious(n);        
            n.setNext(last.getNext());
            n.setPrevious(last);
            last.setNext(n);
            last = n;
            return true;
        }        
    }

    @Override
    public E removeFirst() {
        Node<E> tmp = last.getNext();
        last.setNext(tmp.getNext());
        tmp.getNext().setPrevious(last);
        return tmp.getContent();
    }

    @Override
    public E removeLast() {
        Node<E> tmp = last;
        last.getPrevious().setNext(last.getNext());
        last.getNext().setPrevious(last.getPrevious());
        last = tmp.getPrevious();
        return tmp.getContent();
    }

    @Override
    public boolean add(int index, E element) {
        if (index == 0 || this.isEmpty()){
            this.addFirst(element);
            return true;
        }    
        if (index == this.size() - 1){
            this.addLast(element);
            return true;
        }
        if (index >= this.size())
            return false;
        Node<E> tmp = last.getNext();
        for (int i = 0; i != index - 1; i++){
            tmp = tmp.getNext();
        }
        Node<E> n = new Node<>(element);
        n.setNext(tmp.getNext());
        tmp.getNext().setPrevious(n);
        n.setPrevious(tmp.getPrevious().getNext());
        tmp.setNext(n);
        return true;
    }

    @Override
    public E removeForIndex(int index) {
       if (index == 0){
           Node<E> tmp = last.getNext();
           this.removeFirst();
           return tmp.getContent();
       }
       if (index == this.size() - 1){
           Node<E> tmp = last;
           this.removeLast();
           return last.getContent();
       }
       Node<E> tmp = last.getNext();
       for (int i = 0; i != index; i++){
           tmp = tmp.getNext();
       }
       tmp.getPrevious().setNext(tmp.getNext());
       tmp.getNext().setPrevious(tmp.getPrevious());
       return tmp.getContent();
    }

    @Override
    public E get(int index) {
        Node<E> tmp = last.getNext();
        for (int i = 0; i != index; i++){
            tmp = tmp.getNext();
        }
        return tmp.getContent();
    }

    @Override
    public E set(int index, E element) {
        if (index == 0){
            E e = last.getNext().getContent();
            last.getNext().setContent(element);
            return e;
        }if (index > this.size()){
            return null;
        }else{
            Node<E> tmp = last.getNext();
            E e = tmp.getContent();
            for (int i = 0; i != index - 1; i++){
                tmp = tmp.getNext();
            }
            tmp.getNext().setContent(element);
            return e;
        }
    }
    
    @Override
    public E removeElement(Comparator<E> cmp, E element) {
        Node<E> tmp = last.getNext();
        for (int i = 0; i != this.size(); i++){
            if (cmp.compare(tmp.getContent(), element) == 0){
                return this.removeForIndex(i);
            }
            tmp = tmp.getNext();
        }
        return null;
    }  
    
    //este puede ser usado como for each. Es un iterador normal 
    @Override
    public Iterator<E> iterator(){
        if (this.isEmpty()) return null;
        Iterator<E> it = new Iterator<>(){
            Node<E> cursor = last.getNext();
            boolean esPrimero = true;
            @Override
            public boolean hasNext() {
                return esPrimero || cursor != last.getNext();
            }

            @Override
            public E next() {
                E e = cursor.getContent();
                cursor = cursor.getNext();
                esPrimero = false;
                return e;
            }
            
        };
        return it;
    }
    
    public Iterator<E> reverseIterator(){
        Iterator<E> itAux = this.iterator();
        LinkedList<E> lAux = new LinkedList<>();
        
        while (itAux.hasNext()){
            lAux.addFirst(itAux.next());
        }
        
        Iterator<E> it = new Iterator<>(){
            Node<E> cursor = lAux.last.getNext();
            boolean esPrimero = true;
            
            @Override
            public boolean hasNext() {
                return esPrimero || cursor != last.getNext();
            }

            @Override
            public E next() {
                E e = cursor.getContent();
                cursor = cursor.getNext();
                esPrimero = false;
                return e;
            }
        
        };
        return it;
    }
}
