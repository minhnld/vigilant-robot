package vn.example.email_scheduler.domain_model.payload

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ScheduleEmailResponse(
    @JsonProperty("isSuccess")
    private val isSuccess: Boolean,
    @JsonProperty("jobId")
    private val jobId: String? = null,
    @JsonProperty("message")
    private val message: String
)

