package com.cen.ibe.model

import javax.persistence.MappedSuperclass
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue
import javax.persistence.Id


@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

}