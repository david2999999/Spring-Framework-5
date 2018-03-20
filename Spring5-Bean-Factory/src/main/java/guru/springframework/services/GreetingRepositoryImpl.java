package guru.springframework.services;

import org.springframework.stereotype.Component;

@Component
public class GreetingRepositoryImpl implements GreetingRepository {

    @Override
    public String getEnglishGreeting() {
        return "Hello, Primary Greeting Service";
    }

    @Override
    public String getSpanishGreeting() {
        return "Servicido de Saludo Primaro";
    }

    @Override
    public String getGermanGreeting() {
        return "Pimarer grudienst";
    }
}
