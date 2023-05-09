//package vn.example.email_scheduler.infrastructure.input_job_sqs.spring
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider
//import com.amazonaws.auth.BasicAWSCredentials
//import com.amazonaws.services.sqs.AmazonSQS
//import com.amazonaws.services.sqs.AmazonSQSClientBuilder
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import vn.example.email_scheduler.domain_model.queue.InputJobPublisher
//import vn.example.email_scheduler.infrastructure.input_job_sqs.InputJobPublisherSqs
//
//
//@Configuration
//class InputJobSqsConfig {
//
//    @Value("\${aws.sqs.queue.url}")
//    private lateinit var queueUrl: String
//
//
//    @Value("\${app.config.aws.access_key_id}")
//    private val awsAccessKeyId: String? = null
//
//    @Value("\${app.config.aws.secret_key_id}")
//    private val awsSecretKeyId: String? = null
//
//    @Bean
//    fun amazonSQSClient(): AmazonSQS {
//        val awsCredentials = BasicAWSCredentials(awsAccessKeyId, awsSecretKeyId)
//        return AmazonSQSClientBuilder.standard()
//            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
//            .withRegion("ap-southeast-1").build()
//    }
//
//    @Bean
//    fun inputJobPublisherSqs(): InputJobPublisher {
//        return InputJobPublisherSqs(
//            amazonSQSClient(),
//            queueUrl
//        )
//    }
//}