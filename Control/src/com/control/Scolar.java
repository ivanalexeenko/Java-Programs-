package com.control;

public class Scolar extends Pupil {
    private int form;

    public enum Types {
        BAD,GOOD,EXCELLENT;
    }
    private Types Behaviour;

    public Scolar(String surname, String university, int mark, int form, Types behaviour) {
        super(surname, university, mark);
        this.form = form;
        Behaviour = behaviour;
    }

    public String toString() {
        super.toString();

        if(Behaviour.equals(Types.BAD)) {
            return "|Form:" + form + "|Behaviour:BAD";
        }
        else if(Behaviour.equals(Types.GOOD)) {
            return "|Form:" + form + "|Behaviour:GOOD";
        }
        else  {
            return "|Form:" + form + "|Behaviour:EXCELLENT";
        }
    }
    public boolean equals(Scolar scolar) {
        boolean check = super.equals(scolar);
        if(check && this.form == scolar.form && this.Behaviour.equals(scolar.Behaviour)) {
            return true;
        }
        else return false;
    }

}
