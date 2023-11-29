package util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayList<E> implements List<E>, Serializable{
    
    private E[] elements;
    private int CAPACITY = 100;
    private int effectiveSize;
    
    public ArrayList () {
        elements = (E[]) new Object[CAPACITY];
        effectiveSize = 0;
    }
    
    private boolean isFull() {
        return effectiveSize == CAPACITY;
    }

    private void addCapacity() {
        CAPACITY *= 2;
        E[] newElements = (E[]) new Object[CAPACITY];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } 
        if (isEmpty()) {
            elements[effectiveSize] = element;
            effectiveSize++;

            return true;
        }
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize; i > 0; i--) {
            elements[i] = elements[i - 1];
        }
        elements[0] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        elements[effectiveSize++] = element;
        return true;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E e = elements[0];
        for (int i = 0; i < effectiveSize; i++) {
            elements[i] = elements[i + 1];
        }

        elements[effectiveSize] = null;
        effectiveSize--;
        return e;
    }

    @Override
    public E removeLast() {
        if(isEmpty())
            return null;
        E e = elements[effectiveSize - 1];
        elements[effectiveSize - 1] = null;
        effectiveSize--;
        return e;
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index > effectiveSize) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public E removeForIndex(int index) {
        if(this.isEmpty()){
            return null;
        } 
        E e = elements[index];
        for(int i = index; i < effectiveSize; i++){
            elements[i] = elements[i + 1];
        }
        effectiveSize--;
        return e;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= effectiveSize) {
            return null;
        }
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (element == null || index < 0 || index >= effectiveSize) {
            return null;
        }

        E i = elements[index];
        elements[index] = element;
        return i;
    }

    @Override
    public E removeElement(Comparator<E> cmp, E element) {
        for(int i = 0; i < effectiveSize; i++){
            if (cmp.compare(elements[i], element) == 0)
                return this.removeForIndex(i);
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                E element = elements[cursor];
                cursor++;
                return element;
            }
        };
        return it;
    }

}
