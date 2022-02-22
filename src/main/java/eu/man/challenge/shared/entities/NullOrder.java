package eu.man.challenge.shared.entities;

import eu.man.challenge.shared.entities.OrderEntity;

public class NullOrder extends OrderEntity {
    @Override
    public boolean isNull() {
        return true;
    }
}
