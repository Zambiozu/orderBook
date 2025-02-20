package org.training.orderbookservice.orderbook;

import org.training.orderbookservice.order.Order;

public interface OrderBook {

    void addOrder(Order order);

    void cancelOrder(int orderId);

    int getBestBid();

    int getBestAsk();
}
