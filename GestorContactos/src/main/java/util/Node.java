/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Usuario
 */
public class Node<E> {
    
    private Node<E> next;
    private Node<E> previous;
    private E content;

    public Node(E content) {
        this.next = this;
        this.previous = this;
        this.content = content;
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public E getContent() {
        return content;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }

    public void setContent(E content) {
        this.content = content;
    }
    
}
