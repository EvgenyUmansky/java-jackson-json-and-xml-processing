package jackson.topics.views;

public class MovieViews {
    public interface Public {
    }

    public interface Overview extends Public {
    }

    public interface Genres extends Public {
    }

    public interface ProductionCompanies extends Public {
    }

    public interface ProductionCountries extends Public {
    }

    public interface ProductionCountryName extends ProductionCountries {
    }
}
