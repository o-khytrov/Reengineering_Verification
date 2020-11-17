
/**
 * Mimics functional empty list.
 * @author Oleksandr Khytrov
 * @since JDK1.4
 */
public class Empty extends FunList
{

    /**
     * Singleton instance
     */
    private static Empty _instance = new Empty();

    /**
     * private constructor
     */
    private Empty()
    {

    }
    /**
     * Returns a singleton instance
     * @return Empty
     */
    public static   Empty UniqueInstance()
    {
        return  _instance;
    }


    public int car(){
        throw new java.util.NoSuchElementException("car requires a non Empty Funlist");
    }

    /**
     *
     * @return FunList
     */
    public FunList cdr(){
        throw new java.util.NoSuchElementException("cdr requires a non Empty Funlist");
    }

    /**
     *
     * @return string representation
     */
    String toStringHelp(){
        return "";
    }

    @Override
    void insertInOrder(int i) {

    }

    @Override
    void sort() {

    }

    /**
     *
     * @param other next note to append
     * @return current node
     */
    public FunList Append(FunList other)
    {
            return  this;
    }
}

