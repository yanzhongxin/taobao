package cn.yanzhongxin.search.service.impl;

import cn.yanzhongxin.pojo.SearchResult;
import cn.yanzhongxin.search.dao.SearchDao;
import cn.yanzhongxin.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author www.yanzhongxin.com
 * @date 2019/1/6 15:51
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;

    @Value("${DEFAULT_FIELD}")
    private String DEFAULT_FIELD;

    @Override
    public SearchResult search(String keyWord, int page, int rows) throws Exception {
        //创建一个SolrQuery对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(keyWord);
        //设置分页条件
        query.setStart((page - 1) * rows);
        //设置rows
        query.setRows(rows);
        //设置默认搜索域
        query.set("df", DEFAULT_FIELD);
        //设置高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        //执行查询
        SearchResult searchResult = searchDao.search(query);
        //计算总页数
        int recourdCount = (int) searchResult.getRecordCount();
        int pages = recourdCount / rows;
        if (recourdCount % rows > 0) pages++;
        //设置到返回结果
        searchResult.setTotalPages(pages);
        return searchResult;
    }

}
