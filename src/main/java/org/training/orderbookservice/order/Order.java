package org.training.orderbookservice.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.training.orderbookservice.limit.Limit;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private int idNumber;
    private boolean buyOrSell;
    private int shares;
    private int limitPrice;
    private Limit limit;
    private int entryTime;
    private int eventTime;
    private Order prevOrder;
    private Order nextOrder;

    public void reset() {
        this.nextOrder = null;
        this.prevOrder = null;
    }
}
