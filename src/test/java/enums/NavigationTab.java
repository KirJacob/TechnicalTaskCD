package enums;

public enum NavigationTab {
    STUDENT_LOAN_REFINANCE("tab-refi"),
    PRIVATE_STUDENT_LOAN("tab-inschool"),
    PERSONAL_LOAN("tab-personal");

    private String navigationTabClassName;

    private NavigationTab(String navigationTabClassName) {
        this.navigationTabClassName = navigationTabClassName;
    }

    public String getNavigationTabClassName() {
        return navigationTabClassName;
    }

    @Override
    public String toString() {
        return "NavigationTab{" +
                "navigationTabClassName='" + navigationTabClassName + '\'' +
                '}';
    }
}
