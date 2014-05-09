package spssoftware.business;


public class NameBuilder {

    private StringBuilder builder;

    public NameBuilder() {
        builder = new StringBuilder();
    }

    public NameBuilder withTitle(String value) {
        if (value != null) {
            builder.append(value).append(" ");
        }
        return this;
    }

    public NameBuilder withFirstName(String value) {
        if (value != null) {
            builder.append(value).append(" ");
        }
        return this;
    }

    public NameBuilder withSurname(String value) {
        if (value != null) {
            builder.append(value).append(" ");
        }
        return this;
    }

    public String build() {
        return builder.toString().trim();
    }
}
