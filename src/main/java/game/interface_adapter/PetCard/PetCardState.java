package game.interface_adapter.PetCard;

import game.entity.User;

public class PetCardState {
    private User user;
    private String errorMessage;
    private int newEnergyLevel;

    public PetCardState(PetCardState state) {
        this.user = state.user;
        this.errorMessage = state.errorMessage;
        this.newEnergyLevel = state.newEnergyLevel;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setNewEnergyLevel(int newEnergyLevel) {
        this.newEnergyLevel = newEnergyLevel;
    }
}
