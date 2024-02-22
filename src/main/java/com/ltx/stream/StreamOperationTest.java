package com.ltx.stream;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOperationTest {
    public static void main(String[] args) {
        /**
         * Stream.of方法将一组条目变为一个流
         */
        Stream.of(1,"哈哈哈哈",2.3f)
                .forEach(System.out::println);
        /**
         * Collection集合类型也可以将
         */
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
        System.out.println(list.stream().mapToInt(item->item).sum());
        /**
         * 通过流将集合转换为别的集合类型
         */
        List<Integer> collect = list.stream().map(item -> item).collect(Collectors.toList());
        /**
         * iterate()方法，第一个参数指定一个种子数据，然后将这个种子数据作为第二个参数(方法引用)的参数，传入方法引用，
         * 第二个方法引用返回一个相同类型，并作为下一次调用iterate()方法的种子。
         * 这样会创建一个iterate()的流对象。
         */
        Stream<Integer> iterate = Stream.iterate(0, (item) -> {
            item++;
            return item;
        });
        System.out.println("Stream类型的iterate方法:");
        Integer i = iterate.limit(20).findFirst().orElse(null);
        System.out.println("迭代流的第一个元素:"+i);
        /**
         * 流生成器
         */
        System.out.println("流生成器");
        Stream.Builder<String> builder = Stream.builder();
        builder.add("a").add("b").add("c").build().forEach(System.out::print);
        /**
         * 流generate方法,根据提供的Suppliy<T>方法引用创建不限量的T类型的元素的流。
         */
        System.out.println("流generate方法");
        Integer i1 = Stream.generate(() -> 2).limit(10).findFirst().orElse(null);
        /**
         * Arrays.stream()方法,将数组转化为流对象
         */
        System.out.println("Arrays.stream()方法");
        Arrays.stream(new int[]{1,2,3,4,5,6},3,5).forEach(System.out::println);
        /***
         * peek()方法可以查看流对象中的元素
         */
        System.out.println("流的peek()调试方法");
        Stream.of(1,2,3,4,5,6,7).limit(1).peek(item->{
            System.out.println(item);
        });
        /**
         * sorted()流对象进行排序
         */
        System.out.println("流的sorted()方法进行排序");
        Stream.of(12,3,4,5,6,7,8).sorted(Comparator.naturalOrder()).forEach(System.out::println);
        /**
         * distinct()，对于流对象进行一个去重
         * filter(),对流中的对象进行筛选。
         */
        System.out.println("流的distinct(),filter()");
        Stream.of(12,3,3,5,6,7,8).distinct().filter(item->item%3==0).forEach(System.out::println);
        /**
         * map(Function),接受一个函数，该函数将应用于整个流的每个对象，并返回一个新流对象.
         * mapToInt()
         * mapToLong()
         * mapToDouble()
         */
        System.out.println("流的map(Function)方法");
        Stream.of(12,12,3,4,5,5,6,6,7,7).map(item->item%3).forEach(System.out::println);
        /**
         * flatMap(),接受将流元素处理为一个新流的函数，将这个新流打平为一个流元素，而不是流，合并在当前流对象中。
         */
        System.out.println("打平方法");
        Stream.of(1,3,4,5,6,7,8).flatMap(item->{
            return Stream.of(1,2,3,4,5);
        }).forEach(System.out::println);
        /**
         * 创建一个空流，然后测试以下创建Optional类型的内容
         */
        System.out.println("返回Optional类型的流操作");
        System.out.println(Stream.<String>empty().findFirst());
        System.out.println(Stream.<String>empty().findAny());
        System.out.println(Stream.<String>empty().max(String.CASE_INSENSITIVE_ORDER));
        System.out.println(Stream.<String>empty().min(String.CASE_INSENSITIVE_ORDER));
        System.out.println(Stream.<String>empty().reduce((s1,s2)-> s1+s2));
        System.out.println(IntStream.empty().average());
        test(Stream.of("Epithets").findFirst());
        test(Stream.<String>empty().findFirst());
        /**
         * ifPresent(Consumer) 如果值存在则，则用这个值来调用Consumer,否则什么都不做
         * orElse(otherObject) 如果对象存在,则返回这个对象，否则返回otherObject
         * orElseGet(Supplier) 如果对象存在，则返回这个对象，否则返回使用Supplier函数创建的替代对象。
         * orElseThrow(Supplier) 如果对象存在，则返回这个对象，否则抛出一个使用Supplier函数创建的异常
         */
        System.out.println("Optional的快捷操作");
        test("basics",StreamOperationTest::basics);
        test("ifPresent",StreamOperationTest::ifPresent);
        test("orElse",StreamOperationTest::orElse);
        test("orElseGet",StreamOperationTest::orElseGet);
        test("orElseThrow",StreamOperationTest::orElseThrow);

        /**
         * 创建Optional对象
         * 三种静态方法:
         * empty():返回一个空的Optional
         * of(value):如果已经知道这个value不是null,可以使用该方法将其包在一个Optional中。
         * ofNullable(value):如果不知道这个value是不是null,使用这个方法。如果value为null，
         * 则它会自动返回Optional.empty,否则会将这个value包在一个Optional中。
         */
        System.out.println("创建Optional对象的静态方法");
        test("empty",Optional.empty());
        test("of",Optional.of("Howdy"));
        try{
            test("of",Optional.of(null));
        }catch (Exception e){
            System.out.println(e);
        }
        test("ofNullable",Optional.ofNullable("Hi"));
        test("ofNullable",Optional.ofNullable(null));
        /**
         * 针对Optional对象的操作
         * filter(Predicate):将Predicate应用于Optional的内容,并返回其结果。如果Optional于Predicate不匹配，则将其转换为empty。
         * 如果Optional本身已经是empty，则直接传回。
         * map(Function):如果Optional不为empty,则将Function应用于Optional中包含的对象，并返回结果。否则传回Optional.empty
         * flatMap(Function):和map()类似，但是所提供的映射函数会将结果包在Optional中，这样flatMap()最后就不会再做任何包装了。
         *
         * note:数值化的Optional上没有提供这些操作。
         *
         * 对于流的filter()而言,如果Predicate返回false,它会将元素从流中删除。
         * 但是，对于Optional.filter()而言，如果Predicate返回false,它不会删除元素，但是会转化为empty.
         */
        System.out.println("Optional对象操作");
        Integer i2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8).findAny().filter(item -> {
            return item % 2 == 0;
        }).orElse(null);
        System.out.println(i2);
        /**
         * 终结操作
         * toArray():将流元素转换为适当类型的数组,有时需要将流对象转换为数组存储。之后需要将这个数组以流的形式进行复用。
         * toArray(generator):generator用于在特定的情况下分配自己的数组存储
         *
         * forEach(Consumer):循环遍历流
         * forEachOrdered(Consumer):以流的原始顺序循环遍历流
         * parallel()让Java尝试在多个处理器上执行各种操作,并发执行。
         */
        System.out.println("终结操作测试");
        rands();
        /**
         * 收集操作
         * collect(Collector):使用这个Collector将流元素累加到一个结果集合中。
         * collect(Supplier,BiConsumer,BiConsumer):Supplier需要提供一个创建集合的构造器
         * 第一个BiConsumer是一个用于将元素添加到Supplier创建的集合的方法，
         * 第二个BiConsumer用于将两个值组合在一起的(看方法签名应该是将两个集合组合在一起的方法。)
         */
        System.out.println("收集操作测试");
        Stream.of(1,2,3,4,5,6,7).collect(Collectors.toList());
        Stream.of(1,2,3,44,5,6,77,7,8999).collect(LinkedList::new,LinkedList::add,LinkedList::addAll);
        /**
         * 组合所有的流元素
         * reduce(BinaryOperator):使用BinaryOperator来组合所有的流元素。因为这个流可能为空，所以返回的是一个Optional
         * reduce(identity,BinaryOperator):和上面一样，但是将identity用作这个组合的初始值。即使这个流是空的，我们仍能得到identity作为结果。
         * reduce(identity,BiFunction,BinaryOperator):这个更复杂。可以通过组合显示的map()和reduce()操作来更简单地表达这样的需求。
         */
        /**
         * 匹配:
         * allMatch(Predicate):当使用所提供的Predicate检测流中的元素时，如果每一个元素使用Predicate,都得到true,则返回true。在遇到第一个false时，会短路计算。
         * 也就是说在找到一个false之后，它不会继续计算。
         * anyMatch(Predicate):当使用所提供的Predicate检测流中的元素时。如果有任何一个元素能得到true,则返回true.在遇到第一个true时，会短路计算
         * noneMatch(Predicate):当使用所提供的Predicate检测流中的元素时，如果没有元素得到true,则返回true。在遇到第一个true时，会短路计算。
         */
        boolean flag = Stream.of(1, 2, 3, 4, 5, 76).allMatch(item -> item > 0);
        boolean flagB = Stream.of(5, 12, 2, 3, 5).anyMatch(item -> item > 3);
        /**
         * 选择一个元素
         * findFirst():返回一个包含流中第一个元素的Optional,如果流中没有元素，则返回Optional.empty。
         * findAny():返回一个包含流中某个元素的Optional,如果流中没有元素，则返回Optional.empty
         */
        /**
         * 获得流相关的信息
         * count():获得流中元素的数量
         * max(Comparator):通过Comparator确定这个流中的"最大"元素
         * min(Comparator):通过Comparator确定这个流中的"最小"元素
         * 数值化流相关的信息
         * average():就是通常的意义，获得平均值
         * max()与min():这些操作不需要一个Comparator,因为它们处理的是数值化流
         * sum():将流的数值累加起来。
         * summaryStatistics():返回可能有用的摘要数据。
         */
    }
    private static int[] rints = new Random(47).ints(0,1000).limit(100).toArray();
    static IntStream rands(){
        return Arrays.stream(rints);
    }
    static void test(String testName,Optional<String> opt){
        System.out.println(" === " + testName + " === ");
        System.out.println(opt.orElse("Null"));
    }
    static void basics(Optional<String> optString){
        if(optString.isPresent()){
            System.out.println(optString.get());
        }
        else{
            System.out.println("Nothing inside!");
        }
    }
    static void ifPresent(Optional<String> optString){
        optString.ifPresent(System.out::println);
    }
    static void orElse(Optional<String> optString){
        System.out.println(optString.orElse("Nada"));
    }
    static void orElseGet(Optional<String> optString){
        System.out.println(optString.orElseGet(()->"Generated"));
    }
    static void orElseThrow(Optional<String> optString){
        try{
            System.out.println(optString.orElseThrow(()->new Exception("Supplied")));
        }catch(Exception e){
            System.out.println("Caught " + e);
        }
    }
    static void test(String testName, Consumer<Optional<String>> cos){
        System.out.println(" === " + testName + " === ");
        cos.accept(Stream.of("Epithets").findFirst());
        cos.accept(Stream.<String>empty().findFirst());
    }    static void test(Optional<String> optString){
        if(optString.isPresent()){
            System.out.println(optString.get());
        }else{
            System.out.println("Nothing inside!");
        }
    }
}
