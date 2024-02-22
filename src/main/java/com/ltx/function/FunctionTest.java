package com.ltx.function;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 函数式编程
 * 所有的数据是不可变的，设置一次永不改变。函数会接受值，然后产生新值，但绝对不会修改自身之外的任何东西。
 * 函数式编程的"不可变对象和无副作用"这一编程范式解决了并行编程中的"可变的共享状态"问题。
 * "可变的共享状态"意为着，运行在不同处理器上的
 * Function<T,R> T R T输入的参数类型，R函数的返回类型，方法引用
 * Predicate<T> T输入的参数类型,返回布尔值 的方法引用.
 * Consumer<T> T输入的参数类型，返回值为void 的方法引用
 * Supplier<T> 无参数列表,返回值类型为T 的方法引用。
 */
public class FunctionTest {
    public static void main(String[] args) {
        NewType newType = testFunction(1, Optional.of((ints) -> ints+1.5d));
        NewType newType2 = testFunction(2,Optional.ofNullable(null));
        NewType newType3 = testFunction(3);
        System.out.println(newType);
        System.out.println(newType2);
        System.out.println(newType3);
    }
    static NewType testFunction(Integer a){
        return testFunction(a,Optional.ofNullable(null));

    }
    static NewType testFunction(Integer a, Optional<Function<Integer, Double>> func) {
        double apply;
        if (func.isPresent()) {
            apply = func.get().apply(a);
        }else {
            apply = 0d;
        }

        return new NewType(apply);
    }
}
class NewType{
    double fx;
    public NewType(double data){
        this.fx = data;
    }

    @Override
    public String toString() {
        return new Double(fx).toString();
    }
}
