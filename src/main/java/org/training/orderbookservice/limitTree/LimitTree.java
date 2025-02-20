//package org.training.orderbookservice.limitTree;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.training.orderbookservice.binarysearchtree.BinarySearchTree;
//import org.training.orderbookservice.limit.Limit;
//import org.training.orderbookservice.order.Order;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Getter
//@Setter
//public class LimitTree extends BinarySearchTree {
//    private final Map<Integer, Limit> limitMap = new HashMap<>();
//    private Limit best;
//    private int count;
//    private int volume;
//    //TODO use comparator function instead of < !
//
//    public void putLimit(Order order) {
//        if (!limitMap.containsKey(order.getLimitPrice())) {
//            Limit limit = new Limit(order.getLimitPrice());
//            limitMap.put(order.getLimitPrice(), limit);
//            limit.addOrder(order);
//            super.insert(limit);
//            updateBestIfNeeded(limit);
//        } else {
//            limitMap.get(order.getLimitPrice()).addOrder(order);
//        }
//        count++;
//        volume += order.getShares();
//    }
//
//    private void updateBestIfNeeded(Limit limit) {
//        if (limit.getKey() > best.getKey()) {
//            best = limit;
//        }
//    }
//}
