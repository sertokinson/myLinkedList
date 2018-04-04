Приветствую вас!<br/>
После изучения коллекций, а именно такие реализации `List`, как `ArrayList` и `LinkedList`, возникла идея, а почему бы не объединить эти структуры данных в одну и посмотреть, что из этого получится. 

## Зачем это нужно?
* Проблема `ArrayList` - у него есть начальный размер по умолчанию `DEFAULT_CAPACITY` или заданный размер `initialCapacity`, при превышении этого размера, создается новый массив большего размера, при этом туда копируются данные из старого массива, что по времени очень затратно и именно это дает в наихудшем случае алгоритмическую сложность `O(n)`
* Проблема `LinkedList` - здесь наоборот, добавить новый элемент, это всего лишь добавить новую связь (создать еще одну `Node` и добавить ссылку на неё), но операция получения элемента по индексу очень затратна, т.к. нужно будет пройтись по всему списку от начала, что очень затратно и дает `O(n)`

## Решение
Что если создать такую структуру данных, при которой вставка и получение любого элемента будет за константное время. Буду использовать технологию `ArrayList` без пересоздания массива, что конечно же проигрывает по памяти, но выигрывает в скорости, т.к. память дешевая и её очень много, выигрыш в производительности считаю приоритетным.
Для того чтобы связать их между собой, буду использовать двусвязный список:
![](https://habrastorage.org/webt/kd/t_/zl/kdt_zlyrbscofg7unhqyc43vc9s.jpeg)
<cut/>
##Реализация
Перейдем непосредственно к исходному коду:<br/>
```java
public class Node<T> {
    Node prev;
    Node next;
    T[] value;

    public Node(Node prev, Node next, T[] value) {
        this.prev = prev;
        this.next = next;
        this.value = value;
    }
}
```   
Для начало создадим стандартную структуру двусвязного списка, где `value` - это массив значений. 
Далее перейдем к конкретной реализации класса, объявим необходимые переменные:    
```java
public static int INIT_CAPACITY = 100;
private Object[] arr = new Object[INIT_CAPACITY];
private int index = 0;
private int level = 0;
private Node<T> first = new Node<>(null, null, (T[]) arr);
private Node last = first;
private Node current = null;
private int size = 0;
```      
Здесь `INIT_CAPACITY` - начальный размер массива, его можно переопределить в соответствующем конструкторе, `arr` - собственно сам массив, переменная `index` - понадобится для расчета индекса, `level` - для расчета уровня, далее подробно будет рассказано для чего это нужно, `first` - ссылка на первый элемент списка,
`last` - ссылка на последний элемент списка, `current` - ссылка на текущий элемент списка (последней выборки), так можно ускорить выборку подряд идущих элементов или близ - лежащих к ним, `size` - размер (или количество данных).<br/>
Зададим 2 коструктора - по умолчанию и для изменения начального размера:
```java
public MyLinkedList() {
    first.next = last.next;
}
public MyLinkedList(int size) {
    INIT_CAPACITY = size;
    arr = new Object[INIT_CAPACITY];
    first.next = last.next;
}
```
Добавление элемента:
```java
public void add(T i) {
    if (index == INIT_CAPACITY) {
        arr = new Object[INIT_CAPACITY];
        last.next = new Node<>(last, null, (T[]) arr);
        index = 0;
        last = last.next;
    }
    arr[index] = i;
    index++;
    size++;
}
``` 
Здесь проверяем условие, если массив заполнен, то создаем новый и запоминаем ссылку на него.<br/>
Получение элемента:
```java
public T get(int i) {
    T value;
    int level = i / INIT_CAPACITY;
    int index = i % INIT_CAPACITY;
    if (this.current == null) {
        this.level = 0;
        this.current = first;
    }
    if(this.level > level)
        for (int j = this.level; j < level; j++) {
            this.level = level;
            current = current.prev;
        }
    else
        for (int j = this.level; j < level; j++) {
            this.level = level;
            current = current.next;
        }
    value = (T) current.value[index];
    return value;
}
```
Уровни это количество массивов в списке, т.е на 0-м уровне 1 массив, на 1-м уровне 2 массива и т.д.,`index` - это индекс текущего уровня `0..INIT_CAPACITY`, также у нас есть ссылка на текущий элемент списка `current`, который был получен из предыдущей выборки, т.е. если новый уровень больше предыдущего, то идем вперед от текущего элемента и если наоборот, то назад. Также добавил 2 быстрые операции - получение первого и последнего элемента:
```java
public T getFirst(){
    return first.value[0];
}
public T getLast() {
    return (T) last.value[(size-1) % INIT_CAPACITY];
}
```
Первый и последний элемент получить также просто и быстро, как в массиве.
Операция удаления последнего элемента - быстрее всего это затирать значение null-ом, если весь массив становится заполненным null-ми, то теряем ссылку на него и garbage collector все почистит:
```java
public void removeLast(){
    if (last.value[0] == null) {
        last = last.prev;
        index=INIT_CAPACITY-1;
    }
    last.value[(size-1) % INIT_CAPACITY]=null;
    size--;
    index--;
}
```
Получение размера - очевидно. Также был добавлен итератор, т.е. этот класс имплементирует Iterable и реализует метод iterator
```java
private Node<T> first;
private int index = -1;
public MyLinkedListIterator(Node<T> first) {
    this.first = first;
}
 
@Override
public boolean hasNext() {
   index++;
   return first != null;
}

@Override
public T next() {
    T value;
    int index = this.index % INIT_CAPACITY;
    value= first.value[index];
    if(index==INIT_CAPACITY-1||this.first.value[index+1]==null)
        first=first.next;
    return value;
}
```
##Время работы
Возможно корректность способа замера оставляет желать лучшего, но делал это так:
```java
long start = System.currentTimeMillis();
// операция add и операция get - с начала, конца и середины списка
// N - кол-во элементов
long finish = System.currentTimeMillis();
long time = finish - start;
```
Делал по 3 запуска и брал среднее, возьмем 100 тысяч элементов:

| N=100000    |   Вставка в конец   |   Получение первого  |    Получение среднего  |   Получение последнего        |
|:-----------: |:-------------------:| --------------------:|:-----------------------|:------------------------:| 
|  MyDeque    |         8           |         0            |          4             |            0             |   
| ArrayDeque  |        10           |         2            |          -             |            2             |      
| ArrayList   |        50           |         2            |          4             |            3             |      
| LinkedList  |        30           |         4            |        86214           |            4             |   

Возьмем миллион элементов:

| N=1000000   |   Вставка в конец   |   Получение первого  |   Получение среднего   |   Получение последнего   |
|:------------|:--------------:| --------------------:|:-----------------------|:------------------------:| 
|  MyDeque    |         203         |         2            |          26            |            7             |   
| ArrayDeque  |         262         |         11           |          -             |           15             |      
| ArrayList   |         340         |         13           |          17            |           12             |      
| LinkedList  |         486         |         23           |       >100000          |           25             |   

И наконец, возьмем 10 миллионов элементов:

| N=10000000  |   Вставка в конец                |   Получение первого  |   Получение среднего   |   Получение последнего   |
|:------------|:--------------------------------:| --------------------:|:-----------------------|:------------------------:| 
|  MyDeque    |        2410                      |         31           |          26            |           71             |   
| ArrayDeque  |        4628                      |         322          |          -             |           111            |      
| ArrayList   |        4796                      |         115          |          17            |           120            |      
| LinkedList  |                            OutOfMemoryError: Java heap space                                                                             


#Вывод

![](https://habrastorage.org/webt/ga/zv/mv/gazvmvcjcuhrwb1xah6gkoazzt8.jpeg)

Из графика видно, что с увлечением количества элементов в коллекции, скорость работы популярного ArrayList уступает моей структуре.

В сухом остатке, получили очередь LIFO, которая работает быстрее, чем обычная Deque. В дальнейшем планируется реализовать такие операции как вставка и удаление из любого места без потери производительности, уже есть кое-какие наработки по этому поводу, на данном этапе хотелось бы получить обратную связь и увидеть заинтересованность сообщества для дальнейшей работы над новой структурой данных. 

Проект можно посмотреть по ссылке https://github.com/sertokinson/myLinkedList