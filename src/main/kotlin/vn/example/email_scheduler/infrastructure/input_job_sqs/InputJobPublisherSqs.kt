package vn.example.email_scheduler.infrastructure.input_job_sqs

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import vn.example.email_scheduler.domain_model.InputJobEvent
import vn.example.email_scheduler.domain_model.queue.InputJobPublisher

@Component
class InputJobPublisherSqs(
    private val sqsClient: AmazonSQS,
    private val queueUrl: String
): InputJobPublisher {

//    fun publishJob(job: ScheduledJob) {
//        val message = ObjectMapper().writeValueAsString(job)
//        sqsClient.sendMessage(SendMessageRequest(queueUrl, message))
//    }
//
//    fun receiveJobs(): List<ScheduledJob> {
//        val receiveMessageRequest = ReceiveMessageRequest(queueUrl)
//            .withMaxNumberOfMessages(10)
//            .withWaitTimeSeconds(20)
//        val messages = sqsClient.receiveMessage(receiveMessageRequest).messages
//        return messages.mapNotNull { message ->
//            try {
//                ObjectMapper().readValue(message.body, ScheduledJob::class.java)
//            } catch (e: Exception) {
//                null
//            }
//        }
//    }


    override fun publish(inputJobEvent: InputJobEvent) {
        val message = ObjectMapper().writeValueAsString(inputJobEvent)
        sqsClient.sendMessage(SendMessageRequest(queueUrl, message))
    }
}
