package com.zjx.happy.closure

/**
 * 闭包的定义与使用
 * @author zjxstar
 */

def closure0 = {} // 接收一个参数，空执行语句
closure0('only')

def closure1 = { ++it } // 接收一个参数，默认it
println(closure1(1))
//closure1() // 错误

def closure2 = { -> println('no param')} // 没有参数，必须写->
closure2()
//closure2(1) // 错误

// 可以声明参数类型
def closure3 = { String x, int y -> println("x is ${x}, y is ${y}") }
closure3('Tom', 12)

// 可以省略参数类型
def closure4 = { x, y -> println("x is ${x}, y is ${y}") }
closure4('Jerry', 10)
assert closure4 instanceof Closure

// 一个参数，多行执行语句
def closure5 = { x ->
    int y = -x
    println("-x is ${y}")
}
closure5(99)

Closure closure6 = { println('Done') }
closure6() // 默认返回null
assert closure6() == null

Closure<Boolean> closure7 = { int a, int b -> a == b }
println(closure7.call(22, 22)) // 可以使用call方法

def isOdd = { int i -> i%2 != 0 }
assert isOdd(3) == true
assert isOdd.call(2) == false

def code = { 123 }
assert code() == 123

/**
 * 参数传递
 * @author zjxstar
 */

// 一个参数，不声明参数类型
def closureWithOneArg = { str -> str.toUpperCase() }
assert closureWithOneArg('groovy') == 'GROOVY'

// 一个参数，声明参数类型
def closureWithOneArgAndExplicitType = { String str -> str.toUpperCase() }
assert closureWithOneArgAndExplicitType('groovy') == 'GROOVY'

// 多个参数，逗号分隔
def closureWithTwoArgs = { a,b -> a+b }
assert closureWithTwoArgs(1,2) == 3

// 多个参数，都声明参数类型
def closureWithTwoArgsAndExplicitTypes = { int a, int b -> a+b }
assert closureWithTwoArgsAndExplicitTypes(1,2) == 3

// 多个参数，某几个声明参数类型
def closureWithTwoArgsAndOptionalTypes = { a, int b -> a+b }
assert closureWithTwoArgsAndOptionalTypes(1,2) == 3

// 多个参数，有默认参数
def closureWithTwoArgAndDefaultValue = { int a, int b=2 -> a+b }
assert closureWithTwoArgAndDefaultValue(1) == 3

def closureWithTwoArgAndDefaultValue2 = { int a=2, int b -> a+b }
assert closureWithTwoArgAndDefaultValue2(1) == 3

def greeting1 = { "Hello, $it!" }
assert greeting1('Groovy') == 'Hello, Groovy!'

def greeting2 = { it -> "Hello, $it!" }
assert greeting2('Groovy') == 'Hello, Groovy!'

def magicNumber = { -> 42 }
println(magicNumber())
//magicNumber(11) // 错误，不接受参数

def concat1 = { String... args -> args.join('') }
assert concat1('abc','def') == 'abcdef'
// 效果和数组参数一样
def concat2 = { String[] args -> args.join('') }
assert concat2('abc', 'def') == 'abcdef'

// 变长参数必须放在参数列表的最后
def multiConcat = { int n, String... args ->
    args.join('')*n
}
assert multiConcat(2, 'abc','def') == 'abcdefabcdef'

