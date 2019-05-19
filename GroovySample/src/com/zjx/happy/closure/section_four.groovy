package com.zjx.happy.closure

/**
 * 动态闭包
 * @author zjxstar
 */

def say(closure) {
    if (closure) { // 判断是否传入了一个闭包
        closure()
    } else {
        println('say nothing')
    }
}

say()
say() {
    println('i\'m hungry')
}

def compute(amount, Closure computer) {
    total = 0
    // 判断闭包的参数个数是否为2
    if (computer.maximumNumberOfParameters == 2) {
        total = computer(amount, 6)
    } else {
        total = computer(amount)
    }

    println("total is $total")
}

compute(100) {
    it * 8 // 800
}

compute(100) { // 600
amount, weight ->
    amount * weight
}

/**
 * 对闭包的参数类型做要求
 * @param closure
 */
def execute(Closure closure) {
    println("params count: ${closure.maximumNumberOfParameters}")

    if (closure.delegate != closure.owner) {
        println('I have a custom delegate')
    }

    // 遍历闭包的参数类型
    for (paramType in closure.parameterTypes) {
        if (paramType.name == 'java.util.Date') {
            println('Force today')
        } else {
            println(paramType.name)
        }

    }
}

execute {} // 一个Object类型参数
execute { it } // 一个默认参数，Object类型
execute { -> } // 没有参数
execute { String value -> println(value) } // 一个String类型参数
execute {int a, Date b -> println('Two params')} // 两个参数，int类型和Date类型

class A {
    String name
}
Closure closure = { println(it) }
closure.delegate = new A(name: 'Tom')
execute(closure)
