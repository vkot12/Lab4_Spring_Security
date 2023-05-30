package ua.kpi.its.lab.rest.svc.impl

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.dto.MagazineRequestDto
import ua.kpi.its.lab.rest.dto.MagazineResponseDto
import ua.kpi.its.lab.rest.entity.Magazine
import ua.kpi.its.lab.rest.svc.MagazineService
import ua.kpi.its.lab.rest.repository.MagazineRepository
import java.time.LocalDate

@Service
class MagazineServiceImpl(
    private val magazineRepository: MagazineRepository
) : MagazineService {

    @PreAuthorize("hasRole('EDITOR')")
    override fun createMagazine(magazineRequest: MagazineRequestDto): MagazineResponseDto {
        val magazines = Magazine (name = magazineRequest.name, subject = magazineRequest.subject, dateofestablishment = LocalDate.EPOCH, ISSN = magazineRequest.ISSN, price = magazineRequest.price, periodicity = magazineRequest.periodicity)
        val savedMagazines = magazineRepository.save(magazines)
        return MagazineResponseDto.fromEntity(savedMagazines)
    }
    @PreAuthorize("hasAnyRole('EDITOR', 'VIEWER')")
    override fun getMagazineById(id: Long): MagazineResponseDto {
        val magazines = magazineRepository.findById(id).orElseThrow()
        return MagazineResponseDto.fromEntity(magazines)
    }
    @PreAuthorize("hasRole('EDITOR')")
    override fun updateMagazine(id: Long, magazineRequest: MagazineRequestDto): MagazineResponseDto {
        val magazines = magazineRepository.findById(id).orElseThrow()
        magazines.name = magazineRequest.name
        magazines.subject = magazineRequest.subject
        val updateMagazines = magazineRepository.save(magazines)
        return MagazineResponseDto.fromEntity(updateMagazines)
    }
    @PreAuthorize("hasRole('EDITOR')")
    override fun deleteMagazine(id: Long): Boolean {
        return if (magazineRepository.existsById(id)) {
            magazineRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}