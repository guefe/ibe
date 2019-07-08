package com.cen.ibe.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany

@Entity
class RoomType(

        @Column(nullable = false)
        var name: String,

        @Column(nullable = false, columnDefinition = "bpchar")
        var code: String,

        @Column(nullable = false)
        var capacity: Int,

        @OneToMany(mappedBy = "roomType", fetch = FetchType.LAZY)
        var preferredRoomConf: MutableList<PreferredRoomConf>

) : BaseEntity() {
        override fun toString(): String {
                return "RoomType(name='$name', code='$code', capacity=$capacity, preferredRoomConf=$preferredRoomConf)"
        }
}