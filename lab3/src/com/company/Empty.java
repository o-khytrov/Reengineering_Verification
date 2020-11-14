package com.company;

/**
 * Mimics functional empty list.
 * @author Vyacheslav Moskalenko
 * @since JDK1.4
 */
public class Empty extends FunList
{

    private static Empty _instance = new Empty();
    private Empty()
    {

    }
    public static   Empty UniqueInstance()
    {
        return  _instance;
    }
    public int car(){
        throw new java.util.NoSuchElementException("car requires a non Empty Funlist");
    }
    
    public FunList cdr(){
        throw new java.util.NoSuchElementException("cdr requires a non Empty Funlist");
    }
    
    String toStringHelp(){
        return "";
    }

    @Override
    void insertInOrder(int i) {

    }

    @Override
    void sort() {

    }

    public FunList Append(FunList other)
    {
            return  this;
    }
}

