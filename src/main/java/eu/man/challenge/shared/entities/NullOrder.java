package eu.man.challenge.shared.entities;

public class NullOrder extends Order {
    public NullOrder() {
        super();
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
