package vn.example.email_scheduler.domain_model


import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import org.springframework.data.annotation.Id
import java.time.LocalDate
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

data class InputJobEvent(
    val emailDetail: EmailDetail,
    val emailTrigger: EmailTrigger,

    @JsonProperty("createAt")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer::class)
    val createAt: LocalDate = LocalDate.now(),

    ) {
    val inputJobEventId: Int get() = hashCode()
    companion object {
        fun from(emailDetail: EmailDetail, emailTrigger: EmailTrigger) = InputJobEvent(
            emailDetail = emailDetail,
            emailTrigger = emailTrigger
        )
    }

}