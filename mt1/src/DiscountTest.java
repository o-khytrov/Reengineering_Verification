import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DiscountTest {
    private static final int[] Q_VALUES = {1, 9, 10, 19};
    private static final Item.Type[] T_VALUES =
            {
                    Item.Type.SECOND,
                    Item.Type.REGULAR,
                    Item.Type.SALE,
                    Item.Type.DISCOUNT
            };
    private static final int[][] D_VALUES
            =
            {
                    {0,50},
                    {0, 0, 0, 0},
                    {80},
                    {10, 10, 20, 20}
            };

    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static Collection getTypeQuantityPairs()
    {
            Collection pairs = new ArrayList();
            for (int q = 0; q < Q_VALUES.length;q++)
                    for(int t = 0; t < T_VALUES.length;t++)
                            pairs.add(new Object[]{
                                    T_VALUES[t],
                                    Q_VALUES[q],
                                    (D_VALUES[t].length>q)? D_VALUES[t][q]:
                                            D_VALUES[t][D_VALUES[t].length-1]
                            });

                    return  pairs;

    }

    private Item.Type _type;
    private int _quantity;
    private int _discount;

    public DiscountTest(Item.Type type, int quantity, int discount)
    {
            _type = type;
            _quantity = quantity;
            _discount = discount;
    }

    @Test
    public void discountTest()
    {
            var item = new Item();
            item.setPrice(5.99f);
            item.setQuantity(_quantity);
            item.setTitle("Title");
            item.setType(_type);

         assertEquals("type:"+_type + ", quantity:"+_quantity,
                 _discount, ShoppingCart.calculateDiscount(
                 item
         ),0.01f);
    }


}
