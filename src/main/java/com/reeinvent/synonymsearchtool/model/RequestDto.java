package com.reeinvent.synonymsearchtool.model;

import lombok.Data;

import java.util.Set;

@Data
public class RequestDto {
  private String word;
  private Set<String> synonyms;
}
