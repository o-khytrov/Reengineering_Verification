public class Customer {

    private final String name;
    private String surname;
    private final String email;
    private final CustomerType customerType;
    final Account account;
    private double companyOverdraftDiscount = 1;

    public Customer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
    }

    // use only to create companies
    public Customer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.name = name;
        this.email = email;
        this.customerType = CustomerType.COMPANY;
        this.account = account;
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    public void withdraw(double sum, String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        double discount = getDiscount();
        account.withdraw(sum, discount);
    }

    private double getDiscount() {
        if (customerType == CustomerType.PERSON) return 1;
        else return (account.getType() == AccountType.premium) ? companyOverdraftDiscount/2 : companyOverdraftDiscount;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String printCustomerDaysOverdrawn() {
        String fullName = getFullName();

        String accountDescription = "Account: IBAN: " + account.getIban() + ", Days Overdrawn: " + account.getDaysOverdrawn();
        return fullName + accountDescription;
    }

    private String getFullName() {
        return name + " " + surname + " ";
    }

    public String printCustomerMoney() {
        String fullName = getFullName();

        String accountDescription = account.getDescription();
        return fullName + accountDescription;
    }


    public String printCustomer() {
        return getName() + " " + getEmail();
    }
}
