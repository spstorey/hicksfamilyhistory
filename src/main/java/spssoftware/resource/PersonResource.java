package spssoftware.resource;

public class PersonResource {

    public enum Sex {MALE, FEMALE}

    private String firstName;
    private String[] middlesNames;
    private String surname;
    private Sex sex;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String[] getMiddlesNames() {
        return middlesNames;
    }

    public void setMiddlesNames(String[] middlesNames) {
        this.middlesNames = middlesNames;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}