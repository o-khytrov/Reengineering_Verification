package com.company;

/**
 * Mimics functional lists.
 * @author Moskalenko Vyacheslav
 * @since JDK1.4
 */
public abstract class FunList {
    
    public abstract int car();
    
    public abstract FunList cdr();

    public abstract FunList Append(FunList other);
    
    /**
     * NOTE: toString () method is NOT abstract. It calls, toStringHelp() , an abstract method.
     * It represents what we call an "invariant" behavior for <code>FunList</code>.
     * It is an example of the "Template Method Pattern". A "template method" is a method that
     * makes calls to at least one abstract method in its own class.
     */
    public String toString(){
        return "(" + toStringHelp() + " ) ";
    }
    
    abstract String toStringHelp();

    abstract void insertInOrder(int i);

    abstract void sort();
}