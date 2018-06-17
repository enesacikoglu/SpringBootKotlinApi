package com.cengenes.kotlin.api.controller

import com.cengenes.kotlin.api.entity.Hotel
import com.cengenes.kotlin.api.model.request.CheckInRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HotelControllerIT {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate


    @Test
    fun it_should_get_hotel_by_name() {
        //Given
        var hotelName = "Hotel California"

        //When
        val hotelResponse: ResponseEntity<Hotel> = restTemplate.getForEntity(url = "/hotels/" + hotelName, uriVariables = Hotel::class)

        //Then
        assertThat(hotelResponse).isNotNull
        assertThat(hotelResponse.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(hotelResponse.body?.name).isEqualTo("Hotel California")
        assertThat(hotelResponse.body?.classification).isEqualTo(101L)
        assertThat(hotelResponse.body?.totalRoomCount).isEqualTo(200L)
        assertThat(hotelResponse.body?.freeRoomCount).isEqualTo(1L)
    }

    @Test
    fun it_should_get_all_hotels() {
        //Given

        //When
        val hotelResponse: ResponseEntity<MutableList<Hotel>> = restTemplate.exchange("/hotels", HttpMethod.GET, HttpEntity.EMPTY, object : ParameterizedTypeReference<MutableList<Hotel>>() {})

        //Then
        assertThat(hotelResponse).isNotNull
        assertThat(hotelResponse.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(hotelResponse.body?.get(0)?.name).isEqualTo("Hotel California")
        assertThat(hotelResponse.body?.get(0)?.classification).isEqualTo(101L)
        assertThat(hotelResponse.body?.get(0)?.totalRoomCount).isEqualTo(200L)
        assertThat(hotelResponse.body?.get(0)?.freeRoomCount).isEqualTo(1L)
        assertThat(hotelResponse.body?.get(1)?.name).isEqualTo("Ns Hilton")
        assertThat(hotelResponse.body?.get(1)?.classification).isEqualTo(102L)
        assertThat(hotelResponse.body?.get(1)?.totalRoomCount).isEqualTo(400L)
        assertThat(hotelResponse.body?.get(1)?.freeRoomCount).isEqualTo(400L)
    }

    @Test
    fun it_should_checkin_succesfully_when_free_room_count_is_available() {
        //Given
        val checkInRequest = CheckInRequest("Hotel California", 199L)

        //When
        val response = restTemplate.postForEntity("/hotels/checkIn", checkInRequest, Boolean::class.java)

        //Then
        assertThat(response).isNotNull
        assertThat(response.body).isTrue()

    }

    @Test
    fun it_should_checkin_failed_when_free_room_count_is_not_available() {
        //Given
        val checkInRequest = CheckInRequest("Hotel California", 201L)

        //When
        val response = restTemplate.postForEntity("/hotels/checkIn", checkInRequest, Boolean::class.java)

        //Then
        assertThat(response).isNotNull
        assertThat(response.body).isFalse()
    }
}