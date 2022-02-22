package eu.man.challenge.shared.entities;

public class NullOrder extends Order {
    @Override
    public boolean isNull() {
        return true;
    }
}
