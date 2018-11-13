/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramLab;

/**
 *
 * @author tuandung
 */
public class AgeException extends Exception{
    @Override
    public String getMessage(){
        return "value something wrong";
    }
}
