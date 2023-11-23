package org.javaacadmey.wonder_field.player;

public class PlayerAnswer {
    private String answerPlayer;
    private String   typeAnswerPlayer;

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
