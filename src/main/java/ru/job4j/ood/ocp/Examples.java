package ru.job4j.ood.ocp;

public class Examples {
    /**
     * Пусть у нас есть класс, который что-то выводит текст в консоль.
     * Если завтра мы захотим выводить в консоль числа,
     * нам нужно будет делать новый класс, способный принимать цифры.
     */
    public static class StringWriter {
        public void writeString(String toWrite) {
            System.out.println(toWrite);
        }
    }

    /**
     * Нам нужно абстрагироваться через инферфейс,
     * чтобы мы могли легко добавлять нужную нам реализацию без ущерба ранее написанному коду.
     */
    public static class StringWriterInterface implements ConsoleWriter {

        @Override
        public void write(Object obj) {
            System.out.println(obj);
        }
    }

    /**
     * В первом случае, мы можем напечатьа только String.
     * Во втором - любой тип данных.
     * @param args параметры для передачи
     */
    public static void main(String[] args) {
        StringWriter stringWriter = new StringWriter();
        stringWriter.writeString("I want to be printed in console");

        StringWriterInterface stringWriterInterface = new StringWriterInterface();
        stringWriterInterface.write(1);
        stringWriterInterface.write('C');
        stringWriterInterface.write("String to be printed");
    }

    /**
     * В предыдщуем задании в одном из классовв поля объекта я передал реализацию, а не интерфейс.
     * Теперь вижу проблему в том, что если мы что-то изменим в переданной реалзации,
     * каскадно может поломаться весь код программы в том месте, где эта реализация используется
     */

    /**
     * То же самое было и с переданным параметров в одну из реализаций,
     * если мы что-то захотим изменить, мы не сможем это сделать, тк привязаны к реализации.
     */
}