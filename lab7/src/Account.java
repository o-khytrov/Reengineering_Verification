public class Account {

    private String iban;

    private final AccountType type;

    private final int daysOverdrawn;

    private double money;

    private Currency currency;

    Customer customer;

    public Account(AccountType type, int daysOverdrawn) {
        super();
        this.type = type;
        this.daysOverdrawn = daysOverdrawn;
    }

    public double bankcharge() {
        double result = 4.5;

        result += overdraftCharge();

        return result;
    }

    private double overdraftCharge() {
        if (type == AccountType.premium) {
            double result = 10;
            if (getDaysOverdrawn() > 7)
                result += (getDaysOverdrawn() - 7) * 1.0;
            return result;
        } else
            return getDaysOverdrawn() * 1.75;
    }

    public double overdraftFee() {
        if (type == AccountType.premium) {
            return 0.10;
        } else {
            return 0.20;
        }
    }


    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getType() {
        return type;
    }

    public String getCurrency() {
        return currency.getName();
    }

    public void setCurrency(String currency) {
        this.currency = new Currency();
        this.currency.setName(currency);
    }

    public String print(Customer customer) {
        return "Account: IBAN: " + getIban() + ", Money: "
                + getMoney() + ", Account type: " + getType();
    }

    void withdraw(double sum, double discount) {
        setMoney((getMoney() - sum) - calculateOverdraft(sum, discount));

    }

    private double calculateOverdraft(double sum, double discount) {
        if (getMoney() > 0)
            return 0;
        return sum * overdraftFee() * discount;
    }

    String getDescription() {
        return "Account: IBAN: " + getIban() + ", Money: " + getMoney();
    }
}
