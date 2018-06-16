package com.cengenes.kotlin.api.controller

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.helper.JsonHelper
import com.cengenes.kotlin.api.model.request.CheckInRequest
import com.cengenes.kotlin.api.service.HotelServiceManager
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc;

    @MockBean
    private lateinit var hotelServiceManager: HotelServiceManager

    @Autowired
    private lateinit var jsonHelper: JsonHelper

    @Test
    fun it_should_get_all_hotels() {
        //Given
        val california = Hotel("Hotel California", 99L, 250L)
        val istanbul = Hotel("Istanbul", 101L, 400L)
        val newyork = Hotel("Newyork", 102L, 600L)

        given(hotelServiceManager.findAll()).willReturn(arrayListOf(california, istanbul, newyork))

        //When and Then
        mockMvc.perform(get("/hotels")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].name").value("Hotel California"))
                .andExpect(jsonPath("$[0].classification").value(99L))
                .andExpect(jsonPath("$[0].totalRoomCount").value(250L))
                .andExpect(jsonPath("$[0].freeRoomCount").value(250L))
                .andExpect(jsonPath("$[1].name").value("Istanbul"))
                .andExpect(jsonPath("$[1].classification").value(101L))
                .andExpect(jsonPath("$[1].totalRoomCount").value(400L))
                .andExpect(jsonPath("$[1].freeRoomCount").value(400L))
                .andExpect(jsonPath("$[2].name").value("Newyork"))
                .andExpect(jsonPath("$[2].classification").value(102L))
                .andExpect(jsonPath("$[2].totalRoomCount").value(600L))
                .andExpect(jsonPath("$[2].freeRoomCount").value(600L))
    }

    @Test
    fun it_should_get_hotel_by_name() {
        //Given
        val istanbul = Hotel("Istanbul", 101L, 400L)

        given(hotelServiceManager.findByName("Istanbul")).willReturn(istanbul)

        //When and Then
        mockMvc.perform(get("/hotels/{name}", "Istanbul")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name").value("Istanbul"))
                .andExpect(jsonPath("$.classification").value(101L))
                .andExpect(jsonPath("$.totalRoomCount").value(400L))
                .andExpect(jsonPath("$.freeRoomCount").value(400L))
    }


    @Test
    fun it_should_checkin_succesfully() {
        //Given
        val checkInRequest = CheckInRequest("Hilton Hotel", 5000L)

        given(hotelServiceManager.checkIn(checkInRequest)).willReturn(true)

        //When and Then
        mockMvc.perform(post("/hotels/checkIn")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonHelper.serializeToJson(checkInRequest)))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect({ mvcResult -> mvcResult.equals(true) })
    }

    @Test
    fun it_should_checkin_failed() {
        //Given
        val checkInRequest = CheckInRequest("Hilton Hotel", 5000L)

        given(hotelServiceManager.checkIn(checkInRequest)).willReturn(false)

        //When and Then
        mockMvc.perform(post("/hotels/checkIn")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonHelper.serializeToJson(checkInRequest)))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect({ mvcResult -> mvcResult.equals(false) })
    }
}