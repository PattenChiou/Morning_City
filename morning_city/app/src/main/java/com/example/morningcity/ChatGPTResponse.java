package com.example.morningcity;

import java.util.ArrayList;

public class ChatGPTResponse{
    String id;
    String object;
    int created;
    String model;
    ArrayList<Choice> choices;

    Usage usage;
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getObject() {
        return this.object;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public int getCreated() {
        return this.created;
    }
    public void setCreated(int created) {
        this.created = created;
    }
    public String getModel(){
        return this.model;
    }
    public void setModel(String model){
        this.model = model;
    }
    public ArrayList<Choice> getChoices(){
        return this.choices;
    }
    public void setChoices(ArrayList<Choice> choices){
        this.choices = choices;
    }
    public Usage getUsage() {
        return this.usage;
    }
    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public class Choice{
        int index;
        Message message;
        public int getIndex() {
            return this.index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public Message getMessage(){
            return this.message;
        }
        public void setMessage(Message message){
            this.message = message;
        }
        public class Message{
            String role;
            String content;
            public String getRole(){
                return this.role;
            }
            public void setRole(String role){
                this.role = role;
            }
            public String getContent(){
                return this.content;
            }
            public void setContent(String content){
                this.content = content;
            }
        }
    }

    public class Usage{
        int prompt_tokens;
        int completion_tokens;
        int total_tokens;
        public int getPrompt_tokens() {
            return this.prompt_tokens;
        }
        public void setPrompt_tokens(int prompt_tokens) {
            this.prompt_tokens = prompt_tokens;
        }
        public int getCompletion_tokens() {
            return this.completion_tokens;
        }
        public void setCompletion_tokens(int completion_tokens) {
            this.completion_tokens = completion_tokens;
        }
        public int getTotal_tokens() {
            return this.total_tokens;
        }
        public void setTotal_tokens(int total_tokens) {
            this.total_tokens = total_tokens;
        }
    }
}





