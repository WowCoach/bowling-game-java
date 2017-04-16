package tw.game.bowling.model;

public enum FrameType {
    MISS("-"), SPARE("/"), REWARD(""), STRIKE("X");

    private String code;

    FrameType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isMatch(String code) {
        return this.code.equals(code);
    }
}
