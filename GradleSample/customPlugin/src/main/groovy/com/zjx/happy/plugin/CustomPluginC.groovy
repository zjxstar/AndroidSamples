package com.zjx.happy.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPluginC implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.task('CustomPluginTaskC') {
            doFirst {
                println 'This is Custom Plugin TaskC'
            }
        }

    }
}