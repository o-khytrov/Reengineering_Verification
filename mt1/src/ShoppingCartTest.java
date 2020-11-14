import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart cart;

    @Before
    public void CreateCart() {
        cart = new ShoppingCart();
    }

    @Test
    void addItemItemNameCanNotBeEmpty() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> cart.addItem("", 0.99, 5, Item.Type.REGULAR));
        assertEquals(exception.getMessage(), "Illegal title");
    }

    @Test
    void addItemTooLongItemNameNotValid() {

        String longItemName = new String(new char[33]).replace('\0', ' ');
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> cart.addItem(longItemName, 0.99, 5, Item.Type.REGULAR));
        assertEquals(exception.getMessage(), "Illegal title");
    }

    @Test
    void addItemPriceLessThanZeroNotValid() {

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> cart.addItem("IPhone", -150, 5, Item.Type.REGULAR));
        assertEquals(exception.getMessage(), "Illegal price");
    }

    @Test
    void addItemPriceGreaterThanThousandNotValid() {

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> cart.addItem("IPhone", 10000, 5, Item.Type.REGULAR));
        assertEquals(exception.getMessage(), "Illegal price");
    }

    @Test
    void addItemQuantityLessThanZeroNotValid() {

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> cart.addItem("IPhone", 100, -1, Item.Type.REGULAR));
        assertEquals(exception.getMessage(), "Illegal quantity");
    }

    @Test
    void addItemQuantityGreaterThanThousandNotValid() {

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> cart.addItem("IPhone", 100, 1020, Item.Type.REGULAR));
        assertEquals(exception.getMessage(), "Illegal quantity");
    }


    @Test
    void addItemItemTypeMustBeValid() {

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            for (int i = 0; i < 100; i++)
                cart.addItem("IPhone", 100, 10, Item.Type.DISCOUNT);
        });
        assertEquals(exception.getMessage(), "No more space in cart");
    }
}