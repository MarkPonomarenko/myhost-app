package app.facade.impl;

import org.springframework.stereotype.Service;
import app.facade.SearchServerFacade;
import app.service.SearchServerService;

import java.util.List;

@Service
public class SearchServerFacadeImpl implements SearchServerFacade {

    private final SearchServerService searchServerService;

    public SearchServerFacadeImpl(SearchServerService searchServerService) {
        this.searchServerService = searchServerService;
    }

    @Override
    public List<String> fetchSuggestions(String query) {
        return searchServerService.fetchSuggestions(query);
    }
}
