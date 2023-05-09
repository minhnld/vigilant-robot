package vn.example.email_scheduler.application.web.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import vn.example.email_scheduler.domain_model.queue.InputJobPublisher
import vn.example.email_scheduler.domain_model.EmailDetail
import vn.example.email_scheduler.domain_model.ScheduleType
import vn.example.email_scheduler.domain_model.EmailTrigger
import vn.example.email_scheduler.domain_model.InputJobEvent
import vn.example.email_scheduler.domain_model.payload.ScheduleEmailRequest
import vn.example.email_scheduler.domain_model.payload.ScheduleEmailResponse
import java.time.ZonedDateTime
import java.util.*
import javax.validation.Valid

@RestController
class EmailSchedulerController(
    private val inputJobPublisher: InputJobPublisher
) {

    private val logger: Logger =
        LoggerFactory.getLogger(EmailSchedulerController::class.java)

    @PostMapping("/scheduleEmail")
    fun scheduleEmail(@Valid @RequestBody scheduleEmailRequest: ScheduleEmailRequest): ResponseEntity<ScheduleEmailResponse?>? {
        return try {
            val dateTime = ZonedDateTime.of(scheduleEmailRequest.dateTime, scheduleEmailRequest.timeZone)
            if (dateTime.isBefore(ZonedDateTime.now())) {
                val scheduleEmailResponse = ScheduleEmailResponse(
                    false,
                    message = "dateTime must be after current time"
                )
                return ResponseEntity.badRequest().body<ScheduleEmailResponse?>(scheduleEmailResponse)
            }
            val emailDetail: EmailDetail = buildJobDetail(scheduleEmailRequest)
            val emailTrigger: EmailTrigger = buildJobTrigger(dateTime)
            val inputJobEvent = InputJobEvent.from(emailDetail, emailTrigger)
            inputJobPublisher.publish(inputJobEvent)

            val scheduleEmailResponse = ScheduleEmailResponse(
                true,
                inputJobEvent.inputJobEventId.toString(),
                "Email Scheduled Successfully!"
            )
            ResponseEntity.ok<ScheduleEmailResponse?>(scheduleEmailResponse)
        } catch (ex: Exception) {
            logger.error(ex.message)
            val scheduleEmailResponse = ScheduleEmailResponse(
                false,
                message = "Error scheduling email. Please try later!"
            )
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body<ScheduleEmailResponse?>(scheduleEmailResponse)
        }
    }

    private fun buildJobDetail(scheduleEmailRequest: ScheduleEmailRequest): EmailDetail {
        return EmailDetail(
            "",
            scheduleEmailRequest.email,
            scheduleEmailRequest.subject,
            scheduleEmailRequest.body
        )
    }

    private fun buildJobTrigger(startAt: ZonedDateTime): EmailTrigger {
        return EmailTrigger(
            ScheduleType.SIMPLE,
            startAt.toString()
        )
    }
}
