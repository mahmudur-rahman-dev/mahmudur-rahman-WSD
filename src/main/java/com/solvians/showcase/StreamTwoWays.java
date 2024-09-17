package com.solvians.showcase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTwoWays {



  public List<SomeObject> mapDtos(List<SomeDto> dtos) {


    List<SomeObject> result = new ArrayList<>();

    for (SomeDto dto : dtos) {
      result.add(map(dto));
    }

    return result;

  }



  public List<SomeObject> mapDtos2(List<SomeDto> dtos) {


    List<SomeObject> result = dtos.stream().map(this::map).collect(Collectors.toList());

    return result;

  }
  
  
  

  private SomeObject map(SomeDto dto) {
    return new SomeObject(dto.getName(), dto.getId());
  }



  class SomeDto {

    private final String name;
    private final Long id;

    public SomeDto(String name, Long id) {
      super();
      this.name = name;
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public Long getId() {
      return id;
    }


  }


  class SomeObject {

    private final String name;
    private final Long id;

    public SomeObject(String name, Long id) {
      this.name = name;
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public Long getId() {
      return id;
    }

  }
}
