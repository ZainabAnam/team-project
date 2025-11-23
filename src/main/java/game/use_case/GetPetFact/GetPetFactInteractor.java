package game.use_case.GetPetFact;

public final class GetPetFactInteractor implements GetPetFactInputBoundary {
    private final PetFactDataAccessInterface gateway;
    private final GetPetFactOutputBoundary presenter;
    public GetPetFactInteractor(PetFactDataAccessInterface g, GetPetFactOutputBoundary p){ this.gateway=g; this.presenter=p; }

    @Override
    public void execute(String species, String breed) {
        var out = gateway.fetchFact(species, breed)
                .orElse(new GetPetFactOutputData("", "", false));
        presenter.present(out);
    }
}
