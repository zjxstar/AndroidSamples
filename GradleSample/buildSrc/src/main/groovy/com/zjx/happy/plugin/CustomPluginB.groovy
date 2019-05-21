package com.zjx.happy.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

// 在单独的groovy文件中定义插件类
class CustomPluginB implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task('CustomPluginTaskB') {
            doFirst {
                println 'This is custom plugin TaskB'
            }
        }
    }
}