package game.data_access;

import game.use_case.GetPetFact.PetFactDataAccessInterface;
import game.use_case.GetPetFact.GetPetFactOutputData;

import java.util.Optional;

public class CompositePetFactDataAccessObject implements PetFactDataAccessInterface {
    private final PetFactDataAccessInterface dogDao;
    private final PetFactDataAccessInterface catDao;

    public CompositePetFactDataAccessObject(PetFactDataAccessInterface dogDao,
                                            PetFactDataAccessInterface catDao) {
        this.dogDao = dogDao;
        this.catDao = catDao;
    }

    @Override
    public Optional<GetPetFactOutputData> fetchFact(String species, String breed) {
        if (species == null) return Optional.empty();

        switch (species.toLowerCase()) {
            case "dog":
                return dogDao.fetchFact(species, breed);
            case "cat":
                return catDao.fetchFact(species, breed);
            default:
                return Optional.empty();
        }
    }
}

