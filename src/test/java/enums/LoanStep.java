package enums;

public enum LoanStep {
    PERSONAL("Personal", "personal"),
    PROFILE("Profile", "profile");

    private String visibleName;
    private String className;

    private LoanStep(String visibleName, String className){
        this.visibleName = visibleName;
        this.className = className;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public String getClassName() {
        return className;
    }
}
