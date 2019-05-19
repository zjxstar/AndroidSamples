package com.zjx.happy.closure

/**
 * 闭包的委托策略
 * @author zjxstar
 */

/**---------------------------this-----------------------------*/

/**
 * 普通类
 */
class Enclosing {
    void run() {
        // getThisObject指向Enclosing
        def whatIsThisObject = { getThisObject() }
        assert whatIsThisObject() == this
        println('getThisObject(): ' + whatIsThisObject())

        // this指向Enclosing
        // this和getThisObject是等价的
        def whatIsThis = { this }
        assert whatIsThis() == this
        println('this in Closure: ' + whatIsThis())
        println('this: ' + this)
    }
}

new Enclosing().run()

/**
 * 内部类
 */
class EnclosedInInnerClass {
    class Inner {
        // 此处的this指向Inner，而不是EnclosedInInnerClass
        Closure cl = { this }
    }
    void run() {
        def inner = new Inner()
        assert inner.cl() == inner
        println('Closure in Inner, this: ' + inner.cl())
    }
}

new EnclosedInInnerClass().run()

/**
 * 嵌套闭包
 */
class NestedClosures {
    // this始终指向NestedClosures
    void run() {
        def nestedClosures = {
            def cl = { this }
            println("cl this: " + cl())
            cl()
        }
        assert nestedClosures() == this
        println("nestedClosures this: " + nestedClosures())
        println("this: " + nestedClosures())
    }
}

new NestedClosures().run()

/**---------------------------owner-----------------------------*/

/**
 * 普通类
 */
class EnclosingOwner {
    void run() {
        // getOwner指向EnclosingOwner
        def whatIsOwnerMethod = { getOwner() }
        assert whatIsOwnerMethod() == this
        // owner指向EnclosingOwner
        def whatIsOwner = { owner }
        assert whatIsOwner() == this

        println('getOwner: ' + whatIsOwnerMethod())
        println('owner: ' + whatIsOwner())
        println('this: ' + this)
    }
}

new EnclosingOwner().run()

/**
 * 内部类
 */
class EnclosedInInnerClassOwner {
    class Inner {
        // 指向Inner
        Closure cl = { owner }
    }
    void run() {
        def inner = new Inner()
        assert inner.cl() == inner
        println('inner owner: ' + inner.cl())
    }
}

new EnclosedInInnerClassOwner().run()

/**
 * 嵌套闭包
 */
class NestedClosuresOwner {
    void run() {
        def nestedClosures = {
            // 指向的就是nestedClosures闭包对象了，而不是NestedClosuresOwner
            // 这里就和this不一样了
            def cl = { owner }
            println('Closure owner: ' + cl())
            println('Closure this: ' + this)
            cl()
        }
        assert nestedClosures() == nestedClosures
    }
}

new NestedClosuresOwner().run()

/**---------------------------delegate-----------------------------*/

/**
 * delegate默认表现
 */
class EnclosingDelegate {
    void run() {
        // 指向的是EnclosingDelegate
        // 和 this、owner表现一致
        def cl = { getDelegate() }
        def cl2 = { delegate }
        assert cl() == cl2()
        assert cl() == this
        println(cl())

        // 指向的是enclosed
        // 和 owner表现一致
        def enclosed = {
            { -> delegate }.call()
        }
        assert enclosed() == enclosed
        println(enclosed())
    }
}

new EnclosingDelegate().run()

/**
 * delegate的动态表现
 */
class Person {
    String name
}
class Thing {
    String name
}

def p = new Person(name: 'Tom')
def t = new Thing(name: 'Teapot')

def upperCasedName = { delegate.name.toUpperCase() }
// upperCasedName() // 直接报错，因为delegate指向的对象没有name属性
upperCasedName.delegate = p
println(upperCasedName()) // delegate指向Person
upperCasedName.delegate = t
println(upperCasedName()) // delegate指向Thing

//name = 'jerry' // 如果定义了一个name，下面的闭包会打印JERRY
// 不显式使用 delegate.
def upperCasedName2 = {
    println(name.toUpperCase()) // 打印TOM
    // 返回owner对象
    owner
}
// 直接赋值
upperCasedName2.delegate = p
// 正常工作
println(upperCasedName2()) // 打印owner对象，指向的是groovy脚本
println(upperCasedName2() instanceof GroovyObject) // 其实是GroovyObject类实例

/**
 * 切换策略
 */
class Person2 {
    String name
    int age
    def fetchAge = { age }
}
class Thing2 {
    String name
}

def p2 = new Person2(name:'Jessica', age:42)
def t2 = new Thing2(name:'Printer')
def cl = p2.fetchAge
cl.delegate = p2
assert cl() == 42 // owner里有age
cl.delegate = t2
assert cl() == 42 // 还是找的owner，因为默认策略是owner优先
cl.resolveStrategy = Closure.DELEGATE_ONLY //切换为只使用delegate
cl.delegate = p2
assert cl() == 42 // delegate指向p2，有age属性
cl.delegate = t2
try {
    cl() // delegate指向t2，但t2中没有age属性，会抛出异常
    assert false
} catch (MissingPropertyException ex) {
    // "age" is not defined on the delegate
}

