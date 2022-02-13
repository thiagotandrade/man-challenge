package eu.man.challenge.modules.orders.infra.entities;

public class NullOrder extends OrderEntity {
    @Override
    public boolean isNull() {
        return true;
    }
}
