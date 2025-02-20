//package org.training.orderbookservice.binarysearchtree;
//
//public class BinarySearchTree<K> {
//    private Node<K> root;
//
//    public void insert(Node<K> newNode) {
//        if (root == null) {
//            root = newNode;
//            return;
//        }
//
//        Node<K> parent = null;
//        Node<K> current = root;
//        while (current != null) {
//            parent = current;
//            if (newNode.compareTo(current) < 0)
//                current = current.left;
//            else
//                current = current.right;
//        }
//
//        newNode.parent = parent;
//        if (newNode.compareTo(parent) < 0)
//            parent.left = newNode;
//        else
//            parent.right = newNode;
//    }
//
//    public void delete(Node nodeToRemove) {
//        boolean hasNoLeft = nodeToRemove.left == null;
//        boolean hasNoRight = nodeToRemove.right == null;
//        if (processedLeafNode(nodeToRemove, hasNoLeft, hasNoRight)) {
//            return;
//        }
//    }
//
//    private boolean processedLeafNode(Node nodeToRemove, boolean hasNoLeft, boolean hasNoRight) {
//        if (hasNoLeft && hasNoRight) {
//            if (this.root == nodeToRemove) {
//                this.root = null;
//            } else if (nodeToRemove.parent.right == nodeToRemove) {
//                nodeToRemove.parent.right = null;
//            } else {
//                nodeToRemove.parent.left = null;
//            }
//            return true;
//        }
//        return false;
//    }
//
//    private Node findMin(Node node) {
//        while (node.left != null) {
//            node = node.left;
//        }
//        return node;
//    }
//
//    private Node findMax(Node node) {
//        while (node.right != null) {
//            node = node.right;
//        }
//        return node;
//    }
//}
