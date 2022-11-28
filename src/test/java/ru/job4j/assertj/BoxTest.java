package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .startsWith("Te");
    }

    @Test
    void isThisUNKNOWN() {
        Box box = new Box(3, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty();
    }

    @Test
    void whenVertexIsThree() {
        Box box = new Box(3, 3);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(-1)
                .isLessThan(0);
    }

    @Test
    void whenVertexIsFour() {
        Box box = new Box(4, 3);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(4)
                .isGreaterThan(3);
    }

    @Test
    void whenExists() {
        Box box = new Box(8, 4);
        boolean existence = box.isExist();
        assertThat(existence).isTrue();
    }

    @Test
    void whenNotExists() {
        Box box = new Box(7, 4);
        boolean existence = box.isExist();
        assertThat(existence).isFalse();
    }

    @Test
    void whenSqrIsTwentySeven() {
        Box box = new Box(4, 4);
        double sqr = box.getArea();
        assertThat(sqr).isEqualTo(27.71d, withPrecision(0.005d))
                .isGreaterThan(25d);
    }

    @Test
    void whenSqrIsTwenty() {
        Box box = new Box(0, 3);
        double sqr = box.getArea();
        assertThat(sqr).isEqualTo(113.09d, withPrecision(0.05d))
                .isLessThan(150d);
    }
}