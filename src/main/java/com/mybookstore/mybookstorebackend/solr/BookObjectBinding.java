package com.mybookstore.mybookstorebackend.solr;

import com.mybookstore.mybookstorebackend.constant.SolrConstant;
import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.repository.BookRepository;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class BookObjectBinding {

    @Autowired
    private BookRepository bookRepository;

    public void indexing() throws IOException, SolrServerException {
        final SolrClient client = getSolrClient();

        final List<Book> bookList = bookRepository.getAll();
        for(Book b : bookList){
            final SolrBookObject solrBookObject = new SolrBookObject(b);
            final UpdateResponse response = client.addBean(SolrConstant.COLLECTION_NAME, solrBookObject);
        }

        client.commit(SolrConstant.COLLECTION_NAME);

        System.out.println("Solr: finish book indexing");

    }

    public List<SolrBookObject> querying(String field_search, String keyword) throws IOException, SolrServerException {
        final SolrClient client = getSolrClient();

        final SolrQuery query = new SolrQuery(String.format("%s:*%s*",field_search, keyword));
        query.addField("id");
        query.addField("name");
        query.addField("author");
        query.addField("description");
        query.addField("image");
        query.addField("isbn");
        query.setSort("id", SolrQuery.ORDER.asc);

        final QueryResponse responseOne = client.query(SolrConstant.COLLECTION_NAME, query);
        final List<SolrBookObject> products = responseOne.getBeans(SolrBookObject.class);
        return products;
    }

    public static SolrClient getSolrClient() {
        final String solrUrl = "http://localhost:8983/solr";
        return new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }
}
