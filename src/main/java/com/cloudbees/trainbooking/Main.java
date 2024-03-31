package com.cloudbees.trainbooking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Train Ticket Booking Demo",
        description = "# App to be coded:\n" +
                "1.\tI want to board a train from London to France. The train ticket will cost $20.\n" +
                "2.\tCreate API where you can submit a purchase for a ticket. Details included in the receipt are:\n" +
                "3.\tFrom, To, User , price paid.\n" +
                "4.\tUser should include first and last name, email address\n" +
                "5.\tThe user is allocated a seat in the train. Assume the train has only 2 sections, section A and section B.\n" +
                "6.\tAn API that shows the details of the receipt for the user\n" +
                "7.\tAn API that lets you view the users and seat they are allocated by the requested section\n" +
                "8.\tAn API to remove a user from the train\n" +
                "9.\tAn API to modify a user's seat"
))
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}