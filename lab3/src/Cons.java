package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Mimics fundamental function cons for constructs
 * memory objects which hold two values or pointers to values
 *
 * @author Vyacheslav Moskalenko
 * @since JDK1.4
 */
public class Cons extends FunList {

    private int _dat;
    private FunList _cdr;

    public Cons(int carDat, FunList cdr) {
        _dat = carDat;
        _cdr = cdr;
    }

    /**
     * @param i a left side (head) of the list.
     */
    public Cons(int i) {
        _dat = i;
        _cdr = Empty.UniqueInstance();
    }

    /**
     * @return the first int in the list object
     */
    public int car() {
        return _dat;
    }

    /**
     * @return the tail (all but the first element) of the list object
     */
    public FunList cdr() {
        return _cdr;
    }

    /**
     * @return a String description of the list object
     */
    String toStringHelp() {
        return " " + _dat + _cdr.toStringHelp();
    }

    @Override
    void insertInOrder(int i) {

        this.sort();

        FunList current = this;
        while (true)
        {
            if (current.cdr() instanceof  Empty)
                break;;

                if (current.car() <= i && current.cdr().car() >=i)
                {
                    FunList tale = current.cdr();
                    current.Append(new Cons(i));
                    current.cdr().Append(tale);
                    return;
                }
            current = current.cdr();

        }
        current.Append(new Cons(i));

    }

    @Override
    public void sort() {

        List<Integer> list = new ArrayList<Integer>();
        FunList current = this;
        while (true)
        {
            if (current instanceof  Empty)
                break;;

            list.add(current.car());
            current = current.cdr();

        }
        Collections.sort(list);

        this._dat = list.get(0);
        current =this;
        for (int i = 1; i  < list.size();i++)
        {
            current.Append(new Cons(list.get(i)));
            current = current.cdr();
        }
    }


    public FunList Append(FunList other) {
        this._cdr = other;
        return this;
    }
}