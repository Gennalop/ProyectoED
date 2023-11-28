/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Comparator;

/**
 *
 * @author Usuario
 */
public interface List<E> extends Iterable<E>{
    
    public int size();

    public boolean isEmpty();

    public boolean addFirst(E element);

    public boolean addLast(E element);

    public E removeFirst();

    public E removeLast();

    public boolean add(int index, E element);

    public E removeForIndex(int index);

    public E get(int index);

    public E set(int index, E element);    
    
    public E removeElement(Comparator<E> cmp, E element);  
    
}
