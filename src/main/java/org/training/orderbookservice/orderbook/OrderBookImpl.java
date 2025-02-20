package org.training.orderbookservice.orderbook;

import org.training.orderbookservice.limit.Limit;
import org.training.orderbookservice.order.Order;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OrderBookImpl implements OrderBook {
    private final TreeMap<Integer, Limit> buyTree = new TreeMap<>(Collections.reverseOrder());  // Buy side (max heap)
    private final TreeMap<Integer, Limit> sellTree = new TreeMap<>();  // Sell side (min heap)
    private final Map<Integer, Order> orderMap = new HashMap<>();  // Quick lookup by order ID
    private final Map<Integer, Limit> limitMap = new HashMap<>();


    @Override
    public void addOrder(Order order) {
        TreeMap<Integer, Limit> bookSide = order.isBuyOrSell() ? buyTree : sellTree;
        Limit limit = bookSide.computeIfAbsent(order.getLimitPrice(), Limit::new);
        limit.addOrder(order);
        limitMap.put(limit.getLimitPrice(), limit);
        orderMap.put(order.getIdNumber(), order);
    }

    @Override
    public void cancelOrder(int orderId) {
        Order order = orderMap.remove(orderId);
        if (order != null) {
            Limit limit = limitMap.get(order.getLimitPrice());
            if (limit != null) {
                limit.removeOrder(order);
                if (limit.isEmpty()) {
                    limitMap.remove(order.getLimitPrice());
                }
            }
        }
    }

    @Override
    public int getBestBid() {
        return buyTree.isEmpty() ? -1 : buyTree.firstKey();
    }

    @Override
    public int getBestAsk() {
        return sellTree.isEmpty() ? -1 : sellTree.firstKey();
    }
}
