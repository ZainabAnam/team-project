package game.use_case.GetPetFact;

public interface PetFactDataAccessInterface {
    java.util.Optional<GetPetFactOutputData> fetchFact(String species, String breed);
}
