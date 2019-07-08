package com.cen.ibe.repository

import com.cen.ibe.model.Room
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface RoomRepository : CrudRepository<Room, Long>{
    @Query(
            "select * from room where id not in " +
                    "(select r.id " +
                    " from " +
                    "    room r " +
                    "    join reservation_room rr on r.id = rr.room_id " +
                    "    join reservation res on rr.reservation_id = res.id " +
                    " where " +
                    "    res.start_date between :start_date and :end_date " +
                    "    or res.end_date between :start_date and :end_date)",
            nativeQuery = true)
    fun findAvailableRoomsByDates(@Param("start_date") startDate: LocalDate,
                                  @Param("end_date") endDate: LocalDate): MutableList<Room>
}