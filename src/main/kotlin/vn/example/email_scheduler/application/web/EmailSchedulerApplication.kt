package vn.example.email_scheduler.application.web

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmailSchedulerApplication

fun main(args: Array<String>) {
    runApplication<EmailSchedulerApplication>(*args)
}
