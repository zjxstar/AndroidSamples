package com.zjx.happy.closure

/**
 * 闭包带来的好处
 * @author zjxstar
 */

/**
 * 传统方式实现1到n的奇数和
 * @param n
 * @return
 */
def sum(n) {
    total = 0
    for (int i = 1; i <= n; i += 2) {
        // 计算1到n的奇数和
        total += i
    }
    total
}

println('传统方式实现 sum = ' + sum(9))

/**
 * 传统方式实现1到n的奇数乘积
 * @param n
 * @return
 */
def multiply(n) {
    total = 1
    for (int i = 1; i <= n; i += 2) {
        // 计算1到n的奇数乘积
        total *= i
    }
    total
}

println('传统方式实现 multiply = ' + multiply(9))

/**
 * 使用闭包实现
 * @param n
 * @param closure
 */
def pickOdd(n, closure) { // 此处的closure可以换成别的参数名
    for (int i = 1; i <= n; i += 2) {
        // 执行逻辑都由传入的闭包决定
        closure(i)
    }
}

/**
 * 打印奇数
 */
pickOdd(9) {
    println it
}

/**
 * 求和
 */
total = 0
pickOdd(9, {
    num -> total += num // 可以访问total变量
})
println("Sum of odd is: ${total}")