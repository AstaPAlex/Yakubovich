package org.javaacadmey.wonder_field.player;

public class PlayerAnswer {
    private final String answerPlayer;
    private final String   typeAnswerPlayer;

    public PlayerAnswer(String answerPlayer, String typeAnswerPlayer) {
        this.answerPlayer = answerPlayer.toUpperCase();
        this.typeAnswerPlayer = typeAnswerPlayer;
    }

    public String getAnswerPlayer() {
        return answerPlayer;
    }

    public String getTypeAnswerPlayer() {
        return typeAnswerPlayer;
    }
}
