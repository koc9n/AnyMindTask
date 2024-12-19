package org.test.anymindtask.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class AdditionalItem @JsonCreator constructor(
    @JsonProperty("last4") val last4: String?,
    @JsonProperty("courier") val courier: String?,
    @JsonProperty("bankName") val bankName: String?,
    @JsonProperty("accountNumber") val accountNumber: String?,
    @JsonProperty("chequeNumber") val chequeNumber: String?
)
