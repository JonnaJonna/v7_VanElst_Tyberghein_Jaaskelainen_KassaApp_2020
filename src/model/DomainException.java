package model;

public class DomainException extends RuntimeException{

    public DomainException(){}

    public DomainException(String error){System.out.println(error);}

}
