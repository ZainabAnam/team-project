package game.interface_adapter.RenamePet;

public class RenamePetState {
        private String oldName = "";
        private String newName = "";
        private String message = "";
        private boolean success = false;
    private String previousView = "";

        public RenamePetState() {
        }

        // Copy constructor
        public RenamePetState(RenamePetState copy) {
            this.oldName = copy.oldName;
            this.newName = copy.newName;
            this.message = copy.message;
            this.success = copy.success;
            this.previousView = copy.previousView;
        }

        // Getters and Setters
        public String getOldName() {
            return oldName;
        }

        public void setOldName(String oldName) {
            this.oldName = oldName;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
        public String getPreviousView() {
            return previousView;
        }

        public void setPreviousView(String previousView) {
            this.previousView = previousView;
        }
    }
