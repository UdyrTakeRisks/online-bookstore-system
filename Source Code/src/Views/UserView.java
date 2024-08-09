package Views;

import javax.swing.*;
import java.util.Scanner;

public class UserView {
    private Scanner scanner;

    public UserView(){
        scanner = new Scanner(System.in);
    }
    public void DisplayMainView(){
        System.out.println("------------------------------------------------------------");
        System.out.println("            Welcome To The Online BookStore :)          ");
        System.out.println("------------------------------------------------------------");
        System.out.println("                        1. SignIn                           ");
        System.out.println("                        2. SignUp                           ");
        System.out.println("                        3. exit                             ");

    }

    public void DisplaySignInView(){
        System.out.println("------------------------------------------------------------");
        System.out.println("                       SignIn                              ");
        System.out.println("------------------------------------------------------------");
        System.out.println("                       UserName:                           ");
        System.out.println("                       Password:                           ");
    }
    public void DisplaySignUpView(){
        System.out.println("------------------------------------------------------------");
        System.out.println("                       SignUp                              ");
        System.out.println("------------------------------------------------------------");
        System.out.println("                       Name:                              ");
        System.out.println("                       UserName:                          ");
        System.out.println("                       Password:                          ");
    }




}
