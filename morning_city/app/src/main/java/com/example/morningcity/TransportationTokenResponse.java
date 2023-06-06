package com.example.morningcity;

public class TransportationTokenResponse{
    public String getAccess_token() {
        return this.access_token; }
    public void setAccess_token(String access_token) {
        this.access_token = access_token; }
    String access_token;
    public int getExpires_in() {
        return this.expires_in; }
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in; }
    int expires_in;
    public String getToken_type() {
        return this.token_type; }
    public void setToken_type(String token_type) {
        this.token_type = token_type; }
    String token_type;
}
