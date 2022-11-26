package app.service.impl;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import app.elastic.index.ServerIndex;
import app.service.SearchServerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServerServiceImpl implements SearchServerService {

    private static final String SERVER_INDEX = "serverindex";

    private final ElasticsearchOperations elasticsearchOperations;

    public SearchServerServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<String> fetchSuggestions(String query) {
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("model", query + "*");
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();
        SearchHits<ServerIndex> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        ServerIndex.class,
                        IndexCoordinates.of(SERVER_INDEX));
        final List<String> suggestions = new ArrayList<>();
        searchSuggestions.getSearchHits().forEach(searchHit -> suggestions.add(searchHit.getContent().getModel()));
        return suggestions;
    }
}
