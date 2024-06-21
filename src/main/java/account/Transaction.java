package account;

class Transaction {
    public String reference;
    private double amount;

    public Transaction(String reference, double amount) {
        this.reference = reference;
        this.amount = amount;
    }
}
