package br.com.webfluxstudies.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AdviceResponse {
    private int slip_id;
    private String advice;
    private int total_results;
    private List<String> slips;
}
