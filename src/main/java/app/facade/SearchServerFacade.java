package app.facade;

import java.util.List;

public interface SearchServerFacade {

    List<String> fetchSuggestions(String query);
}
