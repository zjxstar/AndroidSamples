package com.zjx.happy.closure

/**
 * 闭包的特色用法
 * @author zjxstar
 */

/**------------------------在GStrings中使用-------------------------*/

def x = 1
// 这里的 ${x} 并不是闭包，而是一个表达式
def gs = "x = ${x}" // gs的值已经确定了
assert gs == 'x = 1'

x = 2
//assert gs == 'x = 2' // gs还是 x = 1
println(gs)


def y = 1
// 这里的 ${-> y} 才是一个闭包
def gsy = "y = ${-> y}"
assert gsy == 'y = 1'

y = 2
assert gsy == 'y = 2' // 会重新使用y值

// 定义一个类，包含toString方法
class Dog {
    String name
    String toString() { name }
}
def sam = new Dog(name:'Sam')
def lucy = new Dog(name:'Lucy')
def p = sam // 给p赋值
def gsDog = "Name: ${p}" // 此时gsDog的值已经确定了
assert gsDog == 'Name: Sam'
p = lucy // 虽然p的引用发生了变化，但对gsDog无效
assert gsDog == 'Name: Sam'
sam.name = 'Lucy' // name的值发生了变化，会影响gsDog的值
assert gsDog == 'Name: Lucy'

// 如果改成闭包，就会针对引用有效
def sam2 = new Dog(name: 'Sam2')
def lucy2 = new Dog(name: 'Lucy2')
def q = sam2
def gsDog2 = "Name: ${-> q}"
assert gsDog2 == 'Name: Sam2'
q = lucy2 // q的引用发生了变化，对gsDog依然有效
assert gsDog2 == 'Name: Lucy2'

/**------------------------闭包的强转-------------------------*/

// SAM类型的接口和抽象类
interface Predicate<T> {
    boolean accept(T obj)
}

abstract class Greeter {
    abstract String getName()
    void greet() {
        println "Hello, $name"
    }
}

// 使用as关键字强转
Predicate filter = { it.contains 'G' } as Predicate
assert filter.accept('Groovy') == true

Greeter greeter = { 'Groovy' } as Greeter
greeter.greet()
println(greeter.getName())

// 2.2.0之后的版本可以省略as关键字
Predicate filter2 = { it.contains 'G' }
assert filter2.accept('Groovy') == true

Greeter greeter2 = { 'Groovy' }
greeter2.greet()

// 可以使用方法指针
boolean doFilter(String s) { s.contains('G') }

Predicate filter3 = this.&doFilter
assert filter3.accept('Groovy') == true

Greeter greeter3 = GroovySystem.&getVersion
greeter3.greet()

// SAM类当参数传递
public <T> List<T> filterList(List<T> source, Predicate<T> predicate) {
    source.findAll { predicate.accept(it) }
}

assert filterList(['Java','Groovy'], { it.contains 'G'} as Predicate) == ['Groovy']

assert filterList(['Java','Groovy']) { it.contains 'G'} == ['Groovy']

// 闭包可以强制转成任意类，特别是接口
//interface FooBar {
//    int foo()
//    void bar()
//}

class FooBar {
    int foo() { 1 }
    void bar() { println 'barn' }
}

def impl = { println 'ok'; 123 } as FooBar

assert impl.foo() == 123
impl.bar()

/**------------------------Curring-------------------------*/

def nCopies = { int n, String str -> str*n }
def twice = nCopies.curry(2) // 最左边的参数赋值
assert twice('bla') == 'blabla'
assert twice('bla') == nCopies(2, 'bla')
def blah = nCopies.rcurry('bla') // 最右边的参数赋值
assert blah(2) == 'blabla'
assert blah(2) == nCopies(2, 'bla')

def volume = { double l, double w, double h -> l*w*h }
def fixedWidthVolume = volume.ncurry(1, 2d) // 指定某个位置的参数进行赋值，这里指定第二个参数
assert volume(3d, 2d, 4d) == fixedWidthVolume(3d, 4d)
def fixedWidthAndHeight = volume.ncurry(1, 2d, 4d) // 第二个参数之后的参数
assert volume(3d, 2d, 4d) == fixedWidthAndHeight(3d)

def oneCurring = { item -> println(item)} // 支持一个参数的情况
def oneCurring2 = oneCurring.curry("hello")
oneCurring2()
def funCurring = {int a, int b, int c, int d -> a * b * c * d}
def funCurring2 = funCurring.ncurry(1, 2, 3)
assert funCurring(1, 2, 3, 4) == funCurring2(1, 4)

/**------------------------Memoization-------------------------*/

def fib
fib = { long n -> n < 2 ? n : fib(n - 1) + fib(n - 2) }
// 某些值会被重复计算
assert fib(15) == 610 // slow!
fib = { long n -> n < 2 ? n : fib(n - 1) + fib(n - 2) }.memoize()
assert fib(25) == 75025 // fast!
fib = { long n -> n < 2 ? n : fib(n - 1) + fib(n - 2) }.memoizeAtMost(8)
assert fib(25) == 75025 // fast!
fib = { long n -> n < 2 ? n : fib(n - 1) + fib(n - 2) }.memoizeAtLeast(3)
assert fib(25) == 75025 // fast!
fib = { long n -> n < 2 ? n : fib(n - 1) + fib(n - 2) }.memoizeBetween(3, 8)
assert fib(25) == 75025 // fast!

/**------------------------Trampoline-------------------------*/

def factorial
factorial = { int n, def accu = 1G ->
    if (n < 2) return accu
    factorial.trampoline(n - 1, n * accu)
}
factorial = factorial.trampoline() // 会转成 TrampolineClosure

assert factorial(1) == 1
assert factorial(3) == 1 * 2 * 3
assert factorial(1000) // == 402387260.. plus another 2560 digits

/**------------------------Composition-------------------------*/

def plus2  = { it + 2 }
def times3 = { it * 3 }

// 先计算箭头的尾部闭包，再将其值传入箭头的头部闭包中进行计算
def times3plus2 = plus2 << times3 // plus2(times3())
assert times3plus2(3) == 11
assert times3plus2(4) == plus2(times3(4))

def plus2times3 = times3 << plus2 // times3(plus2())
assert plus2times3(3) == 15
assert plus2times3(5) == times3(plus2(5))

// reverse composition
def times3plus22 = times3 >> plus2
assert times3plus2(3) == times3plus22(3) // plus2(times3())
println(times3plus22(2)) // 8

