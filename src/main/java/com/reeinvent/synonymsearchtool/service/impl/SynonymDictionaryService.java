package com.reeinvent.synonymsearchtool.service.impl;

import com.reeinvent.synonymsearchtool.model.RequestDto;
import com.reeinvent.synonymsearchtool.model.SynonymDictionary;
import com.reeinvent.synonymsearchtool.service.ISynonymDictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SynonymDictionaryService implements ISynonymDictionaryService {

  private final SynonymDictionary synonymDictionary = new SynonymDictionary();

  @Override
  public void createSynonyms(List<RequestDto> request) {
    request.forEach(word -> synonymDictionary.addSynonyms(word.getWord(), word.getSynonyms()));
  }

  @Override
  public Set<String> getSynonyms(String word) {
    return synonymDictionary.getSynonyms(word);
  }
}
