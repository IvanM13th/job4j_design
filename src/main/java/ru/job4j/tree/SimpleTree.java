package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                rsl = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(eNode -> value.equals(eNode.value));
    }

    @Override
    public boolean isBinary() {
        boolean rsl = true;
        Optional<Node<E>> node = findByPredicate(eNode -> eNode.children.size() > 2);
        if (node.isPresent()) {
            rsl = false;
        }
         return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> el = findBy(parent);
        if (el.isPresent()) {
            if (findBy(child).isEmpty()) {
                el.get().children.add(new Node<>(child));
                rsl = true;
            }
        }
        return rsl;
    }
}
