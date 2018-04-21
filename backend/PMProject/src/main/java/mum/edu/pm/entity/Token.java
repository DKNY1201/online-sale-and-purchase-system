package mum.edu.pm.entity;

import mum.edu.pm.util.TokenGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    public Token() {
    }

    public Token(String userName, String passWord) {
        this.value = TokenGenerator.generate(userName + ":" + passWord);
    }
}
