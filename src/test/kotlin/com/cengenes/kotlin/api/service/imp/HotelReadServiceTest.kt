package com.cengenes.kotlin.api.service.imp

import com.cengenes.kotlin.api.repository.HotelRepository
import com.cengenes.kotlin.api.service.HotelReadService
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HotelReadServiceTest {

    @InjectMocks
    private lateinit var hotelReadService: HotelReadService

    @Mock
    private lateinit var hotelRepository: HotelRepository


}