package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.MagazineRequestDto
import ua.kpi.its.lab.rest.dto.MagazineResponseDto

interface MagazineService {

    fun getMagazineById(id: Long): MagazineResponseDto?
    fun createMagazine(magazineDto: MagazineRequestDto): MagazineResponseDto
    fun updateMagazine(id: Long, magazineDto: MagazineRequestDto): MagazineResponseDto?
    fun deleteMagazine(id: Long): Boolean
}