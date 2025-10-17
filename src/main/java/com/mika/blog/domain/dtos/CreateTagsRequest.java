package com.mika.blog.domain.dtos;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTagsRequest {

    @NotEmpty(message = "Atleast one tag is required")
    @Size(max = 10, message = "Maximum {max} tags are allowed")
    private Set<@Size(min = 2, max = 30, message = "Category name must be between {min} and {max} characters") @Pattern(regexp = "^[A-Za-z0-9\\s-]+$", message = "Category name can only contain letters, numbers, spaces, and hyphens") String> names;

}
