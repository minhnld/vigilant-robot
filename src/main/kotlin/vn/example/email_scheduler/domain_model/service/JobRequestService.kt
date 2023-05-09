//package vn.example.email_scheduler.domain_model.service
//
//import org.springframework.stereotype.Service
//import vn.example.email_scheduler.domain_model.JobDetail
//import vn.example.email_scheduler.domain_model.JobRequest
//import vn.example.email_scheduler.domain_model.JobRequestRepository
//
//@Service
//class JobRequestService(
//    private val jobRequestRepository: JobRequestRepository
//) {
//    fun save(jobRequest: JobDetail): JobDetail {
//        return jobRequestRepository.save(jobRequest)
//    }
//
//    fun findScheduledJobs(): List<JobRequest> {
//        val scheduledJobs = jobRequestRepository.findByStatus("SCHEDULED")
//        scheduledJobs.forEach {
//            it.status = "PENDING"
//            jobRequestRepository.save(it)
//        }
//        return scheduledJobs
//    }
//}
