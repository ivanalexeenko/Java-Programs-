package com.session;

public class SessionData implements Comparable<SessionData> {
    int number;
    String surname;
    String subject;

    public int getNumber() {
        return number;
    }

    public String getSurname() {
        return surname;
    }

    public int getMark() {
        return mark;
    }

    int mark;

    public SessionData(int number, String surname, String subject, int mark) {
        this.number = number;
        this.surname = surname;
        this.subject = subject;
        this.mark = mark;
    }

    @Override
    public int compareTo(SessionData o) {
        return this.subject.compareTo(o.subject);
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "number=" + number +
                ", surname='" + surname + '\'' +
                ", subject='" + subject + '\'' +
                ", mark=" + mark +
                '}';
    }
    public String getSubject() {
        return subject;
    }

    public String toXMLString() {
        return "\t<sessionData>\n\t\t<number name =  \""+ number +"\" />\n\t\t" +
                "<surname name = \""+surname+"\" />\n\t\t" +
                "<subject name = \"" + subject + "\" />\n\t\t" +
                "<mark name = \"" + mark + "\" />\n" +
                "\t</sessionData>";
    }


}
