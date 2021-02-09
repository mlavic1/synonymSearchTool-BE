package com.reeinvent.synonymsearchtool.controller;

import com.reeinvent.synonymsearchtool.model.RequestDto;
import com.reeinvent.synonymsearchtool.service.impl.SynonymDictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("https://synonym-dictionary.herokuapp.com")
@RequestMapping("synonym")
@RequiredArgsConstructor
public class SynonymEngineController {

  private final SynonymDictionaryService synonymDictionaryService;

  @GetMapping(value = "/{word}")
  public ResponseEntity<Set<String>> getSynonyms(@NotNull @PathVariable String word) {
    return ResponseEntity.ok(synonymDictionaryService.getSynonyms(word));
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Void> addSynonyms(@NotNull @RequestBody @Valid List<RequestDto> request) {
      synonymDictionaryService.createSynonyms(request);
      return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
