package com.company;

public class SortTest {

    public static void main(String[] args) {
/*
        FunList myList = new Cons(randInt());
        myList.Append(new Cons(randInt()));
        FunList cdr = myList.cdr();
        for (int i = 3; i <10; i++)
        {
            cdr.Append(new Cons(randInt()));
            cdr = cdr.cdr();
        }
        System.out.println(myList.toString());
        myList.sort();
        System.out.println(myList.toString());
*/
        FunList myList = new Cons(1);
        myList.Append(new Cons(2));
        FunList cdr= myList.cdr();

        cdr.Append(new Cons(8));
        cdr= cdr.cdr();

        cdr.Append(new Cons(12));
        cdr= cdr.cdr();

        cdr.Append(new Cons(5));
        cdr= cdr.cdr();

        cdr.Append(new Cons(3));
        cdr= cdr.cdr();

        System.out.println("Original list "+ myList.toString());
        myList.insertInOrder(6);
        System.out.println("List after insertion "+myList.toString());


    }
    public static int randInt() {
        return  randInt(1,50);
    }

    public static int randInt(int min, int max) {
        return  min + (int)(Math.random() * ((max - min) + 1));
    }
}
