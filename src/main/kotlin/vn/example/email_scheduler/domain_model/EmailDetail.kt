package vn.example.email_scheduler.domain_model



data class EmailDetail (
    val description: String? = null,
    val email: String,
    val subject: String,
    val body: String,
)
