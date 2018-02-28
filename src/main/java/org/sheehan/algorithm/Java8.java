package org.sheehan.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

/**
 * Created by bsheehan on 2/16/16.
 */
public class Java8 {

    public static List<Integer> evenSquares(List<Integer> numbers, IntPredicate filterOp, IntFunction<Integer> mapOp) {

        return numbers.stream()
                .filter(n -> {
                    System.out.println("filtering " + n);
                    return filterOp.test(n);
                })
                .map(n -> {
                    System.out.println("mapping " + n);
                    return mapOp.apply(n);
                })
                .limit(2)
                .collect(Collectors.toList());
    }

    class Person {
        String name;
        Date dob;

        public Person(String name, String dob) {
            this.name = name;

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            try {
                this.dob = formatter.parse(dob);
               // System.out.println(date);
                //System.out.println(formatter.format(date));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Override public String toString() {
            return name.toString() + " " + dob.toString();
        }
    }

    public class Person2 extends Person {
        public Person2(String name, String dob) {
            super(name, dob);
        }

        @Override
        public boolean equals(Object person) {
            if (Java8.Person2.this == person)
                return true;

            //if (!(person instanceof Person2))
            if (person.getClass() != Person2.class)
                return false;

            Java8.Person2 p = (Java8.Person2) person;

            boolean nameCheck = Java8.Person2.this.name == null ? (p.name != null ? false : true) : (p.name == null ? true : Java8.Person2.this.name == p.name);

            boolean dateCheck = Java8.Person2.this.dob == null ? (p.dob != null ? false : true) : (p.dob == null ? true : Java8.Person2.this.dob.equals(p.dob));

            return nameCheck && dateCheck;
        }

        @Override
        public int hashCode() {
            int hash = super.hashCode();

            if (name != null)
                hash += name.hashCode();

            if (dob != null)
                hash += dob.hashCode();

            return hash;
        }
    }


    public static void main(String []args){

        Person p1 = new Java8().new Person("bob", "7-Jun-2013");
        Person p2 = new Java8().new Person("bob", "7-Jun-2013");

        if (p1 == p2)
            System.out.println("Person p1==p2 " + p1.toString() + " " + p2.toString());
        else
            System.out.println("Person p1!=p2 " + p1.toString() + " " + p2.toString());

        if (p1.equals(p2))
            System.out.println("Person p1.equals(p2) " + p1.toString() + " " + p2.toString());
        else
            System.out.println("Person !p1.equals(p2) " + p1.toString() + " " + p2.toString());

        Person2 p3 = new Java8(). new Person2("bob", "7-Jun-2013");
        Person2 p4 = new Java8(). new Person2("bob", "7-Jun-2013");

        if (p3 == p4)
            System.out.println("Person2 p3==p4 " + p3.toString() + " " + p4.toString());
        else
            System.out.println("Person2 p3!=p4 " + p3.toString() + " " + p4.toString());

        if (p3.equals(p4))
            System.out.println("Person2 p3.equals(p4) " + p3.toString() + " " + p4.toString());
        else
            System.out.println("Person !p3.equals(p4) " + p3.toString() + " " + p4.toString());

        Person2 p5 = new Java8(). new Person2("bob", "8-Jun-2015");
        Person2 p6 = new Java8(). new Person2("bob", "7-Jun-2013");
        if (p5 == p6)
            System.out.println("Person2 p5==p6 " + p5.toString() + " " + p6.toString());
        else
            System.out.println("Person2 p5!=p6 " + p5.toString() + " " + p6.toString());

        if (p5.equals(p6))
            System.out.println("Person2 p5.equals(p6) " + p5.toString() + " " + p6.toString());
        else
            System.out.println("Person !p5.equals(p6) " + p5.toString() + " " + p6.toString());


        if (p1.equals(p3))
            System.out.println("Person2 p1.equals(p3) " + p1.toString() + " " + p3.toString());
        else
            System.out.println("Person !p1.equals(p3) " + p1.toString() + " " + p3.toString());

        if (p3.equals(p1))
            System.out.println("Person2 p3.equals(p1) " + p1.toString() + " " + p3.toString());
        else
            System.out.println("Person !p3.equals(p1) " + p1.toString() + " " + p3.toString());

        boolean val = "test"=="test";
        System.out.println("\"test\"==\"test\" " + val);
        val = "test".equals("test");
        System.out.println("\"test\".equals(\"test\") " + val);
        val = new String("test") == (new String("test"));
        System.out.println("new String(\"test\")==(new String(\"test\")) " + val);
        val = new String("test").equals(new String("test"));
        System.out.println("new String(\"test\").equals(new String(\"test\")) " + val);



    }


}
