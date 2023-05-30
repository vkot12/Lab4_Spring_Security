package ua.kpi.its.lab.rest.svc.impl

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.dto.ArticleRequestDto
import ua.kpi.its.lab.rest.dto.ArticleResponseDto
import ua.kpi.its.lab.rest.entity.Article
import ua.kpi.its.lab.rest.svc.ArticleService
import ua.kpi.its.lab.rest.repository.ArticleRepository

@Service
class ArticleServiceImpl(
    private val articleRepository: ArticleRepository
) : ArticleService {

    @PreAuthorize("hasRole('EDITOR')")
    override fun createArticle(articleRequest: ArticleRequestDto): ArticleResponseDto {
        val articles = Article (name = articleRequest.name, author = articleRequest.author, numberofwords = articleRequest.numberofwords, numberoflinks = articleRequest.numberoflinks, dateofwriting = articleRequest.dateofwriting, originallanguage = articleRequest.originallanguage)
        val savedArticles = articleRepository.save(articles)
        return ArticleResponseDto.fromEntity(savedArticles)
    }
    @PreAuthorize("hasAnyRole('EDITOR', 'VIEWER')")
    override fun getArticleById(id: Long): ArticleResponseDto {
        val articles = articleRepository.findById(id).orElseThrow()
        return ArticleResponseDto.fromEntity(articles)
    }
    @PreAuthorize("hasRole('EDITOR')")
    override fun updateArticle(id: Long, articleRequest: ArticleRequestDto): ArticleResponseDto {
        val articles = articleRepository.findById(id).orElseThrow()
        articles.name = articleRequest.name
        articles.author = articleRequest.author
        val updateArticles = articleRepository.save(articles)
        return ArticleResponseDto.fromEntity(updateArticles)
    }
    @PreAuthorize("hasRole('EDITOR')")
    override fun deleteArticle(id: Long): Boolean {
        return if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}