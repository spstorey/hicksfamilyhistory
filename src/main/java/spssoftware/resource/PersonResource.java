package spssoftware.resource;

public class PersonResource {

    public enum Sex {MALE, FEMALE}

    private String firstName;
    private String[] middlesName;
    private String surname;
    private Sex sex;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String[] getMiddlesName() {
        return middlesName;
    }

    public void setMiddlesName(String[] middlesName) {
        this.middlesName = middlesName;
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