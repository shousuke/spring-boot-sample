package entpay.util;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import entpay.model.Page;
import entpay.model.PageQuery;

public class PagerUtil {
	private static final int ADJACENTS = 4;
    
    public static Page process(int pageNo, int countPerPage, int totalRowCnt) {
    	int totalPage = (int) Math.ceil((double) totalRowCnt / countPerPage);
        
        if (totalPage == 0) {
        	totalPage = 1;
        }
        
        if (pageNo <= 0) {
        	pageNo = 1;
        }
        
        if (pageNo > totalPage) {
        	pageNo = totalPage;
        }
        
        int prevPage = (pageNo == 1) ? 1 : (pageNo - 1);
        int nextPage = (pageNo == totalPage) ? totalPage : (pageNo + 1);
        
        int startPage = (pageNo > ADJACENTS) ? (pageNo - ADJACENTS) : 1;
        int endPage = startPage + ADJACENTS * 2;
        
        if (endPage > totalPage) {
        	endPage = totalPage;
        }
        
        int[] pages = new int[endPage + 1];
        for (int i = 0; i < endPage - startPage; i++) {
            pages[i] = startPage + i;
        }
        
    	Page page = new Page();
    	page.setCountPerPage(countPerPage);
    	page.setEndPage(endPage);
    	page.setNextPage(nextPage);
    	page.setPage(pageNo);
    	page.setPages(pages);
    	page.setPrevPage(prevPage);
    	page.setStart((pageNo - 1) * countPerPage);
    	page.setStartPage(startPage);
    	page.setTotalCount(totalRowCnt);
    	page.setTotalPage(totalPage);
        
        return page;
    }
    
    public static PageQuery getPagedQuery(DataSource dataSource, String query, Object[] params, 
    		int page, int countPerPage) {
    	String countSql = "SELECT COUNT(*) " + query.substring(query.indexOf("FROM"));
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int totalCount = jdbcTemplate.queryForObject(countSql, params, Integer.class);
		Page pager = process(page, countPerPage, totalCount);
		query += " LIMIT " + pager.getStart() + ", " + countPerPage;
		
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPagination(pager);
		pageQuery.setQuery(query);
		
		return pageQuery;
    }
}
