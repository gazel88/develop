package net.srook.common.code;

public enum PasswordTycd {
    TEMP("임시"),
    OFFICIAL("정식");

    private final String name;

    PasswordTycd(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
