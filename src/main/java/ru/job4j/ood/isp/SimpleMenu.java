package ru.job4j.ood.isp;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        if (Objects.equals(parentName, Menu.ROOT)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            rsl = true;
        } else {
            Optional<ItemInfo> itemInfo = findItem(parentName);
            itemInfo.ifPresent(info -> info.menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate)));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> menuItemInfo = Optional.empty();
        Optional<ItemInfo> itemInfo = findItem(itemName);
        if (itemInfo.isPresent()) {
            menuItemInfo = Optional.of(new MenuItemInfo(
                    itemInfo.get().menuItem,
                    itemInfo.get().number));
        }
        return menuItemInfo;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        List<ItemInfo> list = new ArrayList<>();
        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            list.add(dfsIterator.next());
        }

        return new Iterator<>() {
            final Iterator<ItemInfo> items = list.iterator();
            ItemInfo itemInfo = null;

            @Override
            public boolean hasNext() {
                return items.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                itemInfo = items.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        ItemInfo rsl = null;
        ItemInfo current;

        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            current = dfsIterator.next();
            if (name.equals(current.menuItem.getName())) {
                rsl = current;
                break;
            }
        }

        return Optional.ofNullable(rsl);
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }

    }

    public List<MenuItem> getRootElements() {
        return rootElements;
    }

    public class OutPrinter implements MenuPrinter {

        @Override
        public void print(Menu menu) {
        }
    }
}
