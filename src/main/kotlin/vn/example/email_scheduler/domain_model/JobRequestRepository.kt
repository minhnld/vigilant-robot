package vn.example.email_scheduler.domain_model

import org.springframework.data.jpa.repository.JpaRepository
import vn.example.email_scheduler.domain_model.payload.ScheduleEmailRequest

interface JobRequestRepository : JpaRepository<ScheduleEmailRequest, Long> {
    fun findByStatus(status: String): List<Int>
}