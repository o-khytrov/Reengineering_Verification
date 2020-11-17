
/**
 * Entry point of the app
 * @author Vyacheslav Moskalenko
 * @since JDK1.4
 */
public class SortTest {

    public static void main(String[] args) {

        FunList myList = new Cons(randInt());
        myList.Append(new Cons(randInt()));
        FunList cdr = myList.cdr();
        for (int i = 3; i <10; i++)
        {
            cdr.Append(new Cons(randInt()));
            cdr = cdr.cdr();
        }


        System.out.println("Original list of random integers: "+ myList.toString());
        int next = randInt();
        System.out.println("Next random int: "+next);
        myList.insertInOrder(next);
        System.out.println("List after insertion "+myList.toString());


    }
    public static int randInt() {
        return  randInt(1,50);
    }

    public static int randInt(int min, int max) {
        return  min + (int)(Math.random() * ((max - min) + 1));
    }
}
