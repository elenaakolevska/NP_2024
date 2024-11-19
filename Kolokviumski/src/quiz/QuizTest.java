package quiz;


import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import java.util.Scanner;

class InvalidOperationException extends Exception {
    public InvalidOperationException(String message) {
        super(message);
    }
}

enum TYPE {
    TF,
    MC
}

abstract class Question implements Comparable<Question> {
    TYPE type;
    String text;
    double points;
    String answer;

    public Question(TYPE type, String text, double points, String answer) {
        this.type = type;
        this.text = text;
        this.points = points;
        this.answer = answer;
    }

    public TYPE getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public double getPoints() {
        return points;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public int compareTo(Question o) {
        return Double.compare(this.points, o.points);
    }

    public abstract double checkAnswer(String givenAnswer);

}

class TFQuestion extends Question {
    boolean answer;

    public TFQuestion(TYPE type, String text, double points, String answer) {
        super(type, text, points, answer);
        this.type = TYPE.TF;
        this.answer = Boolean.parseBoolean(answer);
    }

    @Override
    public double checkAnswer(String givenAnswer) {
        boolean givenAnswerBoolean = Boolean.parseBoolean(givenAnswer);
        if (givenAnswerBoolean == answer) {
            return points;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("True/False Question: %s Points: %.0f Answer: %s", text, points, answer);
    }


}

class MCquestion extends Question {
    String answer;

    public MCquestion(TYPE type, String text, double points, String answer) {
        super(type, text, points, answer);
        this.type = TYPE.MC;
        this.answer = answer;
    }

    @Override
    public double checkAnswer(String givenAnswer) {
        if (givenAnswer.equals(answer)) {
            return points;
        } else return points * 0.2 * (-1);
    }

    @Override
    public String toString() {
        return String.format("Multiple Choice Question: %s Points %.0f Answer: %s", text, points, answer);
    }
}

class Quiz {
    List<Question> questions;
    List<String> allowedAnswers;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.allowedAnswers = new ArrayList<>();
        allowedAnswers.add("A");
        allowedAnswers.add("B");
        allowedAnswers.add("C");
        allowedAnswers.add("D");
        allowedAnswers.add("E");

    }

    public void addQuestion(String questionData) throws InvalidOperationException {
        String[] parts = questionData.split(";");
        TYPE type = TYPE.valueOf(parts[0]);
        String text = parts[1];
        double points = Double.parseDouble(parts[2]);
        String answer = parts[3];
        if (type == TYPE.MC && !allowedAnswers.contains(answer)) {
            throw new InvalidOperationException(String.format("%s is not allowed option for this question", answer));
        }
        if (type.equals(TYPE.TF)) {
            type = TYPE.TF;
            questions.add(new TFQuestion(type, text, points, answer));
        } else {
            type = TYPE.MC;
            questions.add(new MCquestion(type, text, points, answer));
        }
    }

    public void printQuiz(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);


        questions.stream().sorted(Comparator.reverseOrder()).forEach(p->{
            pw.println(p.toString());
        });

        pw.flush();
    }

    void answerQuiz (List<String> answers, OutputStream os) throws InvalidOperationException {
        PrintWriter pw = new PrintWriter(os);
        double totalPoints =0.0;

        if(answers.size()!= questions.size()) throw new InvalidOperationException(String.format("Answers and questions must be of same length!"));

        for (int i = 0; i < questions.size(); i++) {
            double points = questions.get(i).checkAnswer(answers.get(i));
            pw.println(String.format("%d. %.2f", i+1, points));
            totalPoints+=points;
        }

        pw.println(String.format("Total points: %.2f",totalPoints));
        pw.flush();
    }
}


public class QuizTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Quiz quiz = new Quiz();

        int questions = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < questions; i++) {
            try {
                quiz.addQuestion(sc.nextLine());
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }

        }

        List<String> answers = new ArrayList<>();

        int answersCount = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < answersCount; i++) {
            answers.add(sc.nextLine());
        }

        int testCase = Integer.parseInt(sc.nextLine());

        if (testCase == 1) {
            quiz.printQuiz(System.out);
        } else if (testCase == 2) {
            try {
                quiz.answerQuiz(answers, System.out);
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid test case");
        }
    }
}