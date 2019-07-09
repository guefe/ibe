package com.cen.ibe.repository

import com.cen.ibe.model.RoomType
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface RoomTypeRepository : CrudRepository<RoomType, Int> {
    @Query(
            "select rt.* " +
                    "from room_type rt " +
                    "         left join reservation_room rr on rt.id = rr.room_type_id " +
                    "         left join reservation res on rr.reservation_id = res.id " +
                    "where res.id is null or res.start_date between :start_date and :end_date " +
                    "   or res.end_date between :start_date and :end_date " +
                    "group by (rt.id) " +
                    "having rt.quantity > count(rr.id)",
            nativeQuery = true)
    fun findAvailableRoomsByDates(@Param("start_date") startDate: LocalDate,
                                  @Param("end_date") endDate: LocalDate): MutableList<RoomType>

    fun findByCode(roomTypeCode: String): RoomType
}