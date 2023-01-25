package ru.job4j.ood.lcp;

import java.util.List;

public class Examples {
    /**
     * ПРИМЕР 1
     *
     * У нас есть простая модель кандидата, у которого есть имя и скорость бега
     */
    class Candidate {
        private String name;
        private int speed;

        public Candidate(String name, int speed) {
            this.name = name;
            this.speed = speed;
        }

        public String getName() {
            return name;
        }

        public int getSpeed() {
            return speed;
        }
    }

    /**
     * Пусть у нас есть класс спортшкола, в которую люди попадают при условии,
     * если они бегают быстрее какого-то значения
     */
    class Sportchool {
        private List<Candidate> candidates;
        private int minSpeed;

        public Sportchool(int minSpped) {

        }

        public void add(Candidate candidate) {
            if (candidate.getSpeed() > minSpeed) {
                candidates.add(candidate);
            }
        }
    }

    /**
     * Мы открыли другую спортшколу в соседнем городе, в которой должны быть такие же условия отбора.
     */
    class AnotherSportSchool extends Sportchool {
        private List<Candidate> candidates;

        public AnotherSportSchool(int minSpped) {
            super(minSpped);
        }

        /**
         * Однако в коде мы забыли добавить требование проверки скорости бега,
         * что в дальнейшем приведет к некорреткному отбору кандидатов.
         */
        public void add(Candidate candidate) {
            candidates.add(candidate);
        }
    }

    /**
     * ПРИМЕР 2
     *
     *У нас есть компания,в который все сотрудники должны умеет ходить, усердно работать и кодить.
     * Вот модель нашего типичного содрутника.
     */
    class Worker {
        public void walk() {

        }

        public String workHard() {
            return "I work Hard";
        }

        public void code() {

        }
    }

    /**
     * У нас появился очень умный сотрудник, который стал еще и зп просить.
     * Ну тут вроде все ок, он и функции все выполняет
     */
    class TooSmartWorker extends Worker {
        public void walk() {

        }

        public String workHard() {
            return "I am the hardest worker";
        }

        public void code() {

        }

        public void demandSalary() {

        }
    }

    /**
     * Но тут мы выяснили, что один из сотрудников не работает усердно,
     * результат его работы, соответсвтенно - null.
     * Нам такие сотрудники не нужны, кроме того, здесь снова ошибка наследования.
     */

    class LazyWorker extends Worker {
        public void walk() {

        }

        public String workHard() {
            return null;
        }

        public void code() {

        }
    }
}
