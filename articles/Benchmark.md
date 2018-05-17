Приветствую вас!<br/>
После написания статьи https://habr.com/post/352930/, возник вопрос, о том как измерять время выполнения метода

## Как я это делал?
Наверное,как и многие новички, начал замерять время, таким вот способом:
```java
long start = System.currentTimeMillis();
// вызов метода
long finish = System.currentTimeMillis();
long time = finish - start;
```
Столкнулся с такой проблемой, что при первом вызове, метод исполнялся медленей чем при последующих,
при изучении этого вопроса, выяснил, что существует JIT - компилятор,
путём компиляции байт-кода в машинный код во время работы программы достигается высокая скорость выполнения по сравнению с интерпретируемым байт-кодом.
```java
public class TestAddArrayList {
    private static long add(){
        List<Integer> arrayList=new ArrayList<>();
        long start=System.currentTimeMillis();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            arrayList.add(i);
        }
        long finish=System.currentTimeMillis();
        return finish-start;
    }

    public static void main(String[] args) {
        System.out.println("cold start time " + String.format("%,12d",add()) + " ms");
        System.out.println("warmed JRE time " + String.format("%,12d",add()) + " ms");
    }
}
```
|   N=1000000  |   Первый запуск     |   Второй запуск        |    Третий запуск     |  
|:-----------: |:-------------------:| --------------------:|:-----------------------|
|  cold start time    | 362 ms       |         450 ms       |          355 ms        |           
| warmed JRE time     | 289 ms       |         219 ms       |          251 ms        |          

Как видим можно получить, абсолютно разные результаты