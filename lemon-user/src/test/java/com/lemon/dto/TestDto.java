package com.lemon.dto;

import lombok.Data;

/**
 * TestDto
 *
 * @author sjp
 * @date 2019/12/7
 */
@Data
public class TestDto {
	private Long	id;
	private String	name;

    public TestDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TestDto() {
    }
}
