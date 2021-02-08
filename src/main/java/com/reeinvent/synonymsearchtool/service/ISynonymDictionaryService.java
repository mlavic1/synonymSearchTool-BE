package com.reeinvent.synonymsearchtool.service;

import com.reeinvent.synonymsearchtool.model.RequestDto;

import java.util.List;
import java.util.Set;

public interface ISynonymDictionaryService {
  void createSynonyms(List<RequestDto> request);
  Set<String> getSynonyms(String word);
}
