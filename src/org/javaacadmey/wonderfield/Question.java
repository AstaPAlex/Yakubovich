package org.javaacadmey.wonderfield;

public class Question {
    private final String[] questions;
    private final String[] answers;
    private final int numberRounds;

    public Question(int numberRounds) {
        this.questions = new String[numberRounds];
        this.answers = new String[numberRounds];
        this.numberRounds = numberRounds;
    }

    public void createQuestionAndAnswerAuto() {
        questions[0] = "Какое существо может задержать дыхание на 6 суток?";
        answers[0] = "Скорпион";
        questions[1] = "Какое живое существо может спать, не просыпаясь 3 года?";
        answers[1] = "Улитка";
        questions[2] = "У какого живого существа кровь синего цвета?";
        answers[2] = "Осьминог";
        questions[3] = "Какая страна мира имеет две столицы?";
        answers[3] = "Боливия";
        questions[4] = "Как у западных и южных славян назывались селение, деревня, курень?";
        answers[4] = "Жупа";
    }

    public void createQuestionAndAnswer() {
        for (int i = 0; i < numberRounds; i++) {
            System.out.println("Введите вопрос #" + (i + 1));
            questions[i] = Game.scanner.nextLine();
            System.out.println("Введите ответ #" + (i + 1));
            answers[i] = Game.scanner.nextLine();
        }
    }

    public String getQuestion(int numberRound) {
        return questions[numberRound];
    }

    public String getAnswer(int numberRound) {
        return answers[numberRound];
    }

}
