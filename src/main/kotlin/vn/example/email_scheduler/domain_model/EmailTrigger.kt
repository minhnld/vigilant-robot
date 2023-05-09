package vn.example.email_scheduler.domain_model

enum class ScheduleType {
    SIMPLE
}

data class EmailTrigger(
    val scheduleType: ScheduleType,
    val scheduleValue: String,
)