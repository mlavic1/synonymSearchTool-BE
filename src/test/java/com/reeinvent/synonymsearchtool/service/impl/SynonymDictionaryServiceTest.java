package com.reeinvent.synonymsearchtool.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.reeinvent.synonymsearchtool.model.RequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class SynonymDictionaryServiceTest {

  @Resource
  private SynonymDictionaryService synonymDictionaryService;

  @Before
  public void setup() {
    synonymDictionaryService = new SynonymDictionaryService();
  }

  @Test
  public void getSynonymsTest() {
    RequestDto requestDto = new RequestDto();
    requestDto.setWord("word1");
    requestDto.setSynonyms(Set.of("synonym1"));
    synonymDictionaryService.createSynonyms(List.of(requestDto));

    Set<String> synonyms = synonymDictionaryService.getSynonyms("word1");

    assertThat(synonyms.size()).isEqualTo(1);
  }

  @Test
  public void testTransitive() {
    RequestDto requestDto = new RequestDto();
    requestDto.setWord("word1");
    requestDto.setSynonyms(Set.of("synonym1"));
    synonymDictionaryService.createSynonyms(List.of(requestDto));

    requestDto.setWord("synonym1");
    requestDto.setSynonyms(Set.of("synonymOfSynonym"));
    synonymDictionaryService.createSynonyms(List.of(requestDto));

    Set<String> synonyms = synonymDictionaryService.getSynonyms("word1");

    assertThat(synonyms.size()).isEqualTo(2);
    assertThat(synonyms.contains("synonym1")).isTrue();
    assertThat(synonyms.contains("synonymOfSynonym")).isTrue();
  }
}
