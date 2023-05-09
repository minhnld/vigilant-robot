package vn.example.email_scheduler.domain_model.queue

import vn.example.email_scheduler.domain_model.InputJobEvent

interface InputJobPublisher {
    fun publish(inputJobEvent: InputJobEvent)
}