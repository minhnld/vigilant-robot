package vn.example.email_scheduler.application.web.spring

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import vn.example.email_scheduler.domain_model.queue.InputJobPublisher
import vn.example.email_scheduler.infrastructure.input_job_sqs.InputJobPublisherSqs


@Configuration
class InputJobSqsConfig {

    @Value("\${aws.sqs.queue.url}")
    private lateinit var queueUrl: String


    @Value("\${aws.accessKeyId}")
    private val awsAccessKeyId: String? = null

    @Value("\${aws.secretKey}")
    private val awsSecretKeyId: String? = null

    @Value("\${aws.region}")
    private val awsRegion: String? = null

    @Bean
    fun amazonSQSClient(): AmazonSQS {
        val awsCredentials = BasicAWSCredentials(awsAccessKeyId, awsSecretKeyId)
        return AmazonSQSClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .withRegion(awsRegion).build()
    }

    @Bean
    fun inputJobPublisherSqs(): InputJobPublisher {
        return InputJobPublisherSqs(
            amazonSQSClient(),
            queueUrl
        )
    }
}