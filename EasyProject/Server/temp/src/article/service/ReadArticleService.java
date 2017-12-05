package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.easycafe.dao.ArticleContentDao;
import com.easycafe.dao.ArticleDao;

import article.model.Article;
import article.model.ArticleContent;
import jdbc.connection.ConnectionProvider;

public class ReadArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public ArticleData getArticle(int articleNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Article article = articleDao.selectById(conn, articleNum);
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			ArticleContent content = contentDao.selectById(conn, articleNum);
			if(content == null) {
				throw new ArticleContentNotFoundException();
			}
			return new ArticleData(article,content);
		}catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
}
