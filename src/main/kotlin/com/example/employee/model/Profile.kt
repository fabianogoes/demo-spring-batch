package com.example.employee.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PROFILE")
class Profile(
    @Id
    var id: UUID? = UUID.randomUUID(),
    var empCode: Long = 0,
    var empName: String = "",
    var profileName: String = ""
) {
    override fun toString(): String {
        return "Profile(id=$id, empCode=$empCode, empName='$empName', profileName='$profileName')"
    }
}