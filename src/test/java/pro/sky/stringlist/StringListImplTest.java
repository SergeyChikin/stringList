package pro.sky.stringlist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.stringlist.exception.ElementNotFoundException;
import pro.sky.stringlist.exception.IndexNotFoundException;

import java.util.Arrays;

public class StringListImplTest {

    private final StringListImpl stringList = new StringListImpl();

    @BeforeEach
    public void addBefore() {
        stringList.add("Первая ячейка");
        stringList.add("Вторая ячейка");
        stringList.add("Третья ячейка");
    }

    @AfterEach
    public void clearAll() {
        stringList.clear();
    }

    @Test
    public void addStringTest() {
    int beforeAddCount = stringList.size();
    String expected = new StringListImpl().add("Четвёртая ячейка");
        Assertions.assertThat(stringList.add("Четвёртая ячейка"))
                .isEqualTo(expected)
                .isIn(stringList.toArray())
                .isIn(stringList.get(3));
        Assertions.assertThat(stringList.toArray()).hasSize(beforeAddCount + 1);
    }

    @Test
    public void addStringByIndexTest() {
        int beforeAddCount = stringList.size();
        String expected = new StringListImpl().add(0, "Между");
        Assertions.assertThat(stringList.add(3,"Между"))
                .isEqualTo(expected)
                .isIn(stringList.toArray())
                .isIn(stringList.get(3));
        Assertions.assertThat(stringList.toArray()).hasSize(beforeAddCount + 1);
    }

    @Test
    public void setTest() {
        int beforeAddCount = stringList.size();
        String expected = new StringListImpl().set(0, "Вместо");
        Assertions.assertThat(stringList.set(2, "Вместо"))
                .isEqualTo(expected)
                .isIn(stringList.toArray())
                .isIn(stringList.get(2));
        Assertions.assertThat(stringList.toArray()).hasSize(beforeAddCount);
    }

    @Test
    public void removeByItemTest() {
        int beforeAddCount = stringList.size();
        String expected = new StringListImpl().add("Первая ячейка");
        Assertions.assertThat(stringList.remove("Первая ячейка"))
                .isEqualTo(expected)
                .isNotIn(stringList.toArray());
        Assertions.assertThat(stringList.toArray()).hasSize(beforeAddCount - 1);
    }

    @Test
    public void removeIfNotFoundTest() {
        Assertions.assertThatExceptionOfType(ElementNotFoundException.class)
                .isThrownBy(() -> stringList.remove("ячейка"));
    }

    @Test
    public void removeByIndexTest() {
        int beforeAddCount = stringList.size();
        String expected = new StringListImpl().set(0,"Вторая ячейка");
        Assertions.assertThat(stringList.remove("Вторая ячейка"))
                .isEqualTo(expected)
                .isNotIn(stringList.toArray());
        Assertions.assertThat(stringList.toArray()).hasSize(beforeAddCount - 1);
    }

    @Test
    public void removeIfNotFoundIndexTest() {
        Assertions.assertThatExceptionOfType(IndexNotFoundException.class)
                .isThrownBy(() -> stringList.remove(5));
    }

    @Test
    public void getTest() {
        String expected = new StringListImpl().add("Первая ячейка");
        Assertions.assertThat(stringList.add("Первая ячейка"))
                .isEqualTo(expected)
                .isIn(stringList.get(0));
    }

    @Test
    public void toArrayTest() {
        String expected = Arrays.toString(new StringListImpl().toArray());
        Assertions.assertThat(stringList.toArray())
                .hasSize(3)
                .contains("Первая ячейка", "Вторая ячейка", "Третья ячейка");
    }

}
