package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.ArticleRequestDto
import ua.kpi.its.lab.rest.dto.ArticleResponseDto

interface ArticleService {

    fun getArticleById(id: Long): ArticleResponseDto?
    fun createArticle(articleDto: ArticleRequestDto): ArticleResponseDto
    fun updateArticle(id: Long, articleDto: ArticleRequestDto): ArticleResponseDto?
    fun deleteArticle(id: Long): Boolean
}