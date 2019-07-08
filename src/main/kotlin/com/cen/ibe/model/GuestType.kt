package com.cen.ibe.model

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class GuestType(
        @Column
        var name: String

) : BaseEntity() {
        override fun toString(): String {
                return "GuestType(name='$name')"
        }
}