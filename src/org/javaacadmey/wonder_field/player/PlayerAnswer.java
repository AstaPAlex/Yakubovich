package org.javaacadmey.wonder_field.player;

public class PlayerAnswer {
    final private String answerPlayer;
    final private String   typeAnswerPlayer;

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
