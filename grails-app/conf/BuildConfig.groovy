/*
 * Copyright 2013 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.apache.ivy.plugins.resolver.FileSystemResolver
import org.apache.ivy.plugins.resolver.URLResolver

grails.project.work.dir = 'work'
grails.project.class.dir = 'target/classes'
grails.project.test.class.dir = 'target/test-classes'
grails.project.test.reports.dir = 'target/test-reports'
grails.project.war.file = "target/${appName}.war"

grails.project.dependency.resolver = "maven"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
    }

    dependencies {

        compile(
                // Amazon Web Services programmatic interface
                'com.amazonaws:aws-java-sdk:1.9.23',

                // Transitive dependencies of aws-java-sdk, but also used directly.
                'org.apache.httpcomponents:httpcore:4.4',
                'org.apache.httpcomponents:httpclient:4.4',

                // Explicitly including aws-java-sdk transitive dependencies
                //'com.fasterxml.jackson.core:jackson-annotations:2.4.2',
                //'com.fasterxml.jackson.core:jackson-databind:2.4.2',
                //'com.fasterxml.jackson.core:jackson-core:2.4.2',

                // Extra collection types and utilities
                'commons-collections:commons-collections:3.2.1',
                'commons-io:commons-io:2.4',

                // Easier Java from of the Apache Foundation
                'commons-lang:commons-lang:2.6',
     
                // Better Zip Support
                'org.apache.commons:commons-compress:1.9',

                // Easier Java from Joshua Bloch and Google
                'com.google.guava:guava:18.0',

                // Send emails about system errors and task completions
                'javax.mail:mail:1.4.7',

                // Better date API
                'joda-time:joda-time:2.7',

                'net.sourceforge.javacsv:javacsv:2.0',

                'org.apache.poi:poi-ooxml:3.11',
                'org.codehaus.woodstox:wstx-asl:4.0.6',
                'jfree:jfreechart:1.0.13',
                'org.json:json:20140107',
                'org.mapdb:mapdb:1.0.7'

        ) { // Exclude superfluous and dangerous transitive dependencies
            excludes(
                    // Some libraries bring older versions of JUnit as a transitive dependency and that can interfere
                    // with Grails' built in JUnit
                    'junit',

                    'mockito-core',
            )
        }
    }

    plugins {
        build ":tomcat:8.0.20"
    }
}
