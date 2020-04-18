package com.mtit.assignment.microservice.stockservice.Model;

public class Response {

    private String id;
    private String message;

    public Response(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public Response() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
