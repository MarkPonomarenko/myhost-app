package app.logger;

public interface LoggerService {

    void commit(LoggerLevel level, String message);
}
