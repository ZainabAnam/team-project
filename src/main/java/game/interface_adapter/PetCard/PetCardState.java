package game.interface_adapter.PetCard;

import game.entity.User;

public class PetCardState {
    private User user;
    private String errorMessage;
    private int newEnergyLevel;
    private int newAffectionXP;
    private int newPetLevel;
    private int newClickingSpeed;

    public PetCardState(PetCardState state) {
        this.user = state.user;
        this.errorMessage = state.errorMessage;
        this.newEnergyLevel = state.newEnergyLevel;
        this.newAffectionXP = state.newAffectionXP;
        this.newPetLevel = state.newPetLevel;
        this.newClickingSpeed = state.newClickingSpeed;
    }

    public PetCardState() {
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getNewEnergyLevel() {
        return newEnergyLevel;
    }

    public int getNewAffectionXP() {
        return newAffectionXP;
    }

    public int getNewPetLevel() {
        return newPetLevel;
    }

    public int getNewClickingSpeed() {
        return newClickingSpeed;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setNewEnergyLevel(int newEnergyLevel) {
        this.newEnergyLevel = newEnergyLevel;
    }

    public void setNewAffectionXP(int newAffectionXP) {
        this.newAffectionXP = newAffectionXP;
    }

    public void setNewPetLevel(int newPetLevel) {
        this.newPetLevel = newPetLevel;
    }

    public void setNewClickingSpeed(int newClickingSpeed) {
        this.newClickingSpeed = newClickingSpeed;
    }
}
