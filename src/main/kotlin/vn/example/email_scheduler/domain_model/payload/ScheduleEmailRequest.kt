package vn.example.email_scheduler.domain_model.payload


import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.ZoneId
import javax.validation.constraints.Email


@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ScheduleEmailRequest (
    @JsonProperty("email")
    @Email
    val email: String,
    @JsonProperty("subject")
    val subject: String,
    @JsonProperty("body")
    val body: String,
    @JsonProperty("dateTime")
    val dateTime: LocalDateTime,
    @JsonProperty("timeZone")
    val timeZone: ZoneId,
)

