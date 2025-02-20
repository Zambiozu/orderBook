package org.training.orderbookservice.limit;

import lombok.Getter;
import lombok.Setter;
import org.training.orderbookservice.order.Order;

@Setter
@Getter
public class Limit implements Comparable<Limit> {
    private int limitPrice;
    private int size;
    private int totalVolume;
    private Order headOrder;
    private Order tailOrder;

    public Limit(int limitPrice) {
        this.size = 0;
        this.limitPrice = limitPrice;
        this.totalVolume = 0;
    }

    public void addOrder(Order order) {
        if (headOrder == null) {
            headOrder = order;
            tailOrder = order;
            return;
        }
        order.setLimit(this);
        tailOrder.setNextOrder(order);
        order.setPrevOrder(order);
        tailOrder = order;
        size++;
        totalVolume += order.getShares();
    }

    public void removeOrder(Order order) {
        size--;
        totalVolume -= order.getShares();

        if (hasProcessedHeadAndTailOrder(order) || hasProcessedHeadOrder(order) || hasProcessedTailOrder(order)) {
            return;
        }

        processInsideOrder(order);
    }

    private void processInsideOrder(Order order) {
        if (order.getNextOrder() != null) {
            order.getNextOrder().setPrevOrder(order.getPrevOrder());
        }
        if (order.getPrevOrder() != null) {
            order.getPrevOrder().setNextOrder(order.getNextOrder());
        }
    }

    private boolean hasProcessedTailOrder(Order order) {
        if (tailOrder == order) {
            order.getPrevOrder().setNextOrder(null);
            tailOrder = order.getPrevOrder();
            order.reset();
            return true;
        }
        return false;
    }

    private boolean hasProcessedHeadOrder(Order order) {
        if (headOrder == order) {
            order.getNextOrder().setPrevOrder(null);
            headOrder = order.getNextOrder();
            order.reset();
            return true;
        }
        return false;
    }

    private boolean hasProcessedHeadAndTailOrder(Order order) {
        if (headOrder == order && tailOrder == order) {
            headOrder = null;
            tailOrder = null;
            order.reset();
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return headOrder == null;
    }


    @Override
    public int compareTo(Limit o) {
        return Integer.compare(this.getLimitPrice(), o.getLimitPrice());
    }
}
