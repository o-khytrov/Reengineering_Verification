import java.util.*;
import java.text.*;
import java.util.function.Function;

public class ShoppingCart {
    public static enum ItemType {NEW, REGULAR, SECOND_FREE, SALE}

    ;

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 0.99, 5, ItemType.NEW);
        cart.addItem("Banana", 20.00, 4, ItemType.SECOND_FREE);
        cart.addItem("A long piece of toilet paper", 17.20, 1, ItemType.SALE);
        cart.addItem("Nails", 2.00, 500, ItemType.REGULAR);
        System.out.println(cart.formatTicket());
    }


    public void addItem(String title, double price, int quantity, ItemType type) {
        if (title == null || title.length() == 0 || title.length() > 32)
            throw new IllegalArgumentException("Illegal title");
        if (price < 0.01)
            throw new IllegalArgumentException("Illegal price");
        if (quantity <= 0)
            throw new IllegalArgumentException("Illegal quantity");

        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setType(type);
        items.add(item);

    }

    public String formatTicket() {
        double total = calculateItemsParameters();
        return getFormattedTicketTable(total);

    }

    private String getFormattedTicketTable(double total) {
        if (items.size() == 0)
            return "No items.";


        String[] header = {"#", "Item", "Price", "Quan.", "Discount", "Total"};
        int[] align = new int[]{1, -1, 1, 1, 1, 1};
// formatting each line
        int index = 0;
        List<String[]> lines = convertItemsToTableLines();
        String[] footer = {String.valueOf(lines.size()), "", "", "", "",
                MONEY.format(total)};
// formatting table
// column max length
        int[] width = new int[]{0, 0, 0, 0, 0, 0};
        for (String[] line : lines) {
            adjustColumnWidth(width, line);
        }
        adjustColumnWidth(width, header);
        adjustColumnWidth(width, footer);
// line length
        int lineLength = width.length - 1;
        for (int w : width)
            lineLength += w;
        StringBuilder sb = new StringBuilder();
// header
        appendFormattedLine(sb, header, align, width, true);
// separator
        appendSeparator(lineLength, sb);
// lines
        for (String[] line : lines) {
            appendFormattedLine(sb, line, align, width, false);
        }
        if (lines.size() > 0) {
            appendSeparator(lineLength, sb);
        }

        // footer
        appendFormattedLine(sb, footer, align, width, false);
        return sb.toString();
    }

    private List<String[]> convertItemsToTableLines() {
        List<String[]> lines = new ArrayList<String[]>();
        int index = 0;
        for (Item item : items) {

            lines.add(new String[]{
                    String.valueOf(++index),
                    item.getTitle(),
                    MONEY.format(item.getPrice()),
                    String.valueOf(item.getQuantity()),
                    (item.getDiscount() == 0) ? "-" : (String.valueOf(item.getDiscount()) + "%"),
                    MONEY.format(item.getTotalPrice())
            });

        }
        return lines;
    }

    private double calculateItemsParameters() {
        double total = 0.00;

        for (Item item : items) {
            item.setDiscount(calculateDiscount(item.getType(), item.getQuantity()));
            item.setTotalPrice(item.getPrice() * item.getQuantity() * (100.00 - item.getDiscount()) / 100.00);
            total += item.getTotalPrice();
        }
        return total;
    }

    private void appendFormattedLine(StringBuilder sb, String[] lines, int[] align, int[] width, boolean newLine) {
        for (int i = 0; i < lines.length; i++)
            appendFormatted(sb, lines[i], align[i], width[i]);
        sb.append("\n");
    }

    private void adjustColumnWidth(int[] width, String[] columns) {
        for (int i = 0; i < columns.length; i++)
            width[i] = (int) Math.max(width[i], columns[i].length());
    }

    private void appendSeparator(int lineLength, StringBuilder sb) {
        // separator
        for (int i = 0; i < lineLength; i++)
            sb.append("-");
        sb.append("\n");
    }


    // --- private section ----------------------------------------------------
    private static final NumberFormat MONEY;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        MONEY = new DecimalFormat("$#.00", symbols);

    }


    public static void appendFormatted(StringBuilder sb, String value, int align, int width) {
        if (value.length() > width)
            value = value.substring(0, width);
        int before = (align == 0)
                ? (width - value.length()) / 2
                : (align == -1) ? 0 : width - value.length();
        int after = width - value.length() - before;
        while (before-- > 0)
            sb.append(" ");
        sb.append(value);
        while (after-- > 0)
            sb.append(" ");
        sb.append(" ");
    }

    /**
     * Calculates item's discount.
     * For NEW item discount is 0%;
     * For SECOND_FREE item discount is 50% if quantity > 1
     * For SALE item discount is 70%
     * For each full 10 not NEW items item gets additional 1% discount,
     * but not more than 80% total
     */

    public static int calculateDiscount(ItemType type, int quantity) {

        int discount = 0;
        switch (type) {
            case NEW:
                return 0;
            case REGULAR:
                discount = 0;
                break;
            case SECOND_FREE:
                if (quantity > 1)
                    discount = 50;
                break;
            case SALE:
                discount = 70;
                break;

        }
        if (discount < 80) {
            discount += quantity / 10;
            if (discount > 80)
                discount = 80;
        }
        return discount;
    }

    private static class Item {
        private String title;
        private double price;
        private int quantity;
        private ItemType type;
        private int discount;
        private double total;

        public String getTitle() {
            return title;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public ItemType getType() {
            return type;
        }

        public int getDiscount() {
            return discount;
        }

        public double getTotalPrice() {
            return total;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setType(ItemType type) {
            this.type = type;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public void setTotalPrice(double total) {
            this.total = total;
        }
    }

    private List<Item> items = new ArrayList<Item>();
}